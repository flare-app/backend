package de.flare.http.server;

import com.sun.istack.internal.NotNull;
import de.flare.http.exception.HttpException;
import de.flare.http.exception.WebServerAlreadyRunningException;
import de.flare.http.exception.WebServerNotRunningException;
import de.flare.http.definition.StatusCodes;
import de.flare.http.request.APIRequest;
import de.flare.http.request.RestRequest;
import de.flare.http.response.APIResponse;
import de.flare.http.response.RestResponse;
import de.flare.http.route.RestRoute;
import de.flare.http.route.execution.RestRouteHandler;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;
import de.flare.http.server.configuration.WebServerConfiguration;
import de.flare.logging.SimpleLogger;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.Map;

/**
 * {@inheritDoc}
 * This is a spark-based web server.
 */
public class SparkServer implements WebServer {

	//region private members
	private Service sparkService;
	//endregion

	//region ctor
	//endregion

	//region web server
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServer start(@NotNull WebServerConfiguration configuration) throws WebServerAlreadyRunningException {
		if (sparkService != null) {
			throw new WebServerAlreadyRunningException("spark server was started earlier");
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
	public WebServer add(@NotNull RestRoute restRoute, @NotNull RestRouteHandler handler) throws WebServerNotRunningException {
		if (sparkService == null) {
			throw new WebServerNotRunningException("web server is not running yet");
		}

		switch (restRoute.getMethod()) {
			case GET:
				sparkService.get(getSparkUri(restRoute), (request, response) -> {
					executeRequest(restRoute, handler, request, response);
					return response.body();
				});
				break;

			case POST:
				sparkService.post(getSparkUri(restRoute), (request, response) -> {
					executeRequest(restRoute, handler, request, response);
					return response.body();
				});
				break;

			case PUT:
				sparkService.put(getSparkUri(restRoute), (request, response) -> {
					executeRequest(restRoute, handler, request, response);
					return response.body();
				});
				break;

			case DELETE:
				sparkService.delete(getSparkUri(restRoute), (request, response) -> {
					executeRequest(restRoute, handler, request, response);
					return response.body();
				});
				break;

			default:
				SimpleLogger.error(getClass(), "unsupported rest method: '" + restRoute.getMethod().toString() + "'");
		}

		return this;
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
	 * This method executes a specific rest route.
	 * @param route the route, which got called
	 * @param handler the route handler
	 * @param request the spark request
	 * @param response the spark response
	 */
	private void executeRequest(@NotNull RestRoute route, @NotNull RestRouteHandler handler, Request request, Response response) {
		try {
			RestRequest restRequest = convert(route, request);
			RestResponse restResponse = convert(response);

			handler.handle(route, restRequest, restResponse);
			fillResponse(response, restResponse);
		} catch (HttpException e) {
			response.body(e.getRestResponse().getBody());
			response.status(e.getRestResponse().getStatus());
		} catch (Exception e) {
			SimpleLogger.error(getClass(), "error while executing request: " + route.getUri(), e);
			response.body(e.getMessage());
			response.status(StatusCodes.ERROR);
		}
	}

	/**
	 * This method converts the rest route uri to a spark-readable uri.
	 * @param restRoute the rest route to convert
	 * @return the spark-readable uri
	 */
	@NotNull
	private String getSparkUri(@NotNull RestRoute restRoute) {
		String uri = restRoute.getUri();

		for (UriParameter parameter : restRoute.getParameters()) {
			uri = uri.replaceAll(parameter.getUriName(), String.format("{%s}", parameter.getName()));
		}

		return uri;
	}

	/**
	 * This method converts a given spark-request to a rest request.
	 * @param route the called route
	 * @param request the spark request to convert
	 * @return the converted rest request
	 * @throws HttpException thrown, if the parameters are not set correctly
	 */
	private RestRequest convert(@NotNull RestRoute route, @NotNull Request request) throws HttpException {
		RestRequest restRequest = new APIRequest()
				.setBody(request.body())
				.setContentType(request.contentType())
				.setRoute(route);

		for (Map.Entry<String, String> parameter : request.params().entrySet()) {
			try {
				restRequest.setParameter(parameter.getKey(), parameter.getValue());
			} catch (UriParameterInvalidException e) {
				throw new HttpException(e.getMessage());
			}
		}

		return restRequest;
	}

	/**
	 * This method converts a given spark-response to a rest response.
	 * @param response the spark response to convert
	 * @return the converted rest response
	 */
	private RestResponse convert(@NotNull Response response) {
		return new APIResponse()
				.setStatus(response.status())
				.setBody(response.body())
				.setContentType(response.type());
	}

	/**
	 * This method fills the given spark-response with the data from the given rest response.
	 * @param response the spark response to fill
	 * @param restResponse the rest response to take the data from
	 */
	private void fillResponse(@NotNull Response response, @NotNull RestResponse restResponse) {
		response.status(restResponse.getStatus());
		response.body(restResponse.getBody());
		response.type(restResponse.getContentType());
	}
	//endregion
}
