package editor;

import java.util.Scanner;
import com.github.javaparser.Position;

/**
 * A class to convert variable types.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class PositionCalculator {
    /**
     * Calculates the total chars from beginning to the given position.
     * @param content the string that to make calculations on
     * @param position the position variable that holds row and column numbers
     * @return the index of the string with given row and column number
     */
    public static int calculate( String content, Position position) {
        Scanner reader = new Scanner( content);
        int distance = 0;
        for (int i = 1; i < position.line; i++)
            distance += reader.nextLine().length() + 1;
        distance += position.column - 1;
        return distance;
    }
}
