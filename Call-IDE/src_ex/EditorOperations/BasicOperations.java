package EditorOperations;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * A class that handles simple editor operations.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class BasicOperations {
    

    public static void undo( RSyntaxTextArea textArea) {
        textArea.undoLastAction();
    }
    
    public static void redo( RSyntaxTextArea textArea) {
        textArea.redoLastAction();
    }
    
    public static void selectAll( RSyntaxTextArea textArea) {
        textArea.selectAll();
    }
    
    public static void cut( RSyntaxTextArea textArea) {
        textArea.cut();
    }
    
    public static void copy( RSyntaxTextArea textArea) {
        textArea.copy();
    }
    
    public static void paste( RSyntaxTextArea textArea) {
        textArea.paste();
    }
    
}
