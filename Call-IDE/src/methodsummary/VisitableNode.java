package methodsummary;

import java.io.File;
import com.github.javaparser.Position;

/**
 *
 * @author Bahadır
 */
public interface VisitableNode {
    File getFile();
    Position getPosition();
    void configureNode();
}
