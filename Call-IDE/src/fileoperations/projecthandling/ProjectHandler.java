package fileoperations.projecthandling;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is to handle project properties. It can create, save, open and change project properties. 
 * It allows to save in one folder and open in another folder. 
 * <br>
 * <br>
 * Do not forget some file parameters take absolute path or file name, and 
 * others takes path after sources path, and  project file and sources and build must be in same folder.
 * <br>
 * <br>
 * E.g sources path can be "D:/user/workspace/projectX/src" or "src" when project path "D:/user/workspace/projectX/" but main class path must be
 * "packageX/Main.java" or "Main.java". This makes folder changable. 
 * 
 * @see ProjectProperties
 * 
 * @author Ahmet Furkan Biyik
 * @version 1.00, 30.04.2017
 */
public class ProjectHandler
{
    // constants
    public static final String EXTENSION = ".callide";
    
    // properties
    private ProjectProperties project;
    private String projectPath;
    
    // constructors
    /**
     * It creates call-IDE project with given parameters. It creates build and src folders unless they exist.
     * @param build build folder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/classes" or "classes"
     * @param src sourcefolder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/src" or "src"
     * @param mainClass it is a file of main class. Its path cannot be absolute path. It must be path after src absolute path. e.g "packageX/Main.java" so it comes after src path
     * @param projectPath path of where project file will be. It should end with "/". e.g "D:/user/workspace/projectX/".
     * @throws SecurityException if can't create src and build
     */
    public ProjectHandler( File build, File src, File mainClass, String projectPath) throws SecurityException
    {
        this( build, src, mainClass, projectPath, new ArrayList<File>(), new  ArrayList<File>());
    }
    
    /**
     * It creates call-IDE project with given parameters. It creates build and src folders unless they exist.
     * @param build build folder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/classes" or "classes"
     * @param src sourcefolder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/src" or "src"
     * @param mainClass it is a file of main class. Its path cannot be absolute path. It must be path after src absolute path. e.g "packageX/Main.java" so it comes after src path
     * @param projectPath path of where project file will be. It should end with "/". e.g "D:/user/workspace/projectX/".
     * @param allJavaFiles list of project files in src
     * @throws SecurityException if can't create src and build
     */
    public ProjectHandler( File build, File src, File mainClass, String projectPath, ArrayList<File> allJavaFiles, ArrayList<File> externalClassPaths) throws SecurityException
    {
        this.projectPath = projectPath;
        project = new ProjectProperties();
        project.build = build;
        System.out.println("actually writing: " + src);
        project.src = src;
        project.mainClass = mainClass;
        project.allJavaFiles = allJavaFiles;
        project.externalClassPaths = externalClassPaths;
        checkAndCreateFolders();
    }
    
    /**
     * It creates empty project. It may use for open projects.
     * IMPORTANT: project properties will be null!
     */
    public ProjectHandler()
    {
        project = new ProjectProperties();
    }
    
    // methods
    /**
     * It checks if src and build folders exist or not. If don't exist, it creates them. 
     * @throws SecurityException if can't create src and build.
     */
    private void checkAndCreateFolders() throws SecurityException
    {
        sync(); // synchronize src and build absolute path
        
        if ( project.src != null && !project.src.exists() )
        {
            project.src.mkdir();
        }
        
        if ( project.build != null && !project.build.exists() )
        {
            project.build.mkdir();
        }
    }
    
    /**
     * It saves project with given path and given project name.
     * @param path it is absolute path to save. It should end with "/". e.g "D:/user/workspace/projectX/".
     * @param name name of project file. It should not include file extension.
     * @throws FileNotFoundException if file not found
     * @throws IOException if writing fails
     */
    public void saveProject( String path, String name) throws FileNotFoundException, IOException
    {
        projectPath = path;
        
        FileOutputStream fileOut = new FileOutputStream( path + "/" + name + EXTENSION);
        ObjectOutputStream out = new ObjectOutputStream( fileOut );
        out.writeObject( project );
        out.close();
        fileOut.close();
    }
    
    /**
     * It opens project in given path and given project name.
     * @param path it is absolute path to open. It should end with "/". e.g "D:/user/workspace/projectX/".
     * @param name name of project file. It should not include file extension.
     * @throws FilesMismatchException if files that project has mismatch files in src
     * @throws FileNotFoundException if file not found
     * @throws IOException if reading fails
     * @throws ClassNotFoundException if can't found ProjectProperties
     */
    public void openProject( String path, String name ) throws FilesMismatchException, FileNotFoundException, IOException, ClassNotFoundException
    {
        projectPath = path;
        
        FileInputStream fileIn = new FileInputStream( path + name + EXTENSION );
        ObjectInputStream in = new ObjectInputStream( fileIn );
        project = (ProjectProperties) in.readObject();
        in.close();
        fileIn.close();
        
        sync();
        
        if ( !filesExist() )
        {
            throw new FilesMismatchException();
        }
    }
    
