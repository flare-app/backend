package de.flare.http;

import com.sun.istack.internal.NotNull;
import spark.Route;

/**
 * This interface offers methods for web server management.
 */
public interface WebServer {

	int OK = 200;
	int ERROR = 500;

	/**
	 * This method starts the web server, using the specified configuration.
	 * @param configuration the web server configuration to use
	 * @return this web server
	 * @throws WebServerAlreadyRunningException thrown, if the web server was started earlier
	 */
	@NotNull
	WebServer start(WebServerConfiguration configuration) throws WebServerAlreadyRunningException;

	/**
	 * This method stops the web server.
	 * @return this web server
	 */
	@NotNull
	WebServer stop();

	/**
	 * This method registered a new GET route.
	 * @param uri the uri for the route
	 * @param restRoute the rest route handler
	 * @return this web server
	 * @throws WebServerNotRunningException thrown, if the web server is not started yet
	 */
	@NotNull
	WebServer get(String uri, Route restRoute) throws WebServerNotRunningException;

	/**
	 * This method registered a new POST route.
	 * @param uri the uri for the route
	 * @param restRoute the rest route handler
	 * @return this web server
	 * @throws WebServerNotRunningException thrown, if the web server is not started yet
	 */
	@NotNull
	WebServer post(String uri, Route restRoute) throws WebServerNotRunningException;

	/**
	 * This method registered a new PUT route.
	 * @param uri the uri for the route
	 * @param restRoute the rest route handler
	 * @return this web server
	 * @throws WebServerNotRunningException thrown, if the web server is not started yet
	 */
	@NotNull
	WebServer put(String uri, Route restRoute) throws WebServerNotRunningException;

	/**
	 * This method registered a new DELETE route.
	 * @param uri the uri for the route
	 * @param restRoute the rest route handler
	 * @return this web server
	 * @throws WebServerNotRunningException thrown, if the web server is not started yet
	 */
	@NotNull
	WebServer delete(String uri, Route restRoute) throws WebServerNotRunningException;

	/**
	 * This method returns the uri parameter for a given parameter name.
	 * @param parameterName the parameter name
	 * @return the uri parameter representation of the parameter
	 * @throws IllegalArgumentException thrown, if the given parameter is invalid
	 */
	String getRouteParameter(String parameterName) throws IllegalArgumentException;
}
