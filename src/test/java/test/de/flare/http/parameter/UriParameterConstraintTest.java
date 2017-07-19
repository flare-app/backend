package test.de.flare.http.parameter;

import org.junit.Test;

import static de.flare.http.route.parameter.UriParameterConstraint.*;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class UriParameterConstraintTest {

	@Test
	public void testNotNull() {
		assertTrue(NOT_NULL.apply("not null"));
		assertFalse(NOT_NULL.apply(null));
	}

	@Test
	public void testNotEmpty() {
		assertTrue(NOT_EMPTY.apply("not empty"));
		assertFalse(NOT_EMPTY.apply(""));
	}

	@Test
	public void testNotNullNotEmpty() {
		assertTrue(NOT_NULL_NOT_EMPTY.apply("not null & not empty"));
		assertFalse(NOT_NULL_NOT_EMPTY.apply(null));
		assertFalse(NOT_NULL_NOT_EMPTY.apply(""));
	}

	@Test
	public void testIsInt() {
		assertTrue(IS_INT.apply(Integer.toString(Integer.MAX_VALUE)));
		assertTrue(IS_INT.apply(Integer.toString(Integer.MIN_VALUE)));
		assertFalse(IS_INT.apply("not an integer"));
		assertFalse(IS_INT.apply(null));
	}

	@Test
	public void testIsLong() {
		assertTrue(IS_LONG.apply(Long.toString(Long.MAX_VALUE)));
		assertTrue(IS_LONG.apply(Long.toString(Long.MIN_VALUE)));
		assertFalse(IS_LONG.apply("not a long"));
		assertFalse(IS_LONG.apply(null));
	}

	@Test
	public void testIsBool() {
		assertTrue(IS_BOOL.apply("true"));
		assertTrue(IS_BOOL.apply("TRUE"));
		assertTrue(IS_BOOL.apply("false"));
		assertTrue(IS_BOOL.apply("FALSE"));
		assertFalse(IS_BOOL.apply("not a bool"));
		assertFalse(IS_BOOL.apply(null));
	}

	@Test
	public void testIsGreaterThanInt() {
		assertTrue(IS_GREATER_THAN(Integer.MIN_VALUE).apply("0"));
		assertFalse(IS_GREATER_THAN(0).apply(Integer.toString(Integer.MIN_VALUE)));
		assertFalse(IS_GREATER_THAN(0).apply("not a number"));
		assertFalse(IS_GREATER_THAN(0).apply(null));
	}

	@Test
	public void testIsGreaterThanLong() {
		assertTrue(IS_GREATER_THAN(Long.MIN_VALUE).apply("0"));
		assertFalse(IS_GREATER_THAN(0L).apply(Long.toString(Long.MIN_VALUE)));
		assertFalse(IS_GREATER_THAN(0L).apply("not a number"));
		assertFalse(IS_GREATER_THAN(0L).apply(null));
	}

	@Test
	public void testIsSmallerThanInt() {
		assertTrue(IS_SMALLER_THAN(Integer.MAX_VALUE).apply("0"));
		assertFalse(IS_SMALLER_THAN(0).apply(Integer.toString(Integer.MAX_VALUE)));
		assertFalse(IS_SMALLER_THAN(0).apply("not a number"));
		assertFalse(IS_SMALLER_THAN(0).apply(null));
	}

	@Test
	public void testIsSmallerThanLong() {
		assertTrue(IS_SMALLER_THAN(Long.MAX_VALUE).apply("0"));
		assertFalse(IS_SMALLER_THAN(0L).apply(Long.toString(Long.MAX_VALUE)));
		assertFalse(IS_SMALLER_THAN(0L).apply("not a number"));
		assertFalse(IS_SMALLER_THAN(0L).apply(null));
	}
}
