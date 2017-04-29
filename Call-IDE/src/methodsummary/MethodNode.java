package methodsummary;

import com.github.javaparser.Position;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.Type;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author sami.aydin-ug
 */
public class MethodNode extends DefaultMutableTreeNode implements VisitableNode, SummaryNode {
    
    ArrayList<Parameter> parameters;
    Type                 type;
    String               nodeName;
    String               modifier;
    File file;
    Position position;
     MethodDeclaration metDec;
    
    MethodNode( MethodDeclaration metDec )
    {
        super();
        this.metDec = metDec;
        type       = metDec.getType();
        if(metDec.isPrivate())
            modifier = "- ";
        else if(metDec.isPublic())
            modifier = "+ ";
        else if(metDec.isProtected())
            modifier = "# ";
        else
            modifier = "~ ";
        nodeName =  modifier + metDec.getName() + "(";
        if( metDec.getParameters() != null )
        {
            parameters = new ArrayList<Parameter>(metDec.getParameters());
            
            for( Parameter p : parameters )
            {
                nodeName = nodeName + " " + p + ", ";
            }
            if( nodeName.lastIndexOf(',') > 0 )
            {
                nodeName = nodeName.substring(0, nodeName.lastIndexOf(','));
            }
        }
        nodeName = nodeName + ") : " + type.toString();
    }
    
    public String toString()
    {
        return nodeName;
    }
    
    public File getFile() {
        return file;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void configureNode() {
        TreeNode parent = this.parent;
        while (! (parent instanceof ClassNode))
            parent = parent.getParent();
        file = ((ClassNode) parent).file;
        
        position = metDec.getBegin().get();
    }

    @Override
    public int nodeType() {
        if(type.toString().equals("int"))
            return SummaryNode.INT_NODE;
        if(type.toString().equals("byte"))
            return SummaryNode.BYTE_NODE;
        if(type.toString().equals("boolean"))
            return SummaryNode.BOOLEAN_NODE;
        if(type.toString().equals("double"))
            return SummaryNode.DOUBLE_NODE;
        if(type.toString().equals("float"))
            return SummaryNode.FLOAT_NODE;
        if(type.toString().equals("long"))
            return SummaryNode.LONG_NODE;
        if(type.toString().equals("short"))
            return SummaryNode.SHORT_NODE;
        if(type.toString().equals("void"))
            return SummaryNode.VOID_NODE;
        return SummaryNode.OBJECT_NODE;
        
    }

    @Override
    public String getJavadoc() {
        String javadoc;
        
        if( metDec.getJavadoc().isPresent())
        {
            javadoc = "<html>" + metDec.getJavadoc().get().toText() + "</html>" ;
            
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
        return "Javadoc not Found";
    }
}
