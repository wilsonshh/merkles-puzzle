package core;

import util.DuplicateRemover;
import util.KeyBuilder;
import util.PuzzleSelector;
import lib.CryptoLib;
import lib.DES;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to crack the puzzle through brute force attack.
 *
 * @author Wilson Shrestha
 */
@SuppressWarnings("MismatchedReadAndWriteOfArray")
public class PuzzleCracker {

    private static final int KEY_ZERO_BYTES = 6;
    private static final int KEY_BITS = 16;
    private static final int INITIAL_ZERO_BYTES = 16;

    /**
     * Selects a random puzzle then crack the puzzle by brute force attack.
     *
     * @return The unique puzzle number of the cracked puzzle
     * @throws IOException
     */
    public int crackPuzzle() throws IOException {

        PuzzleSelector puzzleSelector = new PuzzleSelector();

        // Select random puzzle then crack it
        String crackedPuzzle = crackPuzzleComponent(puzzleSelector.selectRandomPuzzle());

        // Retrieve cracked puzzle as byte
        byte[] puzzleNumberBytes = PuzzleSelector.selectPuzzleNumber(crackedPuzzle);

        // Convert puzzle number bytes to integer
        int puzzleNumber = CryptoLib.byteArrayToSmallInt(puzzleNumberBytes);

        // Select the key from the cracked puzzle
        byte[] keyBytes = PuzzleSelector.selectKey(crackedPuzzle);

        // Convert the key from byte to string
        String plaintextKey = CryptoLib.byteArrayToString(keyBytes);

        return puzzleNumber;
    }

    /**
     * This is used to crack puzzle by using the brute force approach until the
     * key is decrypted.
     *
     * @param puzzle, puzzle as string that will used for cracking process
     * @return Cracked puzzle component
     * @throws IOException
     */
    private static String crackPuzzleComponent(String puzzle) throws IOException {
        ArrayList<String> plaintextPuzzles = new ArrayList<>();

        String crackedPuzzle = null;

        for (int i = 0; i < Math.pow(2, KEY_BITS); i++) {
            // Retrieve byte at current index
            byte[] keyBits = CryptoLib.smallIntToByteArray(i);
            byte[] zeros = new byte[KEY_ZERO_BYTES];

            // KeyBuilder object
            KeyBuilder keyBuilder = new KeyBuilder();

            // Combine key and zeros
            byte[] key = KeyBuilder.buildKey(keyBits, zeros);

            try {
                byte[] decryptedPuzzle = new DES().decryptByte(puzzle, CryptoLib.createKey(key));
                plaintextPuzzles.add(CryptoLib.byteArrayToString(decryptedPuzzle));
            } catch (Exception e) {
            }
        }

        // DuplicateRemover object
        DuplicateRemover duplicateCleaner = new DuplicateRemover();

        // Remove duplicate entries from the plain text puzzle
        plaintextPuzzles = DuplicateRemover.removeDuplicates(plaintextPuzzles);

        for (String puzzleComponent : plaintextPuzzles) {
            byte[] puzzleBytes = CryptoLib.stringToByteArray(puzzleComponent);

            byte[] zeros = new byte[INITIAL_ZERO_BYTES];
            byte[] zerosComponent = Arrays.copyOfRange(puzzleBytes, 0, INITIAL_ZERO_BYTES);

            if (Arrays.equals(zerosComponent, zeros)) {
                // Select this puzzle as the cracked puzzle
                crackedPuzzle = puzzleComponent;
            }
        }
        return crackedPuzzle;
    }

}
