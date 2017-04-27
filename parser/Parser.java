/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author sami.aydin-ug
 */
public class Parser {

    CompilationUnit cu;
    File root;
    ArrayList<File> javaFiles;
    
    Parser( String root)
    {
        this.root = new File ( root );
        javaFiles = new ArrayList<File>();
        for( int i =0 ; i < this.root.listFiles().length ; i ++ )
        {
             if(this.root.listFiles()[i].toString().endsWith(".java"))
             {
                 javaFiles.add(this.root.listFiles()[i]);
             }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public void parseJavaFilesMethods() {
        // TODO code application logic here
    
    for( File jFile : javaFiles )
    {
        System.out.println(jFile);
        try
        {
        cu = JavaParser.parse(jFile);
            //for( int i = 0; i < //getClass().getMethods().length; i ++)
        new MethodVisitor().visit(cu, null);
        }
        catch( Exception e ){}
    }
    
    
    }
    
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            System.out.println( n.getName() + " --- " + n.getParameters() + " --- " + n.getType());
            super.visit(n, arg);
        }
    }
    
}
