package editor;

import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class to build the logic of find and replace methods by using the
 * written methods in the RSyntax text editor
 * @author  Halil Sahiner
 * @version 1.2
 */
public class FindAndReplace
{
    private int tokenCounter;
    private SearchResult searchResult;
    private JButton nextButton, prevButton, replButton, replAllButton;
    private JCheckBox matchCase, wholeToken;
    private JTextField findText, replaceText;
    private RSyntaxTextArea textArea;
    private JFrame findAndReplaceFrame, mainFrame;
    private final SearchContext EMPTY_CONTEXT = new SearchContext("");
    
    /**
     * A constructor to take the main frame objects from parameter to use for
     * building find and replace logic
     * @param findAndReplaceFrame take the find and replace frame to construct
     * @param mainFrame     take the main frame to build find and replace frame 
     *                      according to main frame place
     * @param textArea      take text area of the text editor 
     * @param nextButton    take next button of find and replace frame
     * @param prevButton    take previous button of find and replace frame
     * @param replButton    take replace button of find and replace frame
     * @param replAllButton take replace all button of find and replace frame
     * @param findText      take the text field which contains the text searched in text
     * @param replaceText   take the text field which contains the text replaced with searched text
     * @param matchCase     take the check box to indicate the case sensitivity of search
     * @param wholeToken     take the check box to indicate the search type in terms of 
     *                      in word or apart from other word conditions
     */
    public FindAndReplace(JFrame findAndReplaceFrame, JFrame mainFrame, RSyntaxTextArea textArea,
                          JButton nextButton, JButton prevButton, JButton replButton, JButton replAllButton,
                          JTextField findText, JTextField replaceText, JCheckBox matchCase, JCheckBox wholeToken)
    {
        this.mainFrame = mainFrame;
        this.findAndReplaceFrame = findAndReplaceFrame;
        this.textArea = textArea;
        this.nextButton = nextButton;
        this.prevButton = prevButton;
        this.replButton = replButton;
        this.replAllButton = replAllButton;
        this.findText = findText;
        this.replaceText = replaceText;
        this.matchCase = matchCase;
        this.wholeToken = wholeToken;
        showFindAndReplace();
    }
    
    /**
     * A method to get the number of tokens which is passed while searching 
     * in the text
     * @return number of word which is passed while searching
     */
    private int getTokenCounter( )
    {
        return this.tokenCounter;
    }
    
    /**
     * A method to set the number of word tokens to intended number
     * @param counter take the number of word to set word counter in integer type
     */
    private void setTokenCounter( int counter)
    {
        tokenCounter = counter;
    }
    
    /**
     * A method to set the search result to relate the search function's results
     * @see   #showFindAndReplace() 
     * @param result take the SearchResult object to set the search result
     */
    private void setSearchResult( SearchResult result)
    {
        searchResult = result;
    }
    
    /**
     * A method to build the find and replace logic such as
     * search forward and backward in text according to match case and whole word
     * conditions, and replace or replace all the searched text with intended text
     */
    private void showFindAndReplace()
    {
        SearchContext searchContext = new SearchContext("");        
        findText.setText("");
        replaceText.setText("");
        setTokenCounter( 0);
        
        JButton[] buttons = new JButton[4];
        buttons[0] = nextButton;
        buttons[1] = prevButton;
        buttons[2] = replButton;
        buttons[3] = replAllButton;
        
        matchCase.setSelected(false);
        wholeToken.setSelected(false);
        
        nextButton.addActionListener(new ActionListener(){
            int counter2;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                counter2 = getTokenCounter();
                searchContext.setSearchForward(true);
                if(!findText.getText().equals(searchContext.getSearchFor()))
                {
                    if( matchCase.isSelected() || wholeToken.isSelected())
                        setTokenCounter(0);
                    counter2 = 1;
                    textArea.setCaretPosition(0);
                    searchContext.setSearchFor(findText.getText());
                    searchResult = SearchEngine.find(textArea, searchContext);
                }
                else
                {
                    if (!searchContext.getSearchFor().isEmpty()) {
                        if( searchResult.getMarkedCount() == counter2)
                        {
                            textArea.setCaretPosition(0);
                            SearchEngine.find(textArea, searchContext);
                            counter2 = 1;
                        }
                        else
                        {                                            
                            textArea.setCaretPosition(textArea.getCaretPosition());
                            SearchEngine.find(textArea, searchContext);
                            counter2++;
                        }  
                    }
                }
                setSearchResult( searchResult);
                setTokenCounter( counter2);
            }                                                   
        });
        
