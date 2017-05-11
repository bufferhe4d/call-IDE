package helputils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 * This class represent links with proper color behavior for exception help
 * @author Mahmud Sami Aydin
 */
class LinkingLabel extends JLabel
{
    //constants
    final Color NON_CLICKED = Color.BLUE;
    final Color HOVER      = Color.RED;
    final Color CLICKED    = Color.MAGENTA;
    
    //properties
    String link;
    Color prevHover;
    
    /**
     *This constructor take link and make a label 
     *@param link web address will be reached when clicked
     */
    LinkingLabel( String link)
    {
        super(  link );
        this.link = link;
        prevHover  = NON_CLICKED;
        setForeground( NON_CLICKED );
        addMouseListener(new linkListener());
    }
    
    /**
     * This method set the link and change test
     *@param link web address will be reached when clicked
     */
    @Override
    public void setText(String link)
    {
        super.setText(link);
        this.link = link;
    }
    
    /**
     * This class a listener for link it react with mouse properly and change text color
     */
    private class linkListener extends MouseAdapter
    {
        boolean inside;
        
        @Override
        public void mouseClicked( MouseEvent e){
            if( !link.equals("No help found about this exception."))
            {
                try { LinkOpener.openLink(link); } catch (Exception exc) {}
                setForeground(CLICKED);
                prevHover = CLICKED;
            }
        }
        
        @Override
        public void mousePressed( MouseEvent e)
        {
            if( !link.equals("No help found about this exception."))
            {
                setForeground(HOVER);
            }
        }
        
        @Override
        public void mouseReleased( MouseEvent e)
        {
            if( !link.equals("No help found about this exception.") )
            {
                if( inside)
                {
                    try { LinkOpener.openLink(link); } catch (Exception exc) {}
                    setForeground(CLICKED);
                    prevHover = CLICKED;
                }    
                else    
                    setForeground(prevHover);
            }
        }
        
        @Override
        public void mouseEntered( MouseEvent e)
        {
            if(  !link.equals("No help found about this exception.") )
            {
                inside = true;
                Cursor cursor = getCursor();
                setCursor(cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
        
        @Override
        public void mouseExited( MouseEvent e)
        {
            if( !link.equals("No help found about this exception.") )
            {
                inside = false;
                Cursor cursor = getCursor();
                setCursor(cursor.getDefaultCursor());
            }
        }
    }
    
} 
