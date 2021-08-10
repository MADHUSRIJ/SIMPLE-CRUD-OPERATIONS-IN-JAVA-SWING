import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class Login extends JFrame implements ActionListener,ItemListener{

	JLabel title,userName,pass,reg;
	JComboBox<String> userName_t;
	JPasswordField pass_t;
	JButton log;
	JCheckBox toggle;
	JPanel jp;
	Container co;
	
	Login(){
		
		co = getContentPane();
		co.setLayout(null);
		
		title = new JLabel("<HTML><U>LOGIN FORM</U></HTML>");
		userName = new JLabel("USERNAME");
		pass = new JLabel("PASSWORD");
		
		userName_t = new JComboBox<>();
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
		
		pass_t = new JPasswordField();
		pass_t.setEchoChar('*');
		
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
		
		log = new JButton("LOGIN");
		reg = new JLabel("<HTML><U>DOESN'T HAVE AN ACCOUNT ?</U></HTML>");
		log.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		title.setBounds(180,15,200,30);
		title.setFont(new Font("SERIF",Font.BOLD,20));
		title.setForeground(new Color(29,70,165));
		
		userName.setBounds(70,80,100,30);
		userName_t.setBounds(200,80,200,30);
		
		pass.setBounds(70,160,100,30);
		pass_t.setBounds(200,160,200,30);
		toggle.setBounds(280,190,200,30);
		
		log.setBounds(200,240,100,30);
		reg.setBounds(160,280,200,30);
		reg .setForeground(new Color(29,70,165));
		
		jp = new JPanel();
		
		jp.setVisible(true);
		jp.setLayout(null);
		jp.setBounds(40,50,500,350);
		jp.setBackground(new Color(255,235,235));
		jp.setBorder(BorderFactory.createLineBorder(new Color(255,178,178), 2, false));
		
		jp.add(title);
		jp.add(userName);
		jp.add(userName_t);
		jp.add(pass);
		jp.add(pass_t);
		jp.add(toggle);
		jp.add(log);
		jp.add(reg);
		
		co.add(jp);
		
		co.setBackground(new Color(255,212,212));
		setSize(600,500);
		setResizable(false);
		setVisible(true);
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                DetailsCheck mn = new DetailsCheck();
				
				mn.setUserName(userName_t.getSelectedItem().toString());
				
				String give_password = pass_t.getText().toString();
				
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
					viewTable vt = new viewTable();
					setVisible(false);
				}
				
			}
		});
		
		reg.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				SignUp sp = new SignUp();
				setVisible(false);
			}
		});
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Login();
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
