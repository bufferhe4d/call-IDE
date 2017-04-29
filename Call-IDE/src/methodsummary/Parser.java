package methodsummary;

import com.github.javaparser.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author sami.aydin-ug
 */
public class Parser {
    
    File root;
    ArrayList<File> javaFiles;
    DefaultMutableTreeNode rootNode;
    
    public Parser( String root)
    {
        this.root = new File ( root );
        javaFiles = new ArrayList<File>();
        
        rootNode = new DefaultMutableTreeNode("Method Summary");
        
        for( int i =0 ; i < this.root.listFiles().length ; i ++ )
        {
            if(this.root.listFiles()[i].toString().endsWith(".java"))
            {
                javaFiles.add(this.root.listFiles()[i]);
            }
        }
        
        addJavaFilesandMethods();
    }
    
    /**
     * @param args the command line arguments
     */
    public void addJavaFilesandMethods() 
    {

        for( File jFile : javaFiles )
        {
            try
            {
                rootNode.add(new ClassNode(jFile));
            }
            catch( ParseException | IOException  e ){}
        }
    }
    
    public DefaultMutableTreeNode getRootNode() {
        return rootNode;
    }
    
    /**
     * This method add a java file into method summary
     * @param file java file which will be parsed
     * @throws ParseException
     * @throws IOException 
     */
    public void addNode( File file ) throws ParseException, IOException
    {
        rootNode.add(new ClassNode(file));
    }
    
    /**
     * This method clear tree
     */
    public void clearNodes()
    {
        ArrayList<MutableTreeNode> childrenOfRoot;
        childrenOfRoot = (ArrayList<MutableTreeNode>) rootNode.children();
        for( MutableTreeNode child : childrenOfRoot)
        {
            child.removeFromParent();
        }
    }
    
}
