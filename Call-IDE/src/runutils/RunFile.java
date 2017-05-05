/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runutils;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;



/**
 *
 * @author abdullah.talayhan-ug
 */
public class RunFile implements Runnable{

    public Thread program = null;
    public Process process = null;
    public String buildPath;
    public ArrayList<File> deps;
    public int exitVal;
    ReadStdOut read;
    //public int initLength;
    
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
            if ( deps != null) {
                for(int i = 0; i < deps.size(); i++) {
                    allDeps = allDeps + deps.get(i).getAbsolutePath() + ";";
                }
            }
            
            ProcessBuilder builder = new ProcessBuilder("java", allDeps + buildPath, fn);
            process = builder.start();

            read = new ReadStdOut(process,console); 
            WriteStdIn write = new WriteStdIn(process, console);
            ReadStdErr readErr = new ReadStdErr(process, console);

            int x = process.waitFor();  
            exitVal = process.exitValue();

            
            while(!read.isFinished() || !readErr.isFinished()) {
                
                
            }
            
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
    public  void appendString(String str, JTextPane pane) throws BadLocationException
    {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
    
    

}










