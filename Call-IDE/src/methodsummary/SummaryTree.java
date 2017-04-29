package methodsummary;

import com.github.javaparser.Position;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.File;
import java.net.MalformedURLException;

/**
 * The JTree component to visualise methods and constructors.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class SummaryTree extends JTree implements TreeSelectionListener {

    NodeVisitor visitor;
    
    public SummaryTree( String root, NodeVisitor visitor) throws MalformedURLException {
        super( (new Parser( root)).getRootNode());
        this.visitor = visitor;
        
        configureNodes((TreeNode) treeModel.getRoot());
        setCellRenderer( new SummaryCellRenderer());
        addTreeSelectionListener( this);
    }
    
    
    @Override
    public void valueChanged( TreeSelectionEvent e) {
        TreeNode clickedNode = (TreeNode)((JTree) e.getSource()).getLastSelectedPathComponent();
        
        if (clickedNode instanceof VisitableNode) {
            File file = ((VisitableNode) clickedNode).getFile();
            Position position = ((VisitableNode) clickedNode).getPosition();
            visitor.visitNode( file, position);
        }
    }
    
    private void configureNodes(TreeNode node) {
        if (node instanceof VisitableNode)
            ((VisitableNode) node).configureNode();
        else
            for (int i = 0; i < node.getChildCount(); i++)
                configureNodes( node.getChildAt(i));
    }
}
