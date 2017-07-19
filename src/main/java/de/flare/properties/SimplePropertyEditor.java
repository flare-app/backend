package de.flare.properties;

import com.sun.istack.internal.NotNull;
import de.flare.logging.SimpleLogger;

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
public final class SimplePropertyEditor implements PropertyEditor {

	//region singleton
	private static PropertyEditor instance;

	/**
	 * This method returns the singleton instance of thic class.
	 * @return the singleton instance of this class
	 */
	public static PropertyEditor getInstance() {
		if (instance == null) {
			instance = new SimplePropertyEditor();
		}

		return instance;
	}

	/**
	 * This constructor loads the properties from the properties-file.
	 */
	private SimplePropertyEditor() {
		loadProperties();
	}
	//endregion

	//region private members
	private Map<String, String> properties = new HashMap<>();
	//endregion

	//region property editor
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString(@NotNull String propertyKey) {
		return getStringOrDefault(propertyKey, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStringOrDefault(@NotNull String propertyKey, String defaultValue) {
		return properties.getOrDefault(propertyKey, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public int getInt(@NotNull String propertyKey) {
		return getIntOrDefault(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getIntOrDefault(@NotNull String propertyKey, int defaultValue) {
		return get(propertyKey, Integer::parseInt, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public long getLong(@NotNull String propertyKey) {
		return getLongOrDefault(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getLongOrDefault(@NotNull String propertyKey, long defaultValue) {
		return get(propertyKey, Long::parseLong, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public boolean getBool(@NotNull String propertyKey) {
		return getBoolOrDefault(propertyKey, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getBoolOrDefault(@NotNull String propertyKey, boolean defaultValue) {
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
	public PropertyEditor setString(@NotNull String propertyKey, String propertyValue) {
		return set(propertyKey, propertyValue, (String value) -> value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor setInt(@NotNull String propertyKey, int propertyValue) {
		return set(propertyKey, propertyValue, (Integer value) -> Integer.toString(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor setLong(@NotNull String propertyKey, long propertyValue) {
		return set(propertyKey, propertyValue, (Long value) -> Long.toString(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public PropertyEditor setBool(@NotNull String propertyKey, boolean propertyValue) {
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
				SimpleLogger.error(getClass(), "cannot read from properties file", e);
			}
		} else {
			SimpleLogger.info(getClass(), "cannot find properties file");
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
		String value = getStringOrDefault(propertyKey, null);

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
			SimpleLogger.error(getClass(), "cannot set property '" + propertyKey + "'", e);
		}

		return this;
	}
	//endregion
}
