package editor;
import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.*;
import java.util.ArrayList;
import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
  *          A class to create auto-complete functionality for IDE
  * @author  Halil Sahiner
  * @version 11.05.2017
  */
public class AutoComplete {

    // properties
    
    AutoCompletion ac;
    RSyntaxTextArea activeTextArea;
    File activeFile;
    CompletionProvider provider;
     
    // constructors
    
    /**
     * A constructor to take active file which will be parsed for auto-completion
     * and text area which will be added auto-completion pane in it
     * @param activeTextArea a parameter to take active text area from the IDE
     * @param activeFile a parameter to take the active file to parse for auto-completion
     */ 
    public AutoComplete( RSyntaxTextArea activeTextArea, File activeFile) 
    {   
       this.activeTextArea = activeTextArea;
       this.activeFile = activeFile;
    }
    
    // methods
    
    /**
     * A method to create a completion provider for the auto-complete functionality
     * @param activeFile a parameter to take the active file to create auto complete for that file
     * @return  CompletionProvider object to create words which will be auto-completed
     * @throws ParseProblemException
     * @throws FileNotFoundException 
     */
    private CompletionProvider createCompletionProvider(File activeFile) throws ParseProblemException,FileNotFoundException 
     {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();
        FileInputStream in = new FileInputStream(activeFile.getAbsolutePath());
        CompilationUnit cu = JavaParser.parse(in);
        
        FieldVisitor namesOfVariables = new FieldVisitor( cu, null);
        namesOfVariables.visit(cu,null);
        
        for( int i = 0; i < namesOfVariables.getNamesOfVariables().size(); i++)
        {
            provider.addCompletion(new BasicCompletion(provider, namesOfVariables.getNamesOfVariables().get(i), namesOfVariables.getDescOfVariables().get(i) ));
            
        }
         return provider;
     }
     
    /**
     * A class to create a field visitor to take the variables of the class
     * and create lists of variables and their descriptions
     */
    private static class FieldVisitor extends VoidVisitorAdapter<Void> {
        
        private ArrayList<String> namesOfVariables;
        private ArrayList<String> descOfVariables;
        private Node mainNode;
        /**
         * A constructor to initialize the ArrayLists of variables and their descriptions,
         * and assigning the mainNode to the main node of the CompilationUnit
         * @param n
         * @param arg 
         */
        public FieldVisitor(CompilationUnit n, Void arg)
        {
            mainNode = n.getTypes().get(0);// get the main node of the CompilationUnit
            namesOfVariables = new ArrayList<String>();
            descOfVariables = new ArrayList<String>();
           
        }
        /**
         * An override method to visit the declaration of variables and 
         * add the variables, which belong to the main node only, and their
         * data types & data modifiers into ArrayLists
         * @param n a parameter to take the visitable node for lookin field of class
         * @param arg
         */
        @Override
        public void visit(FieldDeclaration  n, Void arg) {
                String variable = "";
                if( n.getParentNode().get().getEnd().equals( mainNode.getEnd()) )
                {
                    
                    for( int i = 0; i < n.getVariables().size(); i++)
                    {
                        variable = n.getVariable(i).toString().split( "=")[0];
                        if( n.getModifiers().size() != 0)
                        {
                            namesOfVariables.add( variable );
                            descOfVariables.add( n.getVariable(i).getType() + " " + n.getModifiers().toString());
                        }
                        else
                        {
                            namesOfVariables.add( variable );
                            descOfVariables.add( n.getVariable(i).getType() + " [DEFAULT]");
                        }
                            
                    }
                }
            super.visit(n, arg);
        }
        /**
         * A method to get the list of names of variables
         * @return an ArrayList of Strings of names of variables
         */
        public ArrayList<String> getNamesOfVariables()
        {
            return namesOfVariables;
        }
        /**
         * A method to get the description
         * ( data type and data modifier) of the
         * variables in the main field 
         * @return an ArrayList of Strings of descriptions of variables
         */
        public ArrayList<String> getDescOfVariables()
        {
            return descOfVariables;
        }
    }
    /**
     * A method to update the list of variables which will be auto-completed
     * @throws FileNotFoundException
     * @throws ParseProblemException 
     */
    public void updateAutoComplete() throws FileNotFoundException, ParseProblemException
    {
        provider = createCompletionProvider( activeFile);
        ac = new AutoCompletion(provider);
        ac.install(activeTextArea);
    }
}
