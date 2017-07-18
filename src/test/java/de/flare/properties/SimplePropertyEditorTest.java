package de.flare.properties;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SimplePropertyEditorTest {

	@Test
	public void testSetProperty() {
		String propertyKey = UUID.randomUUID().toString();
		String propertyValue = UUID.randomUUID().toString();

		SimplePropertyEditor.getInstance().setProperty(propertyKey, propertyValue);

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getProperty(propertyKey));
	}

	@Test
	public void testSetInt() {
		String propertyKey = UUID.randomUUID().toString();
		int propertyValue = Integer.MAX_VALUE;

		SimplePropertyEditor.getInstance().setProperty(propertyKey, Integer.toString(propertyValue));

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getPropertyAsInt(propertyKey));
	}

	@Test
	public void testSetLong() {
		String propertyKey = UUID.randomUUID().toString();
		long propertyValue = Long.MAX_VALUE;

		SimplePropertyEditor.getInstance().setProperty(propertyKey, Long.toString(propertyValue));

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getPropertyAsLong(propertyKey));
	}

	@Test
	public void testSetBool() {
		String propertyKey = UUID.randomUUID().toString();
		boolean propertyValue = true;

		SimplePropertyEditor.getInstance().setProperty(propertyKey, Boolean.toString(propertyValue));

		assertEquals(propertyValue, SimplePropertyEditor.getInstance().getPropertyAsBool(propertyKey));
	}

	@Test
	public void getInt() {
		String propertyKey = UUID.randomUUID().toString();

		assertEquals(0, SimplePropertyEditor.getInstance().getPropertyAsInt(propertyKey));
		assertEquals(Integer.MAX_VALUE, SimplePropertyEditor.getInstance().getPropertyAsInt(propertyKey, Integer.MAX_VALUE));
	}

	@Test
	public void getLong() {
		String propertyKey = UUID.randomUUID().toString();

		assertEquals(0, SimplePropertyEditor.getInstance().getPropertyAsLong(propertyKey));
		assertEquals(Long.MAX_VALUE, SimplePropertyEditor.getInstance().getPropertyAsLong(propertyKey, Long.MAX_VALUE));
	}

	@Test
	public void getBool() {
		String propertyKey = UUID.randomUUID().toString();

		assertFalse(SimplePropertyEditor.getInstance().getPropertyAsBool(propertyKey));
		assertTrue(SimplePropertyEditor.getInstance().getPropertyAsBool(propertyKey, true));
	}
}
