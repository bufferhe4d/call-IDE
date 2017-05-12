package fileoperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class to read text files.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class ContentReader {
    
    /** The list of all supported extensions to edit. */
    public static String[] SUPPORTED_EXTENSIONS = {".java", ".txt", ".xml", ".md"};
    
    /**
     * Reads and returns the file contents.
     * @param file the file to read
     * @return the content of the readed file
     */
    public static String read( File file) throws IOException {
        FileReader input = new FileReader( file);
        BufferedReader reader = new BufferedReader( input);
        String content = "";
        String nextLine = reader.readLine();
        while (nextLine != null) {
            content += nextLine + "\n";
            nextLine = reader.readLine();
        }
        input.close();
        reader.close();
        return content;
    }
    
    /**
     * Checks if a file has a supported file extension to read.
     * @param file the file to check
     * @return true if the extension is supported, false otherways.
     */
    public static boolean isSupported( File file) {
        for (String extension : SUPPORTED_EXTENSIONS)
            if (file.getName().endsWith(extension))
                return true;
        return false;
    }
    
}
