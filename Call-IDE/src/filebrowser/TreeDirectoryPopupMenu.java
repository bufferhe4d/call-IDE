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
 * The PopupMenu class for folders in the file explorer.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class TreeDirectoryPopupMenu extends JPopupMenu implements ActionListener
{
    // PROPERTIES
    JMenuItem deleteFolder;
    JMenuItem createFile;
    JMenuItem createFolder;
    JMenuItem paste;
    JMenuItem projectProperties;
    FileNode file;
    FileNavigator navigator;
    
    public TreeDirectoryPopupMenu( FileNavigator navigator)
    {
        super();
        
        this.navigator = navigator;
        
        deleteFolder = new  JMenuItem( "Delete Folder");
        createFile = new JMenuItem( "Create File");
        createFolder = new JMenuItem( "Create Folder");
        paste = new JMenuItem( "Paste Here");
        projectProperties = new JMenuItem( "Project Properties");
        
        add( deleteFolder);
        add( createFile);
        add( createFolder);
        add( paste);
        addSeparator();
        add( projectProperties);
        
        deleteFolder.addActionListener( this);
        createFile.addActionListener( this);
        createFolder.addActionListener( this);
        paste.addActionListener( this);
        projectProperties.addActionListener( this);
    }
    
    public void actionPerformed( ActionEvent e)
    {
        
        if(e.getSource() == deleteFolder)
        {
            int option = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(this),
                                                       "Are you sure want to delete the folder \"" +
                                                       file + "\" ?",  "Deleting Folder",
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION)
                file.delete();
        }
        
        else if( e.getSource() == createFile)
        {
            String fileName = JOptionPane.showInputDialog(null, "Enter file name: ", "Creating File",
                                                          JOptionPane.INFORMATION_MESSAGE);
            if (fileName == null)
                return;
            try {
                if (fileName.trim().length() > 0)
                    file.createFile(fileName); 
            } catch(Exception exc) {
                exc.printStackTrace();
            }
        }
        
        else if( e.getSource() == createFolder )
        {
            String fileName = JOptionPane.showInputDialog(null, "Enter folder name: ", "Creating Folder",
                                                          JOptionPane.INFORMATION_MESSAGE);
            if (fileName == null)
                return;
            if (fileName.trim().length() > 0)
                file.createDirectory(fileName);
        }
        
        else if (e.getSource() == projectProperties)
        {
            System.out.println("Show project properties...");
        }
        
        else if( e.getSource() == paste )
        {
            try {
                file.pasteFile( navigator.clipboardNode);
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
    
}
