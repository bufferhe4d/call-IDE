package methodsummary;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mahmudsami
 */
public class InnerNode extends DefaultMutableTreeNode implements SummaryNode{
    
    ClassOrInterfaceDeclaration   compUnit;
    ArrayList<MethodDeclaration>  innerClassMethods;
    String                        nodeName;
    int                           innerMethodIndex;
    public InnerNode(ClassOrInterfaceDeclaration n) 
    {
        super(n.getName());
        compUnit = n;
        innerClassMethods = new ArrayList<MethodDeclaration>();
        innerMethodIndex = 0;
        nodeName = n.getNameAsString();
        new FindInnerMethods().visit(compUnit, null);
        new ConsturctorVisitor().visit(compUnit, null);
        new MethodVisitor().visit( compUnit, null);        
        new InnerClassVisitor().visit(compUnit, null);
        
    }

    @Override
    public int nodeType() {
        if(compUnit.isInterface())
            return SummaryNode.INFACE_NODE;
        return SummaryNode.INNER_NODE;
    }

    private class FindInnerMethods extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        
            if( !nodeName.equals(n.getNameAsString()))
                innerClassMethods.addAll(n.getMethods());
            
            super.visit(n, arg);
        }
    }

  
    
    class MethodVisitor extends VoidVisitorAdapter<Void> 
    {
        
        @Override
        public void visit(MethodDeclaration n, Void arg) {

            if( innerClassMethods.size() > innerMethodIndex && n.getDeclarationAsString().equals(innerClassMethods.get(innerMethodIndex).getDeclarationAsString()))
                innerMethodIndex++;
            else
                add( new MethodNode(n));
            //System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
    
    private class ConsturctorVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit( ConstructorDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            add( new ConstructorNode(n));
            
            //System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
    
    private class InnerClassVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit( ClassOrInterfaceDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            //System.out.println(n.getNameAsString());
            if( !nodeName.equals(n.getNameAsString()))
            {
                add( new InnerNode(n));
            }
            super.visit(n, arg);
        }
    }
}
