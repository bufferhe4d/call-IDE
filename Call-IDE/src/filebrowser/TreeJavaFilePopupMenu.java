package filebrowser;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * The PopupMenu class for java source files in the file explorer.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class TreeJavaFilePopupMenu extends TreeFilePopupMenu
{
    // PROPERTIES
    JMenuItem compile;
    JMenuItem run;

    // CONSTRUCTORS
    public TreeJavaFilePopupMenu( FileNavigator navigator)
    {
        super(navigator);
        
        compile = new JMenuItem( "Compile File");
        run = new JMenuItem( "Run File");
        add( compile);
        add( run);
        
        compile.addActionListener( this);
        run.addActionListener( this);
    }
    
    // METHODS
    @Override
    public void actionPerformed( ActionEvent e)
    {
        super.actionPerformed( e);
        if( e.getSource() == compile)
        {
            System.out.println("Compiling the file...");
        }
        else if ( e.getSource() == run)
        {
            System.out.println("Running the file...");
        }
        
        navigator.updateUI();
    }
    
}
