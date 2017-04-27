/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import javax.swing.JFrame;
import javax.swing.JTree;

/**
 *
 * @author sami.aydin-ug
 */
public class ParserTest {
    
    public static void main(String[] args)
    {
        Parser p = new Parser( "C:\\Users\\mahmudsami\\Desktop\\FileExplorer");
        JFrame f = new JFrame();
        f.add(new JTree( p.rootNode));
               
        f.setVisible(true);
                
                
                
    }
}
