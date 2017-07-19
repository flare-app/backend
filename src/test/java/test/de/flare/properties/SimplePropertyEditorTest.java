package test.de.flare.properties;

import de.flare.properties.SimplePropertyEditor;
import org.junit.Test;

public class SimplePropertyEditorTest extends PropertyEditorTestParent {

	//region string
	@Test
	@Override
	public void testSetString() {
		testSetString(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetStringOrDefault() {
		testGetStringOrDefault(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetStringOrDefault_Default() {
		testGetStringOrDefault_Default(SimplePropertyEditor.getInstance());
	}
	//endregion

	//region int
	@Test
	@Override
	public void testSetInt() {
		testSetInt(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetIntOrDefault() {
		testGetIntOrDefault(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetIntOrDefault_Default() {
		testGetIntOrDefault_Default(SimplePropertyEditor.getInstance());
	}
	//endregion

	//region long
	@Test
	@Override
	public void testSetLong() {
		testSetLong(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetLongOrDefault() {
		testGetLongOrDefault(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetLongOrDefault_Default() {
		testGetLongOrDefault_Default(SimplePropertyEditor.getInstance());
	}
	//endregion

	//region bool
	@Test
	@Override
	public void testSetBool() {
		testSetBool(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetBoolOrDefault() {
		testGetBoolOrDefault(SimplePropertyEditor.getInstance());
	}

	@Test
	@Override
	public void testGetBoolOrDefault_Default() {
		testGetBoolOrDefault_Default(SimplePropertyEditor.getInstance());
	}
	//endregion
}
