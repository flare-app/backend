package de.flare.storage.user.authorization;


import com.sun.istack.internal.NotNull;
import de.flare.storage.user.User;

/**
 * This interface offers methods to manage authorization of users.
 */
public interface AuthorizationGroup {

	/**
	 * This method adds a collection of authentications to this group.
	 * @param authentications a list of user authentications
	 * @return this authorization group
	 */
	@NotNull
	AuthorizationGroup add(@NotNull UserAuthorization... authentications);

	/**
	 * This method adds a list of special users to this authorization group.
	 * These users will get access, no matter what his authorization is.
	 * @param users a list of users
	 * @return this user authorization group
	 */
	@NotNull
	AuthorizationGroup add(@NotNull User... users);

	/**
	 * This method removes a collection of authentications form the group.
	 * @param authentications a list of user authentications
	 * @return this authorization group
	 */
	@NotNull
	AuthorizationGroup remove(@NotNull UserAuthorization... authentications);

	/**
	 * This method removes a list of users from this authorization group.
	 * @param users a list of users to remove
	 * @return this authorization group
	 */
	@NotNull
	AuthorizationGroup remove(@NotNull User... users);

	/**
	 * This method overrides the user group with the given authentications.
	 * @param authentications a list of user authentications
	 * @return this authorization group
	 */
	@NotNull
	AuthorizationGroup set(@NotNull UserAuthorization... authentications);

	/**
	 * This method overrides the existing users, who have access to this group.
	 * @param users the users to set
	 * @return this user authorization group
	 */
	@NotNull
	AuthorizationGroup set(@NotNull User... users);

	/**
	 * This method determines, whether a given user may access this group.
	 * @param user the user to check
	 * @return true, if the user has access
	 */
	boolean canAccess(@NotNull User user);
}
