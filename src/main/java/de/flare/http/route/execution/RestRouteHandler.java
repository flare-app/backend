package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;
import de.flare.http.request.RestRequest;
import de.flare.http.response.RestResponse;
import de.flare.http.route.RestRoute;

/**
 * This interface offers a method to handle rest requests.
 */
@FunctionalInterface
public interface RestRouteHandler {

	/**
	 * This method handles the execution of a specific rest route.
	 * @param route the route, which was called
	 * @param request the incoming request
	 * @param response the outgoing response
	 */
	void handle(@NotNull RestRoute route, @NotNull RestRequest request, @NotNull RestResponse response);
}
