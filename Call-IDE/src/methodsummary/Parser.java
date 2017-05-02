package methodsummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

/**
 * A class that can parse java source files.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class Parser {
    
    // PROPERTIES
    File root;
    ArrayList<File> javaFiles;
    DefaultMutableTreeNode rootNode;
    
    // CONSTRUCTORS
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
    
    public Parser() {
        rootNode = new DefaultMutableTreeNode("Method Summary");
    }
    
    // METHODS
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
     * This method adds a java file into the method summary
     * @param file java file which will be parsed
     * @throws ParseException
     * @throws IOException 
     */
    public void addNode( File file ) throws ParseException, IOException
    {
        rootNode.add(new ClassNode(file));
    }
    
    /** This method clears the tree */
    public void clearNodes()
    {
        ArrayList<MutableTreeNode> childrenOfRoot;
        childrenOfRoot = (ArrayList<MutableTreeNode>) rootNode.children();
        for( MutableTreeNode child : childrenOfRoot)
        {
            child.removeFromParent();
        }
    }
    
    public boolean contains( File file) {
        for (int i = 0; i < rootNode.getChildCount(); i++)
            if (((ClassNode) (rootNode.getChildAt(i))).file == file)
            return true;
        return false;
    }
    
    public void refreshNode( File file) throws ParseException, IOException {
        int index = getRow( file);
        ((MutableTreeNode) rootNode.getChildAt(index)).removeFromParent();
        rootNode.insert(new ClassNode(file), index);
    }
    
    public void removeNode( File file) {
        int index = getRow( file);
        if (index != -1)
            ((MutableTreeNode) rootNode.getChildAt(index)).removeFromParent();
    }
    
    public boolean hasMain( File file) {
        int index = getRow( file);
        if (index != -1) {
            MutableTreeNode node = ((MutableTreeNode) rootNode.getChildAt(index));
            if (node instanceof ClassNode) {
                for (int i = 0; i < ((ClassNode) node).getChildCount(); i++) {
                    MethodDeclaration metDec = ((MethodNode) (((ClassNode) node).getChildAt(i))).metDec;
                    if (metDec.getNameAsString().equals("main") &&
                        metDec.isPublic() && metDec.isStatic()) {
                        ArrayList<Parameter> parameters = new ArrayList<Parameter>(metDec.getParameters());
                        if (parameters.size() == 1 && parameters.get(0).toString().equals("String[] args"))
                            return true;
                    }
                }
            }
        }
        return false;
    }
    
    public File getMain( File file) {
        int index = getRow( file);
        if (index != -1) {
            MutableTreeNode node = ((MutableTreeNode) rootNode.getChildAt(index));
            if (node instanceof ClassNode) {
                for (int i = 0; i < ((ClassNode) node).getChildCount(); i++) {
                    MethodDeclaration metDec = ((MethodNode) (((ClassNode) node).getChildAt(i))).metDec;
                    if (metDec.getNameAsString().equals("main") &&
                        metDec.isPublic() && metDec.isStatic()) {
                        ArrayList<Parameter> parameters = new ArrayList<Parameter>(metDec.getParameters());
                        if (parameters.size() == 1 && parameters.get(0).toString().equals("String[] args"))
                            return node.file;
                    }
                }
            }
        }
        return null;
    }
    
    private int getRow( File file) {
        for (int i = 0; i < rootNode.getChildCount(); i++)
            if (((ClassNode) (rootNode.getChildAt(i))).file == file)
            return i;
        return -1;
    }
    
}
