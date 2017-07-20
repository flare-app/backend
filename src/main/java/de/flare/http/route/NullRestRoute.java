package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;
import de.flare.storage.model.user.authorization.AuthorizationGroup;
import de.flare.storage.model.user.authorization.SimpleAuthorizationGroup;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a null implementation.
 */
public class NullRestRoute implements RestRoute {

	//region rest route
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RequestMethod getMethod() {
		return RequestMethod.GET;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setMethod(@NotNull RequestMethod method) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getUri() {
		return "null route";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute attachUriSegment(@NotNull String segment) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute attachUriSegment(@NotNull UriParameter parameter) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<UriParameter> getParameters() {
		return new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public AuthorizationGroup getAccessGroup() {
		return SimpleAuthorizationGroup.AUTHENTICATED;
	}
	//endregion
}
