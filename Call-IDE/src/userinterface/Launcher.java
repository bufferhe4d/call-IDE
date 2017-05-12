package userinterface;

/**
 * A class to launch the MainFrame of the IDE.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class Launcher {

    /** Creates a new launcher for the IDE.
     * @param launchWith the path of the file to open in the IDE
     */
    public Launcher( String launchWith ) {
        try {
            System.out.println( "Creating the main frame...");
            new MainFrame( launchWith);
            System.out.println( "Main frame is created.");
        } catch (Exception e) {
            System.out.println( "An error occured while creating the main frame:");
            e.printStackTrace();
        }
        System.out.println( "Launcher instancination ended.");
    }
    
    /**
     * The main method to launch the IDE.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println( "Setting look and feel...");
        MainFrame.setLookAndFeel( "Windows"); // Metal, Nimbus, CDE/Motif, Windows, Windows Classic
        System.out.println( "Instancinating the launcher...");
        
        if (args.length > 1)
            new Launcher( args[1]);
        else
            new Launcher( null);
        System.out.println( "Launching process ended.");
    }

}
