package filebrowser;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    // CONSTRUCTOR
    public TreeFilePopupMenu( FileNavigator navigator)
    {
        super();
        
        this.navigator = navigator;
        
        delete = new JMenuItem("Delete File");
        copy = new JMenuItem("Copy File");
        paste = new JMenuItem("Paste Here...");
        
        add( delete );
        add(copy);
        add( paste );
        
        delete.addActionListener( this);
        copy.addActionListener( this);
        paste.addActionListener(this);
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
            System.out.println(" copy file ");
            copyFile();
        }
        else if( e.getSource() == paste )
        {
            try {
                ((FileNode)file.getParent()).pasteFile( navigator.clipboardNode );
                System.out.println(" paste here ");
            } catch (IOException ex) {
                Logger.getLogger(TreeFilePopupMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        navigator.updateUI();
    }
    
    public void setFile( FileNode file )
    {
        this.file = file;
    }
    
    public void copyFile()
    {
        navigator.clipboardNode = file;
    }
}
