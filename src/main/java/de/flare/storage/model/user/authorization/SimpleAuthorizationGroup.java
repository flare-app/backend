package de.flare.storage.user.authorization;

import com.sun.istack.internal.NotNull;
import de.flare.storage.user.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
public class SimpleAuthorizationGroup implements AuthorizationGroup {

	//region private members
	private Collection<UserAuthorization> authentications = new ArrayList<>();
	private Collection<User> users = new ArrayList<>();
	//endregion

	//region ctor
	//endregion

	//region public const
	public static final AuthorizationGroup NOONE = new SimpleAuthorizationGroup();

	public static final AuthorizationGroup PUBLIC = new SimpleAuthorizationGroup()
			.add(UserAuthorization.values())
			.remove(UserAuthorization.NONE);

	public static final AuthorizationGroup AUTHENTICATED = new SimpleAuthorizationGroup()
			.add(
					UserAuthorization.APP,
					UserAuthorization.DASHBOARD,
					UserAuthorization.LOCAL_ADMINISTRATOR,
					UserAuthorization.FLARE_ADMINISTRATOR
			);
	//endregion

	//region authorization group
	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthorizationGroup add(@NotNull UserAuthorization... authentications) {
		for (UserAuthorization authentication : authentications) {
			if (this.authentications.contains(authentication)) {
				continue;
			}

			this.authentications.add(authentication);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthorizationGroup add(@NotNull User... users) {
		for (User user : users) {
			if (this.users.contains(user)) {
				continue;
			}

			this.users.add(user);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public AuthorizationGroup remove(@NotNull UserAuthorization... authentications) {
		for (UserAuthorization authentication : authentications) {
			this.authentications.remove(authentication);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public AuthorizationGroup remove(@NotNull User... users) {
		for (User user : users) {
			this.users.remove(user);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthorizationGroup set(@NotNull UserAuthorization... authentications) {
		this.authentications.clear();
		add(authentications);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthorizationGroup set(@NotNull User... users) {
		this.users.clear();
		add(users);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canAccess(@NotNull User user) {
		// TODO: implement me
		return false;
		//return users.contains(user) || !Collections.disjoint(authentications, user.getAuthentications());
	}

	//endregion
}
