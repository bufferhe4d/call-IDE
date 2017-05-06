/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filebrowser;

/**
 *
 * @author Abdullah Talayhan
 */
import com.sun.nio.file.SensitivityWatchEventModifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
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
                  //System.out.println(path);
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
                if(tempEvent.kind() == ENTRY_CREATE || tempEvent.kind() == ENTRY_DELETE || tempEvent.kind() == ENTRY_MODIFY)
                    allPaths.add(dir.resolve((Path) tempEvent.context()).toString());
                

            }
            maxPath = allPaths.get(0);
            for(int i = 0; i < allPaths.size(); i++) {
                if(allPaths.get(i).length() > maxPath.length())
                    maxPath = allPaths.get(i);
            }
            //System.out.println(maxPath);
            /*if (eventWithMaxLength.kind() == ENTRY_CREATE ) {
                System.out.println("Create Event:: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }
              else if(eventWithMaxLength.kind() == ENTRY_MODIFY) {
                  System.out.println("Modify Event: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }
              else if(eventWithMaxLength.kind() == ENTRY_DELETE) {

                  System.out.println("Delete Event: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }*/
            File changedFile = new File(maxPath);
            if(changedFile.isDirectory()) {
                explorer.updateDirectory(maxPath);
            }
            else {
                explorer.updateDirectory(changedFile.getAbsolutePath());
            }
            System.out.println("error");
            // reset the key
            key.reset();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
        
      }
  
}