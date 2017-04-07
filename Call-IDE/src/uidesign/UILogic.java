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
            System.out.println("Creating frame...");
            new MainFrame();
            System.out.println("Frame created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Instancination ended.");
    }
    
    public static void main(String[] args) {
        System.out.println("Setting look and feel...");
        MainFrame.setLookAndFeel("Windows"); // Metal, Nimbus, CDE/Motif, Windows, Windows Classic
        System.out.println("Instancinating logic...");
        new UILogic();
         System.out.println("Main method ended.");
    }
    
    
    
}
