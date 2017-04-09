package FileOperations;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * A class to configure the location to save properties and templates of the user.
 * 
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class FileConfigurer {
    
    /**
     * This method should be called while IDE is loading.
     * Use this method to get the save path of the properties and templates of the user.
     * This path info is stored on a hidden file ".callide" on the user.home
     * This path is specified as "user.home\Call-IDE" by default
     * @return (content of .callide) + "/Call-IDE/"
     */
    public static String getPath() throws IOException {
        String userHome = System.getProperty("user.home");
        String configPath = userHome + "/.callide";
        File configFile = new File( configPath);
        
        if (configFile.exists()) { // If the user has a specified a path from before,
            // Import the user's pre-specified path and return it.
            Scanner input = new Scanner( configFile);
            String userPath = input.nextLine();
            input.close();
            return userPath + "/Call-IDE/";
        }
        
        else { // If the user opened the program first time,
            // Setting the user's path to the default path and returning it.
            PrintWriter output = new PrintWriter( configFile);
            output.println( userHome);
            output.close();
            (new File(userHome + "/Call-IDE")).mkdir();
            return userHome + "/Call-IDE/";
        }
    }
    
    /**
     * Change the user's specified path and move their files to that path.
     * This method creates a Call-IDE folder to the specified userPath
     * Then it moves all the files from the old directory.
     * @param userPath the new path to carry Call-IDE folder
     */
    public static boolean setPath( String userPath) throws IOException {
        
        // Check if the given path is valid.
        File userDir = new File( userPath);
        if (!userDir.exists() || !userDir.isDirectory())
            return false;
        
        // Create a Call-IDE folder inside the given path.
        (new File( userPath + "/Call-IDE/")).mkdir(); 
        
        // Getting the old path from .callide ...
        String userHome = System.getProperty("user.home");
        String configPath = userHome + "/.callide";
        File configFile = new File( configPath);
        Scanner input = new Scanner( configFile);
        String oldPath = input.nextLine();
        input.close();
        
        // Moving old files one by one...
        File oldDir = new File( oldPath + "/Call-IDE/");
        if(oldDir.isDirectory()) {
            File[] content = oldDir.listFiles();
            for (int f = 0; f < content.length; f++) {
                Files.move((content[f].toPath()),
                           (new File( userPath + "/Call-IDE/" + content[f].getName())).toPath(),
                           StandardCopyOption.REPLACE_EXISTING);
            }
        }
        
        // Delete the old empty Call-IDE folder.
        oldDir.delete();
        
        // Setting the user's path to given path.
        PrintWriter output = new PrintWriter( configFile);
        output.println( userPath);
        output.close();
        
        return true;
    }
}
