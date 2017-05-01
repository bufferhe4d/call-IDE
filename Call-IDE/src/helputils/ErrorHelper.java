package helputils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;

/**
 * This class represent a error helper tab on output tabs for get errors and give some helper link
 * @author   Mahmud Sami Aydin
 * @version  1.00, 1/5/2017
 */
public class ErrorHelper extends JPanel 
{
    //properties
    static JLabel[]   errors;
    static LinkingLabel[]   links;
    static int valid;
    
    //constructors
    public ErrorHelper()
    {
        super();
        setName("Error Helper");
        GridLayout layout = new GridLayout(10,2);
        setLayout(layout);
        
        errors = new JLabel[10];
        links = new LinkingLabel[10];
        valid = 0;
        
        for( int i = 0; i < 10 ; i++)
        {
            errors[i] = new JLabel();
            links[i] = new LinkingLabel("");
                add( errors[i] );
                add( links [i] );

        }
           
        setVisible(true);
        
    }
    
    //methods
    public static void addError( String error )
    {
        StringBuffer errorName;
        errorName = new StringBuffer( error );
        if( errorName.indexOf("Exception") > -1 )
        {            
            int firstIndex = errorName.lastIndexOf(".", errorName.indexOf("Exception")) + 1;
            int lastIndex = errorName.indexOf("Exception") + "Exception".length();
            error = errorName.substring(firstIndex, lastIndex);
            for( int i = valid ; i > 0 ; i-- )
            {
                errors[i].setName( errors[i-1].getName());
                errors[i].setText( errors[i-1].getText() );
                links[i].setText ( links [i-1].getText() );
            }
            errors[0].setText(error );
            links[0].setText(ExceptionHelper.getHelpLink( error ) );
            if(valid < 9 )
               valid++;            
        }
    }
    
    
}
