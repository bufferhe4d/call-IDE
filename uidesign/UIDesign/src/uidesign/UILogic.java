package uidesign;

import java.awt.Component;
import javax.swing.JButton;

/**
 * A class to wire up the GUI components correctly.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class UILogic {

    public UILogic() {
        try {
            new MainFrame();
        } catch (Exception e) {}
    }
    
    public static void main(String[] args) {
        MainFrame.setLookAndFeel("Windows"); // Metal, Nimbus, CDE/Motif, Windows, Windows Classic
        new UILogic();
    }
    
    
    
}
