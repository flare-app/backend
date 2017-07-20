package test.de.flare.sotrage.user.authentication;

import de.flare.storage.model.user.authentication.PasswordService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class PasswordServiceTestParent {

	public abstract void testGetPasswordToken();

	protected void testGetPasswordToken(PasswordService implementation) throws IllegalStateException {
		String password = UUID.randomUUID().toString();
		String token = implementation.getPasswordToken(password);

		String[] tokenSplit = token.split(PasswordService.TOKEN_SEPARATOR);

		assertEquals(implementation.getServiceId(), tokenSplit[0]);
	}

	public abstract void testMatches();

	protected void testMatches(PasswordService implementation) throws IllegalStateException {
		String password = UUID.randomUUID().toString();
		String token = implementation.getPasswordToken(password);

		assertTrue(implementation.matches(password, token));
	}
}
