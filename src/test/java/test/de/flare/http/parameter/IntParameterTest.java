package test.de.flare.http.parameter;

import de.flare.http.route.parameter.IntParameter;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;

import static org.junit.Assert.assertTrue;

public class IntParameterTest extends UriParameterTestParent {

	@Override
	public void testSetName() {
		UriParameter implementation = new IntParameter("int parameter");
		testSetName(implementation);
	}

	@Override
	public void testGetUriName() {
		UriParameter implementation = new IntParameter("int parameter");
		testGetUriName(implementation);
	}

	@Override
	public void testGetConstraints() {
		UriParameter implementation = new IntParameter("int parameter");
		testGetConstraints(implementation);
	}

	@Override
	public void testValidate() throws UriParameterInvalidException {
		UriParameter implementation = new IntParameter("int parameter");

		assertTrue(implementation.validate(Integer.toString(Integer.MAX_VALUE)));
		expectValidationException(implementation, "not an integer");
		expectValidationException(implementation, null);

		int minimum = 0;
		int maximum = minimum + 10;
		implementation = new IntParameter("int parameter", minimum, maximum);

		for (int i = minimum; i <= maximum; i++) {
			assertTrue(implementation.validate(Integer.toString(i)));
		}

		expectValidationException(implementation, Integer.toString(minimum - 1));
		expectValidationException(implementation, Integer.toString(maximum + 1));
	}
}
