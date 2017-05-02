package filebrowser;

import java.io.File;

/**
 * An interface to make connection between the file explorer
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface FileOpener {
    
    /**
     * Opens the file in the editor.
     * @param file the file to open
     */
    void openFile( File file);
    
    /**
     * Closes an open project.
     * @param projectRoot the root folder of the project
     */
    void closeProject( File projectRoot);
    
    /**
     * Shows project properties of a project.
     * @param projectRoot the root folder of the project
     */
    void showProjectProperties( File projectRoot);
    
}
