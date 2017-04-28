package methodsummary;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mahmudsami
 */
public class InnerNode extends DefaultMutableTreeNode {
    
    ArrayList<MethodDeclaration> methods;
    
    public InnerNode(ClassOrInterfaceDeclaration n) 
    {
        super(n.getName());
        methods = new ArrayList<MethodDeclaration>(n.getMethods());
        
        for(MethodDeclaration method : methods)
        {
            add(new MethodNode(method));
        }
    }
}
