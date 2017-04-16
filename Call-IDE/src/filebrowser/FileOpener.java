package filebrowser;

import java.io.File;

/**
 * An interface to make connection between the file explorer
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface FileOpener {
    void openFile( File file);
}
