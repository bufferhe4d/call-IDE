package userinterface;

/**
 * A class to launch the MainFrame of the IDE.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class Launcher {

    public Launcher() {
        try {
            System.out.println( "Creating the main frame...");
            new MainFrame();
            System.out.println( "Main frame is created.");
        } catch (Exception e) {
            System.out.println( "An error occured while creating the main frame:");
            e.printStackTrace();
        }
        System.out.println( "Launcher instancination ended.");
    }
    
    public static void main(String[] args) {
        System.out.println( "Setting look and feel...");
        MainFrame.setLookAndFeel("Windows"); // Metal, Nimbus, CDE/Motif, Windows, Windows Classic
        System.out.println( "Instancinating the launcher...");
        new Launcher();
        System.out.println( "Launching process ended.");
    }

}
