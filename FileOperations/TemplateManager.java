import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class to import, create and load template files.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class TemplateManager {
    
    public static final String EXTENSION = ".txt";
    
    /** Imports an external text file's content as a template */
    public static void importTemplate( FileHolder fileHolder, String name) throws IOException {
        String path = FileConfigurer.getPath();
        FileSaver fileSaver = new FileSaver( path + name);
        String template = fileHolder.read();
        fileSaver.save( template);
    }
    
    /** Loads the templates to display on the menu. Should be called while IDE is loading. */
    public static String[] loadTemplates() throws IOException {
        String path = FileConfigurer.getPath();
        File folder = new File( path);
        File[] files = folder.listFiles();
        ArrayList<String> templateList = new ArrayList<String>();
        for (int f = 0; f < files.length; f++) {
            File file = files[f];
            String name = file.getName();
            if (name.endsWith( EXTENSION))
                templateList.add( name);
        }
        String[] templates = new String[templateList.size()];
        for (int i = 0; i < templateList.size(); i++)
            templates[i] = templateList.get(i);
        return templates;
    }
    
}
