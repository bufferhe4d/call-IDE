package filebrowser;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTree;

/**
 * A panel which contains a navigator to browse files
 * @author Mahmud Sami Aydin
 * @version 1.10 ,  29/04/2017
 */
public class FileExplorer extends JPanel
{
    // PROPERTIES
    FileNavigator navigator;
    
    // CONSTRUCTORS
    /**
     * This constructor construct an explorer which take many files and a file opener 
     * @param files list of taken files
     * @param opener file opener which opens files
     */
    public FileExplorer( ArrayList<String> files, NavigationParent opener)
    {
        super();
        navigator = new FileNavigator( files, opener);
        setLayout( new FlowLayout( FlowLayout.LEFT));
        add( navigator);
        setVisible( true);
    }
    
    // METHODS
    /**
     * This method opens a project with given root it adds nodes of project root and children
     * @param root file path of project root
     */
    public void openProject( String root)
    {
        navigator.root.add( new FileNode( new PathedFile( root, navigator.root.file.path), navigator.root.nodesAndPaths));
        navigator.updateUI();
    }
        
    /**
     * This method sets the explorer is browsing project
     * @param isProjectBrowser
     */
    public void setIsProjectBrowser( boolean isProjectBrowser)
    {
        navigator.root.setIsBrowsingProjects( isProjectBrowser);        
        navigator.updateUI();
    }
    
    /**
     * This method determines the explorer is browsing project 
     * @return 
     */
    public boolean isProjectBrowser()
    {
        return navigator.root.isBrowsingProjects();
    }
    
    /**
     * This method update directory node and its children,
     * @param directoryPath path of directory updated
     */
    public void updateDirectory( String directoryPath)
    {
        navigator.updateDirectory(directoryPath);
        navigator.updateUI();
    }
    
    /**  This method expands the root of the tree. */
    public void expandRoot() {
        navigator.expandRow(1);
    }
    
    /** This method refreshes the all nodes of the tree. */
    public void refreshAll() {
        for (int i = navigator.root.getChildCount();  i > 0 ; i-- )
            ((FileNode) navigator.root.getChildAt(i-1)).updateChildren();
        navigator.updateUI();
    }
    
    /** This method gives the tree component of the file explorer. */
    public JTree getTree() {
        return navigator;
    }
}
