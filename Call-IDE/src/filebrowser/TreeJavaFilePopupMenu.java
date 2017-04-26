package filebrowser;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


/**
 * 
 * @Mahmud Sami Aydin
 * 
 */
public class TreeJavaFilePopupMenu extends TreeFilePopupMenu
{
  //properties
  JMenuItem compile;
  JMenuItem run;
  
  //constructor
  public TreeJavaFilePopupMenu()
  {
    setVisible(false);
    compile = new JMenuItem("Compile File");
    run = new JMenuItem("Run File");
    add( compile );
    add( run );
    
    compile.addActionListener( this );
    run.addActionListener( this );
    
    compile.setOpaque(true);
      run.setOpaque(true);
      
      

      
      compile.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            System.out.print("enter");
            compile.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            System.out.print("exit");
            compile.setBackground( new Color(240,240,240));
        } 
        
      }
      );
      
      run.addMouseListener( new  MouseAdapter()
      {
        public void mouseEntered( MouseEvent e)
        { 
            run.setBackground( new Color( 145,201,247 ));
        } 
        
         public void mouseExited( MouseEvent e)
        { 
            run.setBackground(new Color(240,240,240));
        } 
        
      }
      );
    
  }
  
  @Override
   public void actionPerformed( ActionEvent e)
   {
    super.actionPerformed( e );
    if( e.getSource() == compile )
    {
      System.out.println("file compiled");
    }
    else if ( e.getSource() == run )
    {
      System.out.println("file run");
    }
  }
}
