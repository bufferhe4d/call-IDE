package submissionsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author abdullah.talayhan-ug
 */
public class ServerSys {
	
	public static ResultSet login(String uid, String passwd, String type)
	{      
            String sql;
            if(type.equals("Student")) {
		sql = "select * from STUDENT where email='"+uid+"' and pwd='"+passwd+"'";
            }
            else {
                sql = "select * from INSTRUCTOR where email='"+uid+"' and pwd='"+passwd+"'"; 
            }
		ResultSet rs = DBSystem.executeQue(sql);
		return rs;
	}
	
	
	
	
	
        public static ArrayList getInsCourses(String email) {
            ArrayList<String> courses = new ArrayList<String>();
            String sql = "select code from teaching where email='" + email +"';";
            ResultSet rs = DBSystem.executeQue(sql);
            try {
			while (rs.next()) {
				courses.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                }
            
            return courses;
        }
        
        public static ArrayList getStuCourses(String email) {
            ArrayList<String> courses = new ArrayList<String>();
            String sql = "select code from enrollment where email='" + email +"';";
            ResultSet rs = DBSystem.executeQue(sql);
                try {
			while (rs.next()) {
				courses.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                }
            
            return courses;
        }
        
        public static DefaultTableModel showStudents(String code) {
            String sql = "select stdid, name, student.email from student join enrollment on student.email = enrollment.email where enrollment.code='" + code + "'";
            
            return DBSystem.showTables(DBSystem.executeQue(sql));
        }
        
        public static void publishAssignment(String name, String un, Date due, Date subDate, String path) {
            
            Assignment asn = new Assignment(name, un,  due, subDate, path, -1);
            String sql = "insert into ins_assignment values('" + name + "','" + due + "','" + subDate + "','" + path +  "')";
            System.out.println(subDate);
            System.out.println(sql);
            DBSystem.executeUp(sql);
            String forSql = "insert into ins_belongs values('" + un + "','" + path + "')"; 
            DBSystem.executeUp(forSql);
        }
        
     
	
	public static void registerStudent(String stdID, String name,String pwd, String email  ) {
		String sql = "insert into STUDENT values('" + stdID + "','" + name + "','" + generateUniqueID() +  "','" +  pwd + "','" + email + "')";
		
		DBSystem.executeUp(sql);
	}
        
        public static void registerInstructor(String stdID, String name,String pwd, String email  ) {
		String sql = "insert into INSTRUCTOR values('" + stdID + "','" + name + "','" + generateUniqueID() +  "','" +  pwd + "','" + email + "')";
		
		DBSystem.executeUp(sql);
	}
	
	public static int createCourse(String title,String code, String instructor, String c_key  ) {
		
		String sql = "insert into course values('" + title + "','"+  code + "','" + instructor +  "','" + c_key + "')";
		String checkQuery = "select * from course where code='" + code + "'";
                ResultSet rs = DBSystem.executeQue(checkQuery);
                
                try {
                    if(!rs.next()) {
                        
                        DBSystem.executeUp(sql);
                        String forSql = "insert into teaching values('" + instructor + "','" + code + "')";
                        DBSystem.executeUp(forSql);
                        return 0;
                         
                    }
                    else {
                        return -1;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServerSys.class.getName()).log(Level.SEVERE, null, ex);
                }
                return -1;
	}
	
        public static ArrayList getCurAssignments(String email, String courseCode) {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            String sql = "select name, duedate, subdate, ins_assignment.path from ins_assignment where upper(ins_assignment.path) like '%" + courseCode +"%' AND duedate >= CURDATE()";
            System.out.println(sql);
            ResultSet rs = DBSystem.executeQue(sql);
            int columncount;
            try {
                columncount = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    ArrayList singleRow = new ArrayList();
                    for(int i=1;i<=columncount;i++)
                    {
                        singleRow.add(rs.getObject(i));
                    }
                    assignments.add(new Assignment((String)rs.getObject(1), email ,(Date)rs.getObject(2), (Date)rs.getObject(3), (String)rs.getObject(4), -1));
                    System.out.println(singleRow);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerSys.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            
            return assignments;
            
        }
        
        public static ArrayList getPastAssignments(String email, String courseCode) {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            
            ResultSet rs = DBSystem.executeQue("select name, duedate, subdate, ins_assignment.path from ins_assignment where upper(ins_assignment.path) like '%" + courseCode +"%' AND duedate < CURDATE()");
            int columncount;
            try {
                columncount = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    ArrayList singleRow = new ArrayList();
                    for(int i=1;i<=columncount;i++)
                    {
                        singleRow.add(rs.getObject(i));
                    }
                    assignments.add(new Assignment((String)rs.getObject(1), email ,(Date)rs.getObject(2), (Date)rs.getObject(3), (String)rs.getObject(4), -1));
                    System.out.println(singleRow);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerSys.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            
            return assignments;
            
        }
        
        public static ArrayList getSubmissions(String email, String courseCode) {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            
            ResultSet rs = DBSystem.executeQue("select name, duedate, subdate, stu_assignment.path from stu_assignment where stu_assignment.path like '%" + courseCode +"%' AND duedate < CURDATE()");
            int columncount;
            try {
                columncount = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    ArrayList singleRow = new ArrayList();
                    for(int i=1;i<=columncount;i++)
                    {
                        singleRow.add(rs.getObject(i));
                    }
                    assignments.add(new Assignment((String)rs.getObject(1), email ,(Date)rs.getObject(2), (Date)rs.getObject(3), (String)rs.getObject(4), (int)rs.getObject(5)));
                    System.out.println(singleRow);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerSys.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            
            return assignments;
            
        }
        
        
        
	public static String enrollCourse(String email, String cKey) {
            
            
            try {
                String sqlCode = "select code from course where c_key='" + cKey +"';";
                ResultSet rs = DBSystem.executeQue(sqlCode);
                String courseCode = rs.getString(1);
                if(rs.next()) {
                    String sql = "insert into enrollment values('" + email + "','"+ courseCode + "')";

                    DBSystem.executeUp(sql);
                    return courseCode;
                }
                else {
                    courseCode = "";
                    return courseCode;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerSys.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return "";
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
	
	public static String sendEmail(String email) {
		String from = "noreply.callide@gmail.com";
		String pwd = "enter-pass";
		String code = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
		
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(from, pwd);
                  }
                });
		
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_user_name@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Welcome!");
            message.setText("Welcome to call-IDE, please verify your account to register by using the code below.\nVerification Code: " + code);

            Transport.send(message);

            System.out.println("Mail Sent");
            return code;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
		
	}
	
        public static int generateUniqueID() {
            return (new Random()).nextInt();
        }
        
        public static String generateClassKey() {
            return RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        }
	
}
