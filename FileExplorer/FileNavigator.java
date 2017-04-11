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
   
   public FileNavigator( String root )
   { 
      super( new DefaultTreeModel(new FileNode( new PathedFile( root ))) );
      setRootVisible(false); 
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
          if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile.isFile()  )
          {
             //System.out.println("File openned"); 
             //There will be tab opened path: (((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( ))).file).getAbsolutePath()
          }

          
          updateUI();
       }  
    }
   
   
   
}    


