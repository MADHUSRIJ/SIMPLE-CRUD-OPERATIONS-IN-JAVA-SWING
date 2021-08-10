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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class SignUp extends JFrame implements ActionListener,ItemListener{

	JLabel title,name,userName,DOB,age,gender,marital,address,state,pincode,phn,altPhn,passWord,rePass;
	JTextField name_t,userName_t,dob_t,age_t,pincode_t,phn_t,altPhn_t;
	JTextArea address_t;
	MaskFormatter f1,f2,f3,f4;
	JPasswordField pass_t,rePass_t;
	ButtonGroup btn;
	JRadioButton male,female;
	JComboBox<String> marital_drop,state_drop;
	JCheckBox toggle;
	JButton submit,back;
	Container co;
	String str_gender = "";
	JPanel jp;

	
	SignUp(){
		setTitle("REGISTRATION FORM");
		
		co = getContentPane();
		co.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//*****************************************REFERNCE***********************************************************************//
		
		title = new JLabel("<HTML><U>REGISTRATION FORM</U></HTML>");
		name = new JLabel("FULL NAME");
		userName = new JLabel("USERNAME");
		DOB = new JLabel("DATE OF BIRTH");
		age = new JLabel("AGE");
		gender = new JLabel("GENDER");
		marital = new JLabel("MARITAL STATUS");
		address = new JLabel("ADDRESS");
		state = new JLabel("STATE");
		pincode = new JLabel("PINCODE");
		phn = new JLabel("PHONE NUMBER");
		altPhn = new JLabel("ALTERNATE PHONE NUMBER");
		passWord = new JLabel("PASSWORD");
		rePass = new JLabel("RE-TYPE PASSWORD");
		back = new JButton("BACK");
		
		name_t = new JTextField();
		userName_t = new JTextField();
		dob_t = new JTextField();
		age_t = new JTextField();
		address_t = new JTextArea();
		address_t.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		address_t.setLineWrap(true);
		pincode_t = new JTextField();
		phn_t = new JTextField();
		altPhn_t = new JTextField();
		
		state_drop = new JComboBox<String>();
		marital_drop = new JComboBox<String>();
		
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
		
		pass_t = new JPasswordField();
		rePass_t = new JPasswordField();
		
		pass_t.setEchoChar('*');
		rePass_t.setEchoChar('*');
		
		btn = new ButtonGroup();
		
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		
		male.setBackground(new Color(255,235,235));
		female.setBackground(new Color(255,235,235));
		
		male.addItemListener(this);
		female.addItemListener(this);
		
		btn.add(male);
		btn.add(female);
		
		
		toggle = new JCheckBox("Show Password");
		
		toggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(toggle.isSelected()) {
					pass_t.setEchoChar((char)0);
				}
				else{
					pass_t.setEchoChar('*');
				}
				
			}
		});
		
		toggle.setBackground(new Color(255,235,235));
		
		submit = new JButton("SUBMIT");
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login l = new Login();
				setVisible(false);
			}
		});
		
		//****************************************** SUBMIT ACTION LISTENER ****************************************************************//
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method
				
				Boolean bool = true;
				
				DetailsCheck mn = new DetailsCheck();
				
				while(bool) {
					mn.setFullName(name_t.getText().toString());
					
					if(mn.getFullName().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER NAME");
			            break;
					}
					
					mn.setUserName(userName_t.getText().toString());
					
					if(mn.getUserName().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER USER NAME\nFORMAT : yourname@something ");
						break;
					}
					
					else {
						
						String userName_Str = mn.getUserName();
						
						
						try {
							
							Connection conn = getConn.getConnection();
							
							Boolean boo = false;
							
							String query = "SELECT USERNAME FROM PROJECT1";
							
							PreparedStatement pstmt = conn.prepareStatement(query);
							
							ResultSet rst = pstmt.executeQuery();
							
							while(rst.next()) {
								
								if(userName_Str.equals(rst.getString("USERNAME"))){
									boo = true;
									break;
								}
							}
							
							if(boo){
								JOptionPane.showMessageDialog(co, "USERNAME ALREADY EXISTS !");
								break;
							}
					
							
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(co, "ERROR FETCHING DATABASE !"+ex);
							break;
						}
						
						
						
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
						JOptionPane.showMessageDialog(rootPane,"PROVIDE YOUR PROPER PHONE NUMBER");
						break;
					}
					
					mn.setAltPhnNumber(altPhn_t.getText().toString());
					
					if(mn.getAltPhnNumber().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,"PROVIDE PROPER ANOTHER PHONE NUMBER");
						break;
					}
					
					
					if(pass_t.getText().toString().equals(rePass_t.getText().toString())) {
						mn.setPassWord(pass_t.getText().toString());
						if(mn.getPassWord().isEmpty()) {
							JOptionPane.showMessageDialog(rootPane,"SET PASSWORD AND RE-TYPE PASSWORD");
							break;
						}
						else {
							
							bool = false;
							Database db = new Database();
							db.dbinsert(mn);
							setVisible(false);
							viewTable vt = new viewTable();
						}
					}
					
					else {
						JOptionPane.showMessageDialog(rootPane,"PASSWORD AND REPASS DOESN'T MATCH");
						break;
					}
				}
				
				
	
			}
		});
		
		//************************************BOUNDS**********************************************************************//
		
		title.setBounds(450,10,1000,80);
		title.setFont(new Font("Serif", Font.BOLD,  25));
		title.setForeground(new Color(29,70,165));
		
		
		name.setBounds(100,120,200,30);
		name_t.setBounds(280,120,200,30);
		userName.setBounds(700,120,200,30);
		userName_t.setBounds(880,120,200,30);
		
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
		
		passWord.setBounds(100,430,200,30);
		pass_t.setBounds(280,430,200,30);
		rePass.setBounds(700,430,200,30);
		rePass_t.setBounds(880,430,200,30);
		
		toggle.setBounds(360,460,200,30);
		
		submit.setBounds(450,530,120,30);
		
		
		back.setBounds(620,530,120,30);
		
	
		//*********************************************************************ADD*******************************************************//
		
		jp = new JPanel();
		jp.setBounds(160,100,1200,600);
		jp.setLayout(null);
		jp.setOpaque(true);
		jp.setVisible(true);
		jp.setBackground(new Color(255,235,235));
		jp.setBorder(BorderFactory.createLineBorder(new Color(255,178,178), 3, false));
		
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
		jp.add(passWord);
		jp.add(pass_t);
		jp.add(toggle);
		jp.add(rePass);
		jp.add(rePass_t);
		jp.add(submit);
		jp.add(back);

		co.add(jp);
		
		setSize(2000,2000);
		co.setBackground(new Color(255,212,212));
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SignUp();

	}


	@Override
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		ItemSelectable item = ie.getItemSelectable();
    	
    	if(item == male){
    		str_gender = "Male";
    	}
    	else if(item == female) {
    		str_gender = "Female";
    	}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
