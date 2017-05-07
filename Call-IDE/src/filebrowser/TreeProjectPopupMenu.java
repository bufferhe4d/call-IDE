package filebrowser;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * This class represent a pop up menu for project root
 * @author Mahmud Sami Aydin
 * @version 1.00, 1/5/2017
 */
public class TreeProjectPopupMenu extends TreeDirectoryPopupMenu{
    
    //properties
    JMenuItem projectProperties;
    JMenuItem closeProject;
    NavigationParent opener;
    
    //constructor
    public TreeProjectPopupMenu(FileNavigator navigator) {
        
        super(navigator);
        
        this.opener = navigator.opener;
        
        
        projectProperties = new JMenuItem( "Project Properties");
        closeProject = new JMenuItem( "Close Project");
        
         deleteFolder.setVisible( false);
        createFile.setVisible( false);
        createFolder.setVisible( false);
        paste.setVisible( false);
        add( projectProperties);
        add( closeProject);
        
        projectProperties.addActionListener( this);
        closeProject.addActionListener( this);
    }
    //methods
    public void actionPerformed( ActionEvent e)
    {
       if (e.getSource() == projectProperties)
        {
            opener.showProjectProperties( file.getFile());
        }
       else if (e.getSource() == closeProject )
        {
            opener.closeProject(file.getFile());
            navigator.closeProject(file);
        }

       navigator.updateUI();
    }
}
