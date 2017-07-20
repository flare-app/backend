package de.flare.storage.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.user.authentication.UserAuthentication;

import java.util.Collection;

/**
 * This interface contains information about registered users.
 */
public interface User {

	/**
	 * This method returns a list of all authentications for this user.
	 * @return a list of all user authentications for this user
	 */
	@NotNull
	Collection<UserAuthentication> getAuthentications();
}
