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
    File lastSelectedFile;   
    TreeDirectoryPopupMenu directoryMenu;
    TreeFilePopupMenu      fileMenu;
    FileOpener opener;
    
    public FileNavigator(  ArrayList<String> files  )
    { 
        super( new DefaultTreeModel(new FileNode(  files )) );
        
        directoryMenu = new TreeDirectoryPopupMenu();
        fileMenu      = new TreeFilePopupMenu();
        
        add( fileMenu );
        add( directoryMenu );
        
        addTreeSelectionListener( this );
        addMouseListener( new TreeMouseListener() );
    }
    
    public FileNavigator(  String root  )
    { 
        super( new DefaultTreeModel(new FileNode( new PathedFile( root ))) );
        setRootVisible(false); 
        
        directoryMenu = new TreeDirectoryPopupMenu();
        fileMenu      = new TreeFilePopupMenu();
        
        add( fileMenu );
        add( directoryMenu );
        
        addTreeSelectionListener( this );
        addMouseListener( new TreeMouseListener() );
    }
    
    public FileNavigator( String root, FileOpener opener) {
        this( root);
        this.opener = opener;
    }
    
    public void  openFile( String file)
    {
        ((FileNode)getModel().getRoot()).openFile( file );
        updateUI();
    }
    
    public void valueChanged(TreeSelectionEvent e)
    {
        lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( ))).file;
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
            
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile.isFile()  )
            {
                opener.openFile(lastSelectedFile);
            }
            
            if(  e.getButton() == 3 )
            {
                lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent()).file;
                
                if( lastSelectedFile.isDirectory() )
                { 
                    System.out.println("Directory Menu");
                    directoryMenu.setFile( new FileNode(((PathedFile) lastSelectedFile) ) );
                    directoryMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY());
                    directoryMenu.setVisible(true);
                }
                else
                {  
                    System.out.println("File Menu");
                    fileMenu.setFile( new FileNode(((PathedFile) lastSelectedFile) ) );
                    fileMenu.setLocation( (int)getLocationOnScreen().getX() + e.getX() ,  (int)getLocationOnScreen().getY() + e.getY());
                    fileMenu.setVisible(true);
                }
            }
            
            System.out.println(lastSelectedFile);
            updateUI();
        }
    }
}
