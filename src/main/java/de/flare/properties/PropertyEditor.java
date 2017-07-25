package de.flare.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertyEditor {

	//region private static members
	private static Map<String, String> properties = new HashMap<>();
	//endregion

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(PropertyEditor.class);
	private static final String PROPERTIES_FILE = "flare-backend.properties";
	//endregion

	//region ctor
	private PropertyEditor() { }

	static {
		loadProperties();
	}
	//endregion

	//region public methods
	public static String getString(String name) {
		return properties.getOrDefault(name, "");
	}

	public static void setString(String name, String value) {
		properties.put(name, value);
	}

	public static int getInt(String name) {
		try {
			return Integer.parseInt(properties.getOrDefault(name, "0"));
		} catch (Exception e) {
			// suppress e
			return 0;
		}
	}

	public static void setInt(String name, int value) {
		properties.put(name, Integer.toString(value));
	}
	//endregion

	//region private static methods
	private static void loadProperties() {
		Properties properties = new Properties();

		InputStream inputStream = PropertyEditor.class.getClassLoader().getResourceAsStream(PropertyEditor.PROPERTIES_FILE);

		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				logger.error("cannot read from properties file", e);
			}
		} else {
			logger.error("cannot find properties file");
		}

		for (Object key : properties.keySet()) {
			PropertyEditor.properties.put(key.toString(), properties.getProperty(key.toString()));
		}
	}
	//endregion
}
