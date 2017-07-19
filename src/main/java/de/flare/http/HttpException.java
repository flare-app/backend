package de.flare.http;

import com.sun.istack.internal.NotNull;
import de.flare.http.response.APIResponse;
import de.flare.http.response.RestResponse;

/**
 * This exception is thrown, whenever something goes wrong during a request execution.
 */
public class HttpException extends Exception {

	//region private members
	private RestResponse restResponse;
	//endregion

	//region ctor

	/**
	 * This constructor initializes a default error-response with a specific message.
	 * @param message the message to send
	 */
	public HttpException(@NotNull String message) {
		restResponse = APIResponse.error(message);
	}

	/**
	 * This constructor initializes a new http exception from a given rest response.
	 * @param restResponse the rest response
	 */
	public HttpException(@NotNull RestResponse restResponse) {
		this.restResponse = restResponse;
	}
	//endregion

	//region public methods
	/**
	 * This method returns the underlying rest response.
	 * @return the rest response
	 */
	@NotNull
	public RestResponse getRestResponse() {
		return restResponse;
	}
	//endregion
}
