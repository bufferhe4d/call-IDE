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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author sami.aydin-ug
 */
public class Parser {
    
    File root;
    ArrayList<File> javaFiles;
    DefaultMutableTreeNode rootNode;
    
    Parser( String root)
    {
        this.root = new File ( root );
        javaFiles = new ArrayList<File>();
        
        rootNode = new DefaultMutableTreeNode("Classes");
        
        for( int i =0 ; i < this.root.listFiles().length ; i ++ )
        {
             if(this.root.listFiles()[i].toString().endsWith(".java"))
             {
                 javaFiles.add(this.root.listFiles()[i]);
             }
        }
        
        addJavaFilesandMethods();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public void addJavaFilesandMethods() 
    {
        // TODO code application logic here
    
    for( File jFile : javaFiles )
    {
        
        try
        {
            System.out.println(jFile);
            rootNode.add(new ClassNode(jFile));
        }
        catch( ParseException | IOException  e ){}
    }
    
    
    }
    
    
}
