package de.flare.http;

import com.sun.istack.internal.NotNull;
import spark.Spark;

/**
 * {@inheritDoc}
 * This is the flare API implementation.
 */
public class APIServer implements WebServer {

	//region private members
	private Spark sparkService;
	//endregion

	//region ctor
	/**
	 * This constructor initializes all members with their default values.
	 */
	public APIServer() {

	}
	//endregion

	//region web server
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer start(WebServerConfiguration configuration) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer stop() {
		return this;
	}

	//endregion
}
