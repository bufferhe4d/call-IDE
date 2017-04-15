package FileExplorer;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */
public class TreeFilePopupMenu extends JPopupMenu implements ActionListener
{
    //properties
   JMenuItem delete;
   JMenuItem copy;
   JMenuItem close;
   FileNode file;
   
   public TreeFilePopupMenu()
   {
      delete = new  JMenuItem("Delete");
      copy   = new  JMenuItem("Copy");
      close = new JMenuItem( "Close");
      
      add( delete );
      add(copy);
      add(close);
      
      delete.addActionListener( this );
      copy.addActionListener( this );
      close.addActionListener( this );
      
      setPreferredSize( new Dimension ( 150 , 100 ));


      
   }
   
   public void actionPerformed( ActionEvent e)
   {
      if(e.getSource() == delete)
      {
         //delete directory..
         System.out.println(" Delete ");
         //file.delete();
         
      }
      else if( e.getSource() == copy )
      {
         //copy the file
         System.out.println(" copy file ");


      }
      else if( e.getSource() == close )
      {
         //Close file
         System.out.println(" create directory ");
  

      }
      else
      {
         //go to project properties..
         System.out.println(" project properties ");

         
      }
      setVisible(false);
     
   }
   
   
   public void setFile( FileNode file )
   {
      this.file = file;
   }
   
   
}
