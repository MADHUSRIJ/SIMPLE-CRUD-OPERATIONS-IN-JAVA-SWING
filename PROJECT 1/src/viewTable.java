import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class viewTable extends JFrame implements ActionListener {

	Container co;
	JLabel title;
	JTable table;
	JScrollPane sp;
	JButton change,back;
	String Column[] = {"FULL NAME","USER NAME","DOB","AGE","GENDER","MARITAL STATUS","ADDRESS","PINCODE","STATE","PHONE NUMBER 1","PHONE NUMBER 2"};
	int n = 0;
	viewTable(){
		
		co = getContentPane();
		setLayout(null);
		
		title = new JLabel("<HTML><U>REGISTERED MEMBERS DETAILS</U></HTML>");
		
		change = new JButton("MAKE CHANGES");
		back = new JButton("BACK");
		
		try {
			Connection conn = getConn.getConnection();
			
			String query = "SELECT * FROM PROJECT1";
			
			PreparedStatement st = conn.prepareStatement(query);
			
			ResultSet rst = st.executeQuery();
			
			while(rst.next()) {
				n++;
			}
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(co,"ERROR COUNTING ROWS !!\nERROR : "+e );
		}
		
		String Row[][] = new String[n][12];
		
		try {
			
			Connection conn = getConn.getConnection();
			
			String query = "SELECT * FROM PROJECT1 ORDER BY FULLNAME";
			
			PreparedStatement st = conn.prepareStatement(query);
			
			ResultSet rst = st.executeQuery();
			
			   
			
			int i = 0;
			
			while(rst.next()) {
				
			   int j = 0;
			   
			   Row[i][j++] = rst.getString("FULLNAME");
			   Row[i][j++] = rst.getString("USERNAME");
			   Row[i][j++] = rst.getString("DOB");
			   String age_Str = Integer.toString(rst.getInt("AGE"));
			   Row[i][j++] = age_Str;
			   Row[i][j++] = rst.getString("GENDER");
			   Row[i][j++] = rst.getString("MARITALSTATUS");
			   Row[i][j++] = rst.getString("ADDRESS");
			   String pin_str = Long.toString(rst.getLong("PINCODE"));
			   Row[i][j++] = pin_str;
			   Row[i][j++] = rst.getString("STATE");
			   String phn_str = Long.toString(rst.getLong("PHONENUMBER"));
			   String altPhn_str = Long.toString(rst.getLong("ALTPHONENUMBER"));
			   Row[i][j++] = phn_str;
			   Row[i][j++] =altPhn_str;
			   
			   i++;
			}
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(co,"ERROR SHOWING DETAILS!!\nERROR : "+e );
		}
		
		
		
		table = new JTable(Row,Column);
		table.setLayout(null);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(9).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(10).setCellRenderer( centerRenderer );
		table.setBackground(Color.white);
		table.setBounds(10,10,10,10);
		table.setRowHeight(30);
		table.setFont(new Font("SERIF",Font.PLAIN,15));
		table.setOpaque(true);
		table.setVisible(true);
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(10,30));
		tableHeader.setForeground(Color.WHITE);
	    tableHeader.setBackground(new Color(255,0,127));
	    tableHeader.setFont(new Font("VERDANA",Font.BOLD,12));
	    
		sp = new JScrollPane(table);
		sp.setForeground(new Color(255,212,212));
		sp.setOpaque(true);
		sp.setVisible(true);
		sp.setBorder(BorderFactory.createEmptyBorder());
		sp.getViewport().setBackground(new Color(255,235,235));
		
		
		title.setBounds(600,30,500,30);
		title.setFont(new Font("SERIF",Font.BOLD,25));
		title.setForeground(new Color(29,70,165));
		change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		sp.setBounds(15,80,1500,600);
		
		change.setBounds(1200,30,150,30);
		back.setBounds(1400,30,100,30);
		
		co.add(title);
		co.add(sp);
		co.add(change);
		co.add(back);
		
		co.setBackground(new Color(255,235,235));
		setSize(2000,2000);
		setVisible(true);
		
		//********************************************** CHANGE ACTION LISTENER ******************************************************//
		
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemberDetails md = new MemberDetails();
				setVisible(false);
			}
		});
		
		//*********************************************** BACK ACTION LISTENER *********************************************************//
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login l = new Login();
				setVisible(false);
			}
		});
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new viewTable();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
