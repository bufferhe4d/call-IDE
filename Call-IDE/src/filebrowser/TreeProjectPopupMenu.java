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
    
    //constructor
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
    //methods
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
