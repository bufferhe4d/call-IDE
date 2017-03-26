import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server class for file upload/download operations.
 * @author Emin Bahadir Tuluce
 * @version 1.0
 */
public class FileServer implements Runnable {
    Socket connectionSocket;
    DataInputStream in;
    DataOutputStream out;
    
    File file;
    FileInputStream fileReader;
    FileOutputStream fileWriter;
    
    public FileServer( Socket connectionSocket) throws Exception {
        this.connectionSocket = connectionSocket;
        this.in = new DataInputStream( connectionSocket.getInputStream());
        this.out = new DataOutputStream( connectionSocket.getOutputStream());
    }
    
    public void run() {
        System.out.println("Client connected: " + connectionSocket);
        try {
            while (true) {
                System.out.println( "Listening ...");
                char command = in.readChar();
                
                // SENDING THE FILE TO THE CLIENT WHICH GAVE A DOWNLOAD COMMAND
                if (command == 'd') {
                    
                    // Check for an unauthorized download request and get the file's path on the server.
                    int downloadID = in.readInt();
                    String downloadPath = Placeholder.getDownloadPath( downloadID);
                    if (downloadPath == null) {
                        System.out.println( "Unauthorized download request (" + downloadID + ") from " + connectionSocket);
                        out.write( new byte[] {0, 0, 0, 0}); // Send 0 as length of the file.
                        continue;
                    }
                    System.out.println( "Download request: " + downloadPath);
                    file = new File( downloadPath);
                    if (!file.exists()) {
                        System.out.println( "File not found.");
                        out.write( new byte[] {0, 0, 0, 0}); // Send 0 as length of the file.
                    }
                    else {
                        System.out.println( "Sending file ...");
                        long start = System.currentTimeMillis();
                        
                        // Send the file length.
                        fileReader = new FileInputStream(file);
                        int length = (int) file.length();
                        out.writeInt( length);
                        
                        // Send the fileName length and fileName.
                        String fileName = file.getName();
                        out.writeInt( fileName.length());
                        for (int i = 0; i < fileName.length(); i++) {
                            out.writeChar( fileName.charAt(i));
                        }
                        
                        // Send the file bytes.
                        byte[] data = new byte[length];
                        fileReader.read( data);
                        fileReader.close();
                        out.write( data);
                        
                        // Measure the time.
                        long end = System.currentTimeMillis();
                        double time = (int) (end - start) / 1000.0;
                        double speed = ((double) length / (1024* 1024)) / time;
                        System.out.println( "File sent.");
                        System.out.printf( "Transfer time: %.2f seconds%nTransfer speed: %.2f MB/s%n", time, speed);
                    }
                }
                
                // RECEIVING THE FILE FROM THE CLIENT WHICH GAVE A UPLOAD COMMAND
                else if (command == 'u') {
                    
                    // Check for an unauthorized upload request and get the path to save the file on the server.
                    int uploadID = in.readInt();
                    String uploadPath = Placeholder.getUploadPath( uploadID);
                    
                    // Read the fileName
                    int nameLength = in.readInt();
                    String fileName = "";
                    for (int i = 0; i < nameLength; i++) {
                        fileName = fileName + in.readChar();
                    }
                    
                    System.out.println( "Upload request: '" + fileName + "' to the path " + uploadPath);
                    file = new File( uploadPath + "//" + fileName);
                    fileWriter = new FileOutputStream(file);
                    System.out.println( "Receiving file ...");
                    long start = System.currentTimeMillis();
                    
                    // Receive the file length
                    int length = in.readInt();
                    
                    // Receive the file bytes.
                    byte[] data = new byte[length];
                    for (int i = 0; i < length; i++)
                        data[i] = (byte) in.read();
                    fileWriter.write( data);
                    fileWriter.close();
                    
                    // Measure the time.
                    long end = System.currentTimeMillis();
                    double time = (int) (end - start) / 1000.0;
                    double speed = ((double) length / (1024 * 1024)) / time;
                    System.out.println( "File received.");
                    System.out.printf( "Transfer time: %.2f seconds%nTransfer speed: %.2f MB/s%n", time, speed);
                }
                
                // DISCONNECT COMMAND FROM THE CLIENT
                else if (command == 'q') {
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    /**
     * Server starts to waiting for clients.
     */
    public static void listen( int PORT) throws Exception {
        System.out.println( "Starting the file server on port " + PORT + " ...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println( "Server started.");
        System.out.println( "Waiting for connections ...");
        while (true) { // For each client...
            Socket connectionSocket = serverSocket.accept(); // This line sleeps until a client connects.
            FileServer cloudServer = new FileServer( connectionSocket); // Create a new instance of FileServer.
            Thread thread = new Thread( cloudServer); // Then run it on a new thread.
            thread.start();
        }
    }
    
    /**
     * Starts the server program on a specified port.
     * @param args the port to start the server on
     */
    public static void main(String[] args) throws Exception {
        listen( Integer.parseInt( args[0]));
    }
}