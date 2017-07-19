package de.flare.storage.user.authentication;


import com.sun.istack.internal.NotNull;

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
	 * This method removes a collection of authentications form the group.
	 * @param authentications a list of user authentications
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup remove(@NotNull UserAuthentication... authentications);

	/**
	 * This method checks, whether a given user authentication is contained in this group.
	 * @param authentication the authentication to check
	 * @return true, if the authentication is contained
	 */
	boolean contains(@NotNull UserAuthentication authentication);

	/**
	 * This method overrides the user group with the given authentications.
	 * @param authentications a list of user authentications
	 * @return this authentication group
	 */
	@NotNull
	AuthenticationGroup set(@NotNull UserAuthentication... authentications);
}
