import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;

import org.apache.commons.io.FileUtils;

/**
 * This class modified form moji quick start example to use in instructor
 * @author Mahmud Sami Aydin
 */
public class MossDetector {
    
    // PROPERTIES
    URL results;
    
    public MossDetector( String labRoot ) throws MossException, IOException
    {
        Collection<File> files = FileUtils.listFiles( 
                new File( labRoot + "\\moss-dir"), new String[] { "java" }, true);
        
        // a list of base files that was given to the students for this assignment.
        Collection<File> baseFiles = FileUtils.listFiles(
                new File( labRoot + "\\moss-base-dir"), new String[] { "java" }, true);
        
        // get a new socket client to communicate with the MOSS server and set its parameters.
        SocketClient socketClient = new SocketClient();
        
        // set your MOSS user ID
        socketClient.setUserID("1");
        // socketClient.setOpt...
        
        // set the programming language of all student source codes
        socketClient.setLanguage("java");
        
        // initialize connection and send parameters
        socketClient.run();
        
        // upload all base files
        for (File f : baseFiles) {
            socketClient.uploadBaseFile(f);}
        
        // upload all source files of students
        for (File f : files) {
            socketClient.uploadFile(f);
        }
        
        // finished uploading, tell server to check files
        socketClient.sendQuery();
        
        // get URL with MOSS results and do something with it
        results = socketClient.getResultURL();
    }
    
    public URL getMossLink()
    {
        return results;
    }
    
}
