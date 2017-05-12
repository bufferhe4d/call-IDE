package editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * A class that handles simple editor operations.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class BasicOperations {
    
    /**
     * A method to provide undo functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void undo( RSyntaxTextArea textArea) {
        textArea.undoLastAction();
    }
    
    /**
     * A method to provide redo functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void redo( RSyntaxTextArea textArea) {
        textArea.redoLastAction();
    }
    
    /**
     * A method to provide select all functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void selectAll( RSyntaxTextArea textArea) {
        textArea.selectAll();
    }
    
    /**
     * A method to provide cut functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void cut( RSyntaxTextArea textArea) {
        textArea.cut();
    }
    
    /**
     * A method to provide copy functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void copy( RSyntaxTextArea textArea) {
        textArea.copy();
    }
    
    /**
     * A method to provide paste functionality of the RSyntaxtTextArea to use interactions
     * in IDE
     * @param textArea a parameter to make the action with the text editor's method in it 
     */
    public static void paste( RSyntaxTextArea textArea) {
        textArea.paste();
    }
    
}
