package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * {@inheritDoc}
 */
public class APIRoute implements RestRoute {

	//region private methods
	private RequestMethod method = RequestMethod.GET;
	private String uri = "";
	private Collection<UriParameter> parameters = new ArrayList<>();
	private int accessLevel = RouteAccessLevel.FLARE_ADMINISTRATOR;
	private int modifyLevel = RouteAccessLevel.FLARE_ADMINISTRATOR;
	//endregion

	//region ctor
	/**
	 * This method returns a new GET route, which can only be modified by the flare administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute getFlare(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.GET)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.FLARE_ADMINISTRATOR);
	}

	/**
	 * This method returns a new GET route, which can be modified by any administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute getLocal(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.GET)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.ADMINISTRATOR);
	}

	/**
	 * This method returns a new POST route, which can only be modified by the flare administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute postFlare(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.POST)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.FLARE_ADMINISTRATOR);
	}

	/**
	 * This method returns a new POST route, which can be modified by any administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute postLocal(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.POST)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.ADMINISTRATOR);
	}

	/**
	 * This method returns a new PUT route, which can only be modified by the flare administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute putFlare(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.PUT)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.FLARE_ADMINISTRATOR);
	}

	/**
	 * This method returns a new PUT route, which can be modified by any administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute putLocal(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.PUT)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.ADMINISTRATOR);
	}

	/**
	 * This method returns a new DELETE route, which can only be modified by the flare administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute deleteFlare(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.DELETE)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.FLARE_ADMINISTRATOR);
	}

	/**
	 * This method returns a new DELETE route, which can be modified by any administrator.
	 * @param accessLevel the access level for the route
	 * @return the new rest route
	 */
	public static RestRoute deleteLocal(int accessLevel) {
		return new APIRoute()
				.setMethod(RequestMethod.DELETE)
				.setAccessLevel(accessLevel)
				.setModificationLevel(RouteAccessLevel.ADMINISTRATOR);
	}
	//endregion

	//region rest route
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RequestMethod getMethod() {
		return method;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setMethod(@NotNull RequestMethod method) {
		this.method = method;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getUri() {
		return uri;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute attachUriSegment(@NotNull String segment) {
		uri = String.format("%s/%s", uri, segment);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute attachUriSegment(@NotNull UriParameter parameter) {
		parameters.add(parameter);
		return attachUriSegment(parameter.getUriName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<UriParameter> getParameters() {
		return Collections.unmodifiableCollection(parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAccessLevel() {
		return accessLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setAccessLevel(int level) {
		accessLevel = level;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getModificationLevel() {
		return modifyLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute setModificationLevel(int level) {
		modifyLevel = level;
		return this;
	}
	//endregion
}
