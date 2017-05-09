package filetransfertests;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * A client class for file upload/download operations.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class FileClient {
    static Socket socket = null;
    static DataInputStream in = null;
    static DataOutputStream out = null;
    
    static File file;
    static FileInputStream fileReader;
    static FileOutputStream fileWriter;
    
    /** Connects to the server with given IP and PORT. */
    public static void connect( String IP, int PORT) throws Exception {
        socket = new Socket( IP, PORT);
        in = new DataInputStream( socket.getInputStream());
        out = new DataOutputStream( socket.getOutputStream());
    }

    /** Makes an attempt to download a file from the server. */
    public static void download( String downloadPath) throws Exception {        
        // Receive the file length.
        int length = in.readInt();
        
        // Start the download if the file exists.
        if (length == 0) {
            System.out.println("File does not exist.");
        }
        else {
            
            // Receive fileName length and fileName.
//            int nameLength = in.readInt();
//            String fileName = "";
//            for (int i = 0; i < nameLength; i++) {
//                fileName = fileName + in.readChar();
//            }
            
            System.out.println("Downloading file...");            
            file = new File(downloadPath); //+ fileName);
            fileWriter = new FileOutputStream(file);
            
            // Download the file.
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = (byte) in.read();
            }
            fileWriter.write(data);
            fileWriter.close();
            System.out.println("File downloaded.");
        }
    }
    
    /** Uploads a file to the server. */
    public static void uploadFile( String filePath) throws Exception {
        file = new File(filePath);
        final int MAX_SIZE = 5242880; // 5 MB
        long lengthLong = file.length();
        if (lengthLong > MAX_SIZE) {
            System.out.println( "Upload failed. The max allowed size is 5 MB");
        }
        else {
            int length = (int) lengthLong;
            
//            String fileName = file.getName();
//            
//            // Send the fileName length and fileName.
//            out.writeInt( fileName.length());
//            for (int i = 0; i < fileName.length(); i++) {
//                out.writeChar( fileName.charAt(i));
//            }
            
            System.out.println( "Uploading file ...");
            fileReader = new FileInputStream( file);
            
            // Upload the file length.
            out.writeInt( length);
            
            // Upload the file.
            byte[] data = new byte[length];
            fileReader.read( data);
            fileReader.close();
            out.write( data);
            System.out.println( "File uploaded.");
        }
        
    }
    
    /** Creates a zip file with the given folder's contents.
      * Uploads the zip file to the server, then deletes it. */
    public static void uploadFolder( String folderPath) throws Exception {
        File folder = new File( folderPath);
        String fileName = folder.getName();
        File tempZipFile = new File(folder.getParent() + "/" + fileName + ".zip");

        System.out.println( "Compressing files...");
        
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        ZipFile studentZip = new ZipFile( tempZipFile);
        studentZip.addFolder(folderPath, parameters);

        uploadFile( tempZipFile.getAbsolutePath());
       
        tempZipFile.delete(); //Clear the compressed file.
    }
    
    /** Main method is only for testing */
    public static void main(String[] args) throws Exception {
        connect( "localhost", 8888);
        download( "D://Downfolder//onserver");
        uploadFile("D://Folder//text2.txt");
        uploadFolder( "D://Folder//");
    }
}