package filebrowser;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.*;

/**
 * A JTree component which can navigate through files on the disk.
 * @author Mahmud Sami Aydin
 * @version 1.10
 */
public class FileNavigator extends JTree implements TreeSelectionListener
{
    
    //properties
    FileNode               lastSelectedFile;   
    TreeDirectoryPopupMenu directoryMenu;
    TreeFilePopupMenu      fileMenu;
    TreeJavaFilePopupMenu  javaFileMenu;
    FileOpener             opener;
    FileNode               root;
    FileNode               clipboardNode;
    
    //constructor
    /**
     *  This constructor create jTree with virtual root node add pop up menus in it and add listeners for interaction
     *  @param files file path added to virtual node
     */
    public FileNavigator(  ArrayList<String> files  )
    { 
        //call super constructor
        super( new DefaultTreeModel(new FileNode(  files )) );
        
        //add menus and refere virtual root
        directoryMenu = new TreeDirectoryPopupMenu( this);
        fileMenu      = new TreeFilePopupMenu( this);
        javaFileMenu  = new TreeJavaFilePopupMenu( this);
        root = ((FileNode)getModel().getRoot());
        
        //add menus
        add( fileMenu);
        add( directoryMenu);
        
        //add listeners
        addTreeSelectionListener( this );
        addMouseListener( new TreeMouseListener() );
    }
    
    /**
     * This method open a file and update ui
     * @param file path of file will be open
     */
    public void  openFile( String file)
    {
        root.openFile( file , root.nodesAndPaths );
        updateUI();
    }
    
    /**
     * This method get last selected node
     * @param e Tree selection event
     */
     @Override
    public void valueChanged(TreeSelectionEvent e)
    {
        lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( )));
    }
    
    /**
     * This method get node from path and update its children
     * @param DirectoryPath path of directory will be updated
     */
    public void updateDirectory( String DirectoryPath )
    {
        ((FileNode)root.nodesAndPaths.get(DirectoryPath)).updateChildren();
        updateUI();
    }
    
    /**
     * This method determines clipboardIsEmpty or not
     * @return clipboard is emtpy
     */
    public boolean clipboradIsEmpty()
    {
        return clipboardNode == null ;
    }
    
    /**
     * This class added for listen tree and provide to interact properly
     */
    class TreeMouseListener extends MouseAdapter
    {
        
        /**
         *This method interact with user when user click the mouse
         *@param e Mouse event
         */
        @Override
        public void mouseClicked( MouseEvent e)
        {
            //hide all menus
            directoryMenu.setVisible(false);
            fileMenu.setVisible(false);
            javaFileMenu.setVisible(false);
            
            //if left click is double clicked when opn file on editor
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile != null
                   && lastSelectedFile.file != null && lastSelectedFile.file.isFile() )
            {
                opener.openFile(lastSelectedFile.file);
            }
            
            //if right clicked pop up menu
            if(  e.getButton() == 3 )
            {
                //update last selection
                lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent());
                
                //conrtol if it empty file show parent
                if (lastSelectedFile.isEmpty()) {
                    directoryMenu.setFile( (FileNode) lastSelectedFile.getParent());
                    directoryMenu.show(FileNavigator.this, e.getX(), e.getY());
                    return;
                }

                //control root
                if (lastSelectedFile.isRoot())
                    return;
                
                //set selection path
                FileNavigator.this.setSelectionPath(getClosestPathForLocation(e.getX() , e.getY()));
                
                //show proper menu
                if( lastSelectedFile.file.isDirectory() )
                {
                    directoryMenu.setFile( lastSelectedFile);
                    directoryMenu.show(FileNavigator.this, e.getX(), e.getY());
                }
                else if( lastSelectedFile.file.isJavaFile() )
                {
                    javaFileMenu.setFile( lastSelectedFile);
                    javaFileMenu.show(FileNavigator.this, e.getX(), e.getY());
                }    
                else
                {
                    fileMenu.setFile( lastSelectedFile);
                    fileMenu.show(FileNavigator.this, e.getX(), e.getY());
                }
            }
            
            //update ui
            updateUI();
        }
    }
    
}
