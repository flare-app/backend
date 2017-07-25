package de.flare.http.request;

import de.flare.http.HttpStatus;

public class NullRequest implements Request {

	//region private members
	private int responseCode;
	private String content;
	private String response;
	//endregion

	//region ctor
	public NullRequest(int responseCode) {
		this(responseCode, "null request");
	}

	public NullRequest(int responseCode, String response) {
		this.responseCode = responseCode;
		this.response = response;
	}
	//endregion

	//region request
	@Override
	public Request setMethod(String method) {
		// empty, since it has no effect
		return this;
	}

	@Override
	public Request setProperty(String key, String value) {
		// empty, since it has no effect
		return this;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public Request setContent(String requestContent) {
		content = requestContent;
		return this;
	}

	@Override
	public int getResponseCode() {
		return responseCode;
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public boolean isSuccessful() {
		return getResponseCode() == HttpStatus.OK;
	}

	@Override
	public boolean isHostReachable() {
		return false;
	}
	//endregion
}
