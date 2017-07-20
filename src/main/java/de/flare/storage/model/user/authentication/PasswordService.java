package de.flare.storage.user.authentication;

import com.sun.istack.internal.NotNull;

/**
 * This interface offers methods to validate a user login.
 */
public interface PasswordService {

	String TOKEN_SEPARATOR = "@";

	/**
	 * This method returns a unique id for the used hashing algorithm.
	 * @return the id of the algorithm used
	 */
	@NotNull
	String getServiceId();

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
	 * @param password the password to validate
	 * @param token the token to compare the password with
	 * @return true, if the password produces the given hash
	 * @throws IllegalStateException thrown, if the hash can not be computed
	 */
	boolean matches(@NotNull String password, @NotNull String token) throws IllegalStateException;
}
