package helputils;

import java.io.File;
import java.util.ArrayList;

/**
 * Checks the current platform's Java Development Kit.
 * @author Ahmet Furkan Biyik
 */
public class JDKChecker
{
    public static final String JDK_LINK =
        "http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html";
    
    private final String OS_NAME = System.getProperty("os.name").toLowerCase();
    private String[] pathVarPaths;
    
    /**
     * Check whether the OS is Windows
     * @return true if it is Windows; false, if not
     */
    private boolean isWindows() 
    {
        return (OS_NAME.indexOf("win") >= 0);
    }
    
    /**
     * Check whether the OS is Mac
     * @return true if it is Mac; false, if not
     */
    private boolean isMac() 
    {
        return (OS_NAME.indexOf("mac") >= 0);
    }
    
    /**
     * Check whether the OS is Unix
     * @return true if it is Unix; false, if not
     */
    private boolean isUnix() 
    {
        return (OS_NAME.indexOf("nix") >= 0 || OS_NAME.indexOf("nux") >= 0 || OS_NAME.indexOf("aix") > 0 );
    }
    
    /**
     * Check whether the OS is solaris
     * @return true if it is solaris; false, if not
     */
    private boolean isSolaris() 
    {
        return (OS_NAME.indexOf("sunos") >= 0);
    }
    
    /**
     * Check the default path. If a path is found, add this path to the ArrayList
     * @return an ArrayList of the paths of JDK's in the current system
     */
    public ArrayList<String> checkDefaultPath()
    {
        ArrayList<String> jdkNames = new ArrayList<String>(); // jdks in default path if any
        
        File file = null;
        
        if ( isWindows() )
        {
            file = new File( System.getenv( "programfiles") + "/java" );
        }
        else if ( isMac() ) 
        {
            file = new File("/Library/Java/JavaVirtualMachines");
        }
        else if ( isUnix() )
        {
            file = new File("/usr/bin/jvm");
            
        }
        else if ( isSolaris() )
        {
            // solaris default java path ?????????
            // File f = new File("???");
        }
        
        // search jdks in path
        if ( file != null && file.canRead() && file.isDirectory() ) 
        {
            for (File temp : file.listFiles()) 
            {
                if ( temp.isDirectory() && temp.getName().indexOf("jdk") >= 0 ) 
                {
                    jdkNames.add( temp.getName() );
                } 
            }
        }
        return jdkNames;   
    }
    
    /**
     * Check JAVA_HOME variable by checking the name of the files 
     * @return null, if there is no match; the name of the found file with jdk, if it is matched
     */
    public String checkJAVA_HOME()
    {
        try {
            File file = new File( System.getenv("JAVA_HOME") );
            
            if ( file != null && file.canRead() && file.isDirectory() ) 
            {
                if ( file.getName().indexOf("jdk") >= 0)
                {
                    return file.getName();
                }
                
            } 
        } catch( Exception e ) {}
        
        return null;
    }
    
    /**
     * Check the path variable
     * @return null, if there is no path variable; file name, if there is a file which has path variable
     */
    public String checkPathVar()
    {
        
        if ( readPathVar() == 1 )
        {
            for ( String path : pathVarPaths )
            {
                if ( path.indexOf("jdk") >= 0)
                {
                    File file = new File( path);
                    file = new File( file.getParent() );
                    
                    return file.getName();
                }
            }
        }
        
        return null;
    }
    
    /**
     * Read path variable
     * @return if the path variable is readable, return 1; if not, return -1
     */
    private int readPathVar()
    {
        try
        {
            pathVarPaths = System.getenv( "path").split(";");
            return 1;
        }
        catch( Exception e)
        {
            return -1;
        }
    }
    
}
