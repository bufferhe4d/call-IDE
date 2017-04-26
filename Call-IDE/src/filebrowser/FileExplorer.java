package filebrowser;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    
    public FileExplorer(  ArrayList<String> files  )
    {
        super();
        navigator = new FileNavigator(   files  );
        setLayout( new FlowLayout(FlowLayout.LEFT));
        add(navigator);
        
        setVisible(true);
    }
    
    public FileExplorer(  ArrayList<String> files, FileOpener opener  )
    {
        this(files);
        navigator.opener = opener;
    }
    
    public void openProject( String root )
    {
        navigator.root.add( new FileNode( new PathedFile( root ) , navigator.root.nodesAndPaths ) );
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
    
    public void updateDirectory( String DirectoryPath )
    {
        navigator.updateDirectory( DirectoryPath );
        navigator.updateUI();
    }
}