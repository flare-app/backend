package test.de.flare.http.parameter;

import de.flare.http.route.parameter.StringParameter;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StringParameterTest extends UriParameterTestParent {

	@Test
	@Override
	public void testSetName() {
		UriParameter implementation = new StringParameter("string parameter");
		testSetName(implementation);
	}

	@Test
	@Override
	public void testGetUriName() {
		UriParameter implementation = new StringParameter("string parameter");
		testGetUriName(implementation);
	}

	@Test
	@Override
	public void testGetConstraints() {
		UriParameter implementation = new StringParameter("string parameter");
		testGetConstraints(implementation);
	}

	@Test
	@Override
	public void testValidate() throws UriParameterInvalidException {
		UriParameter implementation = new StringParameter("string parameter");

		assertTrue(implementation.validate("this is a string"));

		expectValidationException(implementation, "");
		expectValidationException(implementation, null);
	}
}
