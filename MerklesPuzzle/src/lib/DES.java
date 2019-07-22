package lib;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * This class is used for decryption and encryption.
 *
 * @author Wilson Shrestha
 */
public class DES {

    static Cipher cipher;

    /**
     * Constructor Sets cipher to an DES cipher
     * @throws java.lang.Exception
     */
    public DES() throws Exception {
        cipher = Cipher.getInstance("DES");
    }

    /**
     * Encrypts a plaintext byte into cypher text using a secret key
     *
     * @param input
     * @param secretKey
     * @return Cipher text from a byte
     * @throws java.lang.Exception
     */
    public byte[] encryptByte(byte[] input, SecretKey secretKey) throws Exception {

        //Initialise the cipher to be in encrypt mode, using the given key.
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //Perform the encryption
        byte[] encryptedByte = cipher.doFinal(input);

        return encryptedByte;
    }

    /**
     * Encrypts a plaintext string into cypher text using a secret key
     *
     * @param plainText A plain text input.
     * @param secretKey A secret key input.
     * @return Cipher text from a String
     * @throws Exception
     */
    public String encryptString(String plainText, SecretKey secretKey) throws Exception {

        //Convert plaintext into byte representation
        byte[] plainTextByte = plainText.getBytes();

        //Initialise the cipher to be in encrypt mode, using the given key.
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //Perform the encryption
        byte[] encryptedByte = cipher.doFinal(plainTextByte);

        //Get a new Base64 (ASCII) encoder and use it to convert cipher text back to a string
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);

        return encryptedText;
    }

    /**
     * Decrypts a cipher text into plaintext using a secret key
     *
     * @param encryptedText The encrypted text input.
     * @param secretKey The secret key input.
     * @return Plaintext from cipher text
     * @throws Exception
     */
    public String decryptString(String encryptedText, SecretKey secretKey)
            throws Exception {
        byte[] decryptedByte = decryptByte(encryptedText, secretKey);

        //Convert byte representation of plaintext into a string
        String decryptedText = new String(decryptedByte);

        return decryptedText;
    }

    /**
     * Decrypts a cipher text byte into plaintext using a secret key
     *
     * @param encryptedText The encrypted text input.
     * @param secretKey The secret key input.
     * @return Plaintext from cipher text byte
     * @throws Exception
     */
    public byte[] decryptByte(String encryptedText, SecretKey secretKey)
            throws Exception {
        //Get a new Base64 (ASCII) decoder and use it to convert ciphertext from a string into bytes
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);

        //Initialise the cipher to be in decryption mode, using the given key.
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        //Perform the decryption
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);

        return decryptedByte;
    }

}
