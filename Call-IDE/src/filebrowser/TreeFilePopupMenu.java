package filebrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * The PopupMenu class for files in the file explorer.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class TreeFilePopupMenu extends JPopupMenu implements ActionListener
{
    // PROPERTIES
    JMenuItem delete;
    JMenuItem copy;
    JMenuItem paste;
    FileNode file;
    FileNavigator navigator;
    
    // CONSTRUCTORS
    public TreeFilePopupMenu( FileNavigator navigator)
    {
        super();
        
        this.navigator = navigator;
        
        delete = new JMenuItem( "Delete File");
        copy = new JMenuItem( "Copy File");
        paste = new JMenuItem( "Paste");
        
        add( delete);
        add( copy);
        add( paste);
        
        delete.addActionListener( this);
        copy.addActionListener( this);
        paste.addActionListener( this);
    }
    
    // METHODS
    public void actionPerformed( ActionEvent e)
    {
        setVisible(false);
        
        if(e.getSource() == delete)
        {
            int option = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(this),
                    "Are you sure want to delete the file \"" + file + "\" ?",  "Deleting File",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION)
                file.delete();
        }
        
        else if( e.getSource() == copy )
        {
            copyFile();
        }
        
        else if( e.getSource() == paste )
        {
            try {
                ((FileNode)file.getParent()).pasteFile( navigator.clipboardNode);
            } catch (IOException ex) {
                Logger.getLogger(TreeFilePopupMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        navigator.updateUI();
    }
    
    public void setFile( FileNode file)
    {
        this.file = file;
    }
    
    public void copyFile()
    {
        navigator.clipboardNode = file;
    }
    
}
