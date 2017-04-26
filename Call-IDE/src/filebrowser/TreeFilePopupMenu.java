package filebrowser;

import javax.swing.*;
import java.awt.event.*;

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
    FileNode file;
    FileNavigator navigator;
    
    // CONSTRUCTOR
    public TreeFilePopupMenu( FileNavigator navigator)
    {
        super();
        
        this.navigator = navigator;
        
        delete = new JMenuItem("Delete File");
        copy = new JMenuItem("Copy File");
        
        add( delete );
        add(copy);
        
        delete.addActionListener( this);
        copy.addActionListener( this);
    }
    
    // METHODS
    public void actionPerformed( ActionEvent e)
    {
        setVisible(false);
        if(e.getSource() == delete)
        {
            int option = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(this),
                    "Are you sure want to delete this file?",  "Deleting File",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION)
                file.delete();
        }
        else if( e.getSource() == copy )
        {
            System.out.println(" copy file ");
            file.copyFile();
        }
        
        navigator.updateUI();
    }
    
    public void setFile( FileNode file )
    {
        this.file = file;
    }
}
