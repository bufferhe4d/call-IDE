package filebrowser;

/**
 *
 * @author Abdullah Talayhan
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealTimeFolderWatcher extends Thread {

  Path pathToWatch;
  WatchService folderWatcher;
  FileExplorer explorer;

  public RealTimeFolderWatcher(File dirToWatch, FileExplorer explorer) {
        this.explorer = explorer;
        try {
        
        pathToWatch = dirToWatch.toPath();
        folderWatcher = pathToWatch.getFileSystem().newWatchService();
        
        Kind[] events = { ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        registerAll(pathToWatch, events);
        //pathToWatch.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
  }
  
  //Path start, root folder to be watched
  private void registerAll(Path start, Kind[] events){
      try {
          // walk all folders here recursively call the below method for each folder(not file!) the variable dir is a Path Object!!!
          Files.walk(start)
                  .filter(path -> Files.isDirectory(path))
                  .forEach(path -> {
              try {
                  path.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
                  // System.out.println("The reg path: " + path);
              } catch (IOException ex) {
                  Logger.getLogger(RealTimeFolderWatcher.class.getName()).log(Level.SEVERE, null, ex);
              }
          });
                  // call this method for each path: dir.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
      } catch (IOException ex) {
          Logger.getLogger(RealTimeFolderWatcher.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  
  public void run() {
      
        // thread loop
        while (!Thread.currentThread().isInterrupted()) {
            
          try {
            // get the key from watcher
            WatchKey key = folderWatcher.take();
            
            Path dir;
            dir = (Path)key.watchable();
            
            WatchEvent<?> tempEvent;
            // get all events
            ArrayList<WatchEvent<?>> allEvents = (ArrayList<WatchEvent<?>>) key.pollEvents();
            ArrayList<String> allPaths = new ArrayList<String>();
            String maxPath;
            
            // iterate over events
            for (int i = 0; i < allEvents.size(); i++) {
                tempEvent = allEvents.get(i);
                if(tempEvent.kind() == ENTRY_MODIFY)
                    allPaths.add(dir.resolve((Path) tempEvent.context()).toString());
                

            }
            // chech if any event is ENTRY_MODIFY
            if(!allPaths.isEmpty()) {
                maxPath = allPaths.get(0);
                for(int i = 0; i < allPaths.size(); i++) {
                    if(allPaths.get(i).length() > maxPath.length())
                        maxPath = allPaths.get(i);
                }
                // System.out.println(maxPath);
                
                File changedFile = new File(maxPath);
                if(changedFile.isDirectory()) {
                    explorer.updateDirectory(maxPath);
                }
                else {
                    explorer.updateDirectory(changedFile.getAbsolutePath());
                }
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