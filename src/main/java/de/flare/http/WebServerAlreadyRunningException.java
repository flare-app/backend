package de.flare.http;

import com.sun.istack.internal.NotNull;

/**
 * This exception is thrown, when a web server is started, but was started earlier already.
 */
public class WebServerAlreadyRunningException extends Exception {

	//region ctor
	/**
	 * This constructor initializes the super class with the given message.
	 * @param message the message to set
	 */
	public WebServerAlreadyRunningException(@NotNull String message) {
		super(message);
	}
	//endregion
}
