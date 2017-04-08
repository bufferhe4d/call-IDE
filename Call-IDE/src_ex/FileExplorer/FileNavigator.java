package FileExplorer;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

/**
 * Auto Generated Java Class.
 */
public class FileNavigator extends JTree implements TreeSelectionListener
{
 
   
   public FileNavigator( String root )
   { 
      super( new DefaultTreeModel(new FileNode( new PathedFile( root ))) );
      setRootVisible(false); 
      addTreeSelectionListener( this );
      
   }
   
   
   /* ADD YOUR CODE HERE */
   
   public void valueChanged(TreeSelectionEvent e)
   {  
      System.out.println((((FileNode)((JTree)e .getSource()).getLastSelectedPathComponent()).file).getName());
      //updateUI();
   }
   
   
}
