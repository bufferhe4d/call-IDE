package submissionsystem;

import java.sql.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdullah Talayhan
 */
public class DBSystem {
    
    private static Connection cont = null;
    private static Statement stmt = null;
    
    public static void initializeDB(String host, String dbname, String uname,
                                    String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DBMS conected");
            String conInfo = "jdbc:mysql://" + host + "/" + dbname + "?verifyServerCertificate=false&useSSL=true";
            cont = DriverManager.getConnection(conInfo, uname, password);   
            stmt = cont.createStatement();
            System.out.println("Database connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        if (cont != null) {
            try {
                cont.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static ResultSet executeQue(String query) {
        ResultSet rs = null;
        if (cont != null) {
            query = query.toLowerCase();
            try {
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
    public static int executeUp(String query) {
        int alteredRow = 0;
        if (cont != null) {
            query = query.toLowerCase();
            try {
                alteredRow = stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alteredRow;
    }
    
    public ResultSet execute(String query) {
        query = query.toLowerCase();
        ResultSet rs = null;
        try {
            if (query.startsWith("select")) {
                stmt.executeQuery(query);
            } else {
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public static DefaultTableModel showTables(ResultSet rs) {
        DefaultTableModel dtm = new DefaultTableModel();
        Vector allrows = null;
        if (cont != null) {
            try {
                Vector rowVectors = new Vector();
                Vector columnHeaderVector = new Vector();
                
                int columncount = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    Vector singleRow = new Vector();
                    for(int i=1;i<=columncount;i++)
                    {
                        singleRow.addElement(rs.getObject(i));
                    }
                    rowVectors.addElement(singleRow);
                }
                for(int i=1;i<=columncount;i++)
                {
                    columnHeaderVector.addElement(rs.getMetaData().getColumnName(i));
                }
                dtm.setDataVector(rowVectors, columnHeaderVector);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dtm;
    }
    
    public static Vector getTableNames() {
        String query = "show tables";
        ResultSet rs;
        Vector tablenames = new Vector();
        try {
            PreparedStatement pstmt = cont.prepareStatement(query);   
            rs = pstmt.executeQuery();   
            while (rs.next()) {
                tablenames.add(rs.getString(1));
            }
            System.out.println(tablenames);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablenames;
    } 
}
