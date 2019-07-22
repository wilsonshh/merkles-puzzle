package util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used to select a random puzzle and associated components.
 *
 * @author Wilson Shrestha
 */
public class PuzzleRandomized {

    /**
     * Scans the puzzles file and selects a random puzzle.
     *
     * @return A random puzzle from puzzles file
     * @throws IOException
     */
    public static String randomPuzzle() throws IOException {
        PuzzleReader puzzleReader = new PuzzleReader();
        String[] puzzles = puzzleReader.readFile();
        return puzzles[(int) (Math.random() * puzzles.length)];
    }

    /**
     * This is used to generate a random key.
     *
     * @return A random secret key.
     * @throws java.security.NoSuchAlgorithmException
     */
    public SecretKey generateRandomKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey;
    }
}
