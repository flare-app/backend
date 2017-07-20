package de.flare.storage.model.unit;

import com.sun.istack.internal.NotNull;
import de.flare.storage.model.DatabaseEntry;
import de.flare.storage.model.user.User;

import java.util.Collection;

/**
 * This interface contains data for a specific unit.
 */
public interface Unit extends DatabaseEntry {

	/**
	 * This method returns the name of this unit.
	 * @return the name of this unit
	 */
	@NotNull
	String getName();

	/**
	 * This method sets the name of this unit.
	 * @param name the name to set
	 * @return this unit
	 */
	@NotNull
	Unit setName(@NotNull String name);

	/**
	 * This method returns the list of users of this unit.
	 * @return the list of all users of this unit
	 */
	@NotNull
	Collection<User> getUsers();

	/**
	 * This method returns the location of this unit.
	 * @return the location of this unit
	 */
	@NotNull
	UnitLocation getLocation();
}
