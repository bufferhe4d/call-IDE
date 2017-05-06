package runutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Abdullah Talayhan
 */
public class ReadStdOut implements Runnable {
    
    public Thread read = null;
    private BufferedReader reader = null;
    private Process process = null;
    private JTextPane console = null;
    private boolean finish;
    
    public ReadStdOut(Process p,JTextPane t) {
        finish = false;
        process = p;
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        console = t;
        read = new Thread(this);
        read.start();
    }
    
    public void run() {
        try {
            char nextChar = (char) reader.read();
            
            while (("" + nextChar).matches("^\\p{ASCII}*$")) {
                Thread.sleep(1);
                appendString("" + nextChar,console);
                console.setCaretPosition(console.getDocument().getLength());
                nextChar = (char) reader.read();
            }
            
            reader.close();
            finish = true;
            
        } catch (IOException ex) {
            Logger.getLogger(ReadStdOut.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadLocationException ex) {
            Logger.getLogger(ReadStdOut.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public  void appendString(String str, JTextPane pane) throws BadLocationException {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
    
    public void closeAll() {
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadStdOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isFinished() {
        return finish;
    }
    
    public void killUrSelf() {
        read.interrupt();
    }
}
