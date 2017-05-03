package filebrowser;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

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
     * @param isProjectBrowser
     */
    public void setIsProjectBrowser( boolean isProjectBrowser)
    {
        navigator.root.setIsBrowsingProjects( isProjectBrowser);        
        navigator.updateUI();
    }
    
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
    
}
