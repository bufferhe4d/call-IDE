package submissionsystem;

public class ServerTest {
	public static void main(String[] args) {
		DBSystem.initializeDB("localhost:8889", "callIDE", "root", "root");
		//Register Student Instructor (Same)
		//ServerSys.registerStudent("10", "Joe", "joepwd", "joe@mail.com");
		
		// Create Course
		//ServerSys.createCourse("CS101", "20", "joe");
		
		// Enroll Course
		ServerSys.enrollCourse("5", "1");
	}
}
