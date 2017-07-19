package de.flare.http;

import com.sun.istack.internal.NotNull;
import de.flare.logging.SimpleLogger;
import de.flare.properties.PropertyEditor;
import spark.*;

/**
 * {@inheritDoc}
 * This is the flare API implementation.
 */
public class APIServer implements WebServer {

	//region private members
	private Service sparkService;
	//endregion

	//region ctor
	/**
	 * This constructor initializes all members with their default values.
	 */
	public APIServer() {

	}

	/**
	 * This method creates and starts a new API server from a given property editor.
	 * @param propertyEditor the property editor to get the configuration data from
	 * @return the new API server, or null if something goes wrong
	 */
	public static WebServer start(@NotNull  PropertyEditor propertyEditor) {
		WebServerConfiguration configuration = BasicConfiguration.createFromProperties(propertyEditor);
		WebServer server;

		try {
			server = new APIServer();
			return server.start(configuration);
		} catch (WebServerAlreadyRunningException e) {
			SimpleLogger.error(APIServer.class, e.getMessage(), e);
			return null;
		}
	}
	//endregion

	//region web server
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer start(@NotNull WebServerConfiguration configuration) throws WebServerAlreadyRunningException {
		if (sparkService != null) {
			throw new WebServerAlreadyRunningException("API server was started earlier.");
		}

		sparkService = Service
				.ignite()
				.port(configuration.getPort())
				.threadPool(configuration.getThreadPoolSize())
				.staticFileLocation(configuration.getStaticFilePath())
				.secure(configuration.getKeyStoreLocation(), configuration.getKeyStorePassword(),
					configuration.getTrustStoreLocation(), configuration.getTrustStorePassword());

		// TODO: add rest endpoints

		setCors(configuration);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer stop() {
		if (sparkService == null) {
			return this;
		}

		sparkService.stop();
		sparkService = null;

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer get(@NotNull String uri, @NotNull Route restRoute) throws WebServerNotRunningException {
		if (sparkService == null) {
			throw new WebServerNotRunningException("API server was not started yet.");
		}

		sparkService.get(uri, (request, response) -> executeRequest(request, response, restRoute));
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer post(@NotNull String uri, @NotNull Route restRoute) throws WebServerNotRunningException {
		if (sparkService == null) {
			throw new WebServerNotRunningException("API server was not started yet.");
		}

		sparkService.post(uri, (request, response) -> executeRequest(request, response, restRoute));
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer put(@NotNull String uri, @NotNull Route restRoute) throws WebServerNotRunningException {
		if (sparkService == null) {
			throw new WebServerNotRunningException("API server was not started yet.");
		}

		sparkService.put(uri, (request, response) -> executeRequest(request, response, restRoute));
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer delete(@NotNull String uri, @NotNull Route restRoute) throws WebServerNotRunningException {
		if (sparkService == null) {
			throw new WebServerNotRunningException("API server was not started yet.");
		}

		sparkService.delete(uri, (request, response) -> executeRequest(request, response, restRoute));
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getRouteParameter(@NotNull String parameterName) {
		return String.format("{%s}", parameterName);
	}

	//endregion

	//region private methods
	/**
	 * This method sets the cross origin resource sharing options.
	 * @param configuration the configuration, containing the CORS settings
	 */
	private void setCors(@NotNull WebServerConfiguration configuration) {
		sparkService.options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		sparkService.before((request, response) -> {
			response.header("Access-Control-Allow-Origin", configuration.getAllowedOrigins());
			response.header("Access-Control-Request-Method", configuration.getAllowedMethods());
			response.header("Cache-Control", "no-cache, no-store, must-revalidate");
			response.header("Pragma", "no-cache");
			response.header("Expires", "0");
			response.type("application/json");
		});
	}

	/**
	 * This method executes a rest route in a save context.
	 * @param request the spark request
	 * @param response the spark response
	 * @param restRoute the spark route to execute
	 * @return the response body
	 */
	private String executeRequest(@NotNull Request request, @NotNull Response response, @NotNull Route restRoute) {
		try {
			restRoute.handle(request, response);
		} catch (HaltException halt) {
			// suppress exception
			response.status(halt.statusCode());
			response.body(halt.body());
		} catch (Exception e) {
			SimpleLogger.error(getClass(), "error while executing rest request '" + request.url() + "'", e);
			response.status(StatusCodes.ERROR);
			response.body(e.getMessage());
		}

		return response.body();
	}
	//endregion
}
