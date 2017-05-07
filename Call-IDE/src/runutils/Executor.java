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
    
    public Executor(String buildPath) {
        this.buildPath = buildPath;
    }
    
    public Executor(String buildPath, ArrayList<File> deps) {
        this.buildPath = buildPath;
        this.deps = deps;
    }
    
    public void altExecute() {}
    
    public void execute(JTextPane area,String mainName) {
        
         r = new RunFile(area, mainName, buildPath, deps);
        
    }
    
    public void stop() {
        if(r.alive())
            r.kill();
    }
    
    
    
}
