package de.flare.http.route;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;

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
	RestMethod getMethod();

	/**
	 * This method sets the rest request method for this route.
	 * @param method the method to set
	 * @return this rest route
	 */
	@NotNull
	RestRoute setMethod(@NotNull RestMethod method);

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
	 * This method returns the required access level of this rest route.
	 * @return the required access level
	 */
	int getAccessLevel();

	/**
	 * This method sets the required access level for this rest route.
	 * @param level the level to set
	 * @return this rest route
	 */
	RestRoute setAccessLevel(int level);

	/**
	 * This method return the required level to modify this rest route.
	 * @return the required level to modify this rest route via API
	 */
	int getModificationLevel();

	/**
	 * This method sets the required level to modify this rest route via API.
	 * @param level the required level
	 * @return this rest route
	 */
	RestRoute setModificationLevel(int level);
}
