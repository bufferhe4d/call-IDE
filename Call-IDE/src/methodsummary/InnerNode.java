package methodsummary;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * A class to represent a node for inner classes in the method summary tree.
 * @author Mahmud Sami Aydin
 * @version 1.0
 */
public class InnerNode extends DefaultMutableTreeNode implements SummaryNode{
    
    // PROPERTIES
    ClassOrInterfaceDeclaration compUnit;
    ArrayList<MethodDeclaration> innerClassMethods;
    String nodeName;
    int innerMethodIndex;
    
    // CONSTRUCTORS
    public InnerNode( ClassOrInterfaceDeclaration dec) 
    {
        super(dec.getName());
        compUnit = dec;
        innerClassMethods = new ArrayList<MethodDeclaration>();
        innerMethodIndex = 0;
        nodeName = dec.getNameAsString();
        new FindInnerMethods().visit(compUnit, null);
        new ConsturctorVisitor().visit(compUnit, null);
        new MethodVisitor().visit( compUnit, null);        
        new InnerClassVisitor().visit(compUnit, null);
    }

    // METHODS
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
        return "<no javadoc>";
    }

    // INNER CLASSES
    /**
     * This class find inner classes' methods of this class
     */
    private class FindInnerMethods extends VoidVisitorAdapter<Void> 
    {
        /**
         * An override method to visit the inner methods of the class
         * @param n a parameter to take the node for search inner classes or
         *          interface declarations
         * @param arg a parameter to take arg and give to the super.visit 
         */
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            if (!nodeName.equals(n.getNameAsString()))
                innerClassMethods.addAll(n.getMethods());
            super.visit(n, arg);
        }
    }
    
     /**
     * This class find methods of this class
     */
    class MethodVisitor extends VoidVisitorAdapter<Void> 
    {
        /**
         * An override method to visit the methods of the class
         * @param n a parameter to take the node for search the method declarations 
         *          in class
         * @param arg a parameter to take arg and give to the super.visit
         */
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
    
    /**
     * This class find constructors of this class
     */
    private class ConsturctorVisitor extends VoidVisitorAdapter<Void> 
    {
        /**
         * An override method to visit the constructors in the class
         * @param n a parameter to take the node to search the constructor in it
         * @param arg a parameter to take arg and give to the super.visit
         */
        @Override
        public void visit( ConstructorDeclaration n, Void arg) {
            add( new ConstructorNode(n));
            super.visit(n, arg);
        }
    }
    
    /**
     * This class find inner classes of this class
     */
    private class InnerClassVisitor extends VoidVisitorAdapter<Void> 
    {
        /**
         * An override method to visit the inner class and interface declarations in the class 
         * @param n a parameter to take the node to look at the inner class and interface declarations 
         * @param arg a parameter to take arg and give to the super.visit 
         */
        @Override
        public void visit( ClassOrInterfaceDeclaration n, Void arg) {
            if (!nodeName.equals(n.getNameAsString()))
            {
                add( new InnerNode(n));
            }
            super.visit(n, arg);
        }
    }
    
}
