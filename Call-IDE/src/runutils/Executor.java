package runutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.JTextPane;

/**
 * A class to execute java class files with main methods.
 * @author Abdullah Talayhan
 */
public class Executor {
    private String buildPath;
    private Thread executeThread;
    private RunFile r;
    public Executor(String buildPath) {
        this.buildPath = buildPath;
    }
    
    public void altExecute() {}
    
    public void execute(JTextPane area,String mainName ) {
        
         r = new RunFile(area, mainName, buildPath);
    }
    
    public void stop() {
        r.kill();
    }
    
}