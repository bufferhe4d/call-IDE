package FileExplorer;

//import java.io.File;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.*;

public class FileTest
{
   final static String root = "D:\\test";
   static ArrayList<TreePath> treePaths;
   
   public static void main( String[] args)
   {
      ArrayList<String> ways = new ArrayList<String>();
      ways.add(root);
     // ways.add("/home/solledar/04_HW01_Aydin_MahmudSami");
      JFrame d = new JFrame();
      JPanel panel = new JPanel();
      FileExplorer f = new FileExplorer ( ways  );
      
      
      panel.add( f );
      d.add(panel);
      
      d.pack();
      d.setVisible(true);
      Scanner scan = new Scanner( System.in);
      scan.next();
      f.openFile( "D:\\test2" );
      

   }
   
   public static void directorySearch( PathedFile f )
   {
      PathedFile tempFile;

      for(int i=0 ; i < f.list().length; i++)
      {
         tempFile = new PathedFile(f.toString() +"/" +f.list()[i], f.getObjPath() );
         if( tempFile. isDirectory() )
            directorySearch( tempFile );
      }
   }
}