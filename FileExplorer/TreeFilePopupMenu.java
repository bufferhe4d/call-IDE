import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class TreeFilePopupMenu extends JPopupMenu
{
    //properties
   JMenuItem delete;
   JMenuItem copy;
   
   public TreeFilePopupMenu()
   {
      delete = new  JMenuItem("Delete");
      copy   = new  JMenuItem("Copy");
      setVisible(true);
   }
   
   
}
