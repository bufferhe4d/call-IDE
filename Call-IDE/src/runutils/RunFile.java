package runutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Abdullah Talayhan
 */
public class RunFile implements Runnable{
    
    public Thread program = null;
    public Process process = null;
    public String buildPath;
    public ArrayList<File> deps;
    public int exitVal;
    ReadStdOut read;
    private JTextPane console; 
    private String fn;
    
    public RunFile(JTextPane cons,String filename, String buildPath, ArrayList<File> dependencies){ 
        console = cons;
        deps = dependencies;
        fn=filename;
        this.buildPath =buildPath;
        program = new Thread(this);
        program.start();
    }
    
    @Override
    public void run() {     
        try {  
            String allDeps = "";
            
            // check for os seperator
            String sep;
            if(System.getProperty("os.name").startsWith("Windows")) {
                sep = ";";
                
            }
            else {
                sep = ":";
            }
            
            if ( deps != null) {
                for(int i = 0; i < deps.size(); i++) {
                    allDeps = allDeps + deps.get(i).getAbsolutePath() + sep;
                }
            }
            //allDeps = allDeps.substring(0, allDeps.length()-1);
            System.out.println(allDeps + buildPath);
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", allDeps + buildPath, fn);
            process = builder.start();
            
            read = new ReadStdOut(process,console); 
            WriteStdIn write = new WriteStdIn(process, console);
            ReadStdErr readErr = new ReadStdErr(process, console);
            
            int x = process.waitFor();  
            exitVal = process.exitValue();
            
            while(!read.isFinished() || !readErr.isFinished()) {}
            
            appendString("\nCall-IDE: Program ended with exit value: " + process.exitValue() + "\n", console);
        }
        catch (InterruptedException e) {} 
        catch (IOException e1) {} catch (BadLocationException ex) {       
            Logger.getLogger(RunFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void kill() {
        process.destroy();
        read.killUrSelf();
    }
    
    public boolean alive() {
        return process.isAlive();
    }
    
    public  void appendString(String str, JTextPane pane) throws BadLocationException {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
}
