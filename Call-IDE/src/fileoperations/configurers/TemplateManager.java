package fileoperations.configurers;

import fileoperations.ContentReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to load and save the templates of the user
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class TemplateManager {
    
    private String userPath;
    private File templatesFolder;
    private ArrayList<String> templates;
    private ArrayList<String> templateNames;
    
    /**
     * Creates a new TemplateManager with the given user path.
     * @param userPath the absolute path for Call-IDE folder of the user
     * @throws IOException 
     */
    public TemplateManager( String userPath) throws IOException {
        this.userPath = userPath;
        templatesFolder = new File( userPath + "/Templates");
        templates = new ArrayList<String>();
        templateNames = new ArrayList<String>();
        
        File[] content = templatesFolder.listFiles();
        for (int i = 0; i < content.length; i++) {
            if (content[i].isFile()) {
                templateNames.add(content[i].getName());
                templates.add(ContentReader.read(content[i]));
            }
        }
    }
    
    /**
     * Provides the saved template contents of the current user.
     * @return an array of strings with containing the template contents
     */
    public String[] getTemplates() {
        String[] templateArray = new String[templates.size()];
        for (int i = 0; i < templateArray.length; i++)
            templateArray[i] = templates.get(i);
        return templateArray;
    }
    
    /**
     * Provides the names of the saved templates of the current user.
     * @return an array of strings with containing the template names
     */
    public String[] getTemplateNames() {
        String[] nameArray = new String[templateNames.size()];
        for (int i = 0; i < nameArray.length; i++)
            nameArray[i] = templateNames.get(i);
        return nameArray;
    }
    
    /**
     * Creates default templates which are ConsoleTemplate and FrameTemplate
     * @throws IOException 
     */
    public void createDefaults() throws IOException {
        importDefaults("/fileoperations/defaulttemplates/ConsoleTemplate.dat", userPath + "/Templates/ConsoleTemplate");
        importDefaults("/fileoperations/defaulttemplates/FrameTemplate.dat", userPath + "/Templates/FrameTemplate");
    }
    
    /**
     * Imports the default templates from the source files.
     * @param source the source path for the template to import
     * @param target the target path for the template to write
     * @throws IOException 
     */
    private void importDefaults( String source, String target) throws IOException {
        Scanner reader = new Scanner( getClass().getResourceAsStream( source));
        PrintWriter writer = new PrintWriter( new File( target));
        while( reader.hasNext())
            writer.println(reader.nextLine());
        reader.close();
        writer.close();
    }
    
    /**
     * Imports a template from the given file.
     * @param source the source path for the template to import
     * @param target the target path for the template to write
     * @throws IOException 
     */
    public static void importTemplate( File source, File target) throws IOException {
        Scanner reader = new Scanner( source);
        PrintWriter writer = new PrintWriter( target);
        while( reader.hasNext())
            writer.println(reader.nextLine());
        reader.close();
        writer.close();
    }
    
}
