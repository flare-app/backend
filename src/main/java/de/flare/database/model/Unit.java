package de.flare.database.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UNIT")
public class Unit extends DatabaseEntity {

	//region props
	@Column
	private String name = "";

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Location location = new Location();

	public String getName() {
		return name;
	}

	public Unit setName(String name) {
		this.name = name;
		return this;
	}

	public Location getLocation() {
		return location;
	}

	public Unit setLocation(Location location) {
		this.location = location;
		return this;
	}
	//endregion
}
