package runutils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Abdullah Talayhan
 */
public class WriteStdIn implements Runnable{
    
    private Process process = null;
    private JTextPane console = null;
    public Thread write = null;
    private String input = null;
    private BufferedWriter writer = null;
    int initLength;
    
    /**
     * Description: Handles the System.in from a forked process
     * @param p forked process
     * @param t pane that the err stream going to be directed
     */
    public WriteStdIn(Process p, JTextPane t){
        
        process = p;
        console = t;
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        
        write = new Thread(this);
        write.start();
        
        console.addKeyListener(new KeyAdapter() {
            //initLength = 0;
            boolean notTypedYet = true;
            //String inputStr = "";
            
            @Override
            public void keyTyped(KeyEvent e) {
                
                // get the initial document properties
                if(notTypedYet /*&& e.getKeyChar()!= KeyEvent.VK_ENTER*/) {
                    notTypedYet = false;
                    initLength = console.getDocument().getLength();
                    console.setCaretPosition(console.getDocument().getLength());
                    
                    if(e.getKeyChar()== KeyEvent.VK_ENTER) {
                        try {
                            console.getDocument().insertString(initLength, "\n", null);
                            notTypedYet = true;
                            
                        } catch (BadLocationException ex) {
                            Logger.getLogger(WriteStdIn.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else {
                    if(console.getSelectionStart() < initLength) {
                        e.consume();
                    }
                }
                
                // distinguish the input from the actual document and
                // send it to the output stream which pipes into the
                // input stream of the executed program
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        input = console.getText(initLength, console.getDocument().getLength() - initLength - 1);
                        input =input + "\n";
                        notTypedYet = true;
                        write.resume();
                        //inputStr = "";
                    } catch (BadLocationException ex) {
                        Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if(notTypedYet ) {
                    notTypedYet = false;
                    initLength = console.getDocument().getLength();
                    console.setCaretPosition(console.getDocument().getLength());
                    if(e.getKeyChar()== KeyEvent.VK_ENTER) {
                        try {
                            console.getDocument().insertString(initLength, "\n", null);
                            notTypedYet = true;
                        } catch (BadLocationException ex) {
                            Logger.getLogger(WriteStdIn.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else {
                    if(console.getSelectionStart() < initLength) {
                        e.consume();
                    }
                }
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    console.setCaretPosition(console.getDocument().getLength());
                }
                // consume the unwanted backspace occurences.
                else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if(initLength - console.getCaretPosition() >= 0) {
                        e.consume();
                    }
                    if(notTypedYet) {
                        e.consume();
                    }
                }
                else if(e.getKeyCode() == 37) {
                    if(initLength - console.getCaretPosition() >= 0) {
                        e.consume();
                    }
                }
                else if(e.getKeyCode() == 38) {
                    if(initLength - console.getCaretPosition() + (console.getDocument().getLength() - initLength)>= 0) {
                        e.consume();
                    }
                }
            }
        });
        
        console.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e)
            {
                console.setCaretPosition(console.getDocument().getLength());               
            }   
        });
        
        console.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(initLength - console.getCaretPosition() >= 0) {
                    console.setCaretPosition(console.getDocument().getLength());
                }
            }
        });
    }
    
    /**
     * Description: run method for the ReadStdOut thread
     */
    @Override
    public void run() {
        write.suspend();
        while (true) {
            try {
                //send variable input in stdin of process
                writer.write(input);
                writer.flush();
            } catch (IOException e) {}
            write.suspend();
        }
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
    
    /**
     * Description: closes the writer
     */
    public void closeAll() {
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(WriteStdIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * kill the process of the thread
     */
    public void killUrSelf() {
        write.interrupt();
    }
    
}
