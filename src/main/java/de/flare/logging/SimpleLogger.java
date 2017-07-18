package de.flare.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class SimpleLogger {

	//region ctor
	/**
	 * This constructor prevents anyone to create an instance of this class.
	 */
	private SimpleLogger() {

	}
	//endregion

	//region private static members
	private static Map<Class, Logger> loggers = new HashMap<>();
	//endregion

	//region public static methods
	/**
	 * This methods logs an exception.
	 * @param callerClass the class of the caller
	 * @param message a custom message for logging
	 * @param throwable the throwable
	 */
	public static void error(Class callerClass, String message, Throwable throwable) {
		getLogger(callerClass).error(message, throwable);
	}

	/**
	 * This method logs an info message.
	 * @param callerClass the class of the caller
	 * @param message the message to log
	 */
	public static void info(Class callerClass, String message) {
		getLogger(callerClass).info(message);
	}
	//endregion

	//region private static methods
	/**
	 * This method tries to return a cached logger for a given caller class.
	 * If there is no logger yet, the logger will be created
	 * @param callerClass the class of the caller
	 * @return the logger for the caller class
	 */
	private static Logger getLogger(Class callerClass) {
		if (!loggers.containsKey(callerClass)) {
			loggers.put(callerClass, LoggerFactory.getLogger(callerClass));
		}

		return loggers.get(callerClass);
	}
	//endregion
}
