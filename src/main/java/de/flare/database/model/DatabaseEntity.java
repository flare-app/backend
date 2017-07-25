package de.flare.database.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DatabaseEntity {

	//region props
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private long creationTimestamp = System.currentTimeMillis();

	public long getId() {
		return id;
	}

	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	//endregion
}
