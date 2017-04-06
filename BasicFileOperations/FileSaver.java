import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to save text files.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */

public class FileSaver {
    
    /** The saving path of the contents */
    private String path;
    
    /**
     * Creates an instance of FileSaver with the specified path.
     * @param path the saving path of the contents
     */
    public FileSaver( String path) {
        this.path = path;
    }
    
    /** @param path the saving path of the contents */
    public void setPath( String path) {
        this.path = path;
    }
    
    /**
     * Saves the contents to the specified path.
     * @param content the contents to save
     * @throws IOException if the writing is failed
     */
    public void save( String content) throws IOException {
        saveAs( content, path);
    }
    
    /**
     * Saves the contents to the given path.
     * @param content the contents to save
     * @param diffPath the different saving path of the contents
     * @throws IOException if the writing is failed
     */
    public void saveAs( String content, String diffPath) throws IOException {
        Scanner input = new Scanner( content);
        File file = new File( diffPath);
        PrintWriter output = new PrintWriter( file);
        while (input.hasNext())
            output.println( input.nextLine());
        input.close();
        output.close();
    }
}
    
