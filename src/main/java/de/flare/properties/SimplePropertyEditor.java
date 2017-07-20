package de.flare.properties;

import com.sun.istack.internal.NotNull;
import de.flare.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

/**
 * {@inheritDoc}
 * This is a simple implementation.
 */
public class SimplePropertyEditor implements PropertyEditor {

	//region private members
	private Map<String, String> properties = new HashMap<>();
	//endregion

	//region ctor
	/**
	 * This constructor loads all properties from the properties-file.
	 */
	public SimplePropertyEditor() {
		loadProperties();
	}
	//endregion

	//region property editor
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String _getString(@NotNull String propertyKey) {
		return _getStringOrDefault(propertyKey, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String _getStringOrDefault(@NotNull String propertyKey, String defaultValue) {
		return properties.getOrDefault(propertyKey, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int _getInt(@NotNull String propertyKey) {
		return _getIntOrDefault(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int _getIntOrDefault(@NotNull String propertyKey, int defaultValue) {
		return get(propertyKey, Integer::parseInt, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long _getLong(@NotNull String propertyKey) {
		return _getLongOrDefault(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long _getLongOrDefault(@NotNull String propertyKey, long defaultValue) {
		return get(propertyKey, Long::parseLong, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean _getBool(@NotNull String propertyKey) {
		return _getBoolOrDefault(propertyKey, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean _getBoolOrDefault(@NotNull String propertyKey, boolean defaultValue) {
		return get(propertyKey, (String value) -> {
			if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("true")) {
				return Boolean.parseBoolean(value);
			}

			return defaultValue;
		}, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor _setString(@NotNull String propertyKey, String propertyValue) {
		return set(propertyKey, propertyValue, (String value) -> value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor _setInt(@NotNull String propertyKey, int propertyValue) {
		return set(propertyKey, propertyValue, (Integer value) -> Integer.toString(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor _setLong(@NotNull String propertyKey, long propertyValue) {
		return set(propertyKey, propertyValue, (Long value) -> Long.toString(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor _setBool(@NotNull String propertyKey, boolean propertyValue) {
		return set(propertyKey, propertyValue, (Boolean value) -> Boolean.toString(value));
	}

	//endregion

	//region private methods
	/**
	 * This method loads all properties defined in the properties-file.
	 */
	private void loadProperties() {
		Properties properties = new Properties();

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PropertyEditor.PROPERTIES_FILE);

		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				Logger.error(getClass(), "cannot read from properties file", e);
			}
		} else {
			Logger.info(getClass(), "cannot find properties file");
		}

		for (Object key : properties.keySet()) {
			this.properties.put(key.toString(), properties.getProperty(key.toString()));
		}
	}

	/**
	 * This method returns a specified property, converted to a specified type.
	 * @param <Type> the type to cast the property value to
	 * @param propertyKey the property key
	 * @param converter the converter method
	 * @param defaultValue a default value, if something goes wrong
	 * @return the converted property value
	 */
	private <Type> Type get(String propertyKey, Function<String, Type> converter, Type defaultValue) {
		String value = _getStringOrDefault(propertyKey, null);

		try {
			return converter.apply(value);
		} catch (Exception e) {
			// suppress exception
			return defaultValue;
		}
	}

	/**
	 * This method sets the value for a specified property.
	 * @param propertyKey the property to set
	 * @param propertyValue the value to set
	 * @param converter the converter, to convert the value to a string
	 * @param <Type> the value type
	 * @return this property editor
	 */
	@NotNull
	private <Type> PropertyEditor set(@NotNull String propertyKey, Type propertyValue, Function<Type, String> converter) {
		try {
			properties.put(propertyKey, converter.apply(propertyValue));
		} catch (Exception e) {
			Logger.error(getClass(), "cannot set property '" + propertyKey + "'", e);
		}

		return this;
	}
	//endregion
}
