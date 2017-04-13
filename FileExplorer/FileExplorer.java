import javax.swing.*;
import java.awt.event.*;

public class FileExplorer extends JPanel
{
   //properties
   FileNavigator navigator;
      
   public FileExplorer( String root )
   {
      super();
      navigator = new FileNavigator(  root );
      add(navigator);
      //addMouseListener( new treeMouseListener() );
      setVisible(true);
   }
   

}