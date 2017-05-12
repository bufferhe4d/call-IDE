package fileoperations.configurers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to configure the location to save properties and templates of the user.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class FileConfigurer {
    
    private String userPath;
    private String workspace;
    private File configFile;
    
    /** Creates a file configurer which gets and saves the user home to the volatile memory. */
    public FileConfigurer() {
        String userHome = System.getProperty("user.home");
        configFile = new File( userHome + "/.callide");
    }
    
    /**
     * Checks if the configFile is existing on the disk.
     * @return true if the configFile is on the disk, false otherways
     * @throws IOException
     */
    public boolean configExists() throws IOException {
        if (configFile.exists()) 
            return true;
        else
            return false;
    }
    
    /**
     * Reads the configuration data from the config file and saves it to the volatile memory.
     * @throws IOException 
     */
    public void readConfigs() throws IOException {
         Scanner input = new Scanner( configFile);          
         userPath = input.nextLine() + "/Call-IDE/";          
         if (input.hasNextLine())
            workspace = input.nextLine();
         input.close();
    }
    
    /**
     * Checks the folders under the user's home path, creates the non-existing ones.
     * @throws IOException 
     */
    public void checkFolders() throws IOException {
        File mainFolder = new File(userPath);
        File templateFolder = new File(userPath + "/Templates");
        File preferencesFile = new File(userPath + "/" + PreferencesConfigurer.FILE_NAME);
        File buildConfigsFile = new File(userPath + "/BuildConfigs");
        if (!mainFolder.exists())
            mainFolder.mkdir();
        if (!templateFolder.exists())
            templateFolder.mkdir();
        if (!preferencesFile.exists())
            PreferencesConfigurer.save( userPath, Preferences.DEFAULT);
        if (!buildConfigsFile.exists()) {
            buildConfigsFile.mkdir();
            (new BuildConfigurer()).exportConfigs( userPath + "/BuildConfigs");
        }
    }
    
    /**
     * Reads and returns the user path.
     * @return the path of the Call-IDE folder for the current user
     * @throws IOException 
     */
    public String getUserPath()throws IOException {
        readConfigs();
        return userPath; 
    }
    
    /**
     * Reads and returns the workspace path.
     * @return the path of the workspace folder for the current user
     * @throws IOException 
     */
    public String getWorkspace() throws IOException {
        readConfigs();
        return workspace; 
    }
    
    /**
     * Makes the configurations as default.
     * @throws IOException 
     */
    public void configureDefault() throws IOException {
        String userHome = System.getProperty("user.home");
        // Setting the user's path to the default path.
        PrintWriter output = new PrintWriter( configFile);
        output.println( userHome);
        output.close();
        (new File(userHome + "/Call-IDE")).mkdir();
        (new File(userHome + "/Call-IDE/Templates")).mkdir();
        (new File(userHome + "/Call-IDE/BuildConfigs")).mkdir();
        (new TemplateManager(userHome + "/Call-IDE")).createDefaults();
        PreferencesConfigurer.save( userHome + "/Call-IDE/", Preferences.DEFAULT);
        (new BuildConfigurer()).exportConfigs( userHome + "/Call-IDE/BuildConfigs");
    }
    
    /**
     * Sets the workspace of the user.
     * @param workspace the absolute path of the workspace folder
     * @throws IOException 
     */
    public void setWorkspace( String workspace) throws IOException {
        if (!(workspace == null)) {
            String userHome = System.getProperty("user.home");
            String configPath = userHome + "/.callide";
            File configFile = new File( configPath);
            Scanner reader = new Scanner(configFile);
            String savedUserPath = reader.nextLine();
            reader.close();
            PrintWriter writer = new PrintWriter( configFile);
            writer.println(savedUserPath);
            writer.println(workspace);
            writer.close();
        }
    }
    
}
