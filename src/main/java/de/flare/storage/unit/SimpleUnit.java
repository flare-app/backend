package de.flare.storage.unit;

import com.sun.istack.internal.NotNull;
import de.flare.storage.AbstractDatabaseEntry;
import de.flare.storage.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
@Entity
@Table(name = "UNIT")
public class SimpleUnit extends AbstractDatabaseEntry implements Unit {

	//region private members
	@Column(name = "NAME")
	private String name = "";

	@OneToMany
	@JoinColumn(name = "USER_ID")
	private Collection<User> users = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "LOCATION_ID")
	private UnitLocation location = new SimpleUnitLocation();
	//endregion

	//region ctor
	//endregion

	//region unit
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Unit setName(@NotNull String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<User> getUsers() {
		return users;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UnitLocation getLocation() {
		return location;
	}
	//endregion
}
