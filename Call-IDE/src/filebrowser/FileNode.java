package filebrowser;

import fileoperations.ContentReader;
import fileoperations.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
    //properties
    PathedFile file;
    boolean    allowsChildren;
    HashMap    nodesAndPaths;
    boolean    isBrowsingProjects;
    boolean    childrenSorted;
    
    //constructors
    /**
     * This constructor get files and construct root node without real path
     * @param files children of root
     */
    public FileNode( ArrayList<String> files )
    {
        super();
        // Create temp filenode and set properties
        FileNode temp;
        allowsChildren = true;
        nodesAndPaths = new HashMap();
        file = new PathedFile( "/DEFAULT_PATH/");
        nodesAndPaths.put( file.getAbsolutePath(), this);
        
        //add a child for each i
        for (int i =0 ; i < files.size(); i++)
        {
            temp = new FileNode( new PathedFile ( files.get(i), file.getObjPath()), nodesAndPaths);
            if(getIndex( temp ) == -1 && temp != null)
                add( temp );
        }
        
        //check that it is empty
        checkEmptyDir();
    }
    
    /**
     *  This constructor get file linked to node and hashmap for all nodes and paths are related
     *  @param file PathedFile related with node
     *  @param nodesAndPaths show that files and nodes relation
     */
    public FileNode( PathedFile file, HashMap nodesAndPaths)
    {
        super();
        this.file = file;
        allowsChildren = this.file.isDirectory();
        
        this.nodesAndPaths =  nodesAndPaths;
        
        nodesAndPaths.put(this.file.getAbsolutePath(), this);
        
        if( getAllowsChildren())
        {
            addChildren();
            childrenSorted = false;
        }
        else
            
            childrenSorted = true;
        
        checkEmptyDir();
    }
    /**
     * This constructor add root directoy node
     * @param file root file as a pathed file
     */
    public FileNode( PathedFile file)
    {
        super();
        this. file = file;
        allowsChildren = this.file.isDirectory();
        
        nodesAndPaths.put(this.file.getAbsolutePath(), this);
        
        if( getAllowsChildren() )
        {
            addChildren();
            childrenSorted = false;
        }
        else
            childrenSorted = true;
        
        checkEmptyDir();
    }
    
    /** Creates an empty file node. */
    public FileNode( String emptyParent, HashMap nodesAndPaths)
    {
        super();
        this.file = null;
        
        this.nodesAndPaths =  nodesAndPaths;
        
        childrenSorted = true;
        nodesAndPaths.put( emptyParent + "<empty>", this);
    }
    
    /** This method checks directory is empty and add emtpy child*/
    public void checkEmptyDir()
    {
        if (this.file.isDirectory() && this.file.list() != null && this.file.list().length == 0)
            addEmptyChildren();
    }
    
    /** This method adds a empty child */
    public void addEmptyChildren()
    {
        add ( new FileNode( this.file.getAbsolutePath(), nodesAndPaths));
    }
    
    /** this method adds real children of file */
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
    
    /** This method returns node name properly */
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
    
    /** This method compares nodes and if they are equals return true */
    public boolean equals( FileNode n)
    {
        return file.getPath().equals( n.file.getPath());
    }
    
    /**
     *  This method gets the parents tree of node
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
    
    /**  This method deletes file and remove from parent  the node */
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
    
    /**  This method  deletes folder with its children files and subfiles  */
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
    
    /** This method adds a node and create file with a name  */
    public void createFile( String fileName) throws IOException
    {
        FileNode temp;
        temp = new FileNode( new PathedFile( file.getAbsolutePath() + "/"  + fileName
                                                +  "/" ,  file.path ) , nodesAndPaths);
        add( temp);
        new FileSaver( new File( file.getAbsolutePath() + "/" + fileName)).save("");
        ((FileNode)(this.parent)).updateChildren();  
    }
    
    /** This method creates a directory into node */
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
    
    /** This  gets file of node */
    public File getFile()
    {
        return file;
    }
    
    /**  This method adds a file in a node it spefied for visual root */
    public void openFile( String filePath, HashMap map)
    {
        if( getAllowsChildren())
        {
            add( new FileNode( new PathedFile( filePath , file.path ), map)); 
        }
    }
    
    /** This method pastes the source file into this directory */
    public void pasteFile( FileNode sourceNode) throws IOException
    {
        File temp =  new File( file.getAbsolutePath() + "/" + sourceNode.toString());
        FileSaver filePaster = new FileSaver( sourceNode.getFile());
        filePaster.saveAs( ContentReader.read(sourceNode.getFile()), temp);
        updateChildren();
    }
    
    /**  This method updates nodes in directory it create nodes for child files and subdirectories if isn't created */
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
        children.sort(new FileNodeComparator() );
    }
    
    /**  This method determines node is empty node */
    public boolean isEmpty() {
        return file == null && isLeaf();
    }
    
    /** This method sets root  as a broswing project */
    public void setIsBrowsingProjects( boolean isBrowsingProjects)
    {
        if( isRoot())
        {
            this.isBrowsingProjects = isBrowsingProjects;
        }
    }
    
    /** This method gets root  is broswing project */
    public boolean isBrowsingProjects()
    {
        return isRoot() && isBrowsingProjects;
    }
    
    /** This method returns true if node is visual root*/
    public boolean isRoot(){
        return file.equals( new PathedFile( "/DEFAULT_PATH/"));
    }
    
    /** This method clear paths and nodes releation */
    public void clearNodesAndPaths() {
        nodesAndPaths = new HashMap();
    }
    
    /** This method close the node */
    public void closeFile() 
    {
        
        if(  getAllowsChildren() )
        for( int i = 0 ; i < this.getChildCount() ; i++)
        {
            if( !isEmpty() )
            ((FileNode)this.getChildAt(i)).closeFile();
        }
        if( !isEmpty())
            nodesAndPaths.remove(file.getAbsolutePath(), this);
        removeFromParent();
        
    }
    
    public void shallowUpdate()
    {
        if( isRoot() )
        {
            children.sort( new FileNodeComparator() );
        }
        else if(  !isLeaf() && file.listFiles().length != children.size() )
        {
            updateChildren();
        }
        else if ( !childrenSorted )
        {
            childrenSorted = true;
            children.sort( new FileNodeComparator());
        }
    }
    
    private static class FileNodeComparator implements Comparator
    {

        @Override
        public int compare(Object o1, Object o2) {
            FileNode node1 = (FileNode)o1;
            FileNode node2 = (FileNode)o2;
            if (node1.isLeaf() && !node2.isLeaf()) {
                return 1;
            } else if (!node1.isLeaf() && node2.isLeaf()) {
                return -1;
            } else {
                return node1.file.toString().compareToIgnoreCase(node2.file.toString());
    }    
        }
        
    }
}
