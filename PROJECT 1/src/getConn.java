import java.sql.Connection;
import java.sql.DriverManager;

public class getConn {
	
	private static Connection conn;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","madhu123");
		}
		
		catch(Exception e) {
			System.out.println(" "+e);
		}
		
		return conn;
	}

}
