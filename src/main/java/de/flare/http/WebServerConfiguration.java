package de.flare.http;

import com.sun.istack.internal.NotNull;

/**
 * This interface represents a data class to configure a web server.
 */
public interface WebServerConfiguration {

	int MIN_THREADS = 1;
	int MIN_PORT = 1025;

	/**
	 * This method returns the allowed request origins.
	 * @return a string representation of the allowed origins
	 */
	@NotNull
	String getAllowedOrigins();

	/**
	 * This method sets the allowed origins.
	 * @param origins the allowed request origins.
	 * @return this web server configuration
	 * @throws IllegalArgumentException thrown, if the origins are invalid
	 */
	@NotNull
	WebServerConfiguration setAllowedOrigins(String origins) throws IllegalArgumentException;

	/**
	 * This method returns the allowed request methods.
	 * @return the allowed request methods
	 */
	@NotNull
	String getAllowedMethods();

	/**
	 * This method sets the allowed request methods.
	 * @param methods the allowed request methods
	 * @return this web server configuration
	 * @throws IllegalArgumentException thrown, if the methods are invalid
	 */
	@NotNull
	WebServerConfiguration setAllowedMethods(String methods) throws IllegalArgumentException;

	/**
	 * This method returns the size of the underlying thread pool.
	 * @return the size of the server thread pool
	 */
	@NotNull
	int getThreadPoolSize();

	/**
	 * This method sets the size of the thread pool.
	 * @param size the size to set
	 * @return this web server configuration
	 * @throws IllegalArgumentException thrown, if the thread pool size is invalid
	 */
	@NotNull
	WebServerConfiguration setThreadPoolSize(int size) throws IllegalArgumentException;

	/**
	 * This method returns the port of the web server.
	 * @return the port of the web server
	 */
	@NotNull
	int getPort();

	/**
	 * This method sets the port of the web server.
	 * @param port the port to use
	 * @return this web server configuration
	 * @throws IllegalArgumentException thrown, if the port is invalid
	 */
	@NotNull
	WebServerConfiguration setPort(int port) throws IllegalArgumentException;

	/**
	 * This method returns the static file location for the web server.
	 * @return the static file location for the web server
	 */
	@NotNull
	String getStaticFilePath();

	/**
	 * This methods sets the static file location for the web server.
	 * @param location the location of the static files
	 * @return this web server configuration
	 * @throws IllegalArgumentException thrown, if the static file location is invalid
	 */
	@NotNull
	WebServerConfiguration setStaticFileLocation(String location) throws IllegalArgumentException;
}
