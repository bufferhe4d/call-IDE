/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.Type;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author sami.aydin-ug
 */
public class MethodNode extends DefaultMutableTreeNode {

    ArrayList<Parameter> parameters;
    Type                 type;
    String               nodeName;
    
    MethodNode( MethodDeclaration metDec )
    {
        super();
        type       = metDec.getType();
        nodeName = metDec.getName() + " ( ";
        if( metDec.getParameters() != null )
        {
            parameters = new ArrayList<Parameter>(metDec.getParameters());
        
        for( Parameter p : parameters )
        {
            nodeName = nodeName + " " + p + ", ";
        }
        nodeName = nodeName.substring(0, nodeName.lastIndexOf(','));
        }
        nodeName = nodeName + " ) : " + type.toString();
    }
    
    public String toString()
    {
        return nodeName;
    }
    
}
