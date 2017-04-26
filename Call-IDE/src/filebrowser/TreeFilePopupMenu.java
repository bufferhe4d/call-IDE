package filebrowser;

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
       super();
      delete = new  JMenuItem("Delete");
      copy   = new  JMenuItem("Copy");
      close = new JMenuItem( "Close");
      
      add( delete );
      add(copy);
      add(close);
      
      delete.setOpaque(true);
      copy.setOpaque(true);
      close.setOpaque(true);

      
      delete.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            System.out.print("enter");
            delete.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            System.out.print("exit");
            delete.setBackground( new Color(240,240,240));
        } 
        
      }
      );
      
      copy.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            copy.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            copy.setBackground(new Color(240,240,240));
        } 
        
      }
      );
      
      close.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            close.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            close.setBackground( new Color(240,240,240) );
        } 
        
      }
      );
      delete.addActionListener( this );
      copy.addActionListener( this );
      close.addActionListener( this );
   }
   
   public void actionPerformed( ActionEvent e)
   {
       setVisible(false);
      if(e.getSource() == delete)
      {
         //delete directory..
         System.out.println(" Delete ");
         file.delete();
         
      }
      else if( e.getSource() == copy )
      {
         //copy the file
         System.out.println(" copy file ");
         file.copyFile();

      }
      else if( e.getSource() == close )
      {
         //Close file
         System.out.println(" close file ");
         file.closeFile();

      }
      
      
     
   }
   
   
   public void setFile( FileNode file )
   {
      this.file = file;
   }
   
  
}
