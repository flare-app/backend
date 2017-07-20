package de.flare.storage;

import javax.persistence.*;

/**
 * {@inheritDoc}
 * This is the abstract basic implementation.
 */
@MappedSuperclass
public class AbstractDatabaseEntry implements DatabaseEntry {

	//region private members
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "CREATION_TIMESTAMP")
	private long creationTimestamp = System.currentTimeMillis();
	//endregion

	//region database entry
	/**
	 * {@inheritDoc}
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	//endregion
}
