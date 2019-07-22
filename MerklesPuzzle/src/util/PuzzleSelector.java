package util;

import lib.CryptoLib;
import java.util.Arrays;
import java.io.*;

/**
 * This class is used to select components relating to the puzzle.
 *
 * @author Wilson Shrestha
 */
public class PuzzleSelector {

    private static final int START_OF_KEY = 18;
    private static final int END_OF_KEY = 26;
    private static final int START_OF_PUZZLE_NO = 16;
    private static final int END_OF_PUZZLE_NO = 18;

    /**
     * Selects the key component from the puzzle as a string through the 8 bytes
     * (64 bits)
     *
     * @param puzzle, The puzzle as string.
     * @return This returns key component as bytes
     */
    public static byte[] selectKey(String puzzle) {
        byte[] puzzleBytes = CryptoLib.stringToByteArray(puzzle);
        return Arrays.copyOfRange(puzzleBytes, START_OF_KEY, END_OF_KEY);
    }

    /**
     * Selects the unique puzzle number component from the puzzle as a string
     * through the 2 bytes (16-bits).
     *
     * @param puzzle The puzzle as string
     * @return This returns key component as bytes
     */
    public static byte[] selectPuzzleNumber(String puzzle) {
        byte[] puzzleBytes = CryptoLib.stringToByteArray(puzzle);
        return Arrays.copyOfRange(puzzleBytes, START_OF_PUZZLE_NO, END_OF_PUZZLE_NO);
    }

    /**
     * This is used to retrieve random puzzle.
     * @return This returns a randomly selected puzzle.
     * @throws IOException
     */
    public String selectRandomPuzzle() throws IOException {
        PuzzleRandomized puzzleRandomized = new PuzzleRandomized();
        String puzzle = PuzzleRandomized.randomPuzzle();
        return puzzle;
    }

}
