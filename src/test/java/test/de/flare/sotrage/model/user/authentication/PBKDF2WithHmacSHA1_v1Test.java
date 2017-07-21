package test.de.flare.sotrage.model.user.authentication;

import de.flare.storage.model.user.authentication.PBKDF2WithHmacSHA1_v1;
import de.flare.storage.model.user.authentication.PasswordService;
import org.junit.Test;

public class PBKDF2WithHmacSHA1_v1Test extends PasswordServiceTestParent {

	@Test
	@Override
	public void testGetPasswordToken() {
		PasswordService implementation = new PBKDF2WithHmacSHA1_v1(0);
		testGetPasswordToken(implementation);
	}

	@Test
	@Override
	public void testMatches() {
		PasswordService implementation = new PBKDF2WithHmacSHA1_v1(0);
		testMatches(implementation);
	}
}
