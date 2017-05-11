package methodsummary;

import java.io.File;

import com.github.javaparser.Position;

/**
 * An interface to make connection between the SummaryTree
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface NodeVisitor {
    /**
     * Goes to the the given file's given position
     * @param file the file to visit
     * @param position the position of the file to visit
     */
    public void visitNode( File file, Position position);
    
}
