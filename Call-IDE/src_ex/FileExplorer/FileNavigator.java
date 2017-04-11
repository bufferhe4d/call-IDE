package FileExplorer;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.event.*;

public class FileNavigator extends JTree implements TreeSelectionListener
{
    
    //properties
    File lastSelectedFile;
    FileOpener opener;
    
    public FileNavigator( String root )
    { 
        super( new DefaultTreeModel(new FileNode( new PathedFile( root ))) );
        setRootVisible(false); 
        addTreeSelectionListener( this );
        addMouseListener( new treeMouseListener() );
    }
    
    public FileNavigator( String root, FileOpener opener) {
        this( root);
        this.opener = opener;
    }
    
    public void valueChanged(TreeSelectionEvent e)
    {  
        lastSelectedFile = ((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent( ))).file;
    }
    
    private class treeMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked( MouseEvent e)
        {
            if( e.getButton() == 1 && e.getClickCount() == 2 && lastSelectedFile.isFile()  )
            {
                System.out.println((((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent())).file));
                opener.openFile((((FileNode) (((JTree)(e .getSource())).getLastSelectedPathComponent())).file));
            }
        }  
    }
    
}
