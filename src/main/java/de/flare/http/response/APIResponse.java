package de.flare.http.response;

import com.sun.istack.internal.NotNull;
import de.flare.http.definition.MIMETypes;
import de.flare.http.definition.StatusCodes;

/**
 * {@inheritDoc}
 * This is the API implementation. It is also throwable, in case you must stop the execution immediately.
 */
public class APIResponse implements RestResponse {

	//region private members
	private int status = StatusCodes.OK;
	private String contentType = MIMETypes.TEXT_PLAIN;
	private String body = "";
	//endregion

	//region ctor

	/**
	 * This method creates a new ok-response.
	 * @return the new response
	 */
	public static RestResponse ok() {
		return create(StatusCodes.OK, MIMETypes.TEXT_PLAIN, "OK");
	}

	/**
	 * This method creates a new error-response.
	 * @param message the message to send
	 * @return the new response
	 */
	public static RestResponse error(@NotNull String message) {
		return create(StatusCodes.ERROR, MIMETypes.TEXT_PLAIN, message);
	}

	/**
	 * This method creates a new rest response.
	 * @param status the status code to set
	 * @param contentType the content type to set
	 * @param body the body to set
	 * @return the new rest response
	 */
	public static RestResponse create(int status, @NotNull String contentType, @NotNull String body) {
		return new APIResponse()
				.setStatus(status)
				.setContentType(contentType)
				.setBody(body);
	}
	//endregion

	//region rest response
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestResponse setStatus(int status) {
		this.status = status;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getContentType() {
		return contentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestResponse setContentType(@NotNull String contentType) {
		this.contentType = contentType;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getBody() {
		return body;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestResponse setBody(@NotNull String body) {
		this.body = body;
		return this;
	}
	//endregion
}
