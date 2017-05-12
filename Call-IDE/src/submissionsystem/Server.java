package submissionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Abdullah Talayhan
 */
public class Server {
    
    public Server() {
        
        try {
            DBSystem.initializeDB("138.68.93.173:3306", "apo_callide", "apo", "duracell");
            
            ServerSocket server = new ServerSocket(9999);
            System.out.println("Server is running");
            
            System.out.println("Server is wating for client");
            while (true) {
                Socket clientsocket = server.accept();
                MyClientThread tforclient = new MyClientThread(clientsocket);
                tforclient.start();
                System.out.println("Server starts to serve to client");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("END SERVER");
        
    }
    
    public static void main(String[] args) {
        Server server = new Server();
    }
    
    class MyClientThread extends Thread {
        
        Socket clientsocket;
        DataInputStream dis;
        DataOutputStream dos;
        FileInputStream fis;
        FileOutputStream fos;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        String courseCode;
        String courseTitle;
        String cKey;
        String insCode = "bilkent.edu.tr";
        String mainPath = "D:/Users/abdullah.talayhan-ug/Documents/callSub";
        int check;
        public MyClientThread(Socket csocket) {
            this.clientsocket = csocket;
            System.out.println("Client connected");
            try {
                dis = new DataInputStream(clientsocket.getInputStream());
                dos = new DataOutputStream(clientsocket.getOutputStream());
                
                oos = new ObjectOutputStream(clientsocket.getOutputStream());
                ois = new ObjectInputStream(clientsocket.getInputStream());
                
                System.out.println("I/O streams are created");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            try {
                while(true) {
                
                String regOp;
                
                // get REGISTER_OPEN or LOGIN_OPEN
                regOp = dis.readUTF();
                
                if(regOp.equals("REGISTER_OPEN")) {
                    
                    String email = dis.readUTF();
                    if( ServerSys.isValidEmailAddress(email, insCode)) {
                        System.out.println("test");
                        dos.writeUTF("IS_EMAIL");
                        String code = ServerSys.sendEmail(email);
                        System.out.println("test");
                        String userCode = dis.readUTF();
                        
                        if(code.equals(userCode)) {
                            
                            dos.writeUTF("ACCEPT_USER");
                            System.out.println("accept");
                            
                        }
                        else {
                            dos.writeUTF("DECLINE_USER");
                        }
                    }
                    else {
                        dos.writeUTF("NOT_EMAIL");
                        dos.writeUTF(insCode);
                    }
                    
                }
                else if(regOp.equals("REGISTER_ATTEMPT")) {
                    if(dis.readUTF().equals("Student")) {
                        ServerSys.registerStudent(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
                        System.out.println("regdone");
                    }
                    else {
                        ServerSys.registerInstructor(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
                        System.out.println("reginsdone");
                    }
                }
                else if(regOp.equals("LOGIN_OPEN")){
                    String type = dis.readUTF();
                    
                    String un = dis.readUTF();
                    String pw = dis.readUTF();
                    
                    ResultSet rs = ServerSys.login(un, pw, type);
                    
                    
                    if(rs.next())
                    {
                        dos.writeUTF("YES");
                        dos.writeInt(rs.getInt(1));
                        dos.writeUTF(rs.getString(2));
                        
                        //int id = rs.getInt(1);
                        //String name = rs.getString(2);
                        //String surname = rs.getString(4);
                        //int type = rs.getInt(6);  
                        //int dept = rs.getInt(7);
                        

                        if(type.equals("Instructor")) { //Ins      {
                            while(true) {
                                
                                
                                String op = dis.readUTF();
                                if(op.equals("GET_COURSES")) {
                                    ArrayList<String> courseList = ServerSys.getInsCourses(un);
                                    
                                    oos.writeObject(courseList);
                                }
                                else if(op.equals("GET_SUBMISSIONS")) {
                                    String courseCode = dis.readUTF();
                                    String asgnName = dis.readUTF();
                                    // send Table 
                                    oos.writeObject(ServerSys.getAllSubmissionsTable(courseCode, asgnName));
                                    oos.writeObject(ServerSys.getAllSubmissionsForAsgn(un, courseCode, asgnName));
                                    
                                }
                                else if(op.equals("CONSTRUCT_STUDENT_PANEL"))
                                {    
                                    String courseCode = dis.readUTF();
                                    // send result set
                                    oos.writeObject(ServerSys.showStudents(courseCode));
                                }
                                else if(op.equals("CONSTRUCT_ASSIGNMENT_PANEL"))
                                {    
                                    String courseCode = dis.readUTF();
                                    // send result set
                                    oos.writeObject(ServerSys.getAllAssignments(un, courseCode));
                                }
                                else if(op.equals("GET_INS")) {
                                    dos.writeUTF(insCode);
                                }
                                else if(op.equals("CREATE_COURSE")) {
                                    courseCode = dis.readUTF();
                                    courseTitle = dis.readUTF();
                                    cKey = ServerSys.generateClassKey();
                                    check = ServerSys.createCourse(courseTitle, courseCode, un, cKey);
                                    if(check != -1) {
                                        dos.writeUTF("COURSE_CREATED");
                                        dos.writeUTF(cKey);
                                        String path = mainPath + File.separator + courseCode;
                                        boolean customDir = new File(path).mkdir();
                                        new File(path+File.separator + "Assignments").mkdir();
                                        new File(path+File.separator + "Submissions").mkdir();
                                    }
                                    else {
                                        dos.writeUTF("COURSE_EXISTS");
                                    }
                                    
                                }
                                else if(op.equals("DOWNLOAD_SUBS")) {
                                    Assignment subToSend = (Assignment) ois.readObject();
                                    String pathToSend = subToSend.getPath();
                                    
                                    File toSend = new File(pathToSend);
                
                                    fis = new FileInputStream(toSend);
                                    byte[] data = new byte[(int) toSend.length()];

                                    fis.read(data);
                                    System.out.println(java.util.Arrays.toString(data));
                                    fis.close();
                                    oos.writeObject(data);
                                }
                                else if(op.equals("SEND_GRADE")) {
                                    String email = dis.readUTF();
                                    int grade = dis.readInt();
                                    String asgnName = dis.readUTF();
                                    ServerSys.setGrade(grade, email, asgnName);
                                }
                                else if(op.equals("PUBLISH_ASSIGNMENT")) {
                                    // get the file path
                                    // assignment name
                                    String asnName = dis.readUTF();
                                    // due date
                                    Date due = (Date) ois.readObject();
                                    // submission date
                                    Date subDate = (Date) ois.readObject();
                                    
                                    //fileName
                                    //String toSend = dis.readUTF();
                                    //course code
                                    courseCode = dis.readUTF();
                                    
                                    String asPath = mainPath + "/" + courseCode + "/" +  "Assignments" + "/" + asnName;
                                    
                                    File asFolder = new File(asPath);
                                    if(!asFolder.exists())
                                        asFolder.mkdir();
                                    //TODO...
                                    File received = new File(asPath + "/" + asnName + ".zip");
                                    
                                    fos = new FileOutputStream(received);
                                    
                                    byte[] data = (byte[]) ois.readObject();
                                    fos.write(data);
                                    fos.close();
                                    
                                    
                                    
                                    System.out.println(received.getAbsolutePath().replace('\\', '/'));
                                    ServerSys.publishAssignment(asnName, un, due, subDate, received.getAbsolutePath().replace('\\', '/'));
                                    
                                    dos.writeUTF("PUBLISHED");
                                    
                                    
                                }
                                
                                
                            }
                        }
                        else { //student
                            while(true)
                            {
                                String op = dis.readUTF();
                                System.out.println(op);
                                if(op.equals("GET_COURSES")) {
                                    ArrayList<String> courseList = ServerSys.getStuCourses(un);
                                    
                                    oos.writeObject(courseList);
                                }
                                else if(op.equals("GET_CUR_ASSIGNMENTS"))
                                {    
                                    courseCode = dis.readUTF();
                                    if(!courseCode.equals("NO_COURSE")) {
                                        ArrayList curAssignments = ServerSys.getCurAssignments(un, courseCode);
                                        oos.writeObject(curAssignments);
                                    }
                                    
                                }
                                else if(op.equals("GET_PAST_ASSIGNMENTS"))
                                {    
                                    courseCode = dis.readUTF();
                                    if(!courseCode.equals("NO_COURSE")) {
                                        ArrayList pastAssignments = ServerSys.getPastAssignments(un, courseCode);
                                        ArrayList allSubmissions = ServerSys.getSubmissions(un, courseCode);
                                        
                                        oos.writeObject(pastAssignments);
                                        oos.writeObject(allSubmissions);
                                    }
                                }
                                else if(op.equals("DOWNLOAD_ASGN")) {
                                    Assignment asgnToSend = (Assignment) ois.readObject();
                                    String pathToSend = asgnToSend.getPath();
                                    
                                    File toSend = new File(pathToSend);
                
                                    fis = new FileInputStream(toSend);
                                    byte[] data = new byte[(int) toSend.length()];

                                    fis.read(data);
                                    System.out.println(java.util.Arrays.toString(data));
                                    fis.close();
                                    oos.writeObject(data);
                                    
                                }
                                else if(op.equals("SEND_SUBMISSION")) {
                                    Assignment subToAdd = (Assignment) ois.readObject();
                                    courseCode = dis.readUTF();
                                    String asgnName = subToAdd.getName().substring(subToAdd.getName().lastIndexOf("_") + 1);
                                    String subPath = mainPath + "/" + courseCode + "/" +  "Submissions" + "/" + asgnName;
                                    File subFolder = new File(subPath);
                                    System.out.println("start" + " " + subPath);
                                    
                                    if(!subFolder.exists()) {
                                        
                                        subFolder.mkdir();
                                        System.out.println("done");
                                    }
                                    //TODO...
                                    File received = new File(subPath + "/" + un + ".zip");
                                    
                                    fos = new FileOutputStream(received);
                                    
                                    byte[] data = (byte[]) ois.readObject();
                                    fos.write(data);
                                    fos.close();
                                    
                                    int check = ServerSys.pushSubmission(un, subToAdd, received.getAbsolutePath().replace('\\', '/'));
                                    if(check != -1) {
                                        dos.writeUTF("DONE");
                                    }
                                    else {
                                        dos.writeUTF("FAIL");
                                    }
                                }
                                else if(op.equals("ENROLL_COURSE")) {
                                    System.out.println("inside");
                                    cKey = dis.readUTF();
                                    System.out.println(cKey);
                                    courseCode = ServerSys.enrollCourse(un, cKey);
                                    if(!courseCode.equals("")) {
                                        dos.writeUTF("ENROLLED");
                                        dos.writeUTF(courseCode);
                                    }
                                    else {
                                        dos.writeUTF("ENROLL_FAIL");
                                    }
                                }                                   
                            }
                        }
                    }
                    else{ 
                        dos.writeUTF("NO");
                    }
                }
               }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }
}
