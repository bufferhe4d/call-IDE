package fileoperations;

import java.io.File;

/**
 * An interface to make connection between the AutoFileSaver
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface AutosaveHandler {
    /**
     * Passes the file and the saved content of it to the parent frame
     * @param file the file that has been saved
     * @param content the content of the saved file
     */
    void report( File file, String content);
    
}
