package de.flare.http.response;

import com.sun.istack.internal.NotNull;

import java.util.Map;

/**
 * This interface contains data for rest responses.
 */
public interface RestResponse {

	/**
	 * This method returns the status code of the rest response.
	 * @return the status code
	 */
	int getStatus();

	/**
	 * This method sets the status code of the rest response.
	 * @param status the status code to set
	 * @return this rest response
	 */
	@NotNull
	RestResponse setStatus(int status);

	/**
	 * This method returns the content type of this rest response.
	 * @return the content type of this rest response
	 */
	@NotNull
	String getContentType();

	/**
	 * This method sets the content type of this rest response.
	 * @param contentType the content type to set
	 * @return this rest response
	 */
	@NotNull
	RestResponse setContentType(@NotNull String contentType);

	/**
	 * This method returns the body of this rest response.
	 * @return the body of this rest response
	 */
	@NotNull
	String getBody();

	/**
	 * This method sets the body of this rest request.
	 * @param body the body to set
	 * @return this rest response
	 */
	@NotNull
	RestResponse setBody(@NotNull String body);

	/**
	 * This method returns a list of headers of this rest response.
	 * @return the map of headers
	 */
	@NotNull
	Map<String, String> getHeaders();

	/**
	 * This method sets a specified header.
	 * @param name the header to set
	 * @param value the value to set
	 * @return this rest response
	 */
	@NotNull
	RestResponse setHeader(@NotNull String name, @NotNull String value);
}