        prevButton.addActionListener(new ActionListener(){
            int counter;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                counter = getTokenCounter();
                searchContext.setSearchForward(false);
                if(!findText.getText().equals(searchContext.getSearchFor()))
                {
                    setTokenCounter(0);
                    searchContext.setSearchFor(findText.getText());
                    textArea.setCaretPosition(textArea.getText().length());
                    searchResult = SearchEngine.find(textArea, searchContext);
                    counter = searchResult.getMarkedCount();
                }
                else
                {
                    if (!searchContext.getSearchFor().isEmpty()) {
                        if( 1 == counter)
                        {
                            textArea.setCaretPosition(textArea.getText().length());
                            searchResult = SearchEngine.find(textArea, searchContext);
                            
                            counter = searchResult.getMarkedCount();
                        }
                        else
                        {                                            
                            textArea.setCaretPosition(textArea.getCaretPosition() - 1);
                            searchResult = SearchEngine.find(textArea, searchContext);
                            counter--;                                            
                        }  
                    }
                }
                setSearchResult( searchResult);
                setTokenCounter(counter);    
            }         
        });
        
        replButton.addActionListener(new ActionListener(){
            int position = 0;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                searchContext.setReplaceWith( replaceText.getText());
                searchContext.setSearchFor(findText.getText());
                searchContext.setSearchForward(true);
                if( !searchContext.getSearchFor().isEmpty() && !searchContext.getReplaceWith().isEmpty())
                {
                    if( textArea.getCaretPosition() + ( searchContext.getSearchFor().length() - searchContext.getReplaceWith().length() ) == position )
                    {                                            
                        textArea.setCaretPosition(0);
                        searchResult = SearchEngine.replace(textArea,searchContext);
                        if(  searchResult.getMarkedCount() != 0 )
                            textArea.setCaretPosition( textArea.getCaretPosition() - searchContext.getSearchFor().length());                                           
                    }
                    else
                    {
                        position = textArea.getCaretPosition();
                        searchResult = SearchEngine.replace(textArea,searchContext);
                        if(  searchResult.getMarkedCount() != 0 )
                            textArea.setCaretPosition( textArea.getCaretPosition() -  searchContext.getSearchFor().length());                                       
                    }
                    setTokenCounter( getTokenCounter()-1);                              
                    if( searchResult != null) 
                        searchResult.setMarkedCount(searchResult.getMarkedCount() - 1);                                    
                }    
            }         
        });
        
        replAllButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                searchContext.setReplaceWith( replaceText.getText());
                searchContext.setSearchFor(findText.getText());
                if( !searchContext.getSearchFor().isEmpty() && !searchContext.getReplaceWith().isEmpty())
                {
                    textArea.setCaretPosition(0);
                    SearchEngine.replaceAll(textArea, searchContext);
                    setTokenCounter( 0);
                }
            }
        });
        
        matchCase.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JCheckBox cb = (JCheckBox) arg0.getSource();
                if (cb.isSelected()) {
                    //setTokenCounter(0);
                    searchContext.setMatchCase(true);
                } else {
                    searchContext.setMatchCase(false);
                }   
            }   
        });
        
        wholeToken.addActionListener(new ActionListener(){             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JCheckBox cb = (JCheckBox) arg0.getSource();
                if (cb.isSelected()) {
                    searchContext.setWholeWord(true);
                } 
                else {
                    searchContext.setWholeWord(false);
                }  
            }           
        });
        
        findAndReplaceFrame.pack();
        findAndReplaceFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                SearchEngine.find(textArea, EMPTY_CONTEXT );
                for( JButton currentButton: buttons)
                for( ActionListener al : currentButton.getActionListeners() ) {
                    currentButton.removeActionListener( al );
                }
            }
        });
        
        findAndReplaceFrame.setLocationRelativeTo( mainFrame);
        findAndReplaceFrame.setVisible(true);
    }
}
