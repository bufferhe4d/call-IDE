package filebrowser;

import fileoperations.ContentReader;
import fileoperations.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * A class to represent a single file node on the file navigator
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
public class FileNode extends DefaultMutableTreeNode
{
    
    PathedFile file;
    boolean    allowsChildren;
    HashMap    nodesAndPaths;
    boolean    isBrowsingProjects;
    
    public FileNode( ArrayList<String> files )
    {
        super();
        FileNode temp;
        allowsChildren = true;
        nodesAndPaths = new HashMap();
        
        file = new PathedFile( "/DEFAULT_PATH/");
        
        nodesAndPaths.put( file.getAbsolutePath(), this);
        
        for (int i =0 ; i < files.size(); i++)
        {
            temp = new FileNode( new PathedFile ( files.get(i), file.getObjPath()), nodesAndPaths);
            if(getIndex( temp ) == -1 && temp != null)
                add( temp );
        }
        
        checkEmptyDir();
    }
    
    public FileNode( PathedFile file, HashMap nodesAndPaths)
    {
        super();
        this.file = file;
        allowsChildren = this.file.isDirectory();
        
        this.nodesAndPaths =  nodesAndPaths;
        
        nodesAndPaths.put(this.file.getAbsolutePath(), this);
        
        if( getAllowsChildren())
            addChildren();
        
        checkEmptyDir();
    }
    
    public FileNode( PathedFile file)
    {
        super();
        this. file = file;
        allowsChildren = this.file.isDirectory();
        
        nodesAndPaths.put(this.file.getAbsolutePath(), this);
        
        if( getAllowsChildren() )
            addChildren();
        
        checkEmptyDir();
    }
    
    /** Creates an empty file node. */
    public FileNode( String emptyParent, HashMap nodesAndPaths)
    {
        super();
        this.file = null;
        
        this.nodesAndPaths =  nodesAndPaths;
        
        nodesAndPaths.put( emptyParent + "<empty>", this);
    }
    
    public void checkEmptyDir()
    {
        if (this.file.isDirectory() && this.file.list() != null && this.file.list().length == 0)
            addEmptyChildren();
    }
    
    public void addEmptyChildren()
    {
        add ( new FileNode( this.file.getAbsolutePath(), nodesAndPaths));
    }
    
    public void addChildren()
    {
        if (file.listFiles() != null) 
        {
            for( int i = 0; i < file.listFiles().length; i++)
            {
                if(!nodesAndPaths.containsKey(file.listFiles()[i].getAbsolutePath()))
                {
                    add( new FileNode( new PathedFile( file.listFiles()[i].getAbsolutePath(), file.path), nodesAndPaths));
                }
            }
        }
    }
    
    @Override
    public boolean getAllowsChildren()
    {
        return allowsChildren;
    }
    
    @Override
    public String toString()
    {
        if (file == null)
            return "<empty>";
        if (isRoot())
        {
            if (isBrowsingProjects)
                return "Active Projects";
            else
                return "File Explorer";
        }
        return file.toString();
    }
    
    public boolean equals( FileNode n)
    {
        return file.getPath().equals( n.file.getPath());
    }
    
    /**
     *  This method get the parents tree of node
     *  @return tree path for node
     */
    protected TreePath getTree()
    {
        FileNode[] tempNodes;
        tempNodes = new FileNode[ file.path.length ];
        for (int i = 0 ; i < file.path.length ; i++)
        {
            tempNodes[i] = new FileNode( file.path[i]);
        }
        
        return new TreePath( tempNodes);
    }
    
    public boolean delete()
    {
        if (getParent().getChildCount() == 1)
            ((FileNode) getParent()).addEmptyChildren();
        removeFromParent();
        if (file != null)
        {
            if (file.isFile())
                return file.delete();
            else
                return deleteFolder( file);
        }
        return true;
    }
    
    private boolean deleteFolder( File file)
    {
        boolean done = true;
        File[] files = file.listFiles();
        for (File subFile : files)
        {
            if (subFile.isFile())
                subFile.delete();
            else
                done = done && deleteFolder( subFile);
        }
        file.delete();
        return done;
    }
    
    public void createFile( String fileName) throws IOException
    {
        FileNode temp;
        temp = new FileNode( new PathedFile( file.getAbsolutePath() + "/"  + fileName
                                                +  "/" ,  file.path ) , nodesAndPaths);
        add( temp);
        new FileSaver( new File( file.getAbsolutePath() + "/" + fileName)).save("");
        ((FileNode)(this.parent)).updateChildren();  
    }
    
    public void createDirectory( String fileName)
    {
        FileNode temp;
        temp = new FileNode( new PathedFile( file.getAbsolutePath() + "/" + fileName
                                                +  "/" ,  file.path ) , nodesAndPaths);
        temp.file.mkdir();
        add( temp);
        temp.checkEmptyDir();
        ((FileNode)(this.parent)).updateChildren();
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void openFile( String filePath, HashMap map)
    {
        if( getAllowsChildren())
        {
            add( new FileNode( new PathedFile( filePath , file.path ), map)); 
        }
    }
    
    public void pasteFile( FileNode sourceNode) throws IOException
    {
        File temp =  new File( file.getAbsolutePath() + "/" + sourceNode.toString());
        FileSaver filePaster = new FileSaver( sourceNode.getFile());
        filePaster.saveAs( ContentReader.read(sourceNode.getFile()), temp);
        updateChildren();
    }
    
    public void updateChildren()
    {
        addChildren();
        for (int i = getChildCount();  i > 0 ; i-- )
        {
            if( !getChildAt(i-1).isLeaf() )
                ((FileNode) getChildAt(i-1)).updateChildren();
            else if (((FileNode) getChildAt(i-1)).file == null &&
                     ((FileNode) getChildAt(i-1)).getParent().getChildCount() > 1)
                ((FileNode) getChildAt(i-1)).delete();
        }
    }
    
    public boolean isEmpty() {
        return file == null && isLeaf();
    }
    
    public void setIsBrowsingProjects( boolean isBrowsingProjects)
    {
        if( isRoot())
        {
            this.isBrowsingProjects = isBrowsingProjects;
        }
    }
    
    public boolean isBrowsingProjects()
    {
        return isRoot() && isBrowsingProjects;
    }
    
    public boolean isRoot(){
        return file.equals( new PathedFile( "/DEFAULT_PATH/"));
    }
    
    public void clearNodesAndPaths() {
        nodesAndPaths = new HashMap();
    }
    
}
