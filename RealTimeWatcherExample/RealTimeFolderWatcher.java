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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class RealTimeFolderWatcher extends Thread {

  Path pathToWatch;
  WatchService folderWatcher;

  RealTimeFolderWatcher(File dirToWatch) {
        try {
        
        pathToWatch = dirToWatch.toPath();
        folderWatcher = pathToWatch.getFileSystem().newWatchService();
        Kind[] events = { StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY};
        pathToWatch.register(folderWatcher, events, SensitivityWatchEventModifier.HIGH);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
              if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE ) {
                System.out.println("Create Event:: " + event.context().toString());
              }
              else if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                  System.out.println("Modify Event: " + event.context().toString());
              }
              else if(event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {

                  System.out.println("Delete Event: " + event.context().toString());
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
