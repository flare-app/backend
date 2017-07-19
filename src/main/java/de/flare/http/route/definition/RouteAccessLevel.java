package de.flare.http.route.definition;

import de.flare.storage.user.UserAccessLevel;

/**
 * This enum contains all route access levels.
 */
public class RouteAccessLevel extends UserAccessLevel {

	public static final int ADMINISTRATOR = LOCAL_ADMINISTRATOR | FLARE_ADMINISTRATOR;
	public static final int AUTHENTICATED = DASHBOARD | APP | LOCAL_ADMINISTRATOR | FLARE_ADMINISTRATOR;
	public static final int PUBLIC = AUTHENTICATED | EXTERNAL;
}
