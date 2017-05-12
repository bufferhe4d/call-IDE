package filebrowser;

import com.sun.nio.file.SensitivityWatchEventModifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to listen the file changes in a directory
 * @author Abdullah Talayhan
 */
public class RealTimeFolderWatcher extends Thread {
    
    Path pathToWatch;
    WatchService folderWatcher;
    NavigationParent mainFrame;
    
    public RealTimeFolderWatcher(Path pathToWatch, NavigationParent mainFrame) {
        try {
            
            this.pathToWatch = pathToWatch;
            this.mainFrame = mainFrame;
            folderWatcher = pathToWatch.getFileSystem().newWatchService();
 
            //pathToWatch.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * A method to register all the files which will be under the given start path to look
     * @param start the path to start the search files to be registered
     * @throws IOException 
     */
    public void registerAll(Path start) throws IOException {
        try {
            // walk all folders here recursively call the below method for each folder(not file!) the variable dir is a Path Object!!!
            Kind[] events = { ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
            walkFiles( start.toFile(), events);
            
            /*Files.walk(start)
                .filter(path -> Files.isDirectory(path))
                .forEach(path -> {
                try {
                    path.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
                    // System.out.println("The reg path: " + path);
                } catch (IOException ex) {
                    Logger.getLogger(RealTimeFolderWatcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            });*/
        } catch (IOException ex) {
            Logger.getLogger(RealTimeFolderWatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void walkFiles( File folder, Kind[] events) throws IOException {
        Queue<File> dirsq = new LinkedList<>();
        dirsq.add(folder);

        while (!dirsq.isEmpty()) {
            for (File f : dirsq.poll().listFiles()) {
                if (f.isDirectory()) {
                    dirsq.add(f);
                    f.toPath().register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
                }
            }
        }
    }
    
    /**
     * A method to run the threads
     */
    public void run() {
        
        // thread loop
        while (!Thread.currentThread().isInterrupted()) {
            
            try {
                // get the key from watcher
                WatchKey key = folderWatcher.take();

                WatchEvent<?> tempEvent;
                // get all events
                ArrayList<WatchEvent<?>> allEvents = (ArrayList<WatchEvent<?>>) key.pollEvents();
                                
                // iterate over events
                for (int i = 0; i < allEvents.size(); i++) {
                    tempEvent = allEvents.get(i);
                    mainFrame.updateProjects();
                }
                
                // reset the key
                key.reset();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}