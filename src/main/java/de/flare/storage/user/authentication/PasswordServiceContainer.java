package de.flare.storage.user.authentication;

import com.sun.istack.internal.NotNull;
import de.flare.storage.user.User;

/**
 * This interface contains a set of password services.
 */
public interface PasswordServiceContainer {

	/**
	 * This method creates a password token for a given password.
	 * @param password the password to tokenize
	 * @return the created token
	 * @throws IllegalStateException thrown, if the hash can not be computed
	 */
	@NotNull
	String getPasswordToken(@NotNull String password) throws IllegalStateException;

	/**
	 * This method checks, whether a given password will match a given hash.
	 * @param user the user, to whom the password belongs
	 * @param password the password to validate
	 * @param token the token to compare the password with
	 * @return true, if the password produces the given hash
	 * @throws IllegalStateException thrown, if the hash can not be computed
	 */
	boolean matches(@NotNull User user, @NotNull String password, @NotNull String token) throws IllegalStateException;

}
