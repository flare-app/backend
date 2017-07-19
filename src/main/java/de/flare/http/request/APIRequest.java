package de.flare.http.request;

import com.sun.istack.internal.NotNull;
import de.flare.http.MIMETypes;
import de.flare.http.route.NullRestRoute;
import de.flare.http.route.RestRoute;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.UriParameterInvalidException;

import java.util.HashMap;
import java.util.Map;

/**
 * {@inheritDoc}
 * This is the API implementation.
 */
public class APIRequest implements RestRequest {

	//region private members
	private RestRoute route = new NullRestRoute();
	private String contentType = MIMETypes.TEXT_PLAIN;
	private String body = "";
	private Map<String, String> parameters = new HashMap<>();
	//endregion

	//region ctor
	//endregion

	//region rest request
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRoute getRoute() {
		return route;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRequest setRoute(@NotNull RestRoute route) {
		this.route = route;
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
	public RestRequest setContentType(@NotNull String contentType) {
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
	public RestRequest setBody(@NotNull String body) {
		this.body = body;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public RestRequest setParameter(@NotNull String parameterName, @NotNull String parameterValue) throws UriParameterInvalidException {
		for (UriParameter parameter : getRoute().getParameters()) {
			if (parameter.getName().equals(parameterName)) {
				parameter.validate(parameterValue);
			}
		}

		parameters.put(parameterName, parameterValue);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getString(@NotNull String parameterName) throws NullPointerException {
		if (!parameters.containsKey(parameterName)) {
			throw new NullPointerException("parameter '" + parameterName + "' was not received");
		}

		return parameters.get(parameterName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInt(@NotNull String parameterName) throws NullPointerException {
		return Integer.parseInt(getString(parameterName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getLong(@NotNull String parameterName) throws NullPointerException {
		return Long.parseLong(getString(parameterName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getBool(@NotNull String parameterName) throws NullPointerException {
		return Boolean.parseBoolean(getString(parameterName));
	}
	//endregion
}
