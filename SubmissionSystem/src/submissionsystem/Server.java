package submissionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abdullah.talayhan-ug
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
			// TODO Auto-generated catch block
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
		ObjectInputStream ois;
		ObjectOutputStream oos;
                String courseCode;
                String courseTitle;
                String cKey;
                String mainPath = "/Users/ATTJ/Desktop/testMain";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
                    try {
                        
                        
                        String regOp;

                        // get REGISTER_OPEN or LOGIN_OPEN
                        regOp = dis.readUTF();

                        if(regOp.equals("REGISTER_OPEN")) {

                            String email = dis.readUTF();
                            if( ServerSys.isValidEmailAddress(email)) {
                                
                                dos.writeUTF("IS_EMAIL");
                                String code = ServerSys.sendEmail(email);
                                String userCode = dis.readUTF();
                                
                                if(code.equals(userCode)) {

                                    dos.writeUTF("ACCEPT_USER");
                                    if(dis.readUTF().equals("Student")) {
                                        ServerSys.registerStudent(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
                                    }
                                    else {
                                        ServerSys.registerInstructor(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
                                    }

                                }
                                else {
                                        dos.writeUTF("DECLINE_USER");
                                }
                            }
                            else {
                                    dos.writeUTF("NOT_EMAIL");
                            }

                        }
                        else {
                        String type = dis.readUTF();

                        String un = dis.readUTF();
                        String pw = dis.readUTF();

                        ResultSet rs = ServerSys.login(un, pw, type);


                        if(rs.next())
                         {
                            dos.writeUTF("YES");

                            //int id = rs.getInt(1);
                            String name = rs.getString(2);
                            //String surname = rs.getString(4);
                            //int type = rs.getInt(6);		
                            //int dept = rs.getInt(7);




                            if(type.equals("Instructor")) { //Ins					 {
                               while(true) {
                                   

                                                                      
                                   String op = dis.readUTF();
                                   if(op.equals("GET_COURSES")) {
                                    ArrayList<String> courseList = ServerSys.getInsCourses(un);

                                    oos.writeObject(courseList);
                                   }
                                   else if(op.equals("CONSTRUCT_STUDENT_PANEL"))
                                   {    
                                       String courseCode = dis.readUTF();
                                       // send result set
                                       oos.writeObject(ServerSys.showStudents(courseCode));
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
                                           new File(path+File.separator + "Submissons").mkdir();
                                       }
                                       else {
                                           dos.writeUTF("COURSE_EXISTS");
                                       }
                                       
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
                                       String toSend = dis.readUTF();
                                       //course code
                                       courseCode = dis.readUTF();
                                       //TODO...
                                       String asPath = mainPath + File.separator + courseCode + File.separator +  "Assignments" + File.separator + toSend;
                                       new File(asPath);
                                       ServerSys.publishAssignment(asnName, un, due, subDate, asPath);
                                       
                                       dos.writeUTF("PUBLISHED");
                                       
                                       
                                   }
                                   

                               }
                            }
                            else { //student
                               while(true)
                               {
                                   String op = dis.readUTF();
                                   
                                   if(op.equals("GET_COURSES")) {
                                       ArrayList<String> courseList = ServerSys.getStuCourses(un);

                                       oos.writeObject(courseList);
                                   }
                                   else if(op.equals("GET_CUR_ASSIGNMENTS"))
                                   {    
                                       courseCode = dis.readUTF();
                                       ArrayList curAssignments = ServerSys.getCurAssignments(un, courseCode);
                                       					
                                       oos.writeObject(curAssignments);
                                   }
                                   else if(op.equals("GET_PAST_ASSIGNMENTS"))
                                   {    
                                       courseCode = dis.readUTF();
                                       ArrayList pastAssignments = ServerSys.getPastAssignments(un, courseCode);
                                       					
                                       oos.writeObject(pastAssignments);
                                   }
                                   else if(op.equals("ENROLL_COURSE")) {
                                       cKey = dis.readUTF();
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

                    } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
		}
	}
}
