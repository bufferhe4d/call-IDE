package filebrowser;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * This class represent a pop up menu for project root
 * @author Mahmud Sami Aydin
 */
public class TreeProjectPopupMenu extends TreeDirectoryPopupMenu{
    
    
    JMenuItem projectProperties;
    JMenuItem closeProject;
    
    public TreeProjectPopupMenu(FileNavigator navigator) {
        
        super(navigator);
        
        projectProperties = new JMenuItem( "Project Properties");
        closeProject = new JMenuItem( "Close Project");
        
        addSeparator();
        add( projectProperties);
        add( closeProject);
        
        projectProperties.addActionListener( this);
        closeProject.addActionListener( this);
    }
    
    public void actionPerformed( ActionEvent e)
    {
       if (e.getSource() == projectProperties)
        {
            System.out.println("Show project properties...");
        }
       else if (e.getSource() == closeProject )
        {
            System.out.println("close project ");
        }

       navigator.updateUI();
    }
}
