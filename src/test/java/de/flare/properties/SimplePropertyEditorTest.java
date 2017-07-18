package de.flare.properties;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SimplePropertyEditorTest {

	//region string
	@Test
	public void testSetString() {
		String propertyKey = UUID.randomUUID().toString();
		String propertyValue = UUID.randomUUID().toString();

		SimplePropertyEditor.getInstance().setString(propertyKey, propertyValue);

		String property = SimplePropertyEditor.getInstance().getString(propertyKey);

		assertNotNull(property);
		assertEquals(propertyValue, property);
	}

	@Test
	public void testGetStringOrDefault() {
		String propertyKey = UUID.randomUUID().toString();
		String propertyValue = UUID.randomUUID().toString();

		SimplePropertyEditor.getInstance().setString(propertyKey, propertyValue);

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getStringOrDefault(propertyKey, ""));
	}

	@Test
	public void testGetStringOrDefault_Default() {
		String propertyKey = UUID.randomUUID().toString();
		String defaultValue = UUID.randomUUID().toString();

		assertEquals(defaultValue, SimplePropertyEditor.getInstance().getStringOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region int
	@Test
	public void testSetInt() {
		String propertyKey = UUID.randomUUID().toString();
		int propertyValue = Integer.MAX_VALUE;

		SimplePropertyEditor.getInstance().setInt(propertyKey, propertyValue);

		int result = SimplePropertyEditor.getInstance().getInt(propertyKey);

		assertEquals(propertyValue, result);
	}

	@Test
	public void testGetIntOrDefault() {
		String propertyKey = UUID.randomUUID().toString();
		int propertyValue = Integer.MAX_VALUE;

		SimplePropertyEditor.getInstance().setInt(propertyKey, propertyValue);

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getIntOrDefault(propertyKey, 0));
	}

	@Test
	public void testGetIntOrDefault_Default() {
		String propertyKey = UUID.randomUUID().toString();
		int defaultValue = Integer.MAX_VALUE;

		assertEquals(defaultValue, SimplePropertyEditor.getInstance().getIntOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region long
	@Test
	public void testSetLong() {
		String propertyKey = UUID.randomUUID().toString();
		long propertyValue = Long.MAX_VALUE;

		SimplePropertyEditor.getInstance().setLong(propertyKey, propertyValue);

		long result = SimplePropertyEditor.getInstance().getLong(propertyKey);

		assertEquals(propertyValue, result);
	}

	@Test
	public void testGetLongOrDefault() {
		String propertyKey = UUID.randomUUID().toString();
		long propertyValue = Long.MAX_VALUE;

		SimplePropertyEditor.getInstance().setLong(propertyKey, propertyValue);

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getLongOrDefault(propertyKey, 0));
	}

	@Test
	public void testGetLongOrDefault_Default() {
		String propertyKey = UUID.randomUUID().toString();
		long defaultValue = Long.MAX_VALUE;

		assertEquals(defaultValue, SimplePropertyEditor.getInstance().getLongOrDefault(propertyKey, defaultValue));
	}
	//endregion

	//region bool
	@Test
	public void testSetBool() {
		String propertyKey = UUID.randomUUID().toString();
		boolean propertyValue = true;

		SimplePropertyEditor.getInstance().setBool(propertyKey, propertyValue);

		boolean result = SimplePropertyEditor.getInstance().getBool(propertyKey);

		assertEquals(propertyValue, result);
	}

	@Test
	public void testGetBoolOrDefault() {
		String propertyKey = UUID.randomUUID().toString();
		boolean propertyValue = true;

		SimplePropertyEditor.getInstance().setBool(propertyKey, propertyValue);

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getBoolOrDefault(propertyKey, false));
	}

	@Test
	public void testGetBoolOrDefault_Default() {
		String propertyKey = UUID.randomUUID().toString();
		boolean defaultValue = true;

		assertEquals(defaultValue, SimplePropertyEditor.getInstance().getBoolOrDefault(propertyKey, defaultValue));
	}
	//endregion
}
