import java.sql.ResultSet;
import java.sql.SQLException;


public class TestDB {
	
	public static void main(String[] args) {
		
		DBSystem.initializeDB("localhost:8889", "callIDETest", "root", "root");
		String query;
		
		query = "Select * from employees;";
		
		ResultSet rs = DBSystem.executeQue(query);
		
		try {
			while(rs.next()) {
				
				System.out.println(rs.getString(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
