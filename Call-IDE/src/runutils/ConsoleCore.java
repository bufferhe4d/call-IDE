package runutils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.BorderLayout;
import java.io.PrintStream;

/**
 * A class to build different consoles with the given streams.
 * @author Abdullah Talayhan
 */
public class ConsoleCore {
    // Only handles Error and Output Streams
    static Scanner scanOut;
    static Scanner scanErr;
    static SwingWorker<Void, String> sw1;
    static SwingWorker<Void, String> sw2;
    
    public static JTextPane consoleOutErr(final InputStream out, final InputStream err) {

        // Styled document for coloring the error stream
        DefaultStyledDocument document = new DefaultStyledDocument();
        final JTextPane area = new JTextPane(document);

        // Handle "System.out"
        sw1 = new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                scanOut = new Scanner(out);
                // read the stream and publish the results
                while (scanOut.hasNextLine()) publish(scanOut.nextLine() + "\n");

                return null;
            }
            @Override protected void process(List<String> chunks) {
                // read the lines from published chunks
                for (String line : chunks) try {
                    // append them to the text pane
                    appendString(line, area);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }; 
        
        sw1.execute(); // execute the SwingWorker

        sw2 = new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                scanErr = new Scanner(err);
                // read the stream and publish the results
                while (scanErr.hasNextLine()) publish(scanErr.nextLine() + "\n");

                return null;
            }
            @Override protected void process(List<String> chunks) {
                // change the stream color to red for errors
                try {
                    changeColor(document);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                // read the lines from published chunks
                for (String line : chunks) try {
                    //appendString(line, area);
                    //append them to the  text pane
                    document.insertString(document.getLength(), line, changeColor(document));
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }; 
        
        sw2.execute();// execute the SwingWorker

        return area;

    }
    
    
    public static JTextPane consoleOutErr2(final InputStream out, final InputStream err, PrintStream StdOut) {

        // Styled document for coloring the error stream
        DefaultStyledDocument document = new DefaultStyledDocument();
        final JTextPane area = new JTextPane(document);

        // Handle "System.out"
        sw1 = new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                scanOut = new Scanner(out);
                // read the stream and publish the results
                while (scanOut.hasNextLine()) publish(scanOut.nextLine() + "\n");
                
                return null;
            }
            @Override protected void process(List<String> chunks) {
                // read the lines from published chunks
                for (String line : chunks) try {
                    // append them to the text pane
                    StdOut.println(line);
                    appendString(line, area);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };//.execute(); // execute the SwingWorker
        sw1.execute();
        
        sw2 = new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                Scanner scanErr = new Scanner(err);
                // read the stream and publish the results
                while (scanErr.hasNextLine()) publish(scanErr.nextLine() + "\n");

                return null;
            }
            @Override protected void process(List<String> chunks) {
                // change the stream color to red for errors
                try {
                    changeColor(document);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                // read the lines from published chunks
                for (String line : chunks) try {
                    //appendString(line, area);
                    //append them to the  text pane
                    StdOut.println(line);
                    document.insertString(document.getLength(), line, changeColor(document));
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };//.execute(); // execute the SwingWorker
        sw2.execute();
        return area;

    }
    
    // free the swingWorkers.
    public static void free() {
   
        if(sw1 != null) {
            sw1.cancel(true);
            sw2.cancel(true);
        }
    }
    
    public static JTextPane consoleIOE(final InputStream out, final PrintWriter in, final InputStream err) {

        // Styled document for coloring the error stream
        DefaultStyledDocument document = new DefaultStyledDocument();
        final JTextPane area = new JTextPane(document);

        // Handle "System.out"
        new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {

                // read the stream and publish the results
                // BufferedReader for not interrupting the input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(out));
                char nextChar = (char) reader.read();
                while (nextChar != -1) {
                    publish("" + nextChar);
                    nextChar = (char) reader.read();
                }

                return null;
            }

            @Override protected void process(List<String> chunks) {
              // read the lines from published chunks
                for (String line : chunks) try {
                    //append them to the  text pane
                    appendString(line,area);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute(); // execute the SwingWorker

        // Handle "System.in"
        area.addKeyListener(new KeyAdapter() {
            int initLength;
            boolean notTypedYet = true;
            String inputStr = "";

            @Override
            public void keyTyped(KeyEvent e) {
                // get the initial document properties
                if(notTypedYet && e.getKeyChar()!= KeyEvent.VK_ENTER) {
                    notTypedYet = false;
                    initLength = area.getDocument().getLength();
                    area.setCaretPosition(area.getDocument().getLength());
                }
                // distinguish the input from the actual document and
                // send it to the output stream which pipes into the
                // input stream of the executed program
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        inputStr = area.getText(initLength, area.getDocument().getLength() - initLength - 1);
                        in.println(inputStr);
                        notTypedYet = true;
                        inputStr = "";

                    } catch (BadLocationException ex) {
                        Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    area.setCaretPosition(area.getDocument().getLength());
                }
                // consume the unwanted backspace occurences.
                else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if(initLength - area.getCaretPosition() == 0) {
                        e.consume();
                    }
                    if(notTypedYet) {
                        e.consume();
                    }
                }
            }
        });

        // Handle "System.err"
        new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                Scanner s = new Scanner(err);
                int currentLine = 0;
                String error;

                while (s.hasNextLine())
                {
                    currentLine++;
                    if( currentLine == 8 )
                    {
                        // get the error line to use in errorhelper
                        error = s.nextLine();
                        publish(error + "\n");
                    }
                    else
                    {
                        // publish the rest
                        publish(s.nextLine() + "\n");
                    }
                }
                return null;
            }
            
            @Override protected void process(List<String> chunks) {
                // change the stream color to red for errors
                try {
                    changeColor(document);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                // read the chunks and append them to the text pane
                for (String line : chunks)
                    try {
                    document.insertString(document.getLength(), line, changeColor(document));
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute(); // execute the SwingWorker

        return area;
    }

    // append method for JTextPane
    public static void appendString(String str, JTextPane pane) throws BadLocationException
    {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
    // change the color of the JTextPane document
    public static Style changeColor(DefaultStyledDocument document) throws BadLocationException {
        StyleContext context = new StyleContext();
        // build a style
        Style style = context.addStyle("test", null);
        // set some style properties
        StyleConstants.setForeground(style, Color.RED);
        return style;
    }
    
    // dispatch the console from the JTabbedPane and put it back
    public static void  dispatch(JScrollPane scrollPane, JTextPane cons,
                                 JTabbedPane outputTabs, Component tabComp, JFrame frame,
                                 Boolean consoleOut, Attachable mainFrame) {
        frame.setSize(600, 400);
        frame.setLocationRelativeTo( (Component) mainFrame);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        scrollPane.setViewportView(cons);
        frame.add(scrollPane);
        
        if (frame.getWindowListeners().length > 0)
            frame.removeWindowListener(frame.getWindowListeners()[0]);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.attachConsole();
            }
        });
        frame.setVisible(true);
    }

}
