package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;
import de.flare.http.request.RestRequest;
import de.flare.http.response.RestResponse;
import de.flare.http.route.RestRoute;

/**
 * This interface contains data for the route execution.
 */
public interface RestRouteExecutionContext {

	/**
	 * This method returns the called rest route.
	 * @return the rest route
	 */
	@NotNull
	RestRoute getRoute();

	/**
	 * This method sets the rest route.
	 * @param route the route to set
	 * @return this rest route execution context
	 */
	@NotNull
	RestRouteExecutionContext setRoute(@NotNull RestRoute route);

	/**
	 * This method returns the request.
	 * @return the request
	 */
	@NotNull
	RestRequest getRequest();

	/**
	 * This method sets the rest request.
	 * @param request the request to set
	 * @return this rest route execution context
	 */
	@NotNull
	RestRouteExecutionContext setRequest(@NotNull RestRequest request);

	/**
	 * This method returns the response.
	 * @return the response
	 */
	@NotNull
	RestResponse getResponse();

	/**
	 * This method sets the rest response.
	 * @param response the response to set
	 * @return this rest route execution context
	 */
	@NotNull
	RestRouteExecutionContext setResponse(@NotNull RestResponse response);
}
