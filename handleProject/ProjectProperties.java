package handleProject;

import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

/**
 * This is properties of project file.
 * It can be written or read.
 * 
 * @author Ahmet Furkan Býyýk
 * @version 1.00, 30.04.2017
 */
public class ProjectProperties implements Serializable
{
   // properties
   /**
    * build file of project. 
    * It can be absolute path or folder name.
    */
   protected File build;
   
   /**
    * source file of project.
    * It can be absolute path or folder name.
    */
   protected File src;
   
   /**
    * main class of project
    * It must have path after sources path.
    * E.g "packageX/Main.java"
    */
   protected File mainClass;
   
   /**
    * all files that project has
    * They must have path after sources path.
    * E.g "packageX/HelloWorld.java"
    */
   protected ArrayList<File> allJavaFiles;
   
}