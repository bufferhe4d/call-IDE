package methodsummary;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.github.javaparser.Position;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;

/**
 * A class to represent a node for constructors in the method summary tree.
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
public class ConstructorNode extends DefaultMutableTreeNode implements VisitableNode, SummaryNode {
    
    // PROPERTIES
    ArrayList<Parameter> parameters;
    String nodeName;
    String modifier;
    File file;
    Position position;
    ConstructorDeclaration conDec;
    
    // CONSTRUCTORS
    public ConstructorNode(ConstructorDeclaration conDec)
    {
        super();
        this.conDec = conDec;
        if(conDec.isPrivate())
            modifier = "- ";
        else if(conDec.isPublic())
            modifier = "+ ";
        else if(conDec.isProtected())
            modifier = "# ";
        else
            modifier = "~ ";
        nodeName =  modifier + conDec.getName() + "(";
        if( conDec.getParameters() != null )
        {
            parameters = new ArrayList<Parameter>(conDec.getParameters());
            
            for( Parameter p : parameters )
            {
                nodeName = nodeName + " " + p + ", ";
            }
            if( nodeName.lastIndexOf(',') > 0 )
            {
                nodeName = nodeName.substring(0, nodeName.lastIndexOf(','));
            }
        }
        nodeName = nodeName + ")";
    }
    
    // METHODS
    @Override
    public String toString()
    {
        return nodeName;
    }
    
    @Override
    public File getFile() {
        return file;
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    @Override
    public void configureNode() {
        TreeNode parent = getParent();
        while (! (parent instanceof ClassNode))
            parent = parent.getParent();
        file = ((ClassNode) parent).file;
        
        position = conDec.getBegin().get();
    }
    
    @Override
    public int nodeType() {
        return SummaryNode.CONST_NODE;
    }
    
    @Override
    public String getJavadoc() {
        String javadoc;
        if (conDec.getJavadoc().isPresent())
        {
            javadoc = "<html>" + conDec.getJavadoc().get().toText() + "</html>" ;
            
            for( int i = 0; i < javadoc.length(); i++ )
            {
                if( javadoc.charAt(i) == '@' )
                {
                    javadoc = javadoc.substring(0, i) + "<br/>" + javadoc.substring(i);
                    i= i+5;
                }
            }
            return javadoc;
        }
        return "<no javadoc>";
    }
    
}
