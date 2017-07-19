package de.flare.storage.user.authentication;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
public class SimpleAuthenticationGroup implements AuthenticationGroup {

	//region private members
	private Collection<UserAuthentication> authentications = new ArrayList<>();
	//endregion

	//region ctor
	//endregion

	//region public const
	public static final AuthenticationGroup NOONE = new SimpleAuthenticationGroup();

	public static final AuthenticationGroup PUBLIC = new SimpleAuthenticationGroup()
			.add(UserAuthentication.values())
			.remove(UserAuthentication.NONE);

	public static final AuthenticationGroup AUTHENTICATED = new SimpleAuthenticationGroup()
			.add(
					UserAuthentication.APP,
					UserAuthentication.DASHBOARD,
					UserAuthentication.LOCAL_ADMINISTRATOR,
					UserAuthentication.FLARE_ADMINISTRATOR
			);
	//endregion

	//region authentication group
	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthenticationGroup add(@NotNull UserAuthentication... authentications) {
		for (UserAuthentication authentication : authentications) {
			if (contains(authentication)) {
				continue;
			}

			this.authentications.add(authentication);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public AuthenticationGroup remove(@NotNull UserAuthentication... authentications) {
		for (UserAuthentication authentication : authentications) {
			this.authentications.remove(authentication);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(@NotNull UserAuthentication authentication) {
		return authentications.contains(authentication);
	}

	/**
	 * {@inheritDoc}
	 */
	@NotNull
	@Override
	public AuthenticationGroup set(@NotNull UserAuthentication... authentications) {
		this.authentications.clear();
		add(authentications);
		return this;
	}
	//endregion
}
