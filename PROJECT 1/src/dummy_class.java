import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class dummy_class extends JFrame implements ActionListener {

	JLabel userName,Password;
	JTextField userName_t;
	JPasswordField pass_t;
	JButton submit;
	Container co;
	
	dummy_class() {
		
		co = getContentPane();
		co.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		userName = new JLabel("USERNAME");
		Password = new JLabel("PASSWORD");
		
		userName_t = new JTextField();
		
		pass_t = new JPasswordField();
		pass_t.setEchoChar('*');
		
		submit = new JButton("SUBMIT");
		
		userName.setBounds(10,10,100,30);
		userName_t.setBounds(210,10,100,30);
		
		Password.setBounds(10,60,100,30);
		pass_t.setBounds(210,60,100,30);
		
		submit.setBounds(100,110,100,30);
		
		
		co.add(userName);
		co.add(userName_t);
		co.add(Password);
		co.add(pass_t);
		co.add(submit);
		
		co.setBackground(Color.cyan);
		
		setSize(400,400);
		setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new dummy_class();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
