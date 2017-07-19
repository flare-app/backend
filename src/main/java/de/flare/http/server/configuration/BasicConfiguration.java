package de.flare.http.server.configuration;

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
	private String keyStoreLocation = "";
	private String keyStorePassword = "";
	private String trustStoreLocatoin = null;
	private String trustStorePassword = null;
	//endregion

	//region private const
	private static final String PORT_PROPERTY_KEY = "flareBackendPort";
	private static final String THREADS_PROPERTY_KEY = "flareBackendThreads";
	private static final String ALLOWED_ORIGINS_PROPERTY_KEY = "flareBackendAllowedMethods";
	private static final String ALLOWED_METHODS_PROPERTY_KEY = "flareBackendAllowedOrigin";
	private static final String STATIC_FILE_LOCATION_PROPERTY_KEY = "flareBackendStaticFiles";
	private static final String KEY_STORE_LOCATION_PROPERTY_KEY = "flareBackendKeyStoreLocation";
	private static final String KEY_STORE_PASSWORD_PROPERTY_KEY = "flareBackendKeyStorePassword";
	private static final String TRUST_STORE_LOCATION_PROPERTY_KEY = "flareBackendTrustStoreLocation";
	private static final String TRUST_STORE_PASSWORD_PROPERTY_KEY = "flareBackendTrustStorePassword";
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
	public static WebServerConfiguration createFromProperties(@NotNull PropertyEditor propertyEditor) {
		WebServerConfiguration configuration = new BasicConfiguration();

		return configuration
				.setAllowedOrigins(propertyEditor.getStringOrDefault(ALLOWED_ORIGINS_PROPERTY_KEY, "*"))
				.setAllowedMethods(propertyEditor.getStringOrDefault(ALLOWED_METHODS_PROPERTY_KEY, "*"))
				.setPort(propertyEditor.getIntOrDefault(PORT_PROPERTY_KEY, MIN_PORT))
				.setThreadPoolSize(propertyEditor.getIntOrDefault(THREADS_PROPERTY_KEY, MIN_THREADS))
				.setStaticFileLocation(propertyEditor.getStringOrDefault(STATIC_FILE_LOCATION_PROPERTY_KEY, "/public"))
				.setKeyStoreLocations(propertyEditor.getStringOrDefault(KEY_STORE_LOCATION_PROPERTY_KEY, ""))
				.setKeyStorePassword(propertyEditor.getStringOrDefault(KEY_STORE_PASSWORD_PROPERTY_KEY, ""))
				.setTrustStoreLocation(propertyEditor.getStringOrDefault(TRUST_STORE_LOCATION_PROPERTY_KEY, null))
				.setTrustStorePassword(propertyEditor.getStringOrDefault(TRUST_STORE_PASSWORD_PROPERTY_KEY, null));
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
	public WebServerConfiguration setAllowedOrigins(@NotNull String origins) {
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
	public WebServerConfiguration setAllowedMethods(@NotNull String methods) {
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
	public WebServerConfiguration setStaticFileLocation(@NotNull String location) {
		staticFileLocation = location;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getKeyStoreLocation() {
		return keyStoreLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setKeyStoreLocations(@NotNull String location) {
		keyStoreLocation = location;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public WebServerConfiguration setKeyStorePassword(@NotNull String password) {
		keyStorePassword = password;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTrustStoreLocation() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebServerConfiguration setTrustStoreLocation(String location) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTrustStorePassword() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebServerConfiguration setTrustStorePassword(String password) {
		return null;
	}
	//endregion
}
