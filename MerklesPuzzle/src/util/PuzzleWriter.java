package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to write and export text file.
 *
 * @author Wilson Shrestha
 */
public class PuzzleWriter {

    /**
     * Name for the puzzles file.
     */
    public final String OUTPUT_FILE = "puzzles.txt";

    /**
     * This method exports the puzzles to a text file.
     *
     * @param puzzle, the puzzles that are added in the text file
     * @throws IOException
     */
    public void writeToFile(PuzzleBuilder puzzle) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(OUTPUT_FILE, true));
        out.println(puzzle.getEncryptedPuzzleAsString());
        out.close();
    }
}
