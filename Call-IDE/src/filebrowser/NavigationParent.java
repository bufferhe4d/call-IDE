package filebrowser;

import java.io.File;

/**
 * An interface to make connection between the file explorer
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface NavigationParent {
    
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
    
    /**
     * Compile the file which will be given from parameter as a java source file
     * @param file the file to compile
     */
    void compileSelectedFile( File file);
    
    /**
     * Run the file which will be given from parameter as a java class
     * @param file the file to run
     */
    void runSelectedFile( File file);
    
    /**
     * Updates the project files
     */
    void updateProjects();
}
