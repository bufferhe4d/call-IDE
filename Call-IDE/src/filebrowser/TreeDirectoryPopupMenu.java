package filebrowser;

import javax.swing.*;
import java.awt.event.*;

/**
 * The PopupMenu class for folders in the file explorer.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class TreeDirectoryPopupMenu extends JPopupMenu implements ActionListener
{
    //properties
    JMenuItem deleteFolder;
    JMenuItem createFile;
    JMenuItem createFolder;
    JMenuItem projectProperties;
    FileNode  file;
    FileNavigator navigator;
    
    public TreeDirectoryPopupMenu( FileNavigator navigator)
    {
        super();
        
        this.navigator = navigator;
        
        deleteFolder = new  JMenuItem("Delete Folder");
        createFile = new JMenuItem( "Create File");
        createFolder = new JMenuItem( "Create Folder");
        projectProperties = new JMenuItem( "Project Properties");
        
        add(deleteFolder);
        add(createFile);
        add(createFolder);
        addSeparator();
        add(projectProperties);
        
        deleteFolder.addActionListener( this);
        createFile.addActionListener( this);
        createFolder.addActionListener( this);
        projectProperties.addActionListener( this);
    }
    
    public void actionPerformed( ActionEvent e)
    {
        
        if(e.getSource() == deleteFolder)
        {
            int option = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(this),
                    "Are you sure want to delete the folder \"" + file + "\" ?",  "Deleting Folder",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION)
                file.delete();
        }
        else if( e.getSource() == createFile)
        {
            String fileName = JOptionPane.showInputDialog(null, "Enter file name: ",
                    "Creating File", JOptionPane.INFORMATION_MESSAGE);
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
            String fileName = JOptionPane.showInputDialog(null, "Enter folder name: ",
                    "Creating Folder", JOptionPane.INFORMATION_MESSAGE);
            if (fileName == null)
                return;
            if (fileName.trim().length() > 0)
                file.createDirectory(fileName);
        }
        else if (e.getSource() == projectProperties)
        {
            System.out.println("Show project properties...");
        }
        
        navigator.updateUI();
    }
    
    public void setFile( FileNode file)
    {
        this.file = file;
    }
}
