package de.flare.core;

import com.sun.istack.internal.NotNull;
import de.flare.properties.PropertyEditor;
import de.flare.storage.user.authentication.PasswordServiceContainer;

/**
 * This interface represents the main application.
 */
public interface FlareBackend {

	/**
	 * This method returns the property editor of this backend.
	 * @return the property editor
	 */
	@NotNull
	PropertyEditor getPropertyEditor();

	/**
	 * This method returns the password service container of this backend.
	 * @return the password service container
	 */
	@NotNull
	PasswordServiceContainer getPasswordServiceContainer();
}
