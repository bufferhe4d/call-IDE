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

public class TreeDirectoryPopupMenu extends JPopupMenu implements ActionListener
{
   //properties
   JMenuItem delete;
   JMenuItem createFile;
   JMenuItem createDirectory;
   JMenuItem projectProperties;
   FileNode  file;
   
   public TreeDirectoryPopupMenu(  )
   {
      super();
      
      delete = new  JMenuItem("Delete");
      createFile = new JMenuItem ( "Create File" );
      createDirectory = new JMenuItem( "Create Directory");
      projectProperties = new JMenuItem( "Project Properties");
      
      add( delete );
      add(createFile);
      add(createDirectory);
      addSeparator();
      add(projectProperties);
      
      delete.setOpaque(true);
      createFile.setOpaque(true);
      createDirectory.setOpaque(true);
      projectProperties.setOpaque(true);
      
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
      
      createFile.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            createFile.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            createFile.setBackground(new Color(240,240,240));
        } 
        
      }
      );
      
      createDirectory.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            createDirectory.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            createDirectory.setBackground( new Color(240,240,240) );
        } 
        
      }
      );
      
      projectProperties.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            projectProperties.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            projectProperties.setBackground( new Color(240,240,240) );
        } 
        
      }
      );
      delete.addActionListener( this );
      createFile.addActionListener( this );
      createDirectory.addActionListener( this );
      projectProperties .addActionListener( this );
   }
   
   public void actionPerformed( ActionEvent e)
   {
      if(e.getSource() == delete)
      {
         //delete directory..
         System.out.println(" Delete ");
         file.delete();
         
      }
      else if( e.getSource() == createFile )
      {
         //create file on directory
         System.out.println(" create file ");


      }
      else if( e.getSource() == createDirectory )
      {
         //create directory on directory
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
