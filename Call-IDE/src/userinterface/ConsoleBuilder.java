/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author ATTJ
 */
public class ConsoleBuilder {
    
    PipedInputStream inPipe;
    PipedInputStream outPipe;
    PipedInputStream errPipe;
    PrintWriter inWriter;
    
    public ConsoleBuilder() {
        inPipe = new PipedInputStream();
        outPipe = new PipedInputStream();
        errPipe = new PipedInputStream();
        inWriter = null;
        
        System.setIn(inPipe);
        try {
            System.setOut(new PrintStream(new PipedOutputStream(outPipe), true));
            System.setErr(new PrintStream(new PipedOutputStream(errPipe), true));
            inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public JTextArea getInteractiveConsole() {
   
        return ConsoleCore.console(outPipe, inWriter);
    }
    
    public JTextArea getOutputConsole() {
        
        return ConsoleCore.consoleOut(outPipe);
    }
    
    public JTextPane getOutErrConsole() {
        
        return ConsoleCore.consoleOutErr(outPipe, errPipe);
    }
    
    public JTextPane getIOEConsole() {
        return ConsoleCore.consoleIOE(outPipe, inWriter, errPipe);
    }
}
