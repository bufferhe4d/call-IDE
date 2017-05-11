package methodsummary;

import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * A class to handle method summary tree's icons.
 * @author Mahmud Sami Aydin, Emin Bahadir Tuluce
 * @version 1.0
 */
public class SummaryCellRenderer extends DefaultTreeCellRenderer {
    
    private Icon[] icons;
    
    public SummaryCellRenderer() throws MalformedURLException, IOException {
        super();
        buildIcons();
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if( value  instanceof SummaryNode )
        {        
            Icon icon = icons[((SummaryNode)value).nodeType()];
            setIcon(icon);
            if (((SummaryNode)value).nodeType() == 2)
                setFont(getFont().deriveFont(2));
            else
                setFont(getFont().deriveFont(0));
            setToolTipText( ((SummaryNode)value).getJavadoc() );
        }
        return this;
    }
    
    private void buildIcons() throws MalformedURLException, IOException {
        ImageIcon logoIcon = new ImageIcon( ImageIO.read(
                getClass().getResource( "/userinterface/images/logoicon.png")));
        icons = new Icon[15];
        icons[SummaryNode.CLASS_NODE  ] = logoIcon;
        icons[SummaryNode.INNER_NODE  ] = logoIcon;
        icons[SummaryNode.INFACE_NODE ] = logoIcon;
        icons[SummaryNode.BYTE_NODE   ] = logoIcon;
        icons[SummaryNode.SHORT_NODE  ] = logoIcon;
        icons[SummaryNode.INT_NODE    ] = logoIcon;
        icons[SummaryNode.LONG_NODE   ] = logoIcon;
        icons[SummaryNode.FLOAT_NODE  ] = logoIcon;
        icons[SummaryNode.DOUBLE_NODE ] = logoIcon;
        icons[SummaryNode.CHAR_NODE   ] = logoIcon;
        icons[SummaryNode.OBJECT_NODE ] = logoIcon;
        icons[SummaryNode.BOOLEAN_NODE] = logoIcon;
        icons[SummaryNode.CONST_NODE  ] = logoIcon;
        icons[SummaryNode.VOID_NODE   ] = logoIcon;
    }
    
}
