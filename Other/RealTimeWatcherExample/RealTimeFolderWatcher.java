/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casualtest;

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

public class RealTimeFolderWatcher extends Thread {

  Path pathToWatch;
  WatchService folderWatcher;

  RealTimeFolderWatcher(File dirToWatch) {
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
  private void registerAll(Path start, Kind[] events) throws IOException {
      // walk all folders here recursively call the below method for each folder(not file!) the variable dir is a Path Object!!!
        
      // call this method for each path: dir.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);
    }
  
  public void run() {
      
        // thread loop
        while (!Thread.currentThread().isInterrupted()) {
            
          try {
            // get the key from watcher
            WatchKey key = folderWatcher.take();
            
            // get all events
            ArrayList<WatchEvent<?>> allEvents = (ArrayList<WatchEvent<?>>) key.pollEvents();
            
            // iterate over events
            for (WatchEvent<?> event : allEvents) {
                
              // watch each event kind and do the action you want
              // IMPORTANT: the below method gives the absolute path to the changed file as String (not the file name itself!!!)
              // Method for getting absolute path:  pathToWatch.resolve((Path) event.context())  
              if (event.kind() == ENTRY_CREATE ) {
                System.out.println("Create Event:: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }
              else if(event.kind() == ENTRY_MODIFY) {
                  System.out.println("Modify Event: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }
              else if(event.kind() == ENTRY_DELETE) {

                  System.out.println("Delete Event: " + event.context().toString() + "assumed path:" + pathToWatch.resolve((Path) event.context()));
              }

            }
            
            // reset the key
            key.reset();
          }
          catch (Exception e) {
            System.out.println("None: " + e.toString());
          }
        }
        
      }
  
}