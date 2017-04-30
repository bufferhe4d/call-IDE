package runutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Executor {
    private URL codLoc;
    private Thread executeThread;
    public Executor(URL codLoc) {
        this.codLoc = codLoc;
    }
    
    public void altExecute() {}
    
    public void execute(String mainName) {
        
        try {
            final String[] args = {};
            final URL[] classloaderURL = {codLoc};
            final ClassLoader classLoader = new URLClassLoader(classloaderURL, ClassLoader.getSystemClassLoader().getParent());
            final Class mainClass = classLoader.loadClass(mainName);
            final Method main = mainClass.getMethod("main", new Class[]{args.getClass()});
            executeThread = new Thread( new Runnable() {
                public void run() {
                    Thread.currentThread().setContextClassLoader(classLoader);
                    try {
                        main.invoke(null, (Object) args);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }, "program-runner");
            
            executeThread.setDaemon(true);
            executeThread.start();
            
        } catch(ClassNotFoundException e) {
            System.out.println("Class file couldn't be found.");
            
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        executeThread.interrupt();
    }
    
    public Thread getExecuteInstance() {
        return executeThread;
    }
    
}