package de.flare.core;

import com.sun.istack.internal.NotNull;
import de.flare.properties.PropertyEditor;
import de.flare.properties.SimplePropertyEditor;
import de.flare.storage.user.authentication.PasswordServiceContainer;
import de.flare.storage.user.authentication.PasswordServiceType;
import de.flare.storage.user.authentication.SimplePasswordServiceContainer;

/**
 * {@inheritDoc}
 * This is the v1 implementation.
 */
public class FlareBackend_v1 implements FlareBackend {

	//region singleton
	private static FlareBackend instance;

	private FlareBackend_v1() {

	}

	public static FlareBackend getInstance() {
		if (instance == null) {
			instance = new FlareBackend_v1();
		}

		return instance;
	}
	//endregion

	//region private members
	private PropertyEditor propertyEditor = new SimplePropertyEditor();
	private PasswordServiceContainer passwordServiceContainer = new SimplePasswordServiceContainer(PasswordServiceType.PBKDF2WithHmacSHA1_v1);
	//endregion

	//region ctor
	//endregion

	//region flare backend
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor getPropertyEditor() {
		return propertyEditor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PasswordServiceContainer getPasswordServiceContainer() {
		return passwordServiceContainer;
	}
	//endregion
}
