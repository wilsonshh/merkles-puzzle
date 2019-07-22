package lib;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * A collection of useful methods for CSF207's Coursework 2.
 *
 * @author Phillip James
 */
public class CryptoLib {

    /**
     * Create a new DES key from a given byte array. You should use this
     * whenever you need to create a DES key from a particular array of bytes.
     *
     * @param keyData An array of bytes to be used as a key. This is expected to
     * be an array of 8 bytes.
     * @return A DES key computed from the given byte array.
     * @throws java.security.spec.InvalidKeySpecException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     */
    public static SecretKey createKey(byte[] keyData) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        if (keyData.length != 8) {
            throw new IllegalArgumentException("Incorrect Array length expecting 64-bits / 8 bytes.");
        } else {
            SecretKeyFactory sf = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(keyData);
            return sf.generateSecret(keySpec);
        }
    }

    /**
     * Convert a small integer (between 0 and 65535 inclusively) into an array
     * of bytes of length 2.
     *
     * @param i The integer to convert.
     * @return An array of bytes of length 2 representing the given integer in
     * bytes (big endian).
     */
    public static byte[] smallIntToByteArray(int i) {
        if (i >= 65536) {
            throw new IllegalArgumentException("Integer too large, expected range 0-65535.");
        } else {
            byte[] bytesOfNumber = ByteBuffer.allocate(4).putInt(i).array();
            return Arrays.copyOfRange(bytesOfNumber, 2, 4);
        }
    }

    /**
     * Convert an array of bytes of length 2 into a small integer (between 0 and
     * 65535 inclusively).
     *
     * @param bytes A byte array of length 2 (big endian).
     * @return 
     * @returns The computed integer from the array of bytes.
     */
    public static int byteArrayToSmallInt(byte[] bytes) {
        byte[] number = new byte[4];
        number[2] = bytes[0];
        number[3] = bytes[1];

        ByteBuffer bb = ByteBuffer.wrap(number);
        return bb.getInt();
    }

    /**
     * Convert an array of bytes into a string representation (using the Base64
     * binary-to-text encoding scheme - See Wikipedia).
     *
     * @param bytes An array of bytes to be converted into a string.
     * @return A string representation of the given bytes.
     */
    public static String byteArrayToString(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**
     * Convert a string (in Base64 binary-to-text encoding scheme - See
     * Wikipedia) into an array of bytes.
     *
     * @param s A string to be converted into an array of bytes.
     * @return An array of bytes representing the given string.
     */
    public static byte[] stringToByteArray(String s) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(s);
    }

}
