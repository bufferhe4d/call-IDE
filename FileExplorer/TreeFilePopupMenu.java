import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */
public class TreeFilePopupMenu extends JPopupMenu
{
    //properties
   JMenuItem delete;
   JMenuItem copy;
   JMenuItem close;
   
   public TreeFilePopupMenu()
   {
      delete = new  JMenuItem("Delete");
      copy   = new  JMenuItem("Copy");
      close = new JMenuItem( "Close");
      setVisible(true);
   }
   
   
}
