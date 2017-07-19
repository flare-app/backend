package de.flare.http.definition;

/**
 * This enum contains the http status codes.
 */
public final class StatusCodes {

	//region ctor

	/**
	 * This constructor prevents anyone from creating an instance of this class.
	 */
	private StatusCodes() {

	}
	//endregion

	public static final int OK = 200;

	public static final int ERROR = 500;
}
