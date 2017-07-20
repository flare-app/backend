package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;
import de.flare.storage.user.authorization.AuthorizationGroup;

import java.util.Collection;

/**
 * This interface combines data needed to define rest routes.
 */
public interface RestRoute {

	/**
	 * This method returns the rest request method of this route.
	 * @return the rest request method
	 */
	@NotNull
	RequestMethod getMethod();

	/**
	 * This method sets the rest request method for this route.
	 * @param method the method to set
	 * @return this rest route
	 */
	@NotNull
	RestRoute setMethod(@NotNull RequestMethod method);

	/**
	 * This method returns the uri of this rest route.
	 * @return the uri
	 */
	@NotNull
	String getUri();

	/**
	 * This method adds a new segment to the uri.
	 * @param segment the segment to add
	 * @return this rest route
	 */
	@NotNull
	RestRoute attachUriSegment(@NotNull String segment);

	/**
	 * This method adds a new parameter segment to the uri.
	 * @param parameter the parameter to add to the uri
	 * @return this rest route
	 */
	@NotNull
	RestRoute attachUriSegment(@NotNull UriParameter parameter);

	/**
	 * This method returns the set of the uri parameters.
	 * @return the set of uri parameters
	 */
	@NotNull
	Collection<UriParameter> getParameters();

	/**
	 * This method returns the user group, which may access this route.
	 * @return the user group, which is allowed to access this route
	 */
	@NotNull
	AuthorizationGroup getAccessGroup();
}
