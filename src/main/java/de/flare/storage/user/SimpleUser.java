package de.flare.storage.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.AbstractDatabaseEntry;
import de.flare.storage.user.authentication.SimplePasswordServiceContainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a simple user implementation.
 */
@Entity
@Table(name = "USER")
public class SimpleUser extends AbstractDatabaseEntry implements User {

	//region private members
	@Column(name = "FIRST_NAME")
	private String firstName = "";

	@Column(name = "LAST_NAME")
	private String lastName = "";

	@Column(name = "E_MAIL")
	private String eMail = "";

	@Column(name = "PASSWORD_TOKEN")
	private String passwordToken = "";

	@Column(name = "AUTHENTICATION_TOKEN")
	private String authenticationToken = User.generateAuthenticationToken(this);

	@OneToMany
	@JoinColumn(name = "UNIT_AUTHORIZATION_ID")
	private Collection<UnitAuthorization> unitAuthorizations = new ArrayList<>();
	//endregion

	//region ctor
	//endregion

	//region user
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getPasswordToken() {
		return passwordToken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public User setPassword(@NotNull String password) throws IllegalStateException {
		passwordToken = SimplePasswordServiceContainer.getPasswordToken(password);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getEMail() {
		return eMail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public User setEMail(@NotNull String email) {
		eMail = email;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public User setFirstName(@NotNull String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public User setLastName(@NotNull String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<UnitAuthorization> getUnitAuthorizations() {
		return unitAuthorizations;
	}
	//endregion
}
