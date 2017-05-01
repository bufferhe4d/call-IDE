/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filebrowser;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 *
 * @author mahmudsami
 */
public class TreeProjectPopupMenu extends TreeDirectoryPopupMenu{
    
    
    JMenuItem projectProperties;
    JMenuItem closeProject;
    public TreeProjectPopupMenu(FileNavigator navigator) {
        
        super(navigator);
        
        projectProperties = new JMenuItem( "Project Properties");
        
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
