/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

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
