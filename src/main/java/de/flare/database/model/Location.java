package de.flare.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location extends DatabaseEntity {

	//region props
	@Column
	private String city = "";

	@Column
	private float latitude = .0f;

	@Column
	private float longitude = 0.f;

	public String getCity() {
		return city;
	}

	public Location setCity(String city) {
		this.city = city;
		return this;
	}

	public float getLatitude() {
		return latitude;
	}

	public Location setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	public float getLongitude() {
		return longitude;
	}

	public Location setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}
	//endregion
}
