package filebrowser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;

/**
 * A JTree component which can navigate through files on the disk.
 * @author Mahmud Sami Aydin
 * @version 1.10
 */
public class FileNavigator extends JTree implements TreeSelectionListener
{
    // PROPERTIES
    FileNode               lastSelectedFile;   
    TreeDirectoryPopupMenu directoryMenu;
    TreeFilePopupMenu      fileMenu;
    TreeJavaFilePopupMenu  javaFileMenu;
    TreeProjectPopupMenu   projectMenu;
    NavigationParent       opener;
    FileNode               root;
    FileNode               clipboardNode;
    
    // CONSTRUCTORS
    /**
     *  This constructor creates a JTree with virtual root node add pop up menus in it and add listeners for interaction
     *  @param files file path added to virtual node
     */
    public FileNavigator( ArrayList<String> files, NavigationParent opener)
    { 
        // Call super constructor
        super( new DefaultTreeModel( new FileNode(files)));
        
        // Set the parent frame reference
        this.opener = opener;
        
        // Add menus and refere virtual root
        directoryMenu = new TreeDirectoryPopupMenu( this);
        fileMenu = new TreeFilePopupMenu( this);
        javaFileMenu = new TreeJavaFilePopupMenu( this);
        projectMenu = new TreeProjectPopupMenu( this);
        root = (FileNode) getModel().getRoot();
        
        //sort the tree
        root.updateChildren();
        
        // Add listeners
        addTreeSelectionListener( this);
        addMouseListener( new TreeMouseListener());
    }
    
    /**
     * This method get last selected node
     * @param e Tree selection event
     */
    @Override
    public void valueChanged( TreeSelectionEvent e)
    {
        lastSelectedFile = (FileNode) (((JTree)(e.getSource())).getLastSelectedPathComponent());
        lastSelectedFile.shallowUpdate();
    }
    
    /**
     * This method get node from path and update its children
     * @param directoryPath path of directory will be updated
     */
    public void updateDirectory( String directoryPath )
    {
        ((FileNode) root.nodesAndPaths.get(directoryPath)).updateChildren();
        updateUI();
    }
    
    /**
     * 
     * 
     */
    public void closeProject( FileNode rootOfProject )
    {
        rootOfProject.closeFile();
        updateUI();
    }
        
    /**
     * This class added for listening tree and providing interactions
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
            // Hide all menus
            directoryMenu.setVisible(false);
            fileMenu.setVisible(false);
            javaFileMenu.setVisible(false);
            
            // If left button is double clicked, open it on the editor.
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile != null
                   && lastSelectedFile.file != null && lastSelectedFile.file.isFile() )
            {
                opener.openFile(lastSelectedFile.file);
            }
            
            // If right button is clicked show pop up menu
            if(  e.getButton() == 3 )
            {
                // Update last selection
                lastSelectedFile =   ((FileNode)getClosestPathForLocation(e.getX() , e.getY()).getLastPathComponent());
                
                // Check if it's an empty file to show parent
                if (lastSelectedFile.isEmpty()) {
                    directoryMenu.setFile( (FileNode) lastSelectedFile.getParent());
                    directoryMenu.show(FileNavigator.this, e.getX(), e.getY());
                    return;
                }
                
                // Check root
                if (lastSelectedFile.isRoot())
                    return;
                
                // Set selection path
                FileNavigator.this.setSelectionPath(getClosestPathForLocation(e.getX() , e.getY()));
                
                // Show proper menu
                if( lastSelectedFile.file.isDirectory() )
                {
                    if(root.isBrowsingProjects && ((FileNode)lastSelectedFile.getParent()).isRoot() )
                    {
                        projectMenu.setFile( lastSelectedFile);
                        projectMenu.show(FileNavigator.this, e.getX(), e.getY());
                    }
                    else
                    {
                        directoryMenu.setFile( lastSelectedFile);
                        directoryMenu.show(FileNavigator.this, e.getX(), e.getY());
                    }
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
