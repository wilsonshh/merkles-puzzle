package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to crack the puzzle through brute force attack.
 *
 * @author Wilson Shrestha
 */
public class PuzzleReader {

    /**
     * Name for the puzzle file.
     */
    public final static String INPUT_FILE = "puzzles.txt";

    /**
     * Interprets each puzzle line in the file and adds it to an array
     *
     * @return It returns an array from the puzzle file line.
     * @throws IOException
     */
    public static String[] readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
        List<String> linesList = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            linesList.add(line);
        }

        reader.close();

        return linesList.toArray(new String[linesList.size()]);
    }

}
