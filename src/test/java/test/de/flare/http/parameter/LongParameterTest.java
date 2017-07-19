package test.de.flare.http.parameter;

import de.flare.http.route.parameter.LongParameter;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;

import static org.junit.Assert.assertTrue;

public class LongParameterTest extends UriParameterTestParent {

	@Override
	public void testSetName() {
		UriParameter implementation = new LongParameter("long parameter");
		testSetName(implementation);
	}

	@Override
	public void testGetUriName() {
		UriParameter implementation = new LongParameter("long parameter");
		testGetUriName(implementation);
	}

	@Override
	public void testGetConstraints() {
		UriParameter implementation = new LongParameter("long parameter");
		testGetConstraints(implementation);
	}

	@Override
	public void testValidate() throws UriParameterInvalidException {
		UriParameter implementation = new LongParameter("long parameter");

		assertTrue(implementation.validate(Long.toString(Long.MAX_VALUE)));
		expectValidationException(implementation, "not a long");
		expectValidationException(implementation, null);

		long minimum = (long) Integer.MAX_VALUE + 1;
		long maximum = minimum + 10;
		implementation = new LongParameter("long parameter", minimum, maximum);

		for (long i = minimum; i <= maximum; i++) {
			assertTrue(implementation.validate(Long.toString(i)));
		}

		expectValidationException(implementation, Long.toString(minimum - 1));
		expectValidationException(implementation, Long.toString(maximum + 1));
	}
}
