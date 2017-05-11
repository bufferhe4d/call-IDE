package methodsummary;

import com.github.javaparser.ParseException;
import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A class to represent a node for package in the method summary tree.
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
 
public class PackageNode extends DefaultMutableTreeNode implements SummaryNode {
    
    //Property
    File file;
    
    //Constructors
    PackageNode( File file) throws ParseException, IOException
    {
        super(file.getName());
        this.file = file;

        File[] files = file.listFiles();
            for(int i = 0; i< files.length; i++)
            {
                if (files[i].isDirectory()) {
                    add( new PackageNode((files[i])));
                }
                else if(files[i].getAbsolutePath().endsWith(".java"))
                {
                    add( new ClassNode(files[i]));
                }
            }
    }
    
    PackageNode( File file, String title) throws ParseException, IOException {
        super(title);
        this.file = file;

        File[] files = file.listFiles();
            for(int i = 0; i< files.length; i++)
            {
                if (files[i].isDirectory()) {
                    add( new PackageNode((files[i])));
                }
                else if(files[i].getAbsolutePath().endsWith(".java"))
                {
                    add( new ClassNode(files[i]));
                }
            }
    }

    //Methods
    @Override
    public String getJavadoc()
    {
        return file.getAbsolutePath().toString();
    }

    public int nodeType() {
        return SummaryNode.PACKAGE_NODE;
    }
    
}
