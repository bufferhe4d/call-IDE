package RunUtils;

import java.io.File;

import  javax.tools.*;

/**
 * This class is a compiler which compile file with file path
 * @author Abdullah Talayhan, editted by Mahmud Sami Aydin
 * @version 1.00, 19/03/2017
 */
public class Compiler {
 
 JavaCompiler compiler;
 
 public Compiler() {
  
  compiler =  ToolProvider.getSystemJavaCompiler();
 }
 /**
  * This method compile a file
  * @param f file will be compiled
  * @return java compilers output
  */
 
 public int compile(File f) {
  
  return compiler.run(null, null, null, f.getAbsolutePath());
  
 }
 
}