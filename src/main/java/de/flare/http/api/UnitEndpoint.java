package de.flare.http.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.flare.database.DatabaseAccess;
import de.flare.database.model.Location;
import de.flare.database.model.Unit;
import de.flare.http.HttpStatus;
import de.flare.http.RequestContext;
import de.flare.http.WebServer;

public final class UnitEndpoint {

	//region private const
	private static final JsonParser parser = new JsonParser();

	private static final String BASE_URI = "/api/unit/";
	private static final String LIST_URI = BASE_URI + "list";
	//endregion

	//region ctor
	private UnitEndpoint() { }
	//endregion

	//region public static methods
	public static void setup() {
		WebServer.get(LIST_URI, UnitEndpoint::getList);
	}
	//endregion

	//region private static methods
	private static void getList(RequestContext context) {
		JsonArray response = new JsonArray();

		try {
			for (Unit unit : DatabaseAccess.getUnits(context)) {
				response.add(toJson(unit));
			}
		} catch (Exception e) {
			throw WebServer.invalidBody();
		}

		WebServer.respond(context, "ok", response, HttpStatus.OK);
	}

	private static JsonObject toJson(Unit unit) {
		JsonObject jsonUnit = new JsonObject();
		jsonUnit.addProperty("name", unit.getName());
		jsonUnit.add("location", toJson(unit.getLocation()));

		return jsonUnit;
	}

	private static JsonObject toJson(Location location) {
		JsonObject jsonLocation = new JsonObject();
		jsonLocation.addProperty("city", location.getCity());
		jsonLocation.addProperty("longitude", location.getLongitude());
		jsonLocation.addProperty("latitude", location.getLatitude());

		return jsonLocation;
	}
	//endregion
}
