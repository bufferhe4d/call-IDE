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
        init();
    }

    public JTextPane getOutErrConsole() {
        return ConsoleCore.consoleOutErr(outPipe, errPipe);
    }
    
    // for testing, DO NOT DELETE
    public JTextPane getOutErr2Console(PrintStream StdOut) {
        return ConsoleCore.consoleOutErr2(outPipe, errPipe, StdOut);
    }
    
    public JTextPane getIOEConsole() {
        return ConsoleCore.consoleIOE(outPipe, inWriter, errPipe);
    }
    
    public void destroy() {
        try {
            inPipe.close();
            outPipe.close();
            errPipe.close();
            inWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsoleBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void init() {
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
    
}
