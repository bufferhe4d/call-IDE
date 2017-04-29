/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodsummary;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author mahmudsami
 */
public class SummaryCellRenderer extends DefaultTreeCellRenderer{
    
    private Icon[] icons;
    
    public SummaryCellRenderer() throws MalformedURLException
    {
        super();
        buildIcons();
    }
    
    @Override
     public Component getTreeCellRendererComponent(JTree tree, Object value,
                                            boolean selected, boolean expanded,
                                            boolean leaf, int row, boolean hasFocus)
    {
      
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        
        if( value  instanceof SummaryNode )
        {        
        Icon icon = icons[((SummaryNode)value).nodeType()];
        setIcon(icon);
        if (((SummaryNode)value).nodeType() == 2)
            setFont(getFont().deriveFont(2));
        else
            setFont(getFont().deriveFont(0));
        }
        
        
        
        return this;
    }

    private void buildIcons() throws MalformedURLException {
        icons = new Icon[15];
       
            
            icons[0] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[1] = new ImageIcon( new URL("http://icons.iconarchive.com/icons/bo/dilbert/32/Alice-icon.png") );
            icons[2] = new ImageIcon(new URL("http://www.iconninja.com/files/431/644/60/grey-jean-icon.png") );
            icons[3] = new ImageIcon(new URL("http://www.iconninja.com/files/203/584/960/dr-xavier-icon.png") );
            icons[4] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/mad-science/yellow-submarine/32/GEORGE-icon.png") );
            icons[5] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[6] = new ImageIcon( new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[7] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[8] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[9] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[10] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[11] = new ImageIcon( new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[12] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/julie-and-mark/star-trek-TNG/32/Data-icon.png") );
            icons[13] = new ImageIcon(new URL("http://www.iconninja.com/files/111/757/296/child-dr-foster-ned-s-shrink-icon.png") );
            icons[14] = new ImageIcon(new URL("http://icons.iconarchive.com/icons/jeanette-foshee/simpsons-03/32/Guest-Stars-Johnny-Carson-icon.png") );
    }
        
}
