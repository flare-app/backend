package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;
import de.flare.http.request.RestRequest;
import de.flare.http.response.RestResponse;
import de.flare.http.route.RestRoute;
import de.flare.storage.model.user.User;

/**
 * {@inheritDoc}
 * This implementation checks, whether the user may access the called route.
 */
public abstract class SecuredRouteHandler implements RestRouteHandler {

	//region ctor
	//endregion

	//region rest route handler
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(@NotNull RestRoute route, @NotNull RestRequest request, @NotNull RestResponse response) {
		// TODO: get user
		// TODO: check, if user may access this route
		// TODO: call 'execute'
	}
	//endregion

	//region protected methods
	protected abstract void execute(@NotNull RestRoute route, @NotNull RestRequest request, @NotNull RestResponse restResponse, @NotNull User user);
	//endregion
}
