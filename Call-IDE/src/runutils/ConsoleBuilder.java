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
    
    /**
     * Consturctor for the ConsoleBuilder
     */
    public ConsoleBuilder() {
        init();
    }

    /**
     * Description: creates a JTextPane that can handle output and error streams
     * @return JTextPane that can handle output and error streams
     */
    public JTextPane getOutErrConsole() {
        return ConsoleCore.consoleOutErr(outPipe, errPipe);
    }
    
    /**
     * Description: creates alternative out/err pane with dynamic swing workers
     * @return JTextPane that can handle output and error streams
     */
    public JTextPane getOutErr2Console(PrintStream StdOut) {
        return ConsoleCore.consoleOutErr2(outPipe, errPipe, StdOut);
    }
    
    /**
     * Description: creates a JTextPane that can handle input, output and error streams
     * @return JTextPane that can handle input, output and error streams
     */
    public JTextPane getIOEConsole() {
        return ConsoleCore.consoleIOE(outPipe, inWriter, errPipe);
    }
    
    /**
     * Description: destroys the initialized console streams
     */
    public void destroy() {
        try {
            inPipe.close();
            outPipe.close();
            errPipe.close();
            inWriter.close();
        } catch (IOException ex) {}
    }
    
    /**
     * Description: initialize the console streams.
     */
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
        } catch (IOException ex) {}
    }
    
}
