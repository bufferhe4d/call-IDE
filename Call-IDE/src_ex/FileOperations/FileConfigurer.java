package FileOperations;

import java.io.File;
import java.io.FileWriter;
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
    
    private String userPath;
    private String workspace;
    private File configFile;
    
    public FileConfigurer() {
        String userHome = System.getProperty("user.home");
        configFile = new File( userHome + "/.callide");
    }
    
    /**
     * This method should be called while IDE is loading.
     * Use this method to get the save path of the properties and templates of the user.
     * This path info is stored on a hidden file ".callide" on the user.home
     * This path is specified as "user.home\Call-IDE" by default
     * @return (content of .callide) + "/Call-IDE/"
     */
    public boolean configExists() throws IOException {
        if (configFile.exists()) 
            return true;
        else
            return false;
    }
    
    public void readConfigs() throws IOException {
         Scanner input = new Scanner( configFile);          
         userPath = input.nextLine() + "/Call-IDE/";          
         if (input.hasNextLine())
            workspace = input.nextLine();
         input.close();  
    }
    
    public void checkFolders() throws IOException {
        File mainFolder = new File(userPath);
        File templateFolder = new File(userPath + "/Templates");
        File preferencesFile = new File(userPath + "/" + PreferencesConfigurer.FILE_NAME);
        if (!mainFolder.exists())
            mainFolder.mkdir();
        if (!templateFolder.exists())
            templateFolder.mkdir();
        if (!preferencesFile.exists())
            PreferencesConfigurer.save( userPath, Preferences.DEFAULT);
    }
    
    public String getUserPath() { return userPath; }
    public String getWorkspace() { return workspace; }
    
    public void configureDefault() throws IOException {
        String userHome = System.getProperty("user.home");
        // Setting the user's path to the default path.
        PrintWriter output = new PrintWriter( configFile);
        output.println( userHome);
        output.close();
        (new File(userHome + "/Call-IDE")).mkdir();
        (new File(userHome + "/Call-IDE/Templates")).mkdir();
        (new TemplateManager(userHome + "/Call-IDE")).createDefaults();
        PreferencesConfigurer.save( userHome + "/Call-IDE/", Preferences.DEFAULT);
    }
    
    public void setWorkspace( String workspace) throws IOException {
        String userHome = System.getProperty("user.home");
        String configPath = userHome + "/.callide";
        File configFile = new File( configPath);
        
        PrintWriter output = new PrintWriter(new FileWriter(configFile, true));
        output.println( workspace);
        output.close();
    }

    /**
     * Change the user's specified path and move their files to that path.
     * This method creates a Call-IDE folder to the specified userPath
     * Then it moves all the files from the old directory.
     * @param userPath the new path to carry Call-IDE folder
     */
    public boolean setPath( String userPath) throws IOException {
        
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
