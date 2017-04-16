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
    add( compile );
    add( run );
    
    compile.addActionListener( this );
    run.addActionListener( this );
    
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
      System.out.println("file compiled");
    }
  }
}