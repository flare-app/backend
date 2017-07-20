package de.flare.storage.unit;

import com.sun.istack.internal.NotNull;
import de.flare.storage.AbstractDatabaseEntry;
import de.flare.storage.gps.GPSLocation;
import de.flare.storage.gps.SimpleGPSLocations;

import javax.persistence.*;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
@Entity
@Table(name = "UNIT_LOCATION")
public class SimpleUnitLocation extends AbstractDatabaseEntry implements UnitLocation {

	//region private members
	@Column(name = "COUNTRY")
	private String country = "";

	@Column(name = "CITY")
	private String city = "";

	@OneToOne
	@JoinColumn(name = "GPS_LOCATION_ID")
	private GPSLocation gpsLocation = new SimpleGPSLocations();
	//endregion

	//region ctor
	//endregion

	//region unit location
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getCountry() {
		return country;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UnitLocation setCountry(@NotNull String county) {
		this.country = county;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getCity() {
		return city;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UnitLocation setCity(@NotNull String city) {
		this.city = city;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public GPSLocation getGPSLocation() {
		return gpsLocation;
	}
	//endregion
}
