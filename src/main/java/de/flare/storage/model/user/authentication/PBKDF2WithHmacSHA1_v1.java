package de.flare.storage.model.user.authentication;

import com.sun.istack.internal.NotNull;

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

/**
 * {@inheritDoc}
 * This implementation uses the PBKDF2WithHmacSHA1_v1 algorithm.
 *
 * @author erickson
 * @see <a href="http://stackoverflow.com/a/2861125/3474">StackOverflow</a>
 */
public class PBKDF2WithHmacSHA1_v1 implements PasswordService {

	//region private members
	private SecureRandom random;
	private int cost;
	private Pattern hashLayout = Pattern.compile(getServiceId() + PasswordService.TOKEN_SEPARATOR + "(\\d\\d?)" + PasswordService.TOKEN_SEPARATOR + "(.{43})");
	//endregion

	//region private const
	/**
	 * The minimum recommended cost, used by default
	 */
	private static final int DEFAULT_COST_FACTOR = 50;
	private static final int MIN_COST = 0;
	private static final int MAX_COST = 31;

	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int SIZE = 128;
	//endregion

	//region ctor

	/**
	 * This constructor initializes the password service with its default costs.
	 */
	public PBKDF2WithHmacSHA1_v1() {
		this(DEFAULT_COST_FACTOR);
	}

	/**
	 * This constructor sets the costs for this password service.
	 * @param costFactor the cost factor [0..100]
	 */
	public PBKDF2WithHmacSHA1_v1(int costFactor)
	{
		this.cost = cost(costFactor);
		this.random = new SecureRandom();
	}
	//endregion

	//region user authentication service
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getServiceId() {
		return PasswordServiceType.PBKDF2WithHmacSHA1_v1.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getPasswordToken(@NotNull String password) throws IllegalStateException {
		byte[] salt = new byte[SIZE / 8];
		random.nextBytes(salt);
		byte[] dk = pbkdf2(password.toCharArray(), salt, iterations(cost));
		byte[] hash = new byte[salt.length + dk.length];
		System.arraycopy(salt, 0, hash, 0, salt.length);
		System.arraycopy(dk, 0, hash, salt.length, dk.length);
		Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
		return getServiceId() + PasswordService.TOKEN_SEPARATOR + cost + PasswordService.TOKEN_SEPARATOR + enc.encodeToString(hash);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matches(@NotNull String password, @NotNull String token) throws IllegalStateException {
		Matcher m = hashLayout.matcher(token);

		if (!m.matches()) {
			return false;
		}

		int iterations = iterations(Integer.parseInt(m.group(1)));
		byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
		byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
		byte[] check = pbkdf2(password.toCharArray(), salt, iterations);
		int zero = 0;
		for (int idx = 0; idx < check.length; ++idx)
			zero |= hash[salt.length + idx] ^ check[idx];
		return zero == 0;
	}

	//endregion

	//region private methods

	/**
	 * This method returns the amount of iterations for the hashing algorithm, given a certain cost factor.
	 * @param cost the costs for the algorithm
	 * @return the amount of iterations to perform
	 */
	private int iterations(int cost)
	{
		return 1 << Math.min(MAX_COST, Math.max(MIN_COST, cost));
	}

	/**
	 * This method calculates the cost, given a certain cost factor.
	 * @param costFactor the cost factor
	 * @return the actual cost
	 */
	private int cost(int costFactor) {
		costFactor = Math.min(100, Math.max(0, costFactor));

		return Math.round((1 + MAX_COST - MIN_COST) * (costFactor / 100.0f));
	}

	/**
	 * This method computes the hash for a given password, salt and amount of iterations.
	 * @param password the password to hash
	 * @param salt the salt to use
	 * @param iterations the iterations
	 * @return the produced hsah
	 * @throws IllegalStateException thrown, if something goes wrong
	 */
	private byte[] pbkdf2(char[] password, byte[] salt, int iterations) throws IllegalStateException
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
