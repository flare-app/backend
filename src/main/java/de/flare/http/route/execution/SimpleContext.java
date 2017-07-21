package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;
import de.flare.http.request.APIRequest;
import de.flare.http.request.RestRequest;
import de.flare.http.response.APIResponse;
import de.flare.http.response.RestResponse;
import de.flare.http.route.NullRestRoute;
import de.flare.http.route.RestRoute;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
public class SimpleContext implements RestRouteExecutionContext {

	//region private members
	private RestRoute route = new NullRestRoute();
	private RestRequest request = new APIRequest();
	private RestResponse response = new APIResponse();
	//endregion

	//region ctor
	//endregion

	//region rest route execution context

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute getRoute() {
		return route;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRouteExecutionContext setRoute(@NotNull RestRoute route) {
		this.route = route;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRequest getRequest() {
		return request;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRouteExecutionContext setRequest(@NotNull RestRequest request) {
		this.request = request;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestResponse getResponse() {
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRouteExecutionContext setResponse(@NotNull RestResponse response) {
		this.response = response;
		return this;
	}
	//endregion
}
