import javax.swing.*;
import java.awt.event.*;
import java.util.*;


/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */

public class FileExplorer extends JPanel
{
   //properties
   FileNavigator navigator;
      
   public FileExplorer( String root )
   {
      super();
      navigator = new FileNavigator(  root );
      add(navigator);
      //addMouseListener( new treeMouseListener() );
      setVisible(true);
   }
   
   public FileExplorer(  ArrayList<String> files  )
   {
      super();
      navigator = new FileNavigator(   files  );
      add(navigator);
      //addMouseListener( new treeMouseListener() );
      setVisible(true);
   }
   
   public void openFile( String file )
   {
      navigator.openFile( file );
   }

   public void openFiles( ArrayList<String> files )
   {
      for( int i = 0 ; i < files.size(); i++)
      {
         navigator.openFile( files.get ( i ) );
      }
   }
   
}