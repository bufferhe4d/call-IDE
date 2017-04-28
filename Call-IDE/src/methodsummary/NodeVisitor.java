package methodsummary;

import com.github.javaparser.Position;
import java.io.File;

/**
 * An interface to make connection between the SummaryTree
 * and its parent frame.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface NodeVisitor {
    public void visitNode( File file, Position position);
}
