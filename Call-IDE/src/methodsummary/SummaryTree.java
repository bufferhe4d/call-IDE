package methodsummary;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;

import com.github.javaparser.Position;

/**
 * The JTree component to visualise methods and constructors.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class SummaryTree extends JTree implements TreeSelectionListener {

    // PROPERTIES
    NodeVisitor visitor;
    
    // CONSTRUCTORS
    public SummaryTree( Parser parser, NodeVisitor visitor) throws MalformedURLException, IOException {
        super( parser.getRootNode());
        this.visitor = visitor;
        configureTree();
        setCellRenderer( new SummaryCellRenderer());
        addTreeSelectionListener( this);
    }
    
    // METHODS
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
    
    public void configureTree() {
        configureNodes((TreeNode) treeModel.getRoot());
    }
}
