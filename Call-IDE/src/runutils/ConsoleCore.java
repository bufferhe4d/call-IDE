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
import helputils.ErrorHelper;

/**
 * A class to build different consoles with the given streams.
 * @author Abdullah Talayhan
 */
public class ConsoleCore {
    
    public static JTextPane consoleOutErr(final InputStream out, final InputStream err) {
        
        DefaultStyledDocument document = new DefaultStyledDocument();
        final JTextPane area = new JTextPane(document);
        
        // Handle "System.out"
        new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                Scanner s = new Scanner(out);
                while (s.hasNextLine()) publish(s.nextLine() + "\n");
                return null;
            }
            @Override protected void process(List<String> chunks) {
                for (String line : chunks) try {
                    appendString(line, area);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
        
        new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                Scanner s = new Scanner(err);
                while (s.hasNextLine()) publish(s.nextLine() + "\n");
                return null;
            }
            @Override protected void process(List<String> chunks) {
                try {
                    changeColor(document);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (String line : chunks) try {
                    //appendString(line, area);
                    document.insertString(document.getLength(), line, changeColor(document));
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
        
        return area;
        
    }
    
    public static JTextPane consoleIOE(final InputStream out, final PrintWriter in, final InputStream err) {
        
        DefaultStyledDocument document = new DefaultStyledDocument();
        final JTextPane area = new JTextPane(document);
        
        // Handle "System.out"
        new SwingWorker<Void, String>() {
            @Override protected Void doInBackground() throws Exception {
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(out));
                char nextChar = (char) reader.read();
                while (nextChar != -1) {
                    publish("" + nextChar);
                    nextChar = (char) reader.read();
                }
                
                return null;
            }
            
            @Override protected void process(List<String> chunks) {
                for (String line : chunks) try {
                    appendString(line,area);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
        
        // Handle "System.in"
        area.addKeyListener(new KeyAdapter() {
            private StringBuffer line = new StringBuffer();
            @Override public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    in.println(line);
                    line.setLength(0); 
                } else if (c == KeyEvent.VK_BACK_SPACE && line.length() > 0) { 
                    line.setLength(line.length() - 1); 
                } else if (!Character.isISOControl(c)) {
                    line.append(e.getKeyChar());
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
                        error = s.nextLine();
                        ErrorHelper.addError(error);
                        publish(error + "\n");
                    }
                    else
                    {
                        publish(s.nextLine() + "\n");
                    }
                return null;
            }
            @Override protected void process(List<String> chunks) {
                try {
                    changeColor(document);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (String line : chunks)
                    try {
                    document.insertString(document.getLength(), line, changeColor(document));
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConsoleCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
        
        return area;
    }
    
    public static void appendString(String str, JTextPane pane) throws BadLocationException
    {
        StyledDocument document = (StyledDocument) pane.getDocument();
        document.insertString(document.getLength(), str, null);
    }
    
    public static Style changeColor(DefaultStyledDocument document) throws BadLocationException {
        StyleContext context = new StyleContext();
        // build a style
        Style style = context.addStyle("test", null);
        // set some style properties
        StyleConstants.setForeground(style, Color.RED);
        return style;
    }
    
    public static void  dispatch(JScrollPane scrollPane, JTextPane cons,
                                 JTabbedPane outputTabs, Component tabComp, JFrame frame,
                                 Boolean consoleOut, Attachable mainFrame) {
        frame.add(cons);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (frame.getWindowListeners().length > 0)
            frame.removeWindowListener(frame.getWindowListeners()[0]);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                mainFrame.attachConsole();
            }
        });
        frame.setVisible(true);
    }
    
}
