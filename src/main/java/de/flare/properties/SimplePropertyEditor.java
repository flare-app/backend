package de.flare.properties;

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
	public String getProperty(String propertyKey) {
		return properties.getOrDefault(propertyKey, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPropertyAsInt(String propertyKey) {
		return getPropertyAsInt(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPropertyAsInt(String propertyKey, int defaultValue) {
		return getProperty(propertyKey, Integer::parseInt, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getPropertyAsLong(String propertyKey) {
		return getPropertyAsLong(propertyKey, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getPropertyAsLong(String propertyKey, long defaultValue) {
		return getProperty(propertyKey, Long::parseLong, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getPropertyAsBool(String propertyKey) {
		return getPropertyAsBool(propertyKey, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getPropertyAsBool(String propertyKey, boolean defaultValue) {
		return getProperty(propertyKey, Boolean::parseBoolean, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyEditor setProperty(String propertyKey, String propertyValue) {
		properties.put(propertyKey, propertyValue);

		return this;
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
	 * @param propertyKey the property key
	 * @param converter the converter method
	 * @param defaultValue a default value, if something goes wrong
	 * @param <Type> the type to cast the property value to
	 * @return the converted property value
	 */
	private <Type> Type getProperty(String propertyKey, Function<String, Type> converter, Type defaultValue) {
		String value = getProperty(propertyKey);

		if (value.isEmpty()) {
			return defaultValue;
		}

		try {
			return converter.apply(value);
		} catch (Exception e) {
			// suppress exception
			return defaultValue;
		}
	}
	//endregion
}
