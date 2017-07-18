package de.flare.http;

import com.sun.istack.internal.NotNull;
import de.flare.properties.PropertyEditor;

/**
 * {@inheritDoc}
 * This is a basic implementation.
 */
public class BasicConfiguration implements WebServerConfiguration {

	//region private members
	private String allowedOrigins = "*";
	private String allowedMethods = "*";
	private int port = 9898;
	private int threads = 8;
	private String staticFileLocation = "/public";
	//endregion

	//region private const
	private static final String PORT_PROPERTY_KEY = "flareBackendPort";
	private static final String THREADS_PROPERTY_KEY = "flareBackendThreads";
	private static final String ALLOWED_ORIGINS_PROPERTY_KEY = "flareBackendAllowedMethods";
	private static final String ALLOWED_METHODS_PROPERTY_KEY = "flareBackendAllowedOrigin";
	private static final String STATIC_FILE_LOCATION_PROPERTY_KEY = "flareBackendStaticFiles";
	//endregion

	//region ctor
	/**
	 * This constructor initializes all members with their default values.
	 */
	public BasicConfiguration() {

	}

	/**
	 * This method creates a new web server configuration from a given property editor.
	 * @param propertyEditor the property editor to get the configuration values from
	 * @return the created web server configuration
	 */
	public static WebServerConfiguration createFromProperties(PropertyEditor propertyEditor) {
		WebServerConfiguration configuration = new BasicConfiguration();

		return configuration
				.setAllowedOrigins(propertyEditor.getStringOrDefault(ALLOWED_ORIGINS_PROPERTY_KEY, "*"))
				.setAllowedMethods(propertyEditor.getStringOrDefault(ALLOWED_METHODS_PROPERTY_KEY, "*"))
				.setPort(propertyEditor.getIntOrDefault(PORT_PROPERTY_KEY, MIN_PORT))
				.setThreadPoolSize(propertyEditor.getIntOrDefault(THREADS_PROPERTY_KEY, MIN_THREADS))
				.setStaticFileLocation(propertyEditor.getStringOrDefault(STATIC_FILE_LOCATION_PROPERTY_KEY, "/public"));
	}
	//endregion

	//region web server configuration
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getAllowedOrigins() {
		return allowedOrigins;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setAllowedOrigins(String origins) throws IllegalArgumentException {
		if (origins == null || origins.isEmpty()) {
			throw new IllegalArgumentException("illegal allowed origin");
		}

		allowedOrigins = origins;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getAllowedMethods() {
		return allowedMethods;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setAllowedMethods(String methods) throws IllegalArgumentException {
		if (methods == null || methods.isEmpty()) {
			throw new IllegalArgumentException("illegal allowed methods");
		}

		allowedMethods = methods;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public int getThreadPoolSize() {
		return threads;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setThreadPoolSize(int size) throws IllegalArgumentException {
		if (size < MIN_THREADS) {
			throw new IllegalArgumentException("thread pool size must be at least " + MIN_THREADS);
		}

		threads = size;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public int getPort() {
		return port;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setPort(int port) throws IllegalArgumentException {
		if (port < MIN_PORT) {
			throw new IllegalArgumentException("port must be at least " + MIN_PORT);
		}

		this.port = port;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getStaticFilePath() {
		return staticFileLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setStaticFileLocation(String location) throws IllegalArgumentException {
		if (location == null || location.isEmpty()) {
			throw new IllegalArgumentException("invalid static file location");
		}

		staticFileLocation = location;
		return this;
	}

	//endregion
}
