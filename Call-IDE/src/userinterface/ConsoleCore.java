/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import javax.swing.JTabbedPane;
import java.awt.Component;

/**
 *
 * @author abdullah.talayhan-ug
 */
public class ConsoleCore {
    
    public static JTextArea console(final InputStream out, final PrintWriter in) {
    final JTextArea area = new JTextArea();
    DefaultStyledDocument document = new DefaultStyledDocument();
    //static JTextPane textpane = new JTextPane(document);
    // handle "System.out"
    new SwingWorker<Void, String>() {
        @Override protected Void doInBackground() throws Exception {
            Scanner s = new Scanner(out);
            while (s.hasNextLine()) publish(s.nextLine() + "\n");
            return null;
        }
        @Override protected void process(List<String> chunks) {
            for (String line : chunks) area.append(line);
        }
    }.execute();

    // handle "System.in"
    area.addKeyListener(new KeyAdapter() {
        private StringBuffer line = new StringBuffer();
        @Override public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                in.println(line);
                line.setLength(0); 
            } else if (c == KeyEvent.VK_BACK_SPACE) { 
                line.setLength(line.length() - 1); 
            } else if (!Character.isISOControl(c)) {
                line.append(e.getKeyChar());
            }
        }
    });

    return area;
    }
    
    public static JTextArea consoleOut(final InputStream out) {
    
    final JTextArea area = new JTextArea();

    // handle "System.out"
    new SwingWorker<Void, String>() {
        @Override protected Void doInBackground() throws Exception {
            Scanner s = new Scanner(out);
            while (s.hasNextLine()) publish(s.nextLine() + "\n");
            return null;
        }
        @Override protected void process(List<String> chunks) {
            for (String line : chunks) area.append(line);
        }
    }.execute();

    return area;
    }
    
    public static JTextPane consoleOutErr(final InputStream out, final InputStream err) {
        
    DefaultStyledDocument document = new DefaultStyledDocument();
    final JTextPane area = new JTextPane(document);

    // handle "System.out"
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
    
    // handle "System.out"
    new SwingWorker<Void, String>() {
        @Override protected Void doInBackground() throws Exception {
            Scanner s = new Scanner(out);
            while (s.hasNextLine()) publish(s.nextLine() + "\n");
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

    // handle "System.in"
    area.addKeyListener(new KeyAdapter() {
        private StringBuffer line = new StringBuffer();
        @Override public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                in.println(line);
                line.setLength(0); 
            } else if (c == KeyEvent.VK_BACK_SPACE) { 
                line.setLength(line.length() - 1); 
            } else if (!Character.isISOControl(c)) {
                line.append(e.getKeyChar());
            }
        }
    });
    // handle "System.err"
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
    // add some data to the document
    //document.insertString(document.getLength(), "joe", style);
    return style;
    }
    
    public static void  dispatch(JScrollPane scrollPane, JTextArea cons, JTabbedPane outputTabs, Component tabComp) {
        // TODO add your handling code here:
        JFrame frame = new JFrame("\"Console\"");
        frame.add(cons);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                scrollPane.setViewportView(cons);
                outputTabs.setTabComponentAt( 2, tabComp);
                outputTabs.removeChangeListener( outputTabs.getChangeListeners()[0]);
                outputTabs.setSelectedIndex(2);
            }
        });
        //frame.setDefaultCloseOperation(putBack(frame));
        frame.setVisible(true);
        
    }
        
}
