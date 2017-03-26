/** 
 * A placeholder class for handling directories and download/upload ID numbers for the server
 * @author Emin Bahadir Tuluce
 * @version 0.01
 *         little fix, Mahmud Sami Aydin, suitable file path added for bsd and linux.
 */
public class Placeholder {
    /**
     * A method to check the download path
     * @param downloadID a number which associated with the requested file and the client's account
     * @return the path of the file on the server or null for unathorized accounts
     */
    public static String getDownloadPath( int downloadID) {
        if (downloadID == 2)
            return "D://Folder//text1.txt";
        return null; //not authorized
    }
    
    /**
     * A method to check the upload path
     * @param uploadID a number which associated the client's account
     * @return the path of the save location to the server
     */
    public static String getUploadPath( int uploadID) {
        if (uploadID == 3)
            return "D://UpFolder1//";
            //return "//var//tmp//UpFolder1//"; 
        else
            return "D://UpFolder//";
            // return "//var//tmp//UpFolder//";
    }
}
