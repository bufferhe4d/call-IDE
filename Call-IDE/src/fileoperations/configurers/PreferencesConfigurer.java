package fileoperations.configurers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * A class to load the preferences of the user
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class PreferencesConfigurer {
    
    /** The name of the file to save the preferences object's data */
    public static String FILE_NAME = "preferences.dat";
    
    /**
     * Saves the given preferences object to the given folder path.
     * @param userPath the path of the user to save the preferences
     * @param preferences the preferences objects to save
     */
    public static void save( String userPath, Preferences preferences) {
        try{
            FileOutputStream fileOut = new FileOutputStream( userPath + FILE_NAME); // Initialize the file stream.
            ObjectOutputStream objOut = new ObjectOutputStream( fileOut); // Initialize the object stream.
            objOut.writeObject( preferences); // Write the object data.
            objOut.close(); // Close the object stream.
            fileOut.close(); // Close the file stream.
        } catch( Exception e) {}
    }
    
    /**
     * Loads the preferences object that is located on the given folder path.
     * @param userPath the path of the user to load the preferences
     * @return the Preferences object that has been loaded
     */
    public static Preferences load( String userPath) {
        Preferences preferences = null;
        try {
            FileInputStream fileIn = new FileInputStream( userPath + FILE_NAME); // Initialize the file stream.
            ObjectInputStream objIn = new ObjectInputStream( fileIn); // Initialize the object stream.
            preferences = (Preferences) objIn.readObject(); // Read the object data.
            objIn.close(); // Close the object stream.
            fileIn.close(); // Close the file stream.
      } catch( IOException | ClassNotFoundException e) {}
      return preferences;
    }
    
}
