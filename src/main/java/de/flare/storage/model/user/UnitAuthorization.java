package de.flare.storage.model.user;

import com.sun.istack.internal.NotNull;
import de.flare.storage.model.DatabaseEntry;
import de.flare.storage.model.unit.Unit;
import de.flare.storage.model.user.authorization.UserAuthorization;

import java.util.Collection;

/**
 * This interface contains data for the unit authorization.
 */
public interface UnitAuthorization extends DatabaseEntry {

	/**
	 * This method returns a list of all user authorizations.
	 * @return the list of all authorization, this user has in this unit
	 */
	@NotNull
	Collection<UserAuthorization> getAuthorizations();

	/**
	 * This method returns the unit.
	 * @return the unit
	 */
	@NotNull
	Unit getUnit();

	/**
	 * This method sets the unit.
	 * @param unit the unit to set
	 * @return this unit authorization
	 */
	@NotNull
	UnitAuthorization setUnit(@NotNull Unit unit);
}
