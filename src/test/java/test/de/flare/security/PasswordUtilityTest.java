package test.de.flare.security;

import de.flare.security.PasswordUtility;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordUtilityTest {

	@Test
	public void testMatches() {
		String password = UUID.randomUUID().toString();
		String wrongPassword = password + "WRONG";

		String token = PasswordUtility.getToken(password);

		assertTrue(PasswordUtility.matches(password, token));
		assertFalse(PasswordUtility.matches(wrongPassword, token));
	}
}
