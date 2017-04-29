/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodsummary;

/**
 *
 * @author mahmudsami
 */
interface SummaryNode {
    
    final int CLASS_NODE = 0;
    final int INNER_NODE = 1;
    final int INFACE_NODE = 2;
    final int BYTE_NODE = 3;
    final int SHORT_NODE = 4;
    final int INT_NODE = 5;
    final int LONG_NODE = 6;
    final int FLOAT_NODE = 7;
    final int DOUBLE_NODE = 8;
    final int CHAR_NODE = 9;
    final int OBJECT_NODE = 10;
    final int BOOLEAN_NODE = 11;
    final int CONST_NODE  = 12;
    final int VOID_NODE   = 13;
    
    /**
     * This method give node type 
     * @return type of node 
     */
    public int nodeType();
    
    public String getJavadoc();
}
