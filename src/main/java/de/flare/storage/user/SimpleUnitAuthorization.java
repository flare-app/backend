package de.flare.storage.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.AbstractDatabaseEntry;
import de.flare.storage.unit.SimpleUnit;
import de.flare.storage.unit.Unit;
import de.flare.storage.user.authorization.UserAuthorization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
@Entity
@Table(name = "UNIT_AUTHORIZATION")
public class SimpleUnitAuthorization extends AbstractDatabaseEntry implements UnitAuthorization {

	//region private members
	@OneToMany
	@JoinColumn(name = "AUTHORIZATION_ID")
	private Collection<UserAuthorization> authorizations = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "UNIT_ID")
	private Unit unit = new SimpleUnit();
	//endregion

	//region ctor
	//endregion

	//region unit authorization
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<UserAuthorization> getAuthorizations() {
		return authorizations;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Unit getUnit() {
		return unit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UnitAuthorization setUnit(@NotNull Unit unit) {
		this.unit = unit;
		return this;
	}

	//endregion
}
