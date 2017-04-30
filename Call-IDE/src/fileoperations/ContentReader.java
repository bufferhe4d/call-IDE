package fileoperations;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to read text files.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class ContentReader {
    
    /**
     * Reads and returns the file contents.
     * @param file the file to read
     * @return the content of the readed file
     */
    public static String read( File file) throws IOException {
        Scanner input = new Scanner( file);
        String content = "";
        while( input.hasNext())
            content += input.nextLine() + "\n";
        input.close();
        return content;
    }
    
}
