package de.flare.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.flare.database.DatabaseAccess;
import de.flare.database.definition.Authorization;
import de.flare.database.model.User;
import de.flare.http.api.APIRoute;
import de.flare.http.api.UnitEndpoint;
import de.flare.http.api.UserEndpoint;
import de.flare.properties.PropertyEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.HaltException;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.halt;

public final class WebServer {

	//region private static members
	private static Service sparkService;
	//endregion

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
	private static final Gson serializer = new Gson();

	private static final String PORT_PROPERTY_KEY = "wsPort";
	private static final String THREADS_PROPERTY_KEY = "wsThreads";
	private static final String STATIC_FILES_PROPERTY_KEY = "wsStaticFiles";
	private static final String CORS_ORIGIN_PROPERTY_KEY = "wsCorsOrigin";
	private static final String CORS_METHOD_PROPERTY_KEY = "wsCorsMethod";

	private static final String AUTHENTICATION_TOKEN_PARAMETER = "authenticationToken";
	//endregion

	//region ctor
	private WebServer() { }
	//endregion

	//region public static methods
	public static boolean listen() {
		if (sparkService != null) {
			return true;
		}

		try {
			sparkService = Service.ignite()
					.port(PropertyEditor.getInt(PORT_PROPERTY_KEY))
					.threadPool(PropertyEditor.getInt(THREADS_PROPERTY_KEY))
					.staticFileLocation(PropertyEditor.getString(STATIC_FILES_PROPERTY_KEY));

		} catch (Exception e) {
			logger.error("cannot start web server", e);
			return false;
		}

		UserEndpoint.setup();
		UnitEndpoint.setup();

		setCors();

		return true;
	}

	public static void respond(RequestContext context, String message, int status) {
		respond(context, message, null, status);
	}

	public static void respond(RequestContext context, String message, JsonElement response, int status) {
		if (response == null) {
			response = new JsonObject();
		}

		JsonObject responseText = new JsonObject();
		responseText.addProperty("url", context.getRequest().url());
		responseText.addProperty("message", message);
		responseText.add("response", response);

		context.getResponse().body(serializer.toJson(responseText));
		context.getResponse().status(status);
	}

	public static HaltException invalidBody() {
		return halt(HttpStatus.BAD_REQUEST, "body could not be parsed");
	}

	public static void get(String uri, APIRoute route) {
		if (sparkService == null) {
			throw new IllegalStateException("spark service not configured");
		}

		sparkService.get(uri, (request, response) -> executeRequest(request, response, route));
	}

	public static void get(String uri, APIRoute route, boolean allowPasswordExpired, Authorization... neededAuthorization) {
		if (sparkService == null) {
			throw new IllegalStateException("spark service not configured");
		}

		uri = String.format("%s/{%s}", uri, AUTHENTICATION_TOKEN_PARAMETER);
		sparkService.get(uri, (request, response) -> executeRequest(request, response, route, allowPasswordExpired, neededAuthorization));
	}

	public static void post(String uri, APIRoute route) {
		if (sparkService == null) {
			throw new IllegalStateException("spark service not configured");
		}

		sparkService.post(uri, (request, response) -> executeRequest(request, response, route));
	}

	public static void post(String uri, APIRoute route, boolean allowPasswordExpired, Authorization... neededAuthorization) {
		if (sparkService == null) {
			throw new IllegalStateException("spark service not configured");
		}

		uri = String.format("%s/:%s", uri, AUTHENTICATION_TOKEN_PARAMETER);
		sparkService.post(uri, (request, response) -> executeRequest(request, response, route, allowPasswordExpired, neededAuthorization));
	}
	//endregion

	//region private static methods
	private static void setCors() {
		if (sparkService == null) {
			return;
		}

		sparkService.options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "{\"message\":\"ok\"}";
		});

		sparkService.before((request, response) -> {
			response.header("Access-Control-Allow-Origin", PropertyEditor.getString(CORS_ORIGIN_PROPERTY_KEY));
			response.header("Access-Control-Request-Method", PropertyEditor.getString(CORS_METHOD_PROPERTY_KEY));
			response.type("application/json");
			/*
			response.header("Cache-Control", "no-cache, no-store, must-revalidate");
			response.header("Pragma", "no-cache");
			response.header("Expires", "0");
			*/
		});
	}

	private static String executeRequest(Request request, Response response, APIRoute route) {
		RequestContext context = startExecution(request, response);
		execute(context, route);
		return endExecution(context);
	}

	private static String executeRequest(Request request, Response response, APIRoute route, boolean allowPasswordExpired, Authorization... neededAuthorization) {
		RequestContext context = startExecution(request, response);

		String authenticationToken = request.params(AUTHENTICATION_TOKEN_PARAMETER);

		User user = DatabaseAccess.getUser(context, authenticationToken);

		if (user == null) {
			respond(context, "access denied", HttpStatus.FORBIDDEN);
			return endExecution(context);
		}

		if (!allowPasswordExpired && user.isPasswordExpired()) {
			respond(context, "your password is expired. set a new password first.", HttpStatus.FORBIDDEN);
			return endExecution(context);
		}

		List<Authorization> needed = Arrays.asList(neededAuthorization);

		if (!needed.contains(user.getAuthorization())) {
			respond(context, "access denied", HttpStatus.FORBIDDEN);
			return endExecution(context);
		}

		context.setUser(user);
		execute(context, route);
		return endExecution(context);
	}

	private static RequestContext startExecution(Request request, Response response) {
		logger.info("--> " + request.url());

		return new RequestContext()
				.setRequest(request)
				.setResponse(response);
	}

	private static void execute(RequestContext context, APIRoute route) {
		try {
			route.handle(context);
		} catch (HaltException halt) {
			// suppress exception
			respond(context, halt.body(), halt.statusCode());
		} catch (Exception e) {
			logger.error(context.getRequest().url(), e);
			respond(context, e.getMessage(), HttpStatus.ERROR);
		}
	}

	private static String endExecution(RequestContext context) {
		if (context.getSession() != null) {
			context.getSession().close();
		}

		logger.info("<-- " + context.getRequest().url() + " after " + Long.toString(System.currentTimeMillis() - context.getExecutionStartTime()) + " ms");
		return context.getResponse().body();
	}
	//endregion
}
