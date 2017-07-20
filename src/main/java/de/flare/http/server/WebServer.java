package de.flare.http.server;

import com.sun.istack.internal.NotNull;
import de.flare.http.api.endpoint.RestEndpoint;
import de.flare.http.exception.WebServerAlreadyRunningException;
import de.flare.http.exception.WebServerNotRunningException;
import de.flare.http.route.RestRoute;
import de.flare.http.route.execution.RestRouteHandler;
import de.flare.http.server.configuration.WebServerConfiguration;

import java.util.Collection;

/**
 * This interface offers methods for web server management.
 */
public interface WebServer {

	/**
	 * This method starts the web server, using the specified configuration.
	 * @param configuration the web server configuration to use
	 * @return this web server
	 * @throws WebServerAlreadyRunningException thrown, if the web server was started earlier
	 */
	@NotNull
	WebServer start(@NotNull WebServerConfiguration configuration) throws WebServerAlreadyRunningException;

	/**
	 * This method stops the web server.
	 * @return this web server
	 */
	@NotNull
	WebServer stop();

	/**
	 * This method adds a new route to the web server.
	 * @param restRoute the route to add
	 * @param handler the handler, which handles the execution
	 * @return this web server
	 * @throws WebServerNotRunningException thrown, if the web server is not running
	 */
	@NotNull
	WebServer add(@NotNull RestRoute restRoute, @NotNull RestRouteHandler handler) throws WebServerNotRunningException;

	/**
	 * This method returns a list of all rest endpoints, which are registered in this web server.
	 * @return a list of all rest endpoints
	 */
	@NotNull
	Collection<RestEndpoint> getEndpoints();
}