    /**
     * It saves project with given path and given project name.
     * @param projectFile project file to save.
     * @throws FileNotFoundException if file not found
     * @throws IOException if writing fails
     */
    public void saveProject( File projectFile ) throws FileNotFoundException, IOException
    {
        projectPath = projectFile.getParentFile().getAbsolutePath();
        
        FileOutputStream fileOut = new FileOutputStream( projectFile );
        ObjectOutputStream out = new ObjectOutputStream( fileOut );
        out.writeObject( project );
        out.close();
        fileOut.close();
    }
    
    /**
     * It opens project in given path and given project name.
     * @param projectFile project file to open.
     * @throws FilesMismatchException if files that project has mismatch files in src
     * @throws FileNotFoundException if file not found
     * @throws IOException if reading fails
     * @throws ClassNotFoundException if can't found ProjectProperties
     */
    public void openProject( File projectFile ) throws FilesMismatchException, FileNotFoundException, IOException, ClassNotFoundException
    {
        projectPath = projectFile.getParentFile().getAbsolutePath();
        
        FileInputStream fileIn = new FileInputStream( projectFile );
        ObjectInputStream in = new ObjectInputStream( fileIn );
        project = (ProjectProperties) in.readObject();
        in.close();
        fileIn.close();
        sync();
        
        if ( !filesExist() )
        {
            throw new FilesMismatchException();
        }
    }
    
    /**
     * It set build folder.
     * @param build build folder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/classes" or "classes"
     */
    public void setBuild( File build)
    {
        project.build = build;
        sync(); // synchronize src and build absolute path
    }
    
    /**
     * It set src folder.
     * @param src sourcefolder of project. Its path can be absolute path or just name of build folder. e.g "D:/user/workspace/projectX/src" or "src"
     */
    public void setSrc( File src)
    {
        project.src = src;
        sync(); // synchronize src and build absolute path
    }
    
    /**
     * It sets main class of project
     * @param mainClass it is a file of main class. Its path cannot be absolute path. It must be path after src absolute path. e.g "packageX/Main.java" so it comes after src path
     */
    public void setMainClass( File mainClass)
    {
        project.mainClass = mainClass;
    }
    
    /**
     * It sets all java files. This is java files that project has. Files path's in ArrayList cannot be absolute path. It must be path after src absolute path. e.g "packageX/HelloWorld.java" so it comes after src path
     * @param allJavaFiles list of project files in src
     */
    public void setAllJavaFiles( ArrayList<File> allJavaFiles)
    {
        project.allJavaFiles = allJavaFiles;
    }
    
    /**
     * It returns build file.
     * @return build
     */
    public File getBuild()
    {
        return project.build;
    }
    
    /**
     * It returns src file.
     * @return src
     */
    public File getSrc()
    {
        return project.src;
    }
    
    /**
     * It returns main class file.
     * @return main class
     */
    public File getMainClass()
    {
        return new File( project.src.getAbsolutePath() + "/" + project.mainClass.getName() );
    }
    
    /**
     * It returns java files in project.
     * @return all java files in project
     */
    public ArrayList<File> getAllJavaFiles()
    {
        return project.allJavaFiles;
    }
    
    /**
     * It adds a file to project. 
     * @param file to add. It should be path after source folder. E.g "packageX/HelloWorld.java"
     * @return true if adding was successfull
     */
    public boolean addFile( File file)
    {
        return project.allJavaFiles.add( file);
    }
    
    /**
     * It removes a file from project.
     * @param file to remove. It should be path after source folder. E.g "packageX/HelloWorld.java"
     * @return true if removing was successfull
     */
    public boolean removeFile( File file)
    {
        return project.allJavaFiles.remove( file);
    }
    
     /**
     * It adds a file to project. 
     * @param file to add. 
     * @return true if adding was successfull
     */
    public boolean addJar( File file)
    {
        return project.externalClassPaths.add( file);
    }
    
    /**
     * It removes a jar from project.
     * @param file to remove. 
     * @return true if removing was successfull
     */
    public boolean removeJar( File file)
    {
        return project.externalClassPaths.remove( file);
    }
    
    /**
     * It removes all jars from project.
     */
    public void removeAllJars()
    {
        project.externalClassPaths = new ArrayList<File>();
    }
    
    /*
     * It synchronizes src and build absolute path with project path. src and build must be in the same folder with project file.
     */
    private void sync()
    {
        project.src = new File( projectPath + "/" + project.src.getName() );
        project.build = new File( projectPath + "/" + project.build.getName() );
    }
    
    /*
     * It checks if files project has exist in src or not.
     * @return true if all files project has exist in src
     */
    private boolean filesExist()
    {
        for ( File f : project.allJavaFiles)
        {
            f = new File( project.src.getAbsolutePath() + "/" + f.getPath() );
            
            if ( !f.exists() )
                return false;
        }
        
        return true;
    }
    
    public String getPath() {
        return projectPath;
    }
    
    public void setPath( String projectPath) {
        this.projectPath = projectPath;
    }
    
    public ArrayList<File> getJarFiles() {
        return project.externalClassPaths;
    }
    
    //*************************************Inner Exception Class****************************************
    /**
     * It is exception to check files are not matching
     */
    public class FilesMismatchException extends Exception
    {
        /**
         * Files mismatch exception.
         */
        public FilesMismatchException()
        {
            super( "Files did not match.");
        }
    }
}
