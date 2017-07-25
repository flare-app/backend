package de.flare.http;

public final class HttpStatus {

	//region ctor
	private HttpStatus() { }
	//endregion

	//region public const
	public static final int OK = 200;

	public static final int BAD_REQUEST = 400;
	public static final int FORBIDDEN = 403;

	public static final int ERROR = 500;
	public static final int BAD_GATEWAY = 502;
	//endregion
}
