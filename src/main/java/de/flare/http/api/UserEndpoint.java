package de.flare.http.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.flare.database.DatabaseAccess;
import de.flare.database.definition.Authorization;
import de.flare.database.model.Location;
import de.flare.database.model.Unit;
import de.flare.database.model.User;
import de.flare.http.HttpStatus;
import de.flare.http.RequestContext;
import de.flare.http.WebServer;
import de.flare.security.PasswordUtility;

import java.util.UUID;

import static spark.Spark.halt;

public final class UserEndpoint {

	//region private static members

	//endregion

	//region private const
	private static final JsonParser parser = new JsonParser();

	private static final String BASE_URI = "/api/user/";
	private static final String LOGIN_URI = BASE_URI + "login";
	private static final String ADMIN_LOGIN_URI = LOGIN_URI + "/admin";
	private static final String CREATE_URI = BASE_URI + "create";
	private static final String CHANGE_PASSWORD_URI = BASE_URI + "changepassword";
	private static final String INCOMING_URI = BASE_URI + "incoming";
	//endregion

	//region ctor
	private UserEndpoint() { }
	//endregion

	//region public static methods
	public static void setup() {
		WebServer.post(LOGIN_URI, UserEndpoint::postLogin);
		WebServer.post(ADMIN_LOGIN_URI, UserEndpoint::postAdminLogin);
		WebServer.post(CREATE_URI, UserEndpoint::postCreate, false, Authorization.UNIT_ADMINISTRATOR, Authorization.FLARE_ADMINISTRATOR);
		WebServer.post(CHANGE_PASSWORD_URI, UserEndpoint::postChangePassword, true, Authorization.APP, Authorization.DASHBOARD, Authorization.UNIT_ADMINISTRATOR, Authorization.FLARE_ADMINISTRATOR);
		WebServer.post(INCOMING_URI, UserEndpoint::postUserIncoming);
	}
	//endregion

	//region private methods
	private static void postLogin(RequestContext context) {
		String eMail;
		String unitName;
		String unitCity;
		String password;

		try {
			JsonObject requestBody = parser.parse(context.getRequest().body()).getAsJsonObject();

			eMail = requestBody.get("email").getAsString();
			unitName = requestBody.get("unitName").getAsString();
			unitCity = requestBody.get("unitCity").getAsString();
			password = requestBody.get("password").getAsString();
		} catch (Exception e) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().bodyIsInvalid());
		}

		User user = DatabaseAccess.getUser(context, eMail, unitName, unitCity);

		if (user == null || !PasswordUtility.matches(password, user.getPasswordToken())) {
			throw halt(HttpStatus.ERROR, context.getResponseMessages().loginDetailsAreInvalid());
		}

		respondLogin(context, user, context.getResponseMessages().welcome(user));
	}

	private static void postAdminLogin(RequestContext context) {
		String eMail;
		String password;

		try {
			JsonObject requestBody = parser.parse(context.getRequest().body()).getAsJsonObject();

			eMail = requestBody.get("email").getAsString();
			password = requestBody.get("password").getAsString();
		} catch (Exception e) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().bodyIsInvalid());
		}

		User user = DatabaseAccess.getFlareAdminUser(context, eMail);

		if (user == null || !PasswordUtility.matches(password, user.getPasswordToken())) {
			throw halt(HttpStatus.ERROR, context.getResponseMessages().loginDetailsAreInvalid());
		}

		respondLogin(context, user, context.getResponseMessages().welcome(user));
	}

	private static void postCreate(RequestContext context) {
		User administrator = context.getUser();

		if (administrator == null) {
			throw halt(HttpStatus.FORBIDDEN, context.getResponseMessages().accessIsDenied());
		}

		Unit unit = administrator.getUnit();

		if (unit == null) {
			throw halt(HttpStatus.ERROR, context.getResponseMessages().serverError());
		}

		JsonObject requestBody;
		String firstName;
		String lastName;
		String eMail;
		Authorization authorization;
		JsonArray qualifications;
		Location defaultLocation;

		try {
			requestBody = parser.parse(context.getRequest().body()).getAsJsonObject();

			firstName = requestBody.get("firstName").getAsString();
			lastName = requestBody.get("lastName").getAsString();
			eMail = requestBody.get("email").getAsString();
			authorization = Authorization.valueOf(requestBody.get("authorization").getAsString());
			qualifications = requestBody.get("qualifications").getAsJsonArray();
			defaultLocation = locationFromRequest(requestBody.get("defaultLocation").getAsJsonObject());
		} catch (Exception e) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().bodyIsInvalid());
		}

		if (authorization == Authorization.FLARE_ADMINISTRATOR) {
			throw halt(HttpStatus.FORBIDDEN, context.getResponseMessages().accessIsDenied());
		}

		User user = DatabaseAccess.getUser(context, eMail, unit.getName(), unit.getLocation().getCity());

		if (user != null) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().userDoesAlreadyExist());
		}

		String password = UUID.randomUUID().toString();

		user = new User()
				.setFirstName(firstName)
				.setLastName(lastName)
				.setEMail(eMail)
				.setUnit(unit)
				.setAuthorization(authorization)
				.setPassword(password)
				.setPasswordExpired(true)
				.setDefaultLocation(defaultLocation);

		try {
			for (JsonElement qualification : qualifications) {
				user.getQualifications().add(qualification.getAsString());
			}
		} catch (Exception e) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().bodyIsInvalid());
		}

		DatabaseAccess.save(context, user);

		requestBody.addProperty("password", password);
		requestBody.addProperty("unitName", unit.getName());
		requestBody.addProperty("unitCity", unit.getLocation().getCity());
		WebServer.respond(context, context.getResponseMessages().userIsCreated(), requestBody, HttpStatus.OK);
	}

	private static void postChangePassword(RequestContext context) {
		User user = context.getUser();

		if (user == null) {
			throw halt(HttpStatus.FORBIDDEN, context.getResponseMessages().accessIsDenied());
		}

		String oldPassword;
		String newPassword;

		try {
			JsonObject requestBody = parser.parse(context.getRequest().body()).getAsJsonObject();

			oldPassword = requestBody.get("oldPassword").getAsString();
			newPassword = requestBody.get("newPassword").getAsString();
		} catch (Exception e) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().bodyIsInvalid());
		}

		if (!PasswordUtility.matches(oldPassword, user.getPasswordToken())) {
			throw halt(HttpStatus.FORBIDDEN, context.getResponseMessages().loginDetailsAreInvalid());
		}

		if (oldPassword.equalsIgnoreCase(newPassword)) {
			throw halt(HttpStatus.BAD_REQUEST, context.getResponseMessages().newPasswordMustNotBeOldPassword());
		}

		user
				.setPassword(newPassword)
				.setPasswordExpired(false);

		respondLogin(context, user, context.getResponseMessages().passwordIsChanged());
	}

	private static void postUserIncoming(RequestContext context) {
		// TODO: implement me
	}

	private static void respondLogin(RequestContext context, User user, String message) {
		user.updateAuthenticationToken();
		DatabaseAccess.save(context, user);
		JsonObject responseText = new JsonObject();
		responseText.addProperty("authenticationToken", user.getAuthenticationToken());
		responseText.addProperty("passwordExpired", user.isPasswordExpired());

		WebServer.respond(context, message, responseText, HttpStatus.OK);
	}

	private static Location locationFromRequest(JsonObject requestBody) {
		return new Location()
				.setCity(requestBody.get("city").getAsString())
				.setLatitude(requestBody.get("latitude").getAsFloat())
				.setLongitude(requestBody.get("longitude").getAsFloat());
	}
	//endregion
}
