package fileoperations;

import java.io.File;

/**
 * An interface to make connection between the AutoFileSaver
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface AutosaveHandler {
    void report( File file, String content);
}
