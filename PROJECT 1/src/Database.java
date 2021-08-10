
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
	
	public void dbinsert(DetailsCheck mn){
    	
    	try {
    		
    		String FULLNAME = mn.getFullName();
    		String USERNAME = mn.getUserName();
    		String DOB = mn.getDOB();
    		Integer AGE = Integer.parseInt(mn.getAge());
    		String GENDER = mn.getGender();
    		String MARITALSTATUS = mn.getMarital();
    		String ADDRESS = mn.getAddress();
    		String STATE = mn.getState();
    		Long PINCODE = Long.parseLong(mn.getPincode());
    		Long PHONENUMBER = Long.parseLong(mn.getPhnNumber());
    		Long ALTPHONENUMBER = Long.parseLong(mn.getAltPhnNumber());
    		String PASSWORD = mn.getPassWord();
    		
    		Connection conn = getConn.getConnection();
    		Statement stmt = conn.createStatement();
    		
    		String query = "insert into Project1 values('"+FULLNAME+"','"+USERNAME+"','"+DOB+"',"+AGE+",'"+GENDER+"','"+MARITALSTATUS+"','"+ADDRESS+"','"+STATE+"',"+PINCODE+","+PHONENUMBER+","+ALTPHONENUMBER+",'"+PASSWORD+"')";
    		
    		stmt.executeUpdate(query);
    		conn.setAutoCommit(true);
    		
    		JOptionPane.showMessageDialog(null, "Hi ! "+FULLNAME+"\nYOUR REGISTRATION WAS SUCCESSFUL !\nTHANK YOU !\nHAVE A NICE DAY !");
    	}
    	
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "SORRY THERE IS AN ERROR IN REGISTERING YOUR DETAILS\n ERROR : "+e.toString());
    	}
    	
    }


	public void dbupdate(DetailsCheck mn) {
		// TODO Auto-generated method stub

		try {
    		
    		String FULLNAME = mn.getFullName();
    		String USERNAME = mn.getUserName();
    		String DOB = mn.getDOB();
    		Integer AGE = Integer.parseInt(mn.getAge());
    		String GENDER = mn.getGender();
    		String MARITALSTATUS = mn.getMarital();
    		String ADDRESS = mn.getAddress();
    		String STATE = mn.getState();
    		Long PINCODE = Long.parseLong(mn.getPincode());
    		Long PHONENUMBER = Long.parseLong(mn.getPhnNumber());
    		Long ALTPHONENUMBER = Long.parseLong(mn.getAltPhnNumber());
    		
    		Connection conn = getConn.getConnection();
    		
    		String query = "UPDATE PROJECT1 SET FULLNAME=?,DOB=?,AGE=?,GENDER=?,MARITALSTATUS=?,ADDRESS=?,STATE=?,PINCODE=?,PHONENUMBER=?,ALTPHONENUMBER=? WHERE USERNAME=?";
    		
    		PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, FULLNAME);
			pstmt.setString(2, DOB);
			pstmt.setInt(3, AGE);
			pstmt.setString(4, GENDER);
			pstmt.setString(5, MARITALSTATUS);
			pstmt.setString(6, ADDRESS);
			pstmt.setString(7, STATE);
			pstmt.setLong(8,PINCODE);
			pstmt.setLong(9, PHONENUMBER);
			pstmt.setLong(10, ALTPHONENUMBER);
			pstmt.setString(11, USERNAME);
			
			pstmt.executeUpdate();
			conn.setAutoCommit(true);
    		
    		JOptionPane.showMessageDialog(null, FULLNAME+"\nYOUR DETAILS HAS BEEN UPDATED !\nTHANK YOU !\nHAVE A NICE DAY !");
    	}
    	
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "SORRY THERE IS AN ERROR IN UPDATIONG YOUR DETAILS \n ERROR : "+e.toString());
    	}
	}


	public void dbdelete(String name) {
		// TODO Auto-generated method stub
		
		
		
		try {
			Connection conn = getConn.getConnection();
			String query = "DELETE FROM PROJECT1 WHERE USERNAME=?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			conn.setAutoCommit(true);
			JOptionPane.showMessageDialog(null, "YOUR DELETION WAS SUCCESFULL !\nTHANK YOU!");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "SORRY THERE WAS AN ERROR IN DELETIONG YOUR DETAILS\nERROR : "+e);
		}
		
	}


	public void dbpass(DetailsCheck mn) {
		// TODO Auto-generated method stub
		
		try {
			
			String USERNAME = mn.getUserName();
			String PASSWORD = mn.getPassWord();
			
			Connection conn = getConn.getConnection();
    		
    		String query = "UPDATE PROJECT1 SET PASSWORD = ? WHERE USERNAME=?";
    		
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1, PASSWORD);
			pstmt.setString(2, USERNAME);
			
			pstmt.executeUpdate();
			conn.setAutoCommit(true);
    		
    		JOptionPane.showMessageDialog(null, "YOUR PASSWORD IS SUCCEFULLY CHANGED !\nTHANK YOU !\nHAVE A NICE DAY !");
		}
		catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "SORRY THERE IS AN ERROR IN CHANGING YOUR \n ERROR : "+e.toString());
    	}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Database();
	}


	

}
