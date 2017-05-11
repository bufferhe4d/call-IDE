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
    NavigationParent opener;

    // CONSTRUCTORS
    public TreeJavaFilePopupMenu( FileNavigator navigator)
    {
        super(navigator);
        
        this.opener = navigator.opener;
        
        compile = new JMenuItem( "Compile File");
        run = new JMenuItem( "Run File");
        
        addSeparator();
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
            opener.compileSelectedFile( file.getFile());
        }
        else if ( e.getSource() == run)
        {
            opener.runSelectedFile( file.getFile());
        }
        navigator.updateUI();
    }
    
}
