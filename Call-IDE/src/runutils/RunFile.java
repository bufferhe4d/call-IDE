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
    
    /**
     * Description: Runs the given file while handling all of the streams
     * @param cons console pane for redirection
     * @param filename path of the file to be executed
     * @param buildPath path of the build class
     * @param dependencies list of build dependencies
     */
    public RunFile(JTextPane cons,String filename, String buildPath, ArrayList<File> dependencies){ 
        console = cons;
        deps = dependencies;
        fn=filename;
        this.buildPath =buildPath;
        program = new Thread(this);
        program.start();
    }
    
    /**
     * Description: run method for the RunFile thread
     */
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
            // allDeps = allDeps.substring(0, allDeps.length()-1);
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
    
    /**
     * kill the current executing program
     */
    public void kill() {
        if (process != null && read != null) {
            process.destroy();
            read.killUrSelf();
        }
    }
    
    /**
     * check if the process is running or not
     * @return boolean state of the running project
     */
    public boolean alive() {
        return process != null && process.isAlive();
    }
    
    /**
     * Description: method that appends the string to a given pane
     * @param str string to be appended
     * @param pane pane to be appended
     * @throws BadLocationException 
     */
    public  void appendString(String str, JTextPane pane) throws BadLocationException {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
}
