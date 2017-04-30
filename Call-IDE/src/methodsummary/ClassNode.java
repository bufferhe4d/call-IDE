package methodsummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * A class to represent a node for classes in the method summary tree.
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
public class ClassNode extends DefaultMutableTreeNode implements SummaryNode {
    
    // PROPERTIES
    CompilationUnit compUnit;
    ArrayList<MethodDeclaration> innerClassMethods;
    String nodeName;
    int innerMethodIndex;
    boolean isInterface;
    File file;
    
    // CONSTRUCTORS
    ClassNode( File classFile) throws ParseException, IOException
    {
        super(classFile.getName());
        innerClassMethods = new ArrayList<MethodDeclaration>();
        nodeName = classFile.getName();
        compUnit = JavaParser.parse(classFile); 
        innerMethodIndex = 0;
        this.file = classFile;
        
        new FindInnerMethods().visit( compUnit, null);
        new ConsturctorVisitor().visit( compUnit, null);
        new MethodVisitor().visit( compUnit, null);        
        new InnerClassVisitor().visit( compUnit, null);
    }
    
    // METHODS
    @Override
    public int nodeType() {
        if(isInterface)
            return SummaryNode.INFACE_NODE;
        return SummaryNode.CLASS_NODE;
    }
    
    @Override
    public String getJavadoc() {
        String javadoc;
        
        if (!compUnit.getComments().isEmpty())
        {
            javadoc = "<html>" + compUnit.getComments().iterator().next().getContent().substring(5) + "</html>" ;
            
            for( int i = 0; i < javadoc.length(); i++ )
            {
                if( javadoc.charAt(i) == '*' )
                {
                    javadoc = javadoc.substring(0, i) + "<br/>" + javadoc.substring(i+1);
                    i= i+5;
                }
            }
            return javadoc;
        }
        return "<no javadoc>";
    }
    
    private class FindInnerMethods extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            if (!nodeName.equals(n.getNameAsString()+ ".java"))
                innerClassMethods.addAll(n.getMethods());
            super.visit(n, arg);
        }
    }
    
    class MethodVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            if (innerClassMethods.size() > innerMethodIndex &&
                n.getDeclarationAsString().equals(innerClassMethods.get(innerMethodIndex).getDeclarationAsString()))
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
            if (!nodeName.equals(n.getNameAsString()+ ".java"))
                add( new InnerNode(n));
            else
                isInterface = n.isInterface();
            super.visit(n, arg);
        }
    }
    
}
