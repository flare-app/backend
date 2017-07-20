package de.flare.storage.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.DatabaseEntry;

import java.util.Collection;
import java.util.UUID;

/**
 * This interface contains information about registered users.
 */
public interface User extends DatabaseEntry {

	//region static methods
	static String generateAuthenticationToken(@NotNull User user) {
		return String.format("%s@%d", UUID.randomUUID().toString(), user.getCreationTimestamp());
	}
	//endregion

	/**
	 * This method returns the password token.
	 * @return the password token
	 */
	@NotNull
	String getPasswordToken();

	/**
	 * This method updates the password token, based on the new password.
	 * @param password the password to use
	 * @return this user
	 * @throws IllegalStateException thrown, if the new password taken can not be computed
	 */
	@NotNull
	User setPassword(@NotNull String password) throws IllegalStateException;

	/**
	 * This method returns the e-mail address of the user.
	 * @return the e-mail address
	 */
	@NotNull
	String getEMail();

	/**
	 * This method sets the e-mail address of this user.
	 * @param email the e-mail address to set
	 * @return this user
	 */
	@NotNull
	User setEMail(@NotNull String email);

	/**
	 * This method returns the authorization token for this user.
	 * @return the authentication token
	 */
	@NotNull
	String getAuthenticationToken();

	/**
	 * This method returns the first name of the user.
	 * @return the users first name
	 */
	@NotNull
	String getFirstName();

	/**
	 * This method sets the users first name
	 * @param firstName the name to set
	 * @return this user
	 */
	@NotNull
	User setFirstName(@NotNull String firstName);

	/**
	 * This method returns the users last name.
	 * @return the users last name
	 */
	@NotNull
	String getLastName();

	/**
	 * This method sets the users last name.
	 * @param lastName the users last name
	 * @return this user
	 */
	@NotNull
	User setLastName(@NotNull String lastName);

	/**
	 * This method returns a list of unit authorizations of this user.
	 * @return a list of unit authorizations
	 */
	@NotNull
	Collection<UnitAuthorization> getUnitAuthorizations();
}
