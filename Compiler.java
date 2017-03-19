
import java.io.File;

import  javax.tools.*;

/**
 * This class is a compiler which compile file with file path
 * @author Abdullah Talayhan
 * @version 1.00, 19/03/2017
 */
public class Compiler {
	
	private JavaCompiler compiler;
	
	public Compiler() {
		
		compiler =  ToolProvider.getSystemJavaCompiler();
	}
	
	public int compile(File f) {
		
		return compiler.run(null, null, null, f.getAbsolutePath());
		
	}
	
}
