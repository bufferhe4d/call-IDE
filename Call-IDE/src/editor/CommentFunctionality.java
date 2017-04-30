package editor;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Desc     A class to implement the commenting and uncommenting functionality for
 *          text editor and add those functionality to the text editor's pop up
 *          menu to provide menu items for doing functionalities in this class
 * @author  Halil Şahiner
 * @version 1.0 30.04.2017
 */
public class CommentFunctionality{
    
    private RSyntaxTextArea textArea;
    private JMenuItem commentLines, uncommentLines;
    private SearchContext searchContext;
    private String changedText;
    
    /**
     * A constructor to initialize the functionalities of menu items by adding
     * menu items to the text editor's pop up menu and add action listener for
     * those menu items. Listeners call the commenting and uncommenting methods
     * when there is an action
     * 
     * @param textArea a parameter to take the active textArea objects in the
     *                 main frame text editor tabs
     */
    public CommentFunctionality( RSyntaxTextArea textArea)
    {
        this.textArea = textArea;
        changedText = "";
        commentLines = new JMenuItem("Comment Line(s)");
        uncommentLines = new JMenuItem( "Uncomment Line(s)");
        textArea.getPopupMenu().addSeparator();
        textArea.getPopupMenu().add(commentLines);
        textArea.getPopupMenu().add(uncommentLines);
        
        commentLines.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CommentLines();
            }
        });
        uncommentLines.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                UncommentLines();
            }
        });
        
    }
    /**
     * A private method to implement a commenting functionality for the selected
     * texts in the text editor's text area
     */
    private void CommentLines()
    {
        searchContext = new SearchContext ("");
        searchContext.setSearchFor(textArea.getSelectedText());
        changedText = "//";
        if( searchContext.getSearchFor() != null)
        {
            for( int i = 0; i < searchContext.getSearchFor().length(); i++)
                if( searchContext.getSearchFor().charAt(i) == '\n')
                    changedText = changedText + searchContext.getSearchFor().charAt(i) + "//" ;
                else
                    changedText = changedText + searchContext.getSearchFor().charAt(i);
            searchContext.setReplaceWith( changedText);
            SearchEngine.replace( textArea, searchContext);
        }
    }
    /**
     * A private method to implement a uncommenting functionality for the selected
     * texts in the text editor's text area
     */
    private void UncommentLines()
    {
        searchContext = new SearchContext ("");
        searchContext.setSearchFor(textArea.getSelectedText());
        changedText = "";
        if( !searchContext.getSearchFor().substring( 0, 2).equals("//"))
            changedText = searchContext.getSearchFor().substring( 0, 2);
        
        for( int i = 2; i < searchContext.getSearchFor().length(); i++)
        {
            if( searchContext.getSearchFor().charAt( i ) == '\n' && searchContext.getSearchFor().length() - 3 > i)
            {
                changedText = changedText + searchContext.getSearchFor().charAt(i);
                if( searchContext.getSearchFor().substring( i + 1, i + 3).equals("//") )
                    i = i + 2;
            }
            else
                changedText = changedText + searchContext.getSearchFor().charAt(i);
        }
        searchContext.setReplaceWith( changedText);
        SearchEngine.replace( textArea, searchContext);
    }
    
}