package fileoperations;

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
    
    public String getUserPath()throws IOException {
        readConfigs();
        return userPath; 
    }
    
    public String getWorkspace() throws IOException {
        readConfigs();
        return workspace; 
    }
    
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
    
    public void setWorkspace( String workspace) throws IOException {
        if (!(workspace == null))
        {
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
