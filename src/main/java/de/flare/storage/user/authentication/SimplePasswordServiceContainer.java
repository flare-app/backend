package de.flare.storage.user.authentication;

import com.sun.istack.internal.NotNull;
import de.flare.properties.PropertyEditor;
import de.flare.properties.SimplePropertyEditor;
import de.flare.storage.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class SimplePasswordServiceContainer implements PasswordServiceContainer {

	//region private members
	private Map<PasswordServiceType, PasswordService> passwordServices = new HashMap<>();
	private PasswordServiceType defaultServiceType = PasswordServiceType.PBKDF2WithHmacSHA1_v1;
	//endregion

	//region private const
	private static final String PASSWORD_HASH_COST_PROPERTY_KEY = "flareBackendUserPasswordHashCost";
	//endregion

	//region ctor
	/**
	 * This constructor prevents anyone from creating an instance of this class.
	 */
	public SimplePasswordServiceContainer(@NotNull PasswordServiceType defaultServiceType) {
		this.defaultServiceType = defaultServiceType;

		String costString = PropertyEditor.getString(PASSWORD_HASH_COST_PROPERTY_KEY);

		boolean useDefaultCost = true;
		int cost = 0;

		try {
			cost = Integer.parseInt(costString);
			useDefaultCost = false;
		} catch (Exception e) {
			// suppress exception
		}

		if (useDefaultCost) {
			passwordServices.put(PasswordServiceType.PBKDF2WithHmacSHA1_v1, new PBKDF2WithHmacSHA1_v1());
		} else {
			passwordServices.put(PasswordServiceType.PBKDF2WithHmacSHA1_v1, new PBKDF2WithHmacSHA1_v1(cost));
		}
	}
	//endregion

	//region password service container
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String _getPasswordToken(@NotNull String password) throws IllegalStateException {
		return getDefaultService().getPasswordToken(password);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean _matches(@NotNull User user, @NotNull String password, @NotNull String token) throws IllegalStateException {
		String[] tokenSplit = token.split(PasswordService.TOKEN_SEPARATOR);

		if (tokenSplit.length < 2) {
			throw new IllegalStateException("unsupported token format");
		}

		for (Map.Entry<PasswordServiceType, PasswordService> entry : passwordServices.entrySet()) {
			if (!entry.getValue().getServiceId().equals(tokenSplit[0])) {
				continue;
			}

			boolean matches = entry.getValue().matches(password, token);

			if (matches && entry.getValue() != getDefaultService()) {
				// TODO: re-compute the password token for this user
			}

			return matches;
		}

		throw new IllegalStateException("unknown password service id");
	}
	//endregion

	//region private methods
	/**
	 * This method returns the default password service.
	 * @return the default password service, or the first if the default is missing
	 * @throws IllegalStateException thrown, if no password services are registered
	 */
	@NotNull
	private PasswordService getDefaultService() throws IllegalStateException {
		if (passwordServices.isEmpty()) {
			throw new IllegalStateException("no password services");
		}

		return passwordServices.getOrDefault(defaultServiceType, passwordServices.entrySet().iterator().next().getValue());
	}
	//endregion
}
