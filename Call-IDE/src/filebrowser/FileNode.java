package filebrowser;

import java.util.*;
import javax.swing.tree.*;
import java.io.*;

/**
 * 
 * @author Mahmud Sami Aydin
 * 
 */
public class FileNode extends DefaultMutableTreeNode
{
   
   PathedFile file;
   boolean allowsChildren;
   
   public FileNode( ArrayList<String> files )
   {
      super();
      FileNode temp;
      allowsChildren = true;
      for ( int i =0 ; i < files.size(); i++)
      {
         temp = new FileNode( new PathedFile (files.get(i) ) );
         if( getIndex( temp ) == -1 )
         {
            add( temp );
         }
      }
      file = new PathedFile("/Nowhere/it/is/not/a/real/file");
      
   }
   public FileNode( PathedFile file )
   {
      super();
      this. file = file;
      allowsChildren = file.isDirectory();
      
      if( getAllowsChildren() )
      {
         addChildren();
      }
   }
   
   public void addChildren()
   {  
      FileNode temp;
      for( int i = 0; i < file.listFiles().length; i++)   
      {
         
         
         temp = new FileNode (  new PathedFile ( file.listFiles()[i].toString(), file.path )  );
         if( getIndex( temp ) == -1 )
         {
            add( temp );
         }
         
      }
   }
   //------------------
   public boolean getAllowsChildren()
   {
      return allowsChildren;
   }
   
   //------------------------------------------------------------
   @Override
   public String toString()
   {
      return file.toString();
   }
   
   public boolean equals( FileNode n )
   {
      return file.getPath().equals( n.file.getPath() );
   }
   
   /**
    *  This method get the parents tree of node
    *  @return tree path for node
    */
   protected TreePath getTree()
   {
      FileNode[] tempNodes;
      tempNodes = new FileNode[ file.path.length ];
      for ( int i = 0 ; i < file.path.length ; i ++ )
      {
         tempNodes[i] = new FileNode(  file.path[i]  );
      }
      
      return new TreePath( tempNodes );
   }
   
   /**
    * This method delete the node from jtree
    * 
    */
   public boolean delete()
   {
      removeFromParent();
      return file.delete();
   }
   
   
   /**
    * This method add the folder a file
    * 
    */
   public void createFile( String fileName)
   {
      if( getAllowsChildren() )
      {
         add( new FileNode( new PathedFile( file.getAbsolutePath() + "/" 
                                             + fileName ,  file.path )) );
         
      }
      
   }
   
   /**
    * This method get the file of node
    * 
    * 
    */
   public File getFile()
   {
      return file;
   }
 
   
   /**
    * This method open a file froom path
    * 
    * 
    */
   public void openFile( String filePath )
   {
      if( getAllowsChildren() )
      {
         add(new FileNode( new PathedFile ( filePath ) )); 
      }
   }
   
   /**
    * This method remove file node from tree
    * 
    * 
    */
   public void closeFile()
   {
      removeFromParent();
   }
}


