package de.flare.properties;

import com.sun.istack.internal.NotNull;
import de.flare.core.FlareBackend;

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
	String _getString(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	String _getStringOrDefault(@NotNull String propertyKey, String defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	int _getInt(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	int _getIntOrDefault(@NotNull String propertyKey, int defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	long _getLong(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	long _getLongOrDefault(@NotNull String propertyKey, long defaultValue);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or false
	 */
	boolean _getBool(@NotNull String propertyKey);

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or the default value
	 */
	boolean _getBoolOrDefault(@NotNull String propertyKey, boolean defaultValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor _setString(@NotNull String propertyKey, String propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the int value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor _setInt(@NotNull String propertyKey, int propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the long value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor _setLong(@NotNull String propertyKey, long propertyValue);

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the boolean value to set
	 * @return this property editor
	 */
	@NotNull
	PropertyEditor _setBool(@NotNull String propertyKey, boolean propertyValue);

	//region interface forwarding
	/**
	 * This method tries to access a specified property, returning its value.
	 * @param propertyKey the property to access
	 * @return the value of the property, or an empty string
	 */
	static String getString(@NotNull String propertyKey) {
		return FlareBackend.getPropertyEditor()._getString(propertyKey);
	}

	/**
	 * This method tries to access a specified property, returning its value.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	static String getStringOrDefault(@NotNull String propertyKey, String defaultValue) {
		return FlareBackend.getPropertyEditor()._getStringOrDefault(propertyKey, defaultValue);
	}

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	@NotNull
	static int getInt(@NotNull String propertyKey) {
		return FlareBackend.getPropertyEditor()._getInt(propertyKey);
	}

	/**
	 * This method tries to access a specified property, returning its value as int.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	static int getIntOrDefault(@NotNull String propertyKey, int defaultValue) {
		return FlareBackend.getPropertyEditor()._getIntOrDefault(propertyKey, defaultValue);
	}

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @return the value of the property, or 0
	 */
	static long getLong(@NotNull String propertyKey) {
		return FlareBackend.getPropertyEditor()._getLong(propertyKey);
	}

	/**
	 * This method tries to access a specified property, returning its value as long.
	 * @param propertyKey the property to access
	 * @param defaultValue the default value, if something goes wrong
	 * @return the value of the property, or the default value
	 */
	static long getLongOrDefault(@NotNull String propertyKey, long defaultValue) {
		return FlareBackend.getPropertyEditor()._getLongOrDefault(propertyKey, defaultValue);
	}

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or false
	 */
	static boolean getBool(@NotNull String propertyKey) {
		return FlareBackend.getPropertyEditor()._getBool(propertyKey);
	}

	/**
	 * This method tries to access a specified property, returning its value as boolean.
	 * @param propertyKey the property to access
	 * @return the value of the property, or the default value
	 */
	static boolean getBoolOrDefault(@NotNull String propertyKey, boolean defaultValue) {
		return FlareBackend.getPropertyEditor()._getBoolOrDefault(propertyKey, defaultValue);
	}

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the value to set
	 * @return this property editor
	 */
	@NotNull
	static PropertyEditor setString(@NotNull String propertyKey, String propertyValue) {
		return FlareBackend.getPropertyEditor()._setString(propertyKey, propertyValue);
	}

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the int value to set
	 * @return this property editor
	 */
	@NotNull
	static PropertyEditor setInt(@NotNull String propertyKey, int propertyValue) {
		return FlareBackend.getPropertyEditor()._setInt(propertyKey, propertyValue);
	}

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the long value to set
	 * @return this property editor
	 */
	@NotNull
	static PropertyEditor setLong(@NotNull String propertyKey, long propertyValue) {
		return FlareBackend.getPropertyEditor()._setLong(propertyKey, propertyValue);
	}

	/**
	 * This method sets the value for a given property.
	 * @param propertyKey the property to modify
	 * @param propertyValue the boolean value to set
	 * @return this property editor
	 */
	@NotNull
	static PropertyEditor setBool(@NotNull String propertyKey, boolean propertyValue) {
		return FlareBackend.getPropertyEditor()._setBool(propertyKey, propertyValue);
	}
	//endregion
}
