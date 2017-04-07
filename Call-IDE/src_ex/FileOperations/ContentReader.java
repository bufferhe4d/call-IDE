package FileOperations;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * A class to read text files.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */

public class ContentReader {
    
    /** Reads and returns the file contents. */
    public static String read( File file) throws IOException {
        Scanner input = new Scanner( file);
        String content = "";
        while( input.hasNext())
            content += input.nextLine() + "\r\n";
        input.close();
        return content;
    }
    
}
