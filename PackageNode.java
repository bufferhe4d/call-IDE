package methodsummary;


/**
 * A class to represent a node for package in the method summary tree.
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
 
public class PackageNode extends DefaultMutableTreeNode implements SummaryNode {
    File file;
    PackageNode( File file)
    {
        super(classFile.getName());
        this.file = file;

        File[] files = srcDir.listFiles();
            for(int i = 0; i< files.length; i++)
            {
                if (files[i].isDirectory()) {
                    add( new PackageNode((files[i]));
                }
                else if(files[i].getAbsolutePath().endsWith(".java"))
                {
                    addNode( files[i]);
                }
            }
    }

    @Override
    public String getJavadoc()
    {
        return file.getAbsolutePath().toString();
    }

    public int nodeType() {
            return SummaryNode.PACKAGE_NODE;
    }
}
