package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;
import de.flare.storage.user.authentication.AuthenticationGroup;
import de.flare.storage.user.authentication.SimpleAuthenticationGroup;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 */
public class APIRoute implements RestRoute {

	//region private methods
	private RequestMethod method = RequestMethod.GET;
	private String uri = "";
	private Collection<UriParameter> parameters = new ArrayList<>();
	private AuthenticationGroup accessGroup = SimpleAuthenticationGroup.AUTHENTICATED;
	//endregion

	//region ctor

	/**
	 * This is the default constructor for this class.
	 */
	public APIRoute() {

	}

	/**
	 * This constructor sets the access levels for this rest route.
	 * @param accessGroup the group of users, who may access this route
	 */
	public APIRoute(@NotNull AuthenticationGroup accessGroup) {
		this.accessGroup = accessGroup;
	}

	/**
	 * This method creates a new GET route with the specified access group.
	 * @param accessGroup the group of users, who have access to this route
	 * @return the route
	 */
	public static RestRoute get(@NotNull AuthenticationGroup accessGroup) {
		return new APIRoute(accessGroup).setMethod(RequestMethod.GET);
	}

	/**
	 * This method creates a new POST route with the specified access group.
	 * @param accessGroup the group of users, who have access to this route
	 * @return the route
	 */
	public static RestRoute post(@NotNull AuthenticationGroup accessGroup) {
		return new APIRoute(accessGroup).setMethod(RequestMethod.POST);
	}

	/**
	 * This method creates a new PUT route with the specified access group.
	 * @param accessGroup the group of users, who have access to this route
	 * @return the route
	 */
	public static RestRoute put(@NotNull AuthenticationGroup accessGroup) {
		return new APIRoute(accessGroup).setMethod(RequestMethod.PUT);
	}

	/**
	 * This method creates a new DELETE route with the specified access group.
	 * @param accessGroup the group of users, who have access to this route
	 * @return the route
	 */
	public static RestRoute delete(@NotNull AuthenticationGroup accessGroup) {
		return new APIRoute(accessGroup).setMethod(RequestMethod.DELETE);
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
		return parameters;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public AuthenticationGroup getAccessGroup() {
		return accessGroup;
	}
	//endregion
}
