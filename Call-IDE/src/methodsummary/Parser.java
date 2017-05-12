package methodsummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import javax.swing.tree.TreeNode;

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
    /**
     * A constructor to initialize the tools for parser such as assigning root
     * node for java sources and listing the sub files for parsing to the methods
     * to generate method summary
     * @param root a parameter to take root file 
     */

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
     * A constructor to generate a method summary if there is no parent files of
     * the java source that we are parsing
     */

    public Parser() {
        rootNode = new DefaultMutableTreeNode("Method Summary");
    }
    
    // METHODS
    /**
     * A method to add java files to the root node for parsing their methods in it
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
    /**
     * A method to get root node
     * @return root node in DefaultMutableTreeNode type
     */
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
        rootNode.removeAllChildren();
    }
    
    /**
     * A method to check whether the given file from parameter is in it the method
     * summary
     * @param  file a parameter to take the file to check 
     * @return true if it is in the method summary; false, if not
     */
    public boolean contains( File file) {
        for (int i = 0; i < rootNode.getChildCount(); i++)
        {
            if (((ClassNode) (rootNode.getChildAt(i))).file == file)
            return true;
        }
        return false;
    }
    
    /**
     * A method to refresh the method summary if there is any change in the 
     * java source file
     * @param file a parameter to take a file to parse for refreshing its nodes
     * @throws ParseException
     * @throws IOException 
     */
    public void refreshNode( File file) throws ParseException, IOException {
        int index = getRow( file);
        ((MutableTreeNode) rootNode.getChildAt(index)).removeFromParent();
        if( file.getName().endsWith(".java"))
            rootNode.insert(new ClassNode(file), index);
        else
            rootNode.insert(new PackageNode(file, file.getParentFile().getName()), index);
    }
    
    /**
     * A method to remove the given file, which is given from parameter, from 
     * method summary nodes
     * @param file a parameter to take the file which will be removed from method summary
     */
    public void removeNode( File file) {
        int index = getRow( file);
        if (index != -1)
            ((MutableTreeNode) rootNode.getChildAt(index)).removeFromParent();
    }
    
    /**
     * A method to check whether the file has a main method in it or not
     * @param file a parameter to take the file to check
     * @return true if the file has a main method; false, if it hasn't
     */
    public boolean hasMain( File file) {
        int index = getRow( file);
        if (index != -1) {
            MutableTreeNode node = ((MutableTreeNode) rootNode.getChildAt(index));
            if (node instanceof ClassNode) {
                for (int i = 0; i < ((ClassNode) node).getChildCount(); i++) {
                    TreeNode treeNode = (((ClassNode) node).getChildAt(i));
                    if (treeNode instanceof MethodNode) {
                        MethodDeclaration metDec = ((MethodNode) treeNode).metDec;
                        if (metDec.getNameAsString().equals("main") &&
                            metDec.isPublic() && metDec.isStatic()) {
                            ArrayList<Parameter> parameters = new ArrayList<Parameter>(metDec.getParameters());
                            if (parameters.size() == 1 && parameters.get(0).toString().startsWith("String[]"))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * A method to search the main method in the list of files
     * and return the list of files which have main methods in it 
     * @param srcDir a parameter to take the file which has files in it to search main methods
     * @return list of files which have main methods in it in the ArrayList type
     */
    public ArrayList<File> searchMain( File srcDir)
    {
        try {
            ArrayList<File> mains = new ArrayList<File>();
            File[] files = srcDir.listFiles();
            for(int i = 0; i< files.length; i++)
            {
                if (files[i].isDirectory()) {
                    mains.addAll(searchMain(files[i]));
                }
                else if(files[i].getAbsolutePath().endsWith(".java"))
                {
                    addNode( files[i]);
                    if(hasMain(files[i]))
                        mains.add(files[i]);
                }
            }
            return mains;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<File>();
    }
    
    /**
     * A method to get the row of the given file in the method summary
     * @param file a parameter to take the file which row will be founded in the method summary
     * @return the index of the file in the method summary if it is current; -1
     *         if it is not
     */
    private int getRow( File file) {
        for (int i = 0; i < rootNode.getChildCount(); i++)
        {
            if ((file.isFile() && ((ClassNode) (rootNode.getChildAt(i))).file == file)  || ( file.isDirectory() && ((PackageNode) (rootNode.getChildAt(i))).file == file))
                return i;
        }       
        return -1;
    }
    /**
     * A method to add package file under the root file and set the name of the file as a title of the project
     * @param packagefile a parameter to take the file as a src file of the source files in File type
     * @param title a parameter to take the title to set the name of the package
     */
    public void addPackage( File packageFile, String title) throws ParseException, IOException {
        PackageNode insertedNode = new PackageNode( packageFile, title);
        rootNode.add( insertedNode);
    }
    
}
