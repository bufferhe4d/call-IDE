import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * A class to open and close text files.
 * Call the read() method once, then use getContent() if the content is necessary.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */

public class FileHolder {
    
    /** The path of the file */
    private String path;
    
    /** The String to remember the content of the file */
    private String content;
    
    /**
     * Creates an instance of FileHolder with the specified path.
     * @param path the path of the file that will be read later.
     */
    public FileHolder( String path) {
        this.path = path;
    }
    
    /** @param path the path to assign to this FileHolder */
    public void setPath( String path) {
        this.path = path;
    }
    
    /**
     * Reads and remembers the file content from the path of this FileHolder
     * @param path the path of the file that will be opened
     * @throws IOException if the file is invalid for reading
     */
    public String read() throws IOException {
        File file = new File( path);
        Scanner input = new Scanner( file);
        content = "";
        while( input.hasNext())
            content += input.nextLine() + "\n\r";
        return content;
    }
    
    /** @return the file content that is read before, null if read() is not called before */
    public String getContent() {
        return content;
    }
    
    /** Clears the references of this FileHolder. */
    public void clear() {
        path = null;
        content = null;
    }
    
    /** @return the current path that is assigned to this FileHolder */
    public String toString() {
        return "FileHolder[" + path + "]";
    }
}