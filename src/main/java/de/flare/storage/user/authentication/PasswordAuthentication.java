package de.flare.storage.user.authentication;

import com.sun.istack.internal.NotNull;
import de.flare.properties.SimplePropertyEditor;
import de.flare.storage.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * This class offers methods to tokenize and validate passwords.
 */
public final class PasswordAuthentication {

	//region private static members
	private static Map<PasswordServiceType, PasswordService> passwordServices = new HashMap<>();
	//endregion

	//region private const
	private static final String PASSWORD_HASH_COST_PROPERTY_KEY = "flareBackendUserPasswordHashCost";
	private static final PasswordServiceType DEFAULT_SERVICE = PasswordServiceType.PBKDF2WithHmacSHA1_v1;
	//endregion

	//region ctor
	static {

		String costString = SimplePropertyEditor.getInstance().getString(PASSWORD_HASH_COST_PROPERTY_KEY);

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

	/**
	 * This constructor prevents anyone from creating an instance of this class.
	 */
	private PasswordAuthentication() {

	}
	//endregion

	//region public static methods
	/**
	 * This method creates a password token for a given password.
	 * @param password the password to tokenize
	 * @return the created token
	 * @throws IllegalStateException thrown, if the hash can not be computed
	 */
	@NotNull
	public static String getPasswordToken(@NotNull String password) throws IllegalStateException {
		return getDefaultService().getPasswordToken(password);
	}

	/**
	 * This method checks, whether a given password will match a given hash.
	 * @param user the user, to whom the password belongs
	 * @param password the password to validate
	 * @param token the token to compare the password with
	 * @return true, if the password produces the given hash
	 * @throws IllegalStateException thrown, if the hash can not be computed
	 */
	public static boolean matches(@NotNull User user, @NotNull String password, @NotNull String token) throws IllegalStateException {
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

	//region private static methods

	/**
	 * This method returns the default password service.
	 * @return the default password service, or the first if the default is missing
	 * @throws IllegalStateException thrown, if no password services are registered
	 */
	@NotNull
	private static PasswordService getDefaultService() throws IllegalStateException {
		if (passwordServices.isEmpty()) {
			throw new IllegalStateException("no password services");
		}

		return passwordServices.getOrDefault(DEFAULT_SERVICE, passwordServices.entrySet().iterator().next().getValue());
	}
	//endregion
}
