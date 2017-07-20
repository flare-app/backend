package de.flare.storage.unit;

import com.sun.istack.internal.NotNull;
import de.flare.storage.DatabaseEntry;
import de.flare.storage.gps.GPSLocation;

/**
 * This interface contains data for unit locations.
 */
public interface UnitLocation extends DatabaseEntry {

	/**
	 * This method returns the country of this unit location.
	 * @return the country
	 */
	@NotNull
	String getCountry();

	/**
	 * This method sets the country of this unit location.
	 * @param county the country to set
	 * @return this unit location
	 */
	@NotNull
	UnitLocation setCountry(@NotNull String county);

	/**
	 * This method returns the city of this unit location.
	 * @return the city
	 */
	@NotNull
	String getCity();

	/**
	 * This method sets the city of this unit location.
	 * @param city the city to set
	 * @return this unit location
	 */
	@NotNull
	UnitLocation setCity(@NotNull String city);

	/**
	 * This method returns the GPS location of this unit location.
	 * @return the GPS location of this unit location
	 */
	@NotNull
	GPSLocation getGPSLocation();
}
