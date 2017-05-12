package filebrowser;

import fileoperations.ContentReader;
import fileoperations.FileSaver;

import java.io.*;
import java.nio.file.Files;
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
     * This constructor add root directory node
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
    
    /** This method checks directory is empty and add empty child*/
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
    
    /**
     * This method compares nodes.
     * @param n the other FileNode to compare
     * @return true if they are the same
     */
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
    
    /**
     * This method deletes file and remove from parent the node
     * @return true if the deletion is successful
     */
    public boolean delete()
    {
        if (getParent().getChildCount() == 1)
            ((FileNode) getParent()).addEmptyChildren();
        removeFromParent(); // WATCHER CONFLICT
        if (file != null)
        {
            if (file.isFile()) {
                nodesAndPaths.remove( file.getAbsolutePath()); // TODO if works add below as well
                return file.delete();
            }
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
        for (File subFile : files) {
            if (subFile.isFile()) {
                nodesAndPaths.remove( subFile.getAbsolutePath());
                subFile.delete();
            }
            else
                done = done && deleteFolder( subFile);
        }
        nodesAndPaths.remove( file.getAbsolutePath());
        file.delete();
        return done;
    }
    
    /**
     * This method adds a node and create file with a name.
     * @param fileName the name of the file to create
     * @throws IOException
     */
    public void createFile( String fileName) throws IOException
    {
        // ---- WATCHER CONFLICT -----
        FileNode temp;
        temp = new FileNode( new PathedFile( file.getAbsolutePath() + "/"  + fileName
                                                +  "/" ,  file.path ) , nodesAndPaths);
        add( temp);
        // ---- WATCHER CONFLICT -----
        
        new FileSaver( new File( file.getAbsolutePath() + "/" + fileName)).save("");
        updateChildren();
    }
    
    /**
     * This method creates a directory into node
     * @param fileName the name of the directory to add
     */
    public void createDirectory( String fileName)
    {
        FileNode temp;
        temp = new FileNode( new PathedFile( file.getAbsolutePath() + "/" + fileName
                                                +  "/" ,  file.path ) , nodesAndPaths);
        add( temp);
        temp.checkEmptyDir();
        
        (new File(file.getAbsolutePath() + "/" + fileName)).mkdir();
        updateChildren();
    }
    
    /** This gets file of node */
    public File getFile() {
        return file;
    }
    
    /**  
     *   This method adds a file in a node it specified for visual root 
     *   @param filePath path of workspace path
     *   @param map will be construct for all workspace
     */
    public void openFile( String filePath, HashMap map)
    {
        if( getAllowsChildren())
        {
            add( new FileNode( new PathedFile( filePath , file.path ), map));
        }
    }
    
    /** 
     * This method pastes the source file into this directory 
     * @param sourceNode will be pasted on this node
     * @throws IOException
     */
    public void pasteFile( FileNode sourceNode) throws IOException
    {
        if (sourceNode != null) {
            File temp =  new File( file.getAbsolutePath() + "/" + sourceNode.toString());
            FileSaver filePaster = new FileSaver( sourceNode.getFile());
            filePaster.saveAs( ContentReader.read(sourceNode.getFile()), temp);
            updateChildren();
        }
    }
    
    /**  This method updates nodes in directory it create nodes for child files and subdirectories if isn't created */
    public void updateChildren()
    {
        if (file != null && file.isDirectory())
        {
            if( !isRoot())
            {
                for (int i = getChildCount();  i > 0 ; i-- )
                {
                    FileNode temp =  ((FileNode) getChildAt(i-1));
                    if( !temp.isEmpty() && !(( temp.file.isDirectory() && Files.exists(temp.file.toPath()) ) || temp.file.exists() ))
                        temp.closeFile();
                }
            }
            addChildren();
            for (int i = getChildCount();  i > 0 ; i-- )
            {
                FileNode temp = (FileNode) getChildAt(i-1);
                if( temp.file != null && temp.file.isDirectory() )
                    temp.updateChildren();
                else if (temp.file == null &&
                         temp.getParent().getChildCount() > 1)
                    temp.delete();
            }
            if (getChildCount() == 0) {
                addEmptyChildren();
            }
            children.sort(new FileNodeComparator() );
        }
    }
    
    /**
     * This method determines node is empty node
     * @return true if the node is empty
     */
    public boolean isEmpty() {
        return file == null && isLeaf();
    }
    
    /**
     * This method sets root  as a browsing project
     * @param isBrowsingProjects the state of the browser
     */
    public void setIsBrowsingProjects( boolean isBrowsingProjects)
    {
        if( isRoot())
        {
            this.isBrowsingProjects = isBrowsingProjects;
        }
    }
    
    /**
     * This method gets root is browsing project
     * @return true if the current state is browsing projects
     */
    public boolean isBrowsingProjects()
    {
        return isRoot() && isBrowsingProjects;
    }
    
    /** This method returns true if node is visual root*/
    @Override
    public boolean isRoot() {
        if (file == null)
            return false;
        return file.equals( new PathedFile( "/DEFAULT_PATH/"));
    }
    
    /** This method clear paths and nodes map */
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
    
    /**
     * This class provides comparability for file nodes
     */
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
