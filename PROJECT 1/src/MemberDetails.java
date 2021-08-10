import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemberDetails extends JFrame implements ItemListener{
	
	JLabel title,userName,name,DOB,age,gender,marital,address,state,pincode,phn,altPhn;
	JTextField name_t,dob_t,age_t,pincode_t,phn_t,altPhn_t;
	JTextArea address_t;
	ButtonGroup btn;
	JRadioButton male,female;
	JComboBox<String> userName_t,marital_drop,state_drop;
	JButton update,delete,change,back;
	Container co;
	JPanel jp;
	String str_gender = "";
	
	MemberDetails(){
		setTitle("MEMBER DETAILS");
		
		co = getContentPane();
		co.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//**************************************************************************** REFERENCE ********************************************************************************//
		
		title = new JLabel("<HTML><U>CHANGE YOUR DETAILS</U></HTML>");
		name = new JLabel("FULL NAME");
		userName = new JLabel("USER NAME");
		DOB = new JLabel("DATE OF BIRTH");
		age = new JLabel("AGE");
		gender = new JLabel("GENDER");
		marital = new JLabel("MARITAL STATUS");
		address = new JLabel("ADDRESS");
		state = new JLabel("STATE");
		pincode = new JLabel("PINCODE");
		phn = new JLabel("PHONE NUMBER");
		altPhn = new JLabel("ALTERNATE PHONE NUMBER");
		
		name_t = new JTextField();
		dob_t = new JTextField();
		age_t = new JTextField();
		address_t = new JTextArea();
		address_t.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		address_t.setLineWrap(true);
		pincode_t = new JTextField();
		phn_t = new JTextField();
		altPhn_t = new JTextField();
		
		userName_t = new JComboBox<String>();
		state_drop = new JComboBox<String>();
		marital_drop = new JComboBox<String>();
		
		userName_t.setBackground(Color.white);
	
		userName_t.addItemListener(this);
		
		try {
			userName_t.addItem("SELECT USERNAME");
			
			Connection conn = getConn.getConnection();
			
			String query = "select username from project1";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				userName_t.addItem(rst.getString("username"));
			}
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(co, "ERROR!!!!");
		}
		
		state_drop.setBackground(Color.white);
		
		state_drop.addItem("SELECT STATE");
		state_drop.addItem("TamilNadu");
		state_drop.addItem("Karnataka");
		state_drop.addItem("Kerala");
		state_drop.addItem("Andhra Pradesh");
		
		marital_drop.setBackground(Color.white);
		
		marital_drop.addItem("STATUS");
		marital_drop.addItem("Single");
		marital_drop.addItem("Married");
		
		
		btn = new ButtonGroup();
		
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		
		male.setBackground(new Color(255,235,235));
		female.setBackground(new Color(255,235,235));
		
		male.addItemListener(this);
		female.addItemListener(this);
		
		btn.add(male);
		btn.add(female);
		
		update = new JButton("UPDATE");
		delete = new JButton("DELETE");
		change = new JButton("CHANGE PASSWORD");
		back = new JButton("BACK");
		
		//******************************************************************************* BOUNDS*************************************************************************************//
		
		title.setBounds(400,10,1000,80);
		title.setFont(new Font("SERIF", Font.TRUETYPE_FONT,  30));
		title.setForeground(new Color(29,70,165));
		
		name.setBounds(700,120,200,30);
		name_t.setBounds(880,120,200,30);
		userName.setBounds(100,120,200,30);
		userName_t.setBounds(280,120,200,30);
		
		DOB.setBounds(100,170,200,30);
		dob_t.setBounds(280,170,200,30);
		age.setBounds(700,170,200,30);
		age_t.setBounds(880,170,200,30);
		
		gender.setBounds(100,220,200,30);
		male.setBounds(280,220,100,30);
		female.setBounds(400,220,100,30);
		marital.setBounds(700,220,200,30);
		marital_drop.setBounds(880,220,200,30);
		
		address.setBounds(100,270,200,30);
		address_t.setBounds(280,270,200,100);
		state.setBounds(700,280,200,30);
		state_drop.setBounds(880,280,200,30);
		pincode.setBounds(700,330,200,30);
		pincode_t.setBounds(880,330,200,30);
		
		phn.setBounds(100,380,200,30);
		phn_t.setBounds(280,380,200,30);
		altPhn.setBounds(700,380,200,30);
		altPhn_t.setBounds(880,380,200,30);
		
		back.setBounds(300,480,100,30);
		update.setBounds(450,480,100,30);
		delete.setBounds(600,480,100,30);
		change.setBounds(750,480,200,30);
		
		//***************************************************************************** BACK ACTION LISTENER *********************************************************************************//
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				viewTable vt = new viewTable();
			}
		});
		
		//****************************************************************************** UPDATE ACTION LISTENER ******************************************************************************//
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Boolean bool = true;
				
				DetailsCheck mn = new DetailsCheck();
				
				while(bool) {
					mn.setFullName(name_t.getText().toString());
					
					if(mn.getFullName().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER NAME");
			            break;
					}
					
					mn.setUserName(userName_t.getSelectedItem().toString());
					
					if(mn.getUserName().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER USER NAME\nFORMAT : yourname@something ");
						break;
					}
					
					
					String str_date = dob_t.getText().toString();
					mn.setDOB(str_date);
					
					if(mn.getDOB().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER DATE OF BIRTH\nFORMAR : DD-MM-YYYY ");
						break;
					}
					
					mn.setAge(age_t.getText().toString());
					
					if(mn.getAge().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER AGE IN NUMBER");
						break;
					}
					
					mn.setGender(str_gender);
					
					if(mn.getGender().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"SELECT YOUR GENDER");
						break;
					}
					
					mn.setMarital(marital_drop.getSelectedItem().toString());
					
					if(mn.getMarital().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"SELECT YOUR CORRESPONDING MARITAL STATUS");
						break;
					}
					
					mn.setAddress(address_t.getText().toString());
					
					if(mn.getAddress().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER ADDRESS");
						break;
					}
					
					mn.setState(state_drop.getSelectedItem().toString());
					
					if(mn.getState().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"SELECT YOUR STATE");
						break;
					}
					
					mn.setPincode(pincode_t.getText().toString());
					
					if(mn.getPincode().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE YOUR LOCATION PINCODE");
						break;
					}
					
					mn.setPhnNumber(phn_t.getText().toString());
					
					if(mn.getPhnNumber().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE YOUR PHONE NUMBER");
						break;
					}
					
					mn.setAltPhnNumber(altPhn_t.getText().toString());
					
					if(mn.getAltPhnNumber().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE ANOTHER PHONE NUMBER");
						break;
					}
				
					String give_password = JOptionPane.showInputDialog(co,"ENTER YOUR PASSWORD");
				
					String org_password = "";
				
					try {
					
					
						String USERNAME = (String) userName_t.getSelectedItem();
								
						Connection conn = getConn.getConnection();				
					
						String query = "select PASSWORD from project1 where username = ?";
									
						PreparedStatement pstmt = conn.prepareStatement(query);
										
						pstmt.setString(1, USERNAME);					
					
						ResultSet rst = pstmt.executeQuery();
										
						if(rst.next()) {						
							org_password = rst.getString("PASSWORD");					
						}				   
					}				
					catch(Exception e1) {					
						JOptionPane.showMessageDialog(co, "ERROR FETCHING DATA");
					}
				
				
					if(!(give_password.equals(org_password))) {
					
						JOptionPane.showMessageDialog(co, "INCORRECT PASSWORD!");
				
					}
				
				
					else {
					
						bool = false;
						
						Database db = new Database();
						db.dbupdate(mn);
						
						setVisible(false);
						new viewTable();
					}
				}
				
				
		}});
		
		//************************************************************************** DELETE ACTION LISTENER ***********************************************************************//
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String give_password = JOptionPane.showInputDialog(co,"ENTER YOUR PASSWORD");
				
				String org_password = "";
				
				try {
					
					String USERNAME = (String) userName_t.getSelectedItem();
					
					Connection conn = getConn.getConnection();
					
					String query = "select PASSWORD from project1 where username = ?";
					
					PreparedStatement pstmt = conn.prepareStatement(query);
					
					pstmt.setString(1, USERNAME);
					
					ResultSet rst = pstmt.executeQuery();
					
					
					
					if(rst.next()) {
						org_password = rst.getString("PASSWORD");
					}	
			    }
				catch(Exception e1) {
					JOptionPane.showMessageDialog(co, "ERROR FETCHING DATA");
				}
				
				if(!(give_password.equals(org_password))) {
					JOptionPane.showMessageDialog(co, "INCORRECT PASSWORD!");
				}
				
				else {
					Database db = new Database();
					db.dbdelete(userName_t.getSelectedItem().toString());
					
					setVisible(false);
					new MemberDetails();
				}
				
			}
		});
		
		//********************************************************************* CHANGE ACTION LISTENER *****************************************************************************//
		
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				password cp = new password();
			}
		});
		
		//******************************************************************************** ADD ************************************************************************************//
		
		jp = new JPanel();
		jp.setBounds(160,100,1200,600);
		jp.setLayout(null);
		jp.setOpaque(true);
		jp.setVisible(true);
		jp.setBackground(new Color(255,235,235));
		jp.setBorder(BorderFactory.createLineBorder(new Color(255,178,178), 3, false));
		
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		jp.add(title);
		jp.add(name);
		jp.add(name_t);
		jp.add(userName);
		jp.add(userName_t);
		jp.add(DOB);
		jp.add(dob_t);
		jp.add(age);
		jp.add(age_t);
		jp.add(gender);
		jp.add(male);
		jp.add(female);
		jp.add(marital);
		jp.add(marital_drop);
		jp.add(address);
		jp.add(address_t);
		jp.add(state);
		jp.add(state_drop);
		jp.add(pincode);
		jp.add(pincode_t);
		jp.add(phn);
		jp.add(phn_t);
		jp.add(altPhn);
		jp.add(altPhn_t);
		jp.add(update);
		jp.add(delete);
		jp.add(back);
		jp.add(change);
		
		co.add(jp);
		
		setSize(2000,2000);
		co.setBackground(new Color(255,212,212));
		setVisible(true);
	}

	//***************************************************************************** ITEM LISTENER **************************************************************************************//

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		ItemSelectable item = e.getItemSelectable();
		
		if(item == male){
    		str_gender = "Male";
    	}
    	else if(item == female) {
    		str_gender = "Female";
    	}
		
		if(item == userName_t) {
			if(userName_t.getSelectedItem() != "SELECT USERNAME") {
				
				try {
					
					String USERNAME = (String) userName_t.getSelectedItem();
					
					Connection conn = getConn.getConnection();
					
					String query = "select FULLNAME,DOB,AGE,GENDER,MARITALSTATUS,ADDRESS,STATE,PINCODE,PHONENUMBER,ALTPHONENUMBER from project1 where username = ?";
					
					PreparedStatement pstmt = conn.prepareStatement(query);
					
					pstmt.setString(1, USERNAME);
					
					ResultSet rst = pstmt.executeQuery();
					
					if(rst.next()) {
						name_t.setText(rst.getString("FULLNAME"));
						dob_t.setText(rst.getString("DOB"));
						age_t.setText(rst.getString("AGE"));
						if(rst.getString("GENDER").equals("Male")) {
							male.setSelected(true);
						}
						else if(rst.getString("GENDER").equals("Female")){
							female.setSelected(true);
						}
						marital_drop.setSelectedItem(rst.getString("MARITALSTATUS"));
						address_t.setText(rst.getString("ADDRESS"));
						state_drop.setSelectedItem(rst.getString("STATE"));
						pincode_t.setText(rst.getString("PINCODE"));
						phn_t.setText(rst.getString("PHONENUMBER"));
						altPhn_t.setText(rst.getString("ALTPHONENUMBER"));
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(co, "ERROR FETCHING DATA");
				}		
			}
		}
	}
	
	//****************************************************************************************************************************************************************************************//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MemberDetails();
	}
}
