package editor;

import java.util.Scanner;
import com.github.javaparser.Position;

/**
 * Calculates the total chars to the given position
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class PositionCalculator {
    public static int calculate( String content, Position position) {
        Scanner reader = new Scanner( content);
        int distance = 0;
        for (int i = 1; i < position.line; i++)
            distance += reader.nextLine().length() + 1;
        distance += position.column - 1;
        return distance;
    }
}
