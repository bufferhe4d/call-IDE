package filebrowser;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.*;

/**
 * A JTree component which can navigate through files on the disk.
 * @author Mahmud Sami Aydin
 * @version 1.0
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
        
        directoryMenu = new TreeDirectoryPopupMenu( this);
        fileMenu      = new TreeFilePopupMenu( this);
        javaFileMenu  = new TreeJavaFilePopupMenu( this);
        root = ((FileNode)getModel().getRoot());
        
        add( fileMenu);
        add( directoryMenu);
        
        addTreeSelectionListener( this );
        addMouseListener( new TreeMouseListener() );
    }
    
    public void  openFile( String file)
    {
        root.openFile( file , root.nodesAndPaths );
        updateUI();
    }
    
    public void valueChanged(TreeSelectionEvent e)
    {
        lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( )));
    }
    
    public void updateDirectory( String DirectoryPath )
    {
        ((FileNode)root.nodesAndPaths.get(DirectoryPath)).updateChildren();
        updateUI();
    }
    
    class TreeMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked( MouseEvent e)
        {
            directoryMenu.setVisible(false);
            fileMenu.setVisible(false);
            javaFileMenu.setVisible(false);
            
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile != null
                   && lastSelectedFile.file != null && lastSelectedFile.file.isFile() )
            {
                opener.openFile(lastSelectedFile.file);
            }
            
            if(  e.getButton() == 3 )
            {
                lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent());

                if (lastSelectedFile.isEmpty()) {
                    directoryMenu.setFile( (FileNode) lastSelectedFile.getParent());
                    directoryMenu.show(FileNavigator.this, e.getX(), e.getY());
                    return;
                }

                if (lastSelectedFile.isRoot())
                    return;
                
                FileNavigator.this.setSelectionPath(getClosestPathForLocation(e.getX() , e.getY()));
                
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
            
            updateUI();
        }
    }
}
