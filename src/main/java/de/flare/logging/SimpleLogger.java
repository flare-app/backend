package de.flare.logging;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SimpleLogger implements de.flare.logging.Logger {

	//region private members
	private Map<Class, Logger> loggers = new HashMap<>();
	//endregion

	//region ctor
	//endregion

	//region public methods
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void _error(@NotNull Class callerClass, @NotNull String message, @NotNull Throwable throwable) {
		getLogger(callerClass).error(message, throwable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void _error(@NotNull Class callerClass, @NotNull String message) {
		getLogger(callerClass).error(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void _info(@NotNull Class callerClass, @NotNull String message) {
		getLogger(callerClass).info(message);
	}
	//endregion

	//region private methods
	/**
	 * This method tries to return a cached logger for a given caller class.
	 * If there is no logger yet, the logger will be created
	 * @param callerClass the class of the caller
	 * @return the logger for the caller class
	 */
	@NotNull
	private Logger getLogger(@NotNull Class callerClass) {
		if (!loggers.containsKey(callerClass)) {
			loggers.put(callerClass, LoggerFactory.getLogger(callerClass));
		}

		return loggers.get(callerClass);
	}
	//endregion
}
