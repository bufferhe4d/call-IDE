package runutils;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JTextPane;

/**
 * A class to execute java class files with main methods.
 * @author Abdullah Talayhan
 */
public class Executor {
    private String buildPath;
    private RunFile r;
    private ArrayList<File> deps;
    
    /**
     * Constructor for the Executor class
     * @param buildPath build folder which contains the class file to be executed
     */
    public Executor(String buildPath) {
        this.buildPath = buildPath;
    }
    
    /**
     * Constructor for the Executor class
     * @param buildPath build folder which contains the class file to be executed
     * @param deps dependencies of the file to be executed
     */
    public Executor(String buildPath, ArrayList<File> deps) {
        this.buildPath = buildPath;
        this.deps = deps;
    }
    
    /**
     * Description: executes the given main class
     * @param area pane for interacting with the program
     * @param mainName main class name
     */
    public void execute(JTextPane area,String mainName) {
        
         r = new RunFile(area, mainName, buildPath, deps);
        
    }
    
    /**
     * Description: stop the execution of the current program
     */
    public void stop() {
        if (r != null && r.alive())
            r.kill();
    }
    
    
    
}
