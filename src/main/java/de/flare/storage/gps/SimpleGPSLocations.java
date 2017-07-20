package de.flare.storage.gps;

import de.flare.storage.AbstractDatabaseEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
@Entity
@Table(name = "GPS_LOCATION")
public class SimpleGPSLocations extends AbstractDatabaseEntry implements GPSLocation {

	//region private members
	@Column(name = "LONGITUDE")
	private float longitude = .0f;

	@Column(name = "LATITUDE")
	private float latitude = .0f;
	//endregion

	//region ctor
	//endregion

	//region gps location

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getLongitude() {
		return longitude;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GPSLocation setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getLatitude() {
		return latitude;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GPSLocation setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	//endregion
}
