package filebrowser;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

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
    public FileExplorer( ArrayList<String> files, FileOpener opener)
    {
        super();
        navigator = new FileNavigator( files);
        setLayout( new FlowLayout( FlowLayout.LEFT));
        add( navigator);
        navigator.opener = opener;
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
     * This method opens single file
     * @param file path of file
     */
    public void openFile( String file)
    {
        navigator.openFile( file);
    }
    
    /**
     * This method opens many files
     * @param files paths of files
     */    
    public void openFiles( ArrayList<String> files)
    {
        for (int i = 0 ; i < files.size(); i++)
        {
            navigator.openFile( files.get ( i));
        }
    }
    
    /**
     * This method converts explorer workspace if parameter true otherwise it make explorer recent files
     * @param makeWorkspace if true make workspace
     */
    public void convertToWorkspace( boolean makeWorkspace)
    {
        navigator.root.setWorkspace( makeWorkspace);
        if (makeWorkspace) { // clear the old browser node
            ((DefaultMutableTreeNode) navigator.root.getChildAt(0)).removeFromParent();
        }
        navigator.updateUI();
    }
    
    public boolean isWorkspace()
    {
        return navigator.root.isWorkspace();
    }
    
    /**
     * This method update directory node and its children,
     * @param DirectoryPath path of directory updated
     */
    public void updateDirectory( String DirectoryPath)
    {
        navigator.updateDirectory( DirectoryPath);
        navigator.updateUI();
    }

}
