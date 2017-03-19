
import java.io.File;

import  javax.tools.*;

public class Compiler {
	
	private JavaCompiler compiler;
	
	public Compiler() {
		
		compiler =  ToolProvider.getSystemJavaCompiler();
	}
	
	public int run(File f) {
		
		return compiler.run(null, null, null, f.getAbsolutePath());
		
	}
	
}
