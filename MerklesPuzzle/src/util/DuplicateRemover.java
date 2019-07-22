package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to remove duplicates.
 *
 * @author Wilson Shrestha
 */
public class DuplicateRemover {

    /**
     * Removes duplicates from the ArrayList
     *
     * @param list ArrayList consisting duplicates
     * @return ArrayList without duplicate entries
     */
    public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
        // Record encountered Strings in HashSet.
        Set<String> cleanSet = new HashSet<>();

        cleanSet.addAll(list);
        list.clear();
        list.addAll(cleanSet);

        return list;
    }
}
