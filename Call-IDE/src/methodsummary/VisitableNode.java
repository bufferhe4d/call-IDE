package methodsummary;

import java.io.File;
import com.github.javaparser.Position;

/**
 * An interface for nodes which has codes to visit.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public interface VisitableNode {
    /**
     * Enables access to the file of the node
     * @return the file which is linked to the node
     */
    File getFile();
    
    /**
     * Enables access to the position of the node on the file
     * @return the position on the file which is linked to the node
     */
    Position getPosition();
    
    /** Configures the node's file and position properties. */
    void configureNode();
    
}
