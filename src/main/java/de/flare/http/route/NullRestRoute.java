package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;

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
	public RestMethod getMethod() {
		return RestMethod.GET;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setMethod(@NotNull RestMethod method) {
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
	public int getAccessLevel() {
		return RouteAccessLevel.NONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setAccessLevel(int level) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getModificationLevel() {
		return RouteAccessLevel.NONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setModificationLevel(int level) {
		return this;
	}
	//endregion
}
