package de.flare.http.api.endpoint;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.RestRoute;

import java.util.Collection;

/**
 * This interface contains data for rest endpoints.
 */
@FunctionalInterface
public interface RestEndpoint {

	/**
	 * This method returns a list of all routes for this end point.
	 * @return a list of all routes, which are offered for this end point
	 */
	@NotNull
	Collection<RestRoute> getRoutes();
}
