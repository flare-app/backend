package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;
import de.flare.http.request.RestRequest;
import de.flare.http.response.RestResponse;
import de.flare.http.route.RestRoute;

/**
 * {@inheritDoc}
 * This implementation checks, whether the user may access the called route.
 */
public class SecuredRouteHandler implements RestRouteHandler {

	//region ctor
	//endregion

	//region rest route handler

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(@NotNull RestRoute route, @NotNull RestRequest request, @NotNull RestResponse response) {

	}

	//endregion
}
