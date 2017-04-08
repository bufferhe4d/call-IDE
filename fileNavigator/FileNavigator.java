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
      Scanner scan = new Scanner(System.in);
      if(((FileNode)((JTree)e .getSource()).getLastSelectedPathComponent()).file.isFile())
      {
         System.out.println(((FileNode)((JTree)e .getSource()).getLastSelectedPathComponent()).delete()); 
      }
      else
      {
         ((FileNode)((JTree)e .getSource()).getLastSelectedPathComponent()).createFile(scan.next());
      }
      updateUI();
   }
}
