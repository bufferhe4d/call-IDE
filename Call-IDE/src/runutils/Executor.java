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
	
        public void altExecute() {
            
        }
	public void execute(String mainName) {
		
		try {
			
			final String[] args = {};
			final URL[] classloaderURL = {codLoc};
			//System.out.println("classLoaderURL : " + classloaderURL);
			final ClassLoader classLoader = new URLClassLoader(classloaderURL, ClassLoader.getSystemClassLoader().getParent());
			//System.out.println(ClassLoader.getSystemClassLoader().getParent().);
			
			//System.out.println("classloader: " + classLoader);
			
			final Class mainClass = classLoader.loadClass(mainName);
			
			//System.out.println("mainClass:" + mainClass);
			final Method main = mainClass.getMethod("main", new Class[]{args.getClass()});
			//System.out.println("joe");
			executeThread = new Thread(
					new Runnable() {
						public void run() {
							Thread.currentThread().setContextClassLoader(classLoader);
							try {
								main.invoke(null, (Object) args);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
					}, "program-runner"
			);
			
			executeThread.setDaemon(true);
			executeThread.start();
                        
		} catch(ClassNotFoundException e) {
			System.out.println("Class not found.");
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
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