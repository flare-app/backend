package de.flare.core;

import com.sun.istack.internal.NotNull;
import de.flare.logging.Logger;
import de.flare.logging.SimpleLogger;
import de.flare.properties.PropertyEditor;
import de.flare.properties.SimplePropertyEditor;
import de.flare.storage.model.user.authentication.PasswordServiceContainer;
import de.flare.storage.model.user.authentication.PasswordServiceType;
import de.flare.storage.model.user.authentication.SimplePasswordServiceContainer;

/**
 * This is the flare backend.
 */
public final class FlareBackend {

	//region private static members
	private static Logger logger;
	private static PropertyEditor propertyEditor;
	private static PasswordServiceContainer passwordServiceContainer;
	//endregion

	//region ctor
	static {
		logger = new SimpleLogger();
		propertyEditor = new SimplePropertyEditor();
		passwordServiceContainer = new SimplePasswordServiceContainer(PasswordServiceType.PBKDF2WithHmacSHA1_v1);
	}

	/**
	 * This constructor prevents anyone from creating an instance of this class.
	 */
	private FlareBackend() {

	}
	//endregion

	//region public static methods

	/**
	 * This method return the flare backend logger
	 * @return the logger
	 */
	@NotNull
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * This method sets the flare backend logger
	 * @param logger the logger to set
	 */
	public static void setLogger(@NotNull Logger logger) {
		FlareBackend.logger = logger;
	}

	/**
	 * This method returns the flare backend property editor.
	 * @return the property editor
	 */
	@NotNull
	public static PropertyEditor getPropertyEditor() {
		return propertyEditor;
	}

	/**
	 * This method sets the flare backend property editor.
	 * @param propertyEditor the property editor to set
	 */
	public static void setPropertyEditor(@NotNull PropertyEditor propertyEditor) {
		FlareBackend.propertyEditor = propertyEditor;
	}

	/**
	 * This method returns the flare backend password service container.
	 * @return the password service container
	 */
	@NotNull
	public static PasswordServiceContainer getPasswordServiceContainer() {
		return passwordServiceContainer;
	}

	/**
	 * This method sets the flare backend password service container.
	 * @param passwordServiceContainer the password container to set
	 */
	public static void setPasswordServiceContainer(@NotNull PasswordServiceContainer passwordServiceContainer) {
		FlareBackend.passwordServiceContainer = passwordServiceContainer;
	}
	//endregion
}
