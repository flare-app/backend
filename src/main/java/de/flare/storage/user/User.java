package de.flare.storage.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.user.authorization.UserAuthorization;

import java.util.Collection;

/**
 * This interface contains information about registered users.
 */
public interface User {

	/**
	 * This method returns the password hash for this user.
	 * @return the hashed password
	 */
	@NotNull
	String getPasswordHash();

	/**
	 * This method returns the password salt, which was used for the hashing.
	 * @return the salt value
	 */
	@NotNull
	byte[] getPasswordSalt();

	/**
	 * This method sets the password for this user.
	 * @param clearPassword the clear text password to use
	 * @return this user
	 */
	@NotNull
	User setPassword(String clearPassword);

	/**
	 * This method returns a list of all authentications for this user.
	 * @return a list of all user authentications for this user
	 */
	@NotNull
	Collection<UserAuthorization> getAuthentications();
}
