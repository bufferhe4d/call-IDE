package runutils;

import userinterface.MainFrame;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

/**
 * A class that can build consoles as text panes.
 * @author Abdullah Talayhan
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

    public JTextPane getOutErrConsole() {
        return ConsoleCore.consoleOutErr(outPipe, errPipe);
    }
    
    public JTextPane getIOEConsole() {
        return ConsoleCore.consoleIOE(outPipe, inWriter, errPipe);
    }
    
}
