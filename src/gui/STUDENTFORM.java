package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class STUDENTFORM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menu = new JMenuBar();
	private JMenu accnew = new JMenu("Account");
	private JMenuItem logout = new JMenuItem("Log out");
	private JPanel contentPane;
	private JTextField txtHello;
	private JTextField Name;
	private JTextField Phonenum;
	private JTextField Email;
	private JTextField School;
	private JTextField Province;
	private JTextField ID;
	private JTextField textField;
	COURSESCHECK cocheck;
	public static String Studentid;

	public static String getStudentid() {
		return Studentid;
	}

	public static void setStudentid(String studentid) {
		Studentid = studentid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					STUDENTFORM frame = new STUDENTFORM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public STUDENTFORM() {
		
		
		menu.add(accnew);
		accnew.add(logout);
		this.setJMenuBar(menu);
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					STUDENTFORM.this.dispose();
					new Login().setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 718, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		
		JLabel lblNewLabel = new JLabel("STUDENT FORM");
		lblNewLabel.setBounds(265, 10, 160, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 672, 2);
		contentPane.add(separator);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(10, 46, 672, 98);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(10, 38, 79, 17);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setBounds(10, 66, 79, 17);
		contentPane_1.add(lblNewLabel_1_1);
		
		txtHello = new JTextField();
		
		txtHello.setColumns(10);
		txtHello.setBounds(99, 36, 203, 20);
		txtHello.setEditable(false);
		contentPane_1.add(txtHello);
		
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(99, 64, 203, 20);
		contentPane_1.add(textField);
		
		JLabel lblAccount = new JLabel("ACCOUNT");
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAccount.setBounds(99, 0, 160, 25);
		contentPane_1.add(lblAccount);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 147, 672, 7);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("\"Do you not have a course yet?\"\r\n"
				+ "");
		lblNewLabel_2.setBounds(133, 430, 202, 45);
		contentPane.add(lblNewLabel_2);
		
		COURSESREGISTER coursereg = new COURSESREGISTER(new COURSESCHECK(), new Incomemanagement());
		JButton btnNewButton = new JButton("REGISTER COURSES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID.getText();
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Bạn chưa đăng kí học sinh của trung tâm đào tạo vui lòng đăng kí học sinh trước", "", JOptionPane.WARNING_MESSAGE);
				}
				else {
				coursereg.StudentID(id);
				coursereg.setVisible(true);}
			}
		});
		btnNewButton.setBounds(133, 470, 145, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("\"Have you already taken a course?\"");
		lblNewLabel_2_1.setBounds(373, 430, 202, 45);
		contentPane.add(lblNewLabel_2_1);
		
		COURSESCHECK cs = new COURSESCHECK();
		JButton btnCheckCourse = new JButton("CHECK COURSES");
		btnCheckCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID.getText();
				cs.StudentID(id);
				cs.reload();
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Bạn chưa đăng kí học sinh của trung tâm đào tạo vui lòng đăng kí học sinh trước", "", JOptionPane.WARNING_MESSAGE);
				}
				else
				cs.setVisible(true);
			}
		});
		btnCheckCourse.setBounds(373, 470, 145, 45);
		contentPane.add(btnCheckCourse);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("STUDENT NAME");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(33, 244, 145, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(223, 244, 444, 25);
		Name.setEditable(false);
		contentPane.add(Name);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("PHONE NUMBER");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(33, 280, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		Phonenum = new JTextField();
		Phonenum.setColumns(10);
		Phonenum.setBounds(223, 284, 444, 25);
		Phonenum.setEditable(false);
		contentPane.add(Phonenum);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("EMAIL ADDRESS");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1.setBounds(33, 316, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(223, 320, 444, 25);
		Email.setEditable(false);
		contentPane.add(Email);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("SCHOOL");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2.setBounds(33, 352, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		School = new JTextField();
		School.setColumns(10);
		School.setBounds(223, 356, 444, 25);
		School.setEditable(false);
		contentPane.add(School);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("PROVINCE");
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2_1.setBounds(33, 392, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1_2_1);
		
		Province = new JTextField();
		Province.setColumns(10);
		Province.setBounds(223, 396, 444, 25);
		Province.setEditable(false);
		contentPane.add(Province);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("STUDENT ID");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2.setBounds(33, 209, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		ID = new JTextField();
		
		ID.setColumns(10);
		ID.setBounds(223, 209, 444, 25);
		ID.setEditable(false);
		contentPane.add(ID);
		
		
		
		STUDENTREGISTER stu = new STUDENTREGISTER();
		JButton btnNewButton_2 = new JButton("REGISTER STUDENT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID.getText();
				
				if(!id.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Bạn đã đăng kí học sinh rồi bây giờ bạn có thể chọn khóa học", "", JOptionPane.WARNING_MESSAGE);
				}
				else
				{stu.setVisible(true);
				STUDENTFORM.this.dispose();
				}
			
			}
		});
		btnNewButton_2.setBounds(280, 155, 145, 45);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("\"Are you a new student ?\"\r\n");
		lblNewLabel_2_2.setBounds(112, 153, 158, 45);
		contentPane.add(lblNewLabel_2_2);
		
		 
	}
	public void studentform(String username,String password, String id2) {
		  
	      ID.setText(id2);
	      setStudentid(id2);
	      txtHello.setText(username);
	      textField.setText(password);
	      Connection c = Connect.getConnection();
			try {
				
				PreparedStatement pst = c.prepareStatement(
						"Select distinct a.Studentid,a.Studentname,a.Studentphonenumber,a.Studentemail,a.Studentschool,a.Studentprovince from StudentAcc as a where a.Studentid = "+ID.getText()+"");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Name.setText(rs.getString("Studentname"));
					Phonenum.setText(rs.getString("Studentphonenumber"));
					Email.setText(rs.getString("Studentemail"));
					School.setText(rs.getString("Studentschool"));
					Province.setText(rs.getString("Studentprovince"));
				} 
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
}
