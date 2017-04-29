package filebrowser;

import java.awt.FlowLayout;
import javax.swing.*;
import java.util.*;

/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */

public class FileExplorer extends JPanel
{
    // Properties
    FileNavigator navigator;
    
    public FileExplorer(  ArrayList<String> files, FileOpener opener    )
    {
        super();
        navigator = new FileNavigator(   files  );
        setLayout( new FlowLayout(FlowLayout.LEFT));
        add(navigator);
        navigator.opener = opener;
        
        setVisible(true);
    }
    
    public void openProject( String root )
    {
        navigator.root.add( new FileNode( new PathedFile( root , navigator.root.file.path ) , navigator.root.nodesAndPaths ) );
        navigator.updateUI();
    }
    
    public void openFile( String file )
    {
        navigator.openFile( file );
    }
    
    public void openFiles( ArrayList<String> files )
    {
        for( int i = 0 ; i < files.size(); i++)
        {
            navigator.openFile( files.get ( i ) );
        }
    }
    
    public void convertToWorkspace( boolean makeWorkspace)
    {
            navigator.root.setWorkspace(makeWorkspace);
    }
    
    public void updateDirectory( String DirectoryPath )
    {
        navigator.updateDirectory( DirectoryPath );
        navigator.updateUI();
    }
}
