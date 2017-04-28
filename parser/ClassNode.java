/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mahmudsami
 */
public class ClassNode extends DefaultMutableTreeNode {
    
    
    CompilationUnit  compUnit;
    ArrayList<MethodDeclaration>  innerClassMethods;
    String             nodeName;
    int                innerMethodIndex;
    
    ClassNode( File classFile ) throws ParseException, IOException
    {
        super(classFile.getName());
        innerClassMethods = new ArrayList<MethodDeclaration>();
        nodeName = classFile.getName();
        compUnit = JavaParser.parse(classFile); 
        innerMethodIndex = 0;
        
        
        new FindInnerMethods().visit(compUnit, null);
        new ConsturctorVisitor().visit(compUnit, null);
        new MethodVisitor().visit( compUnit, null);        
        new InnerClassVisitor().visit(compUnit, null);
    }

    private class FindInnerMethods extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            
            
            if( !nodeName.equals(n.getNameAsString()+ ".java"))
                innerClassMethods.addAll(n.getMethods());
            
            super.visit(n, arg);
        }
    }

  
    
    class MethodVisitor extends VoidVisitorAdapter<Void> 
    {
        
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
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
            if( !nodeName.equals(n.getNameAsString()+ ".java"))
            {
                add( new InnerNode(n));
            }
            super.visit(n, arg);
        }
    }
}
