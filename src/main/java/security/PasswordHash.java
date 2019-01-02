package security;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * PBKDF2 salted password hashing.
 *
 * @author havoc AT defuse.ca
 * @see <a href="http://crackstation.net/hashing-security.htm">Original
 *      website</a>
 */
public final class PasswordHash {

    /** The Constant PBKDF2_ALGORITHM. */
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

    /** The Constant SALT_BYTES. */
    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTES = 1;

    /** The Constant HASH_BYTES. */
    public static final int HASH_BYTES = 45;

    /** The Constant PBKDF2_ITERATIONS. */
    public static final int PBKDF2_ITERATIONS = 2;

    /** The Constant ITERATION_INDEX. */
    public static final int ITERATION_INDEX = 0;

    /** The Constant SALT_INDEX. */
    public static final int SALT_INDEX = 1;

    /** The Constant PBKDF2_INDEX. */
    public static final int PBKDF2_INDEX = 2;

    /** The hexadecimal. */
    private static final int HEXADECIMAL = 16;

    /** The byte. */
    private static final int BYTE = 8;

    /**
     * This is an utility class. So no constructor should be used.
     */
    private PasswordHash() {
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    public static String createHash(final String password) {
        try {
            return createHash(password.toCharArray());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static String createHash(final char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param password the password to check
     * @param goodHash the hash of the valid password
     * @return true if the password is correct, false if not
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static boolean validatePassword(final String password,
            final String goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), goodHash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param password the password to check
     * @param goodHash the hash of the valid password
     * @return true if the password is correct, false if not
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static boolean validatePassword(final char[] password,
            final String goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the hash into its parameters
        String[] params = goodHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(final byte[] a, final byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * Computes the PBKDF2 hash of a password.
     *
     * @param password   the password to hash.
     * @param salt       the salt
     * @param iterations the iteration count (slowness factor)
     * @param bytes      the length of the hash to compute in bytes
     * @return the PBDKF2 hash of the password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    private static byte[] pbkdf2(final char[] password, final byte[] salt,
            final int iterations, final int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations,
                bytes * BYTE);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(final String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
                    HEXADECIMAL);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(final byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(HEXADECIMAL);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * Tests the basic functionality of the PasswordHash class.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {

        try {
            System.out.println(
                    PasswordHash.createHash("aygusadjfksmgjndsb").length());

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

}
