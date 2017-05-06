/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casualtest;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdullah Talayhan
 */
public class WatcherExample  {
    public static void main(String[] args) {
        File folderToWatch = new File("/Users/ATTJ/Documents/callJoe/src");
        
        RealTimeFolderWatcher watcher = new RealTimeFolderWatcher(folderToWatch);
        watcher.start();
        
        // to stop watching
        watcher.interrupt();
        
    }
}
