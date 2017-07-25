package de.flare.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordUtility {

	//region private static members
	private static SecureRandom random = new SecureRandom();
	//endregion

	//region private const
	private static final int SIZE = 128;
	private static final int SHIFTS = 16;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final Pattern hashLayout = Pattern.compile(ALGORITHM + "\\$(\\d\\d?)\\$(.{43})");
	//endregion

	//region ctor
	private PasswordUtility() { }
	//endregion

	//region public static methods
	public static String getToken(String password) {
		byte[] salt = new byte[SIZE / 8];
		random.nextBytes(salt);
		byte[] dk = pbkdf2(password.toCharArray(), salt, 1 << SHIFTS);
		byte[] hash = new byte[salt.length + dk.length];
		System.arraycopy(salt, 0, hash, 0, salt.length);
		System.arraycopy(dk, 0, hash, salt.length, dk.length);
		Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
		return ALGORITHM + "$" + SHIFTS + "$" + enc.encodeToString(hash);
	}

	public static boolean matches(String password, String token) {
		Matcher m = hashLayout.matcher(token);

		if (!m.matches()) {
			return false;
		}

		int iterations = 1 << SHIFTS;
		byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
		byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
		byte[] check = pbkdf2(password.toCharArray(), salt, iterations);
		int zero = 0;
		for (int idx = 0; idx < check.length; ++idx)
			zero |= hash[salt.length + idx] ^ check[idx];
		return zero == 0;
	}
	//endregion

	//region private static methods
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) throws IllegalStateException
	{
		KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			return f.generateSecret(spec).getEncoded();
		}
		catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
		}
		catch (InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}
	//endregion
}
