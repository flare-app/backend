package de.flare.storage.model.gps;

import com.sun.istack.internal.NotNull;
import de.flare.storage.model.DatabaseEntry;

/**
 * This interface contains gps coordinates.
 */
public interface GPSLocation extends DatabaseEntry {

	/**
	 * This method returns the GPS longitude.
	 * @return the longitude of this location
	 */
	float getLongitude();

	/**
	 * This method sets the GPS longitude.
	 * @param longitude the longitude to set
	 * @return this GPS location
	 */
	@NotNull
	GPSLocation setLongitude(float longitude);

	/**
	 * This method returns the GPS latitude.
	 * @return the latitude of this location
	 */
	float getLatitude();

	/**
	 * This method sets the GPS latitude.
	 * @param latitude the latitude to set
	 * @return this GPS location
	 */
	@NotNull
	GPSLocation setLatitude(float latitude);
}
