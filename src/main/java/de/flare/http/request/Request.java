package de.flare.http.request;

public interface Request {

	String ERROR_RESPONSE = "error while receiving response";

	Request setMethod(String method);

	Request setProperty(String key, String value);

	String getContent();

	Request setContent(String requestContent);

	int getResponseCode();

    String getResponse();

	boolean isSuccessful();

	boolean isHostReachable();
}
