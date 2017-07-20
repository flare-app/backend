package de.flare.logging;

import com.sun.istack.internal.NotNull;
import de.flare.core.FlareBackend;

/**
 * This interface offers methods for convenient logging.
 */
public interface Logger {

	/**
	 * This methods logs an exception.
	 * @param callerClass the class of the caller
	 * @param message a custom message for logging
	 * @param throwable the throwable
	 */
	void _error(@NotNull Class callerClass, @NotNull String message, @NotNull Throwable throwable);

	/**
	 * This method logs an error message.
	 * @param callerClass the class of the caller
	 * @param message the message for logging
	 */
	void _error(@NotNull Class callerClass, @NotNull String message);

	/**
	 * This method logs an info message.
	 * @param callerClass the class of the caller
	 * @param message the message to log
	 */
	void _info(@NotNull Class callerClass, @NotNull String message);

	//region interface forwarding
	/**
	 * This methods logs an exception.
	 * @param callerClass the class of the caller
	 * @param message a custom message for logging
	 * @param throwable the throwable
	 */
	static void error(@NotNull Class callerClass, @NotNull String message, @NotNull Throwable throwable) {
		FlareBackend.getLogger()._error(callerClass, message, throwable);
	}

	/**
	 * This method logs an error message.
	 * @param callerClass the class of the caller
	 * @param message the message for logging
	 */
	static void error(@NotNull Class callerClass, @NotNull String message) {
		FlareBackend.getLogger()._error(callerClass, message);
	}

	/**
	 * This method logs an info message.
	 * @param callerClass the class of the caller
	 * @param message the message to log
	 */
	static void info(@NotNull Class callerClass, @NotNull String message) {
		FlareBackend.getLogger()._info(callerClass, message);
	}
	//endregion
}
