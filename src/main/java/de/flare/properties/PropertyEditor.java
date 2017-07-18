package de.flare.properties;

/**
 * This interface offers methods to access and modify flare properties.
 */
public interface PropertyEditor {

	/**
	 * The properties file for the flare backend.
	 */
	String PROPERTIES_FILE = "flare-backend.properties";

	/**
	 * This method tries to access a specified property, returning its value.
	 * @param propertyKey the property to access
	 * @return the value of the property, or an empty string
	 */
	String getProperty(String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	int getPropertyAsInt(String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	int getPropertyAsInt(String propertyKey, int defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	long getPropertyAsLong(String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	long getPropertyAsLong(String propertyKey, long defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or false
	 */
	boolean getPropertyAsBool(String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or the default value
	 */
	boolean getPropertyAsBool(String propertyKey, boolean defaultValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the value to set
	 * @return this property editor
	 */
	PropertyEditor setProperty(String propertyKey, String propertyValue);
}
