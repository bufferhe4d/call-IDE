import java.util.*;
import javax.swing.tree.*;

public class FileNode implements TreeNode, Enumeration
{
   //Properties
   ArrayList<FileNode> children;
   int enumIndex;
   PathedFile file;
   
   
   
   public FileNode( PathedFile file )
   {
      this. file = file;
      children = new ArrayList<FileNode>();
      enumIndex= 0;
      if( getAllowsChildren() )
      {
         addChildrens();
      }
   }
   
   public void addChildrens()
   {  
      FileNode temp;
      for( int i = 0; i < file.list().length; i++)   
      {
         temp = new FileNode (  new PathedFile ( file.listFiles()[i].toString(), file.path )  );
         if( getIndex( temp ) == -1 )
         {
            enumIndex = 0;
            children.add( temp );
         }
      }
   }
   
   
   public boolean hasMoreElements() 
   {
      return enumIndex < children.size();
   }
   
   public Enumeration nextElement()
   {
      return children.get( enumIndex++);
   }
   
   
   public Enumeration children()
   {
      return this;
   }
   
   public boolean getAllowsChildren()
   {
      return file.isDirectory();
   }
   
   public TreeNode getChildAt(int childIndex)
   {
      return children.get(childIndex);
   }
   
   public int   getChildCount()
   {
      return  children.size();
   }
   
   public TreeNode getParent() 
   {
      return  new FileNode ( file.path[ file.path.length - 2  ] );
      
   }
   
   public boolean isLeaf()
   {
      return getChildCount() == 0;
   }
   
   public int getIndex( TreeNode node )
   {
      while( hasMoreElements()  )
      {
         if( ((FileNode)node).equals( (FileNode)nextElement() ) )
         {
            return enumIndex-1;
         }
      }
      
      return -1;
   }
   
   @Override
   public String toString()
   {
      return file.getName();
   }
   
   public boolean equals( FileNode n )
   {
      return file.getPath().equals( n.file.getPath() );
   }
   
   
   
   
   
   
   
   
   
}


