//import java.io.File;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.JFrame;

public class FileTest
{
   final static String root = "/home/solledar/java/fileHiya";
   static ArrayList<TreePath> treePaths;
   
   public static void main( String[] args)
   {
      JFrame d = new JFrame();
      PathedFile f = new PathedFile ( root );
      FileNode n = new FileNode( f );
      //new PathedFile( root + "/dasdas4 ");
      n.addChildrens();
      int indexPaths;
      JTree tree;
      tree = new JTree( n );
      d.add( tree );
      d.setVisible(true);
      //directorySearch( f );
      


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