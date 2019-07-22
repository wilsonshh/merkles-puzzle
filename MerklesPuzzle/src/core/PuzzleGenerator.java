package core;

import util.PuzzleBuilder;
import util.PuzzleWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class is used to generate puzzles.
 *
 * @author Wilson Shrestha
 */
public class PuzzleGenerator {

    /**
     * Name for output file.
     */
    public final String OUTPUT_FILE = "puzzles.txt";

    /**
     * The total number of puzzles that needs to be generated.
     */
    public final int NUMBER_OF_PUZZLES = 1024;

    /**
     * PuzzleBuilder object
     */
    public final PuzzleBuilder[] puzzles = new PuzzleBuilder[NUMBER_OF_PUZZLES];

    /**
     * This method creates a text file. Then it generates 1024 puzzles and
     * writes to the text file.
     *
     * @throws Exception
     */
    public void writePuzzles() throws Exception {
        PrintWriter puzzleFile = new PrintWriter(OUTPUT_FILE);
        puzzleFile.close();

        generatePuzzles();
    }

    /**
     * This method generates unique puzzle randomly.
     *
     * @throws Exception
     */
    public void generatePuzzles() throws Exception {
        ArrayList<Integer> uniquePuzzleList = new ArrayList<>();

        for (int i = 0; i < puzzles.length; i++) {
            int NUMBER_OF_BITS = 16;
            int puzzleNumber = new Random().nextInt((int) Math.pow(2, NUMBER_OF_BITS));
            Collections.shuffle(uniquePuzzleList);
            uniquePuzzleList.add(puzzleNumber);

            puzzles[i] = new PuzzleBuilder(puzzleNumber);

            PuzzleWriter puzzleWriter = new PuzzleWriter();
            puzzleWriter.writeToFile(puzzles[i]);
        }
    }

    /**
     * This is used to retrieve key associated with a puzzle and it is used for
     * the cracking process.
     *
     * @param uniquePuzzleNumber, This is a unique number component of the
     * puzzle.
     * @return It returns the key component of the puzzle.
     * @throws java.lang.Exception
     */
    public byte[] selectKey(int uniquePuzzleNumber) throws Exception {
        for (PuzzleBuilder puzzleComponent : puzzles) {
            if (puzzleComponent.getUniquePuzzleComponent() == uniquePuzzleNumber) {
                return puzzleComponent.getKeyComponent();
            }
        }
        return null;
    }

}
