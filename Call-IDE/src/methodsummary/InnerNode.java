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

    @Override
    public String getJavadoc() {
        String javadoc;
        
        if ( compUnit.getJavadoc().isPresent() )
        {
             javadoc = "<html>" + compUnit.getJavadoc().get().toText() + "</html>" ;
            
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
            super.visit(n, arg);
        }
    }
    
    private class ConsturctorVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit( ConstructorDeclaration n, Void arg) {
            add( new ConstructorNode(n));
            super.visit(n, arg);
        }
    }
    
    private class InnerClassVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit( ClassOrInterfaceDeclaration n, Void arg) {
            if( !nodeName.equals(n.getNameAsString()))
            {
                add( new InnerNode(n));
            }
            super.visit(n, arg);
        }
    }
}
