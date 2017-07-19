package test.de.flare.http.parameter;

import de.flare.http.route.parameter.BoolParameter;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.UriParameterInvalidException;

import static org.junit.Assert.assertTrue;

public class BoolParameterTest extends UriParameterTestParent {

	@Override
	public void testSetName() {
		UriParameter implementation = new BoolParameter("bool parameter");
		testSetName(implementation);
	}

	@Override
	public void testGetUriName() {
		UriParameter implementation = new BoolParameter("bool parameter");
		testGetUriName(implementation);

	}

	@Override
	public void testGetConstraints() {
		UriParameter implementation = new BoolParameter("bool parameter");
		testGetConstraints(implementation);

	}

	@Override
	public void testValidate() throws UriParameterInvalidException {
		UriParameter implementation = new BoolParameter("bool parameter");

		assertTrue(implementation.validate("true"));
		assertTrue(implementation.validate("FaLSe"));
		expectValidationException(implementation, "not a bool");
		expectValidationException(implementation, null);
	}
}
