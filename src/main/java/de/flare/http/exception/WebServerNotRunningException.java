package de.flare.http.exception;

import com.sun.istack.internal.NotNull;

/**
 * This exception os thrown, when the web server is not started yet.
 */
public class WebServerNotRunningException extends Exception {

	//region ctor
	/**
	 * This constructor initializes the super class with the given message.
	 * @param message the message to set
	 */
	public WebServerNotRunningException(@NotNull String message) {
		super(message);
	}
	//endregion
}
