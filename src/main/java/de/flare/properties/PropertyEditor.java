package de.flare.properties;

import com.sun.istack.internal.NotNull;

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
	String getString(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	String getStringOrDefault(@NotNull String propertyKey, String defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	@NotNull
	int getInt(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	int getIntOrDefault(@NotNull String propertyKey, int defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	@NotNull
	long getLong(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	long getLongOrDefault(@NotNull String propertyKey, long defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or false
	 */
	@NotNull
	boolean getBool(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or the default value
	 */
	boolean getBoolOrDefault(@NotNull String propertyKey, boolean defaultValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor setString(@NotNull String propertyKey, String propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the int value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor setInt(@NotNull String propertyKey, int propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the long value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor setLong(@NotNull String propertyKey, long propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the boolean value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor setBool(@NotNull String propertyKey, boolean propertyValue);
}
