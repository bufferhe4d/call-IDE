/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author sami.aydin-ug
 */
public class ParserTest {
    
    public static void main(String[] args)
    {
        Parser p = new Parser( "H:\\private\\cs102\\Lab03\\lab03a\\src");
        p.parseJavaFilesMethods();
    }
}
