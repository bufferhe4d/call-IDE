import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.event.*;

/**
 * Auto Generated Java Class.
 */
public class FileNavigator extends JTree implements TreeSelectionListener
{
   
   //properties
   File lastSelectedFile;
   TreeDirectoryPopupMenu directoryMenu;
   
   public FileNavigator( String root )
   { 
      super( new DefaultTreeModel(new FileNode( new PathedFile( root ))) );
      setRootVisible(false); 
      directoryMenu = new TreeDirectoryPopupMenu();
      if( getParent() != null )
      getParent().add( directoryMenu );
      addTreeSelectionListener( this );
      addMouseListener( new treeMouseListener() );
   }
   
   
   /* ADD YOUR CODE HERE */
   
   public void valueChanged(TreeSelectionEvent e)
   {  
      lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( ))).file;
      //System.out.println("Last seleted file updated");
   }
   
   
   /**
    * 
    * 
    * 
    */
    private class treeMouseListener extends MouseAdapter
    {
       @Override
       public void mouseClicked( MouseEvent e)
       {
          directoryMenu.setVisible(false);
          if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile.isFile()  )
          {
             System.out.println("File openned"); 
          }
          
          if(  e.getButton() == 3 )
          {
             lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent()).file;
             
             if( lastSelectedFile.isDirectory() )
             { 
                System.out.println("Directory Menu");
                directoryMenu.setFile( new FileNode(((PathedFile) lastSelectedFile) ) );
                directoryMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY());
                directoryMenu.setVisible(true);
             }
             else
             {  
                System.out.println("File Menu");
             }
          }
          
          System.out.println(lastSelectedFile);
          updateUI();
       }  
    }
   
   
   
}    


