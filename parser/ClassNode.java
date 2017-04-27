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
    
    ArrayList<Node> innerClasses;
    Method[]        methods;
    
    ClassNode( File classFile ) throws ParseException, IOException
    {
        super(classFile.getName());
        CompilationUnit  compUnit;
        
        compUnit = JavaParser.parse(classFile); 
        methods = compUnit.getClass().getDeclaredMethods();
        
        
        
        new MethodVisitor().visit( compUnit, null);
    }


  
    
    private class MethodVisitor extends VoidVisitorAdapter<Void> 
    {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            add( new MethodNode(n));
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
}
