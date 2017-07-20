package de.flare.storage;

/**
 * This interface contains data for generic database entries.
 */
public interface DatabaseEntry {

	/**
	 * This method returns the unique identifier for this database entry.
	 * @return the unique identifier of this database entry
	 */
	long getId();

	/**
	 * This method returns the UTC timestamp, when this entry was created.
	 * @return the UTC timestamp of creation
	 */
	long getCreationTimestamp();
}
