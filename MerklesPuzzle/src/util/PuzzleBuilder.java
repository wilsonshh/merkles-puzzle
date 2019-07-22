package util;

import util.PuzzleRandomized;
import lib.CryptoLib;
import lib.DES;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.crypto.SecretKey;

/**
 * This class generates a initial structure for the puzzle.
 *
 * @author Wilson Shrestha
 */

public class PuzzleBuilder {
	
	private int uniqueNumberComponent;
	private SecretKey keyComponent;
	private String encryptedPuzzleAsString;


	/**
	 * This constructor creates puzzle by linking array bytes
	 * together. Then the puzzle is encrypted.
	 * @param uniquePuzzleNumber, Integer value that represents the unique puzzle number
	 * @throws Exception
	 */
	public PuzzleBuilder(int uniquePuzzleNumber)throws Exception {
		uniqueNumberComponent = uniquePuzzleNumber;

		PuzzleRandomized puzzleRandomized = new PuzzleRandomized();

		SecretKey encryptionKey = puzzleRandomized.generateRandomKey();
		
		// 128 zero bits component
		int numberOfZeroBytes = 16;
		byte[] zeroComponent = new byte[numberOfZeroBytes];
		
		// 16 bit uniquePuzzleNumber part
		byte[] uniquePuzzleNumberComponent = CryptoLib.smallIntToByteArray(uniquePuzzleNumber);
		
		// 64 bit key component
		keyComponent = puzzleRandomized.generateRandomKey();

		byte[] unencryptedPuzzle = buildPuzzle(zeroComponent, uniquePuzzleNumberComponent, keyComponent.getEncoded());
		
		// 48 bits zero encryption bits component
		byte[] encryptKey = encryptionKey.getEncoded();
		int END_OF_ZERO_BITS = 8;
		int START_OF_ZERO_BITS = 2;
		Arrays.fill(encryptKey, START_OF_ZERO_BITS, END_OF_ZERO_BITS, (byte) 0);
		encryptionKey = CryptoLib.createKey(encryptKey);


        // DES object
        DES des = new DES();
        // Encrypt
		byte[] encryptedPuzzle = des.encryptByte(unencryptedPuzzle, encryptionKey);

		// Turn into plaintext
		encryptedPuzzleAsString = CryptoLib.byteArrayToString(encryptedPuzzle);
	}
	
	/**
	 * Puzzle is created using three components.
	 * @param zeroComponent, This is the first component consisting 128 zero bits
	 * @param numberComponent, This is the second component consisting unique 16-bit puzzle number
	 * @param keyComponent, This is the last component consisting  64-bit DES key
	 * @return Puzzle with 128 zero bits, 16 bit puzzle number and 64-bit DES key altogether.
	 * @throws IOException
	 */
    private byte[] buildPuzzle(byte[] zeroComponent, byte[] numberComponent, byte[] keyComponent) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		output.write(zeroComponent);
		output.write(numberComponent);
		output.write(keyComponent);
		return output.toByteArray();
	}
	
	/**
	 * This method is used to retrieve the unique puzzle number.
	 * @return This returns the unique number component of the puzzle
	 */
	public int getUniquePuzzleComponent() {
	    return uniqueNumberComponent;
	}
	
	/**
	 * This method is used to retrieve the key component of the puzzle
	 * @return A byte array of the key
	 */
	public byte[] getKeyComponent() {
	    return keyComponent.getEncoded();
	}
	
	/**
	 * This method is used to retrieve the encrypted puzzle in plaintext.
	 * @return This returns the string value for the encrypted puzzle
	 */
	public String getEncryptedPuzzleAsString() {
		return encryptedPuzzleAsString;
	}

}
