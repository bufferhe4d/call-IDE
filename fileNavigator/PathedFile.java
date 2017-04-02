import java.io.File;
import java.util.*;
import javax.swing.tree.*;

public class PathedFile extends File 
{
   //Property
   PathedFile[] path;
   
   //constructors
   public PathedFile( String pathname, PathedFile[] parentPath )
   {
      super( pathname );
      path = new PathedFile[ parentPath.length + 1 ];
      for( int i = 0 ; i < parentPath.length ; i++ )
      {
         path[i] = parentPath[i];
      }

      path[ parentPath.length ] = this;
   }
   
   public PathedFile( String pathname )
   {
      super( pathname );
      path = new PathedFile[  1 ];
      
      path[ 0 ] = this;

   }
   
   //method
   public PathedFile[] getObjPath()
   {
      return path;
   }
      
}