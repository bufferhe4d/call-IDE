package filebrowser;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;


/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */
public class FileNavigator extends JTree implements TreeSelectionListener
{
    
    //properties
    FileNode               lastSelectedFile;   
    TreeDirectoryPopupMenu directoryMenu;
    TreeFilePopupMenu      fileMenu;
    TreeJavaFilePopupMenu  javaFileMenu;
    FileOpener opener;
    FileNode root;
    
    public FileNavigator(  ArrayList<String> files  )
    { 
        super( new DefaultTreeModel(new FileNode(  files )) );
        
        directoryMenu = new TreeDirectoryPopupMenu();
        fileMenu      = new TreeFilePopupMenu();
        javaFileMenu  = new TreeJavaFilePopupMenu();
        root = ((FileNode)getModel().getRoot());
        
        add( fileMenu );
        add( directoryMenu );
        
        addTreeSelectionListener( this );
        addMouseListener( new TreeMouseListener() );
    }
    
    
    public void  openFile( String file)
    {
        root.openFile( file );
        updateUI();
    }
    
    public void valueChanged(TreeSelectionEvent e)
    {
        lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( )));
    }
    
    /**
     * 
     * 
     */
    public void updateDirectory( String DirectoryPath )
    {
        ((FileNode)root.nodesAndPaths.get(DirectoryPath)).updateChildren();
    }
    
    /**
     * 
     * 
     * 
     */
    class TreeMouseListener extends MouseAdapter
    {
        
        @Override
        public void mouseClicked( MouseEvent e)
        {
            directoryMenu.setVisible(false);
            fileMenu.setVisible(false);
            
            if (e == null)
                return;
            
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile != null && lastSelectedFile.file.isFile() )
            {
                opener.openFile(lastSelectedFile.file);
            }
            
            if(  e.getButton() == 3 )
            {
                lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent());
                
                if( lastSelectedFile.file.isDirectory() )
                { 
                    System.out.println("Directory Menu");
                    directoryMenu.setFile(  lastSelectedFile  );
                    directoryMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY());
                    directoryMenu.setVisible(true);
                }
                else if( lastSelectedFile.file.isJavaFile() )
                {
                   System.out.println("Java File Menu");
                    javaFileMenu.setFile(  lastSelectedFile  );
                    javaFileMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY()); 
                    javaFileMenu.setVisible(true);
                
                }  
                else
                {
                    System.out.println("File Menu");
                    fileMenu.setFile(  lastSelectedFile );
                    fileMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY());
                    fileMenu.setVisible(true);
                }
            }
            
            System.out.println(lastSelectedFile);
            updateUI();
        }
    }
}
