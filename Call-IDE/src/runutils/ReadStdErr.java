package runutils;

import helputils.ExceptionParser;
import helputils.LinkOpener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Abdullah Talayhan
 */
public class ReadStdErr implements Runnable{
    
    public Thread read = null;
    private BufferedReader reader = null;
    private Process process = null;
    private JTextPane console = null;
    private boolean finish;
    
    public ReadStdErr(Process p,JTextPane t) {
        finish = false;
        process = p;
        reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        console = t;
        
        read = new Thread(this);
        read.start();
    }

    public void run() {
        String line;
        try {
        while((line = reader.readLine())!=null) {
            if (line.startsWith("Exception in thread")) {
                console.getDocument().insertString(console.getDocument().getLength(),
                    line + " (See the help about this exception: " +
                    ExceptionParser.getExceptionLink( line) + 
                    ") [Double-click to visit.]\n", changeColor(new DefaultStyledDocument()));
                addLink( ExceptionParser.getExceptionLink( line));
            }
            else
                console.getDocument().insertString(console.getDocument().getLength(),
                    line + "\n", changeColor(new DefaultStyledDocument()));
        }
            }catch (IOException e) {} catch (BadLocationException ex) {
            Logger.getLogger(ReadStdErr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadStdErr.class.getName()).log(Level.SEVERE, null, ex);
        }
        finish = true;
    }
    
    public  void appendString(String str, JTextPane pane) throws BadLocationException {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
    
    public  Style changeColor(DefaultStyledDocument document) throws BadLocationException {
        StyleContext context = new StyleContext();
        // build a style
        Style style = context.addStyle("test", null);
        // set some style properties
        StyleConstants.setForeground(style, Color.RED);
        return style;
    }
    
    public boolean isFinished() {
        return finish;
    }
    
    private void addLink( String link) {
        console.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed( MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    try {
                        LinkOpener.openLink( link);
                    } catch (Exception ex) {}
                }
            }
        });
    }
    
}
