package de.flare.storage.user;

/**
 * This enum contains all user access levels.
 */
public class UserAccessLevel {

	public static final int NONE                = 0;
	public static final int EXTERNAL            = 1 << 0;
	public static final int DASHBOARD           = 1 << 1;
	public static final int APP                 = 1 << 2;
	public static final int LOCAL_ADMINISTRATOR = 1 << 3;
	public static final int FLARE_ADMINISTRATOR = 1 << 4;

}
