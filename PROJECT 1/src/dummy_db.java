import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class dummy_db {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       new dummy_db();
	}

	public void insert(dummy_val dv) {
		// TODO Auto-generated method stub
		String uname = dv.getUsername();
		String pass = dv.getPass();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","madhu123");
			
			Statement stmt = conn.createStatement();
			
			String query = "insert into dummy values('"+uname+"','"+pass+"')";
			
			stmt.executeQuery(query);
			
			conn.setAutoCommit(true);
			
			JOptionPane.showMessageDialog(null, "REGISTRATION SUCCESSFUL !");
			
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR !"+e);
		}
		
		
	}

}
