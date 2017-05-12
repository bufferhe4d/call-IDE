package submissionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import submissionsystem.userinterface.VerifyEmail;
/**
 * A class to represent the client in the submission system
 * @author Abdullah Talayhan
 */
public class Client {
    
    private Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    private String name, surname;
    int id;
    private String email;
    
    /**
     * A method to set the client info
     * @param id the ID of the client in integer type
     * @param name the name of the client in the String type
     * @param email the email of the client in the String type
     */
    public void setClientInfo(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    /**
     * A method to set the email of client
     * @param email the email of client in string type to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * A method to get the email of client
     * @return the email of the client in String type
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * A method to get the name of the client
     * @return the name of the client in String type
     */
    public String getName() {
        return name;
    }
    
    /**
     * A method to connect the server from the client perception
     * @return true if the connection is established
     */
    public boolean connectServer() {
        try {
            client = new Socket("localhost", 9999);
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            
            ois = new ObjectInputStream(client.getInputStream());
            oos = new ObjectOutputStream(client.getOutputStream());
            
            // System.out.println("I/O streams for client created");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The connection to the server was failed.", "Call-IDE Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * A method to close the connection from the server
     */
    public void closeConnection() {
        try {
            sendUTFDataToServer("EXIT");
            dis.close();
            dos.close();
            client.close();
            // System.out.println("CLIENT END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Send UTD data to server
     * @param msg UTF data to send
     * @return true, if UTF data is sent to server; false, if not
     */ 
    public boolean sendUTFDataToServer(String msg) {
        if (client != null) {
            try {
                dos.writeUTF(msg);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * Send integer data to server 
     * @param num the integer to send to server
     * @return true, if the number is sent to server; false, if not
     */
    public boolean sendIntDataToServer(int num) {
        if (client != null) {
            try {
                dos.writeInt(num);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Send double data to server 
     * @param num the double to send to server
     * @return true, if the number is sent to server; false, if not
     */
    public boolean sendDoubleDataToServer(double num) {
        if (client != null) {
            try {
                dos.writeDouble(num);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * A method to get the UTF data from the server in String type
     * @return the UTF Data in string type, if the client is not null; empty string if it is
     */
    public String getUTFDataFromServer() {
        String res = "";
        if (client != null) {
            try {
                res = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    /**
     * A method to get the integer data from the server in String type
     * @return the integer data in integer type, if the client is not null; empty integer if it is
     */
    public int getIntDataFromServer() {
        int res = 0;
        if (client != null) {
            try {
                res = dis.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    /**
     * A method to get the double data from the server in String type
     * @return the double data in double type, if the client is not null; empty double if it is
     */
    public double getDoubleDataFromServer() {
        double res = 0;
        if (client != null) {
            try {
                res = dis.readDouble();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    /**
     * A method to send object to server
     * @param obj an object to send
     */
    public void sendObjectToServer(Object obj) {
        if (client != null) {
            try {
                oos.writeObject(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * A method to get the object from the server
     * @return object, if the client is not null; null, if it is
     */
    public Object getObjectFromServer() {
        Object obj = null;
        if (client != null) {
            try {
                obj =  ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
    /**
     * A method to get the ArrayList from server
     * @return ArrayList, if the client is not nullİ null, if it is
     */
    public Object getArrayListFromServer() {
        Object obj = null;
        if (client != null) {
            try {
                obj = (ArrayList) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        client.connectServer();
        VerifyEmail frame = new VerifyEmail();
        frame.setVisible(true);
    }
    
}
