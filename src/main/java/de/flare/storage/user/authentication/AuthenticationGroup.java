package de.flare.storage.user.authentication;


import com.sun.istack.internal.NotNull;
import de.flare.storage.user.User;

/**
 * This interface offers methods to manage authentication of users.
 */
public interface AuthenticationGroup {

	/**
	 * This method adds a collection of authentications to this group.
	 * @param authentications a list of user authentications
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup add(@NotNull UserAuthentication... authentications);

	/**
	 * This method adds a list of special users to this authentication group.
	 * These users will get access, no matter what his authentication is.
	 * @param users a list of users
	 * @return this user authentication group
	 */
	@NotNull
	AuthenticationGroup add(@NotNull User... users);

	/**
	 * This method removes a collection of authentications form the group.
	 * @param authentications a list of user authentications
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup remove(@NotNull UserAuthentication... authentications);

	/**
	 * This method removes a list of users from this authentication group.
	 * @param users a list of users to remove
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup remove(@NotNull User... users);

	/**
	 * This method overrides the user group with the given authentications.
	 * @param authentications a list of user authentications
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup set(@NotNull UserAuthentication... authentications);

	/**
	 * This method overrides the existing users, who have access to this group.
	 * @param users the users to set
	 * @return this user authentication group
	 */
	@NotNull
	AuthenticationGroup set(@NotNull User... users);

	/**
	 * This method determines, whether a given user may access this group.
	 * @param user the user to check
	 * @return true, if the user has access
	 */
	boolean canAccess(@NotNull User user);
}
