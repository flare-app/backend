package de.flare.http.request;


import de.flare.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public final class RequestFactory {

	//region ctor
	private RequestFactory() { }
	//endregion

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(RequestFactory.class);
	private static final String DEFAULT_CONTENT_TYPE = "application/json";
	private static final String DEFAULT_ACCEPT_TYPE = "application/json";
	//endregion

	//region public static methods
	public static Request createRequest(String host, String uri, String requestMethod, String contentType, String acceptType) {
		return createBasicRequest(host, uri)
				.setMethod(requestMethod)
				.setProperty("Content-Type", contentType)
				.setProperty("Accept", acceptType);
	}

	public static Request createPostRequest(String host, String uri, String contentType, String acceptType) {
		return createRequest(host, uri, "POST", contentType, acceptType);
	}

	public static Request createPostRequest(String host, String uri) {
		return createPostRequest(host, uri, DEFAULT_CONTENT_TYPE, DEFAULT_ACCEPT_TYPE);
	}

	public static Request createGetRequest(String host, String uri, String acceptType) {
		return createRequest(host, uri, "GET", DEFAULT_CONTENT_TYPE, acceptType);
	}

	public static Request createGetRequest(String host, String uri) {
		return createGetRequest(host, uri, DEFAULT_ACCEPT_TYPE);
	}

	public static Request createDeleteRequest(String host, String uri) {
		return createDeleteRequest(host, uri, DEFAULT_CONTENT_TYPE, DEFAULT_ACCEPT_TYPE);
	}

	public static Request createDeleteRequest(String host, String uri, String contentType, String acceptType) {
		return createRequest(host, uri, "DELETE", contentType, acceptType);
	}

	public static Request createPutRequest(String host, String uri) {
		return createPutRequest(host, uri, DEFAULT_CONTENT_TYPE, DEFAULT_ACCEPT_TYPE);
	}

	public static Request createPutRequest(String host, String uri, String contentType, String acceptType) {
		return createRequest(host, uri, "PUT", contentType, acceptType);
	}

	public static boolean isReachable(String host) {
		URL url = toUrl(host);

		return url != null && isReachable(url);
	}
	//endregion

	//region private static methods
	private static Request createBasicRequest(String host, String uri) {
		URL requestURL = toUrl(host + uri);
		Request request = null;

		if (requestURL == null) {
			throw new IllegalStateException("request uri must not be null");
		}

		if (!isReachable(requestURL)) {
			return new NullRequest(HttpStatus.BAD_GATEWAY, host + uri + " is not reachable");
		}

		try {
			request = new RestRequest(requestURL);
		} catch (IOException e) {
			logger.error("cannot create request", e);
		}

		return request;
	}

	private static boolean isReachable(URL host) {
		URLConnection hostConnection;
		try {
			hostConnection = host.openConnection();
			hostConnection.connect();

			return true;
		} catch (IOException e) {
			//suppress exception
			return false;
		}
	}

	private static URL toUrl(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			//suppress exception
			return null;
		}
	}
	//endregion
}
