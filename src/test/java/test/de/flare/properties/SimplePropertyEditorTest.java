package test.de.flare.properties;

import de.flare.properties.PropertyEditor;
import de.flare.properties.SimplePropertyEditor;
import org.junit.Test;

public class SimplePropertyEditorTest extends PropertyEditorTestParent {

	//region string
	@Test
	@Override
	public void testSetString() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testSetString(implementation);
	}

	@Test
	@Override
	public void testGetStringOrDefault() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetStringOrDefault(implementation);
	}

	@Test
	@Override
	public void testGetStringOrDefault_Default() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetStringOrDefault_Default(implementation);
	}
	//endregion

	//region int
	@Test
	@Override
	public void testSetInt() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testSetInt(implementation);
	}

	@Test
	@Override
	public void testGetIntOrDefault() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetIntOrDefault(implementation);
	}

	@Test
	@Override
	public void testGetIntOrDefault_Default() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetIntOrDefault_Default(implementation);
	}
	//endregion

	//region long
	@Test
	@Override
	public void testSetLong() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testSetLong(implementation);
	}

	@Test
	@Override
	public void testGetLongOrDefault() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetLongOrDefault(implementation);
	}

	@Test
	@Override
	public void testGetLongOrDefault_Default() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetLongOrDefault_Default(implementation);
	}
	//endregion

	//region bool
	@Test
	@Override
	public void testSetBool() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testSetBool(implementation);
	}

	@Test
	@Override
	public void testGetBoolOrDefault() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetBoolOrDefault(implementation);
	}

	@Test
	@Override
	public void testGetBoolOrDefault_Default() {
		PropertyEditor implementation = new SimplePropertyEditor();
		testGetBoolOrDefault_Default(implementation);
	}
	//endregion
}
