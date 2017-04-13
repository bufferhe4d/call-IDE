/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *         A class to build the logic of find and replace methods by using the
 *         written methods in the RSyntax text editor
 * @author Halil Şahiner
 */
public class FindAndReplace {
    
    private int wordCounter;
    private SearchResult searchResult;
    private JButton nextButton, prevButton, replButton, replAllButton;
    private JCheckBox matchCase, wholeWord;
    private JTextField findText, replaceText;
    private RSyntaxTextArea textArea;
    private JFrame findAndReplaceFrame, mainFrame;
    private final SearchContext EMPTY_CONTEXT = new SearchContext("");
    
            
    
    public FindAndReplace( JFrame  findAndReplaceFrame, JFrame mainFrame,RSyntaxTextArea textArea, JButton nextButton, JButton prevButton, JButton replButton,
                           JButton replAllButton, JTextField findText, JTextField replaceText, JCheckBox matchCase, JCheckBox wholeWord )
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
        this.wholeWord = wholeWord;
        showFindAndReplace();
    }
    private int getWordCounter( )
    {
        return this.wordCounter;
    }  
    private void setWordCounter( int counter)
    {
        wordCounter = counter;
    }

    private void setSearchResult( SearchResult result)
    {
        searchResult = result;
    }
    private void showFindAndReplace()
    {
        
        SearchContext searchContext = new SearchContext("");
        
        findText.setText("");
        replaceText.setText("");
        JButton[] buttons = new JButton[4];
        buttons[0] = nextButton;
        buttons[1] = prevButton;
        buttons[2] = replButton;
        buttons[3] = replAllButton;
        setWordCounter( 0);
        matchCase.setSelected(false);
        wholeWord.setSelected(false);
        
        nextButton.addActionListener(new ActionListener(){
                        int counter2;
			@Override
			public void actionPerformed(ActionEvent arg0) {
                               counter2 = getWordCounter();
                               searchContext.setSearchForward(true);
                               if(!findText.getText().equals(searchContext.getSearchFor()))
                               {
                                   if( matchCase.isSelected() || wholeWord.isSelected())
                                        setWordCounter(0);
                                    counter2 = 1;
                                    textArea.setCaretPosition(0);
                                    searchContext.setSearchFor(findText.getText());
                                    searchResult = SearchEngine.find(textArea, searchContext);
                                    System.out.println( searchResult.getMarkedCount()+ " " + counter2 + "1ilk if " + textArea.getCaretPosition());
                               }
                               else
                               {
                                    if (!searchContext.getSearchFor().isEmpty()) {
                                        
                                        if( searchResult.getMarkedCount() == counter2)
                                        {
                                           
                                            textArea.setCaretPosition(0);
                                            SearchEngine.find(textArea, searchContext);
                                            counter2 = 1;
                                            System.out.println( searchResult.getMarkedCount()+ " " + counter2 + "1iki if " + textArea.getCaretPosition());
                                        }
                                        else
                                        {                                            
                                            textArea.setCaretPosition(textArea.getCaretPosition());
                                            SearchEngine.find(textArea, searchContext);
                                            counter2++;
                                            System.out.println( searchResult.getMarkedCount()+ " " + counter2 + "1üç if " + textArea.getCaretPosition());
                                        }  
                                    }
                               }
                               setSearchResult( searchResult);
                               setWordCounter( counter2);
			}
                        
                          	
        });
        prevButton.addActionListener(new ActionListener(){
                        
                        
                        int counter;
			@Override
			public void actionPerformed(ActionEvent arg0) {
                               counter = getWordCounter();
                               searchContext.setSearchForward(false);
                               if(!findText.getText().equals(searchContext.getSearchFor()))
                               {
                                    setWordCounter(0);
                                    searchContext.setSearchFor(findText.getText());
                                    textArea.setCaretPosition(textArea.getText().length());
                                    searchResult = SearchEngine.find(textArea, searchContext);
                                    counter = searchResult.getMarkedCount();
                                    System.out.println( searchResult.getMarkedCount()+ " " + counter + "2ilk if " +textArea.getCaretPosition());
                               }
                               else
                               {
                                    if (!searchContext.getSearchFor().isEmpty()) {
                                        if( 1 == counter)
                                        {
                                            textArea.setCaretPosition(textArea.getText().length());
                                            searchResult = SearchEngine.find(textArea, searchContext);
                                            
                                            counter = searchResult.getMarkedCount();
                                            System.out.println( searchResult.getMarkedCount()+ " " + counter + "2iki if "+ textArea.getCaretPosition());
                                        }
                                        else
                                        {                                            
                                            textArea.setCaretPosition(textArea.getCaretPosition() - 1);
                                            searchResult = SearchEngine.find(textArea, searchContext);
                                            counter--;
                                            System.out.println( searchResult.getMarkedCount()+ " " + counter + "2üç if " + textArea.getCaretPosition());
                                        }  
                                    }
                               }
                               setSearchResult( searchResult);
                               setWordCounter(counter);
				
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
                                    if( textArea.getCaretPosition() == position )
                                        {
                                            
                                            textArea.setCaretPosition(0);
                                            searchResult = SearchEngine.replace(textArea,searchContext);
                                            if(  searchResult.getMarkedCount() != 0 )
                                                textArea.setCaretPosition( textArea.getCaretPosition() - 1);
                                            
                                        }
                                    else
                                    {
                                        position = textArea.getCaretPosition();
                                        searchResult = SearchEngine.replace(textArea,searchContext);
                                        if(  searchResult.getMarkedCount() != 0 )
                                            textArea.setCaretPosition( textArea.getCaretPosition() -  1);
                                        
                                        System.out.println(textArea.getCaretPosition() + "" + position);
                                    }
                                    setWordCounter( getWordCounter()-1);                              
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
                                    setWordCounter( 0);
                                    System.out.println(getWordCounter());
				}
                                
			}
        	
        });
        
        matchCase.addActionListener(new ActionListener(){
            
                        
                        @Override
			public void actionPerformed(ActionEvent arg0) {
                            JCheckBox cb = (JCheckBox) arg0.getSource();
                            if (cb.isSelected()) {
                                //setWordCounter(0);
                                searchContext.setMatchCase(true);
                            } else {
                               searchContext.setMatchCase(false);
                            }
                            
                        }
        
            
        });
        wholeWord.addActionListener(new ActionListener(){
            
                        
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
       // findAndReplaceFrame.setLocationRelativeTo(this);
        findAndReplaceFrame.addWindowListener(new WindowAdapter(){
            
            @Override
            public void	windowClosing(WindowEvent e)
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
