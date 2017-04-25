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
   
      
   public FileExplorer( String root )
   {
      super();
      navigator = new FileNavigator(  root );
      setLayout( new FlowLayout(FlowLayout.LEFT));
      add(navigator);

      setVisible(true);
   }
   
   public FileExplorer(  ArrayList<String> files  )
   {
      super();
      navigator = new FileNavigator(   files  );
      setLayout( new FlowLayout(FlowLayout.LEFT));
      add(navigator);
     
      addMouseListener( new BrowserMouseListener() );
      setVisible(true);
   }
   
      
   public FileExplorer( String root, FileOpener opener) {
       this(root);
       navigator.opener = opener;
   }
   
      public FileExplorer(  ArrayList<String> files, FileOpener opener  )
   {
      this(files);
      navigator.opener = opener;
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
   
    public void updateProjectFiles( String projectRootPath )
    {
        // This method is not working...
        System.out.println("updated " + projectRootPath);
        navigator.updateProjectFiles(  projectRootPath );
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
      {
        System.out.println("Exited");
        navigator.directoryMenu.setVisible(false);
        navigator.fileMenu.setVisible(false);
        navigator.javaFileMenu.setVisible(false);
      }
      
       public void mouseClicked( MouseEvent e)
      {
        System.out.println("Clicked");
        navigator.directoryMenu.setVisible(false);
        navigator.fileMenu.setVisible(false);
        navigator.javaFileMenu.setVisible(false);
      }
    }
}