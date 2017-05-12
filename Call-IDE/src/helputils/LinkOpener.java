package helputils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A class that can open a link on the default browser.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class LinkOpener {
    
    /** The link to the Oracle's Java API Documentation for Java 8 */
    public static final String API_LINK = "http://docs.oracle.com/javase/8/docs/api/";
    
    /** The link to the Oracle's Java Tutorials */
    public static final String TUTORIALS_LINK = "https://docs.oracle.com/javase/tutorial/";
    
    /**
     * Opens the given link on the default browser.
     * @param link the link to open
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void openLink( String link) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI( link));
    }
}
