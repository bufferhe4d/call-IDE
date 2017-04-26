package filebrowser;


import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
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
   
   
   public FileExplorer(  ArrayList<String> files  )
   {
      super();
      navigator = new FileNavigator(   files  );
      setLayout( new FlowLayout(FlowLayout.LEFT));
      add(navigator);
     
      addMouseListener( new BrowserMouseListener() );
      setVisible(true);
   }
   
   
      public FileExplorer(  ArrayList<String> files, FileOpener opener  )
   {
      this(files);
      navigator.opener = opener;
   }
      
   public void openProject( String root )
   {
       navigator.root.add( new FileNode( new PathedFile( root ) , navigator.root.nodesAndPaths ) );
       navigator.updateUI();
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
   
    public void updateDirectory( String DirectoryPath )
    {
        // CREATES DUBLICATE FOLDERS/FILES
        // DOES NOT REFRESHES AUTOMATICALLY WITHOUT CLOSING+OPENING THE NODE MANUALLY
        System.out.println("updated " + DirectoryPath);
        navigator.updateDirectory( DirectoryPath );
        navigator.updateUI();
    }
   
   /**
    * 
    * 
    * 
    */
    class BrowserMouseListener extends MouseAdapter
    {
      @Override
      public void mouseExited( MouseEvent e)
      {;
        navigator.directoryMenu.setVisible(false);
        navigator.fileMenu.setVisible(false);
        navigator.javaFileMenu.setVisible(false);
      }
      
       public void mouseClicked( MouseEvent e)
      {
        navigator.directoryMenu.setVisible(false);
        navigator.fileMenu.setVisible(false);
        navigator.javaFileMenu.setVisible(false);
      }
    }
}