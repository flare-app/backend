package de.flare.http.request;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.RestRoute;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;

/**
 * This interface contains data for rest requests.
 */
public interface RestRequest {

	/**
	 * This method returns the rest route for this rest request.
	 * @return the rest route for this rest request
	 */
	@NotNull
	RestRoute getRoute();

	/**
	 * This method sets the rest route for this rest request.
	 * @param route the route to set
	 * @return this rest request
	 */
	@NotNull
	RestRequest setRoute(@NotNull RestRoute route);

	/**
	 * This method returns the content type of this rest request.
	 * @return the content type of this rest request
	 */
	@NotNull
	String getContentType();

	/**
	 * This method sets the content type of this rest request.
	 * @param contentType the content type to set
	 * @return this rest request
	 */
	@NotNull
	RestRequest setContentType(@NotNull String contentType);

	/**
	 * This method returns the body of this rest request.
	 * @return the body of this rest request
	 */
	@NotNull
	String getBody();

	/**
	 * This method sets the body of this rest request.
	 * @param body the body to set
	 * @return this rest request
	 */
	@NotNull
	RestRequest setBody(@NotNull String body);

	/**
	 * This method sets the value for a given parameter.
	 * @param parameterName the parameter name
	 * @param parameterValue the parameter value
	 * @return this rest request
	 * @throws UriParameterInvalidException thrown, if the parameter is not valid
	 */
	@NotNull
	RestRequest setParameter(@NotNull String parameterName, @NotNull String parameterValue) throws UriParameterInvalidException;

	/**
	 * This method returns the value for a given parameter.
	 * @param parameterName the parameter name
	 * @return the value for the given parameter as string
	 * @throws NullPointerException thrown, if the requested parameter does not exist
	 */
	@NotNull
	String getString(@NotNull String parameterName) throws NullPointerException;

	/**
	 * This method returns the value for a given parameter.
	 * @param parameterName the parameter name
	 * @return the value for the given parameter as int
	 * @throws NullPointerException thrown, if the requested parameter does not exist
	 */
	int getInt(@NotNull String parameterName) throws NullPointerException;

	/**
	 * This method returns the value for a given parameter.
	 * @param parameterName the parameter name
	 * @return the value for the given parameter as long
	 * @throws NullPointerException thrown, if the requested parameter does not exist
	 */
	long getLong(@NotNull String parameterName) throws NullPointerException;

	/**
	 * This method returns the value for a given parameter.
	 * @param parameterName the parameter name
	 * @return the value for the given parameter as bool
	 * @throws NullPointerException thrown, if the requested parameter does not exist
	 */
	boolean getBool(@NotNull String parameterName) throws NullPointerException;
}
