/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mahmudsami
 */
public class ConstructorNode extends DefaultMutableTreeNode {

    public ConstructorNode(ConstructorDeclaration n) 
    {
        super( n.getDeclarationAsString());
    }
    
}
