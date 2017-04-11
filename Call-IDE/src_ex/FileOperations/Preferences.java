package FileOperations;

import java.awt.Font;
import java.io.Serializable;

/**
 * An immutable and serializable class to store the IDE preferences of the user
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class Preferences implements Serializable {
    
    public static final Font DEF_FONT = new Font("Consolas", Font.PLAIN, 16);
    public static final boolean[] DEF_TOOLBAR = {true, true, true,
        true, true, true, true, true, true, true, true, true, true, true};
    public static final String DEF_THEME = "/org/fife/ui/rsyntaxtextarea/themes/default.xml";
    public static final Preferences DEFAULT = new Preferences(false, true, true, true, DEF_TOOLBAR, DEF_FONT, 0, 4, DEF_THEME);
    
    private boolean autosave;
    private boolean bracketMatching;
    private boolean displayLineNumbers; 
    private boolean showHelpForErrors;
    private boolean[] toolbar; // true/false for every tool in toolbar
    private Font font; // the font object holds font name, size and style
    private int autosaveIn; // autosave period in minutes
    private int indentLevel; // number of spaces for the indentation
    private String theme; // the name of the xml theme file to load
    
    /** Creates a Preferences object with the given propreties. */
    public Preferences(boolean autosave, boolean bracketMatching, boolean displayLineNumbers,
                       boolean showHelpForErrors, boolean[] toolbar, Font font,
                       int autosaveIn, int indentLevel, String theme) {
        this.autosave = autosave;
        this.bracketMatching = bracketMatching;
        this.displayLineNumbers = displayLineNumbers;
        this.showHelpForErrors = showHelpForErrors;
        this.toolbar = toolbar; 
        this.font = font;
        this.autosaveIn = autosaveIn;
        this.indentLevel = indentLevel;
        this.theme = theme;
    }
    
    public boolean getAutosave() {
        return autosave;
    }
    public boolean getBracketMatching() {
        return bracketMatching;
    }
    public boolean getDisplayLineNumbers() {
        return  displayLineNumbers;
    }
    public boolean getShowHelpForErrors() {
        return showHelpForErrors;
    }
    public boolean[] getToolbar() {
        return  toolbar;
    }
    public Font getFont() {
        return font;
    }
    public int getAutosaveIn() {
        return autosaveIn;
    }
    public int getIndentLevel() {
        return indentLevel;
    }
    public String getTheme() {
        return theme;
    }
    
    /** Empty constructor which is needed for serialization */
    public Preferences() {}
}
