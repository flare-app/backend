package test.de.flare.properties;

import de.flare.properties.PropertyEditor;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class PropertyEditorTestParent {

	//region string
	public abstract void testSetString();

	protected void testSetString(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		String propertyValue = UUID.randomUUID().toString();

		implementation.setString(propertyKey, propertyValue);

		String property = implementation.getString(propertyKey);

		assertNotNull(property);
		assertEquals(propertyValue, property);
	}

	public abstract void testGetStringOrDefault();

	protected void testGetStringOrDefault(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		String propertyValue = UUID.randomUUID().toString();

		implementation.setString(propertyKey, propertyValue);

		assertEquals(propertyValue, implementation.getStringOrDefault(propertyKey, ""));
	}

	public abstract void testGetStringOrDefault_Default();

	protected void testGetStringOrDefault_Default(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		String defaultValue = UUID.randomUUID().toString();

		assertEquals(defaultValue, implementation.getStringOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region int
	public abstract void testSetInt();

	protected void testSetInt(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		int propertyValue = Integer.MAX_VALUE;

		implementation.setInt(propertyKey, propertyValue);

		int result = implementation.getInt(propertyKey);

		assertEquals(propertyValue, result);
	}

	public abstract void testGetIntOrDefault();

	protected void testGetIntOrDefault(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		int propertyValue = Integer.MAX_VALUE;

		implementation.setInt(propertyKey, propertyValue);

		assertEquals(propertyValue, implementation.getIntOrDefault(propertyKey, 0));
	}

	public abstract void testGetIntOrDefault_Default();

	protected void testGetIntOrDefault_Default(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		int defaultValue = Integer.MAX_VALUE;

		assertEquals(defaultValue, implementation.getIntOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region long
	public abstract void testSetLong();

	protected void testSetLong(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		long propertyValue = Long.MAX_VALUE;

		implementation.setLong(propertyKey, propertyValue);

		long result = implementation.getLong(propertyKey);

		assertEquals(propertyValue, result);
	}

	public abstract void testGetLongOrDefault();

	protected void testGetLongOrDefault(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		long propertyValue = Long.MAX_VALUE;

		implementation.setLong(propertyKey, propertyValue);

		assertEquals(propertyValue, implementation.getLongOrDefault(propertyKey, 0));
	}

	public abstract void testGetLongOrDefault_Default();

	protected void testGetLongOrDefault_Default(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		long defaultValue = Long.MAX_VALUE;

		assertEquals(defaultValue, implementation.getLongOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region bool
	public abstract void testSetBool();

	protected void testSetBool(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		boolean propertyValue = true;

		implementation.setBool(propertyKey, propertyValue);

		boolean result = implementation.getBool(propertyKey);

		assertEquals(propertyValue, result);
	}

	public abstract void testGetBoolOrDefault();

	protected void testGetBoolOrDefault(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		boolean propertyValue = true;

		implementation.setBool(propertyKey, propertyValue);

		assertEquals(propertyValue, implementation.getBoolOrDefault(propertyKey, false));
	}

	public abstract void testGetBoolOrDefault_Default();

	protected void testGetBoolOrDefault_Default(PropertyEditor implementation) {
		String propertyKey = UUID.randomUUID().toString();
		boolean defaultValue = true;

		assertEquals(defaultValue, implementation.getBoolOrDefault(propertyKey, defaultValue));
	}
	//endregion
}
