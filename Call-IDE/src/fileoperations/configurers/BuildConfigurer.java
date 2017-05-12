package fileoperations.configurers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to configure xml files that is needed for building
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class BuildConfigurer {
    
    /**
     * Exports the configuration data files from the source files to the userpath.
     * @param location the path of the folder to export
     * @throws IOException 
     */
    public void exportConfigs( String location) throws IOException {
        exportConfigData( "/fileoperations/buildconfigs/build.xml", location + "/build.xml");
        exportConfigData( "/fileoperations/buildconfigs/buildFile.xml", location + "/buildFile.xml");
        exportConfigData( "/fileoperations/buildconfigs/buildJavadoc.xml", location + "/buildJavadoc.xml");
        exportConfigData( "/fileoperations/buildconfigs/buildJar.xml", location + "/buildJar.xml");
        exportConfigData( "/fileoperations/buildconfigs/runJava.xml", location + "/runJava.xml");
        exportConfigData( "/fileoperations/buildconfigs/buildDeps.xml", location + "/buildDeps.xml");
        exportConfigData( "/fileoperations/buildconfigs/buildDepJar.xml", location + "/buildDepJar.xml");
    }
    
    /**
     * Exports a particular configuration file from the given location to the target.
     * @param source the path of the source file to read
     * @param target the path of the target file to write
     * @throws IOException
     */
    private void exportConfigData( String source, String target) throws IOException {
        Scanner reader = new Scanner( getClass().getResourceAsStream( source));
        PrintWriter writer = new PrintWriter( new File( target));
        while( reader.hasNext())
            writer.println(reader.nextLine());
        reader.close();
        writer.close();
    }
    
}
