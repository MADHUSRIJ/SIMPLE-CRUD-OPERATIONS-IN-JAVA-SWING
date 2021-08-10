import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class password extends JFrame implements ActionListener,ItemListener {

	JLabel title,userName,op,pass,rePass;
	JPasswordField op_t,pass_t,rePass_t;
	JComboBox<String> userName_t;
	JButton change,back;
	JCheckBox toggle1,toggle2;
	Container co;
	JPanel jp;
	
	password(){
		
		co = getContentPane();
		co.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel("<HTML><U>CHANGE PASSWORD</U></HTML>");
		userName = new JLabel("USERNAME");
		op = new JLabel("OLD PASSWORD");
		pass = new JLabel("NEW PASSWORD");
		rePass = new JLabel("RE-TYPE NEW PASSWORD");
		
		userName_t = new JComboBox<>();
		
		userName_t.setBackground(Color.WHITE);
		
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
		
		op_t = new JPasswordField();
		pass_t = new JPasswordField();
		rePass_t = new JPasswordField();
		
		toggle1 = new JCheckBox("Show Password");
		toggle1.setBackground(new Color(255,235,235));
		
		toggle1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(toggle1.isSelected()) {
					op_t.setEchoChar((char)0);
				}
				else{
					op_t.setEchoChar('*');
				}
				
			}
		});
		
		toggle2 = new JCheckBox("Show Password");
		toggle2.setBackground(new Color(255,235,235));
		
		toggle2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(toggle2.isSelected()) {
					pass_t.setEchoChar((char)0);
				}
				else{
					pass_t.setEchoChar('*');
				}
				
			}
		});
		
		change = new JButton("CONFIRM");
		back = new JButton("BACK");
		
		title.setBounds(160,10,500,30);
		title.setFont(new Font("SERIF",Font.BOLD,20));
		title.setForeground(new Color(29,70,165));
		
		userName.setBounds(50, 70, 200, 30);
		userName_t.setBounds(250,70,200,30);
		
		op.setBounds(50,120,200,30);
		op_t.setBounds(250,120,200,30);
		
		toggle1.setBounds(330,150,120,30);
		
		pass.setBounds(50,200,200,30);
		pass_t.setBounds(250,200,200,30);
		
		toggle2.setBounds(330,230,120,30);
		
		rePass.setBounds(50,280,200,30);
		rePass_t.setBounds(250,280,200,30);
		
		back.setBounds(110,350,100,30);
		change.setBounds(240,350,120,30);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
        jp = new JPanel();
		
		jp.setVisible(true);
		jp.setLayout(null);
		jp.setBounds(70,50,500,400);
		jp.setBackground(new Color(255,235,235));
		jp.setBorder(BorderFactory.createLineBorder(new Color(255,178,178), 2, false));
		
		jp.add(title);
		jp.add(userName);
		jp.add(userName_t);
		jp.add(op);
		jp.add(op_t);
		jp.add(pass);
		jp.add(pass_t);
		jp.add(rePass);
		jp.add(rePass_t);
		jp.add(toggle1);
		jp.add(toggle2);
		jp.add(back);
		jp.add(change);
		
		co.add(jp);
		
		co.setBackground(new Color(255,212,212));
		setSize(650,550);
		setResizable(false);
		setVisible(true);
		
		//******************************************* BACK ACTION LISTENER ***************************************************************//
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				MemberDetails md = new MemberDetails();
			}
		});
		
		//******************************************* CONIRM ACTION LISTENER **************************************************************//
		
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DetailsCheck mn = new DetailsCheck();
				
				mn.setUserName(userName_t.getSelectedItem().toString());
				
				String give_password = op_t.getText().toString();
				
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
					
					if(pass_t.getText().toString().equals(rePass_t.getText().toString())) {
						mn.setPassWord(pass_t.getText().toString());
						if(mn.getPassWord().isEmpty()) {
							JOptionPane.showMessageDialog(rootPane,"SET PASSWORD AND RE-TYPE PASSWORD");
						}
						else {
							Database db = new Database();
							db.dbpass(mn);
							
							userName_t.setSelectedIndex(0);
							op_t.setText("");
							pass_t.setText("");
							rePass_t.setText("");
						}
					}
					
					else {
						JOptionPane.showMessageDialog(rootPane,"NEW PASSWORD AND RE-PASS DOESN'T MATCH");
					}
				}
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new password();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
