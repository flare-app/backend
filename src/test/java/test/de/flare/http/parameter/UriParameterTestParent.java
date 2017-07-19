package test.de.flare.http.parameter;

import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.UriParameterConstraint;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class UriParameterTestParent {

	public abstract void testSetName();

	protected void testSetName(UriParameter implementation) {
		String oldName = implementation.getName();
		String newName = oldName + "_NEW";

		implementation.setName(newName);
		assertEquals(newName, implementation.getName());
	}

	public abstract void testGetUriName();

	protected void testGetUriName(UriParameter implementation) {
		String name = implementation.getName();

		assertEquals(UriParameter.toUriName(name), implementation.getUriName());
	}

	public abstract void testGetConstraints();

	protected void testGetConstraints(UriParameter implementation) {
		int oldNumberOfConstrains = implementation.getConstraints().size();
		implementation.addConstraint(UriParameterConstraint.IS_BOOL);

		assertEquals(oldNumberOfConstrains + 1, implementation.getConstraints().size());
	}

	public abstract void testValidate() throws UriParameterInvalidException;

	protected void expectValidationException(UriParameter implementation, String value) {
		try {
			implementation.validate(value);
			fail();
		} catch (UriParameterInvalidException e) {

		}
	}
}
