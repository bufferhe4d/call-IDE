package filebrowser;

import javax.swing.*;
import java.awt.event.*;

/**
 * The PopupMenu class for java source files in the file explorer.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class TreeJavaFilePopupMenu extends TreeFilePopupMenu
{
    JMenuItem compile;
    JMenuItem run;

    public TreeJavaFilePopupMenu( FileNavigator navigator)
    {
        super(navigator);
        
        compile = new JMenuItem("Compile File");
        run = new JMenuItem("Run File");
        add( compile);
        add( run);
        
        compile.addActionListener( this);
        run.addActionListener( this);
    }
    
    public void actionPerformed( ActionEvent e)
    {
        super.actionPerformed( e);
        if( e.getSource() == compile)
        {
            System.out.println("Compile the file...");
        }
        else if ( e.getSource() == run)
        {
            System.out.println("Run the file...");
        }
        
        navigator.updateUI();
    }
}
