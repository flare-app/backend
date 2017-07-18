package de.flare.http;

import com.sun.istack.internal.NotNull;

/**
 * This interface offers methods for web server management.
 */
public interface WebServer {

	/**
	 * This method starts the web server, using the specified configuration.
	 * @param configuration the web server configuration to use
	 * @return this web server
	 */
	@NotNull
	WebServer start(WebServerConfiguration configuration);

	/**
	 * This method stops the web server.
	 * @return this web server
	 */
	@NotNull
	WebServer stop();
}
