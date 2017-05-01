/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helputils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author mahmudsami
 */
class LinkingLabel extends JLabel
    {
        
        String link;
        
        final Color NON_CLICKED = Color.BLUE;
        final Color HOVER      = Color.RED;
        final Color CLICKED    = Color.MAGENTA;
        
        Color prevHover;
        
        LinkingLabel( String link)
        {
            super(  link );
            this.link = link;
            prevHover  = NON_CLICKED;
            //link =  ExceptionHelper.getHelpLink(exception);
            setForeground( NON_CLICKED );
        
            addMouseListener(new linkListener());
        }
        
        @Override
        public void setText(String link)
        {
            super.setText(link);
            this.link = link;
        }
        
        private class linkListener extends MouseAdapter
        {
            boolean inside;
            
            @Override
            public void mouseClicked( MouseEvent e){
                if( !link.equals("We Can Not Found Help For Exception"))
                {
                LinkOpener.openLink(link);
                setForeground(CLICKED);
                prevHover = CLICKED;
                }
            }
            
            @Override
            public void mousePressed( MouseEvent e)
            {
                 if( !link.equals("We Can Not Found Help For Exception"))
                {
                setForeground(HOVER);
                }
            }
            
            @Override
            public void mouseReleased( MouseEvent e)
            {
                 if( !link.equals("We Can Not Found Help For Exception") )
                {
                if( inside)
                {
                    LinkOpener.openLink(link);
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
                 if(  !link.equals("We Can Not Found Help For Exception") )
                {
                inside = true;
                Cursor cursor = getCursor();
                setCursor(cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
            
            @Override
            public void mouseExited( MouseEvent e)
            {
                 if( !link.equals("We Can Not Found Help For Exception") )
                {
                inside = false;
                Cursor cursor = getCursor();
                setCursor(cursor.getDefaultCursor());
                }
            }
            
        }
    } 
