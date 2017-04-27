/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.Type;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author sami.aydin-ug
 */
public class MethodNode extends DefaultMutableTreeNode {
    
    Type type;
    List<Parameter> parameter;
    
    MethodNode( String name, Type type, List<Parameter> parameter)
    {
        super(name);
        
    }
    
}
