package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Bienlai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Diachi;
	private JTextField Sodienthoai;
	private JTextField Gmail;
	private JTextField Tong;
	private JTextField Ten;
	private JTextField textField_7;
	private JTextField textField;
	private JTextField Studentname;
	public static String Studentid;
	public static String CourseName;
	public static String getCourseName() {
		return CourseName;
	}

	public static void setCourseName(String courseName) {
		CourseName = courseName;
	}
	private JComboBox comboBox2;
	private JTextField textField_1;

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
					Bienlai frame = new Bienlai();
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
	public Bienlai() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReciept = new JLabel("BIÊN LAI THU PHÍ");
		lblReciept.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblReciept.setBounds(158, 11, 336, 60);
		contentPane.add(lblReciept);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 73, 557, 7);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1_3 = new JLabel("Họ và tên người nộp: ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(62, 162, 149, 27);
		contentPane.add(lblNewLabel_1_3);

		comboBox2 = new JComboBox();
		comboBox2.setBounds(264, 317, 298, 22);
		contentPane.add(comboBox2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(264, 137, 298, 22);
		contentPane.add(comboBox);
		Connection c = Connect.getConnection();
		try {
			PreparedStatement pstm = c.prepareStatement("Select distinct StudentID from Studentcourses where Debt = 0");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				comboBox.addItem(rs.getString("StudentID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setStudentid ((String)comboBox.getSelectedItem());
				try {
					PreparedStatement pstm = c
							.prepareStatement("Select distinct StudentName from Studentcourses where StudentID ='"
									+ getStudentid() + "'");
					ResultSet rs = pstm.executeQuery();

					while (rs.next()) {
						Studentname.setText(rs.getString("StudentName"));
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}
				try {
					PreparedStatement pstm = c
							.prepareStatement("Select distinct Studentname,Studentprovince,Studentphonenumber,Studentemail from StudentAcc where Studentid ='"
									+ getStudentid() + "'");
					ResultSet rs = pstm.executeQuery();

					while (rs.next()) {
						Diachi.setText(rs.getString("Studentprovince"));
						Sodienthoai.setText(rs.getString("Studentphonenumber"));
						Gmail.setText(rs.getString("Studentemail"));
						Ten.setText(rs.getString("Studentname"));
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}
				
				comboBox2.removeAllItems();
				Tong.setText("0");
				Connection c = Connect.getConnection();
				try {
					PreparedStatement pstm = c.prepareStatement("Select CourseName from Studentcourses where StudentID ='" + getStudentid() + "' and Debt = 0");
					ResultSet rs = pstm.executeQuery();

					while (rs.next()) {
						comboBox2.addItem(rs.getString("CourseName"));

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				comboBox2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						String coursesName = (String) comboBox2.getSelectedItem();
						setCourseName(coursesName);
						{
							try {
								PreparedStatement pstm = c.prepareStatement(
										"Select Pay1,Pay2,Pay3 from Studentcourses WHERE CourseName =N'"
												+ getCourseName() + "' and StudentID ='" + getStudentid() + "'");
								ResultSet rs = pstm.executeQuery();
								while (rs.next()) {
									String Pay1 = rs.getString("Pay1");
									String Pay2 = rs.getString("Pay2");
									String Pay3 = rs.getString("Pay3");
									if (rs.getString("Pay2") == null) {
										Pay2 = "0";
									}
									if (rs.getString("Pay3") == null) {
										Pay3 = "0";
									}
									Double Thepay1 = Double.parseDouble(Pay1);
									Double Thepay2 = Double.parseDouble(Pay2);
									Double Thepay3 = Double.parseDouble(Pay3);
									
									
									Double Tongthu = Thepay1+Thepay2+Thepay3;
									
									Tong.setText(Tongthu+"");
//								
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								System.out.println("N");
							}

						}
					}
			
		});
			}});
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1.setBounds(62, 200, 149, 27);
		contentPane.add(lblNewLabel_1_3_1);
		
		Diachi = new JTextField();
		Diachi.setEditable(false);
		Diachi.setBounds(264, 204, 298, 20);
		contentPane.add(Diachi);
		Diachi.setColumns(10);
		
		Sodienthoai = new JTextField();
		Sodienthoai.setEditable(false);
		Sodienthoai.setColumns(10);
		Sodienthoai.setBounds(264, 242, 298, 20);
		contentPane.add(Sodienthoai);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1.setBounds(62, 238, 149, 27);
		contentPane.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Gmail:");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1_1.setBounds(62, 276, 149, 27);
		contentPane.add(lblNewLabel_1_3_1_1_1);
		
		Gmail = new JTextField();
		Gmail.setEditable(false);
		Gmail.setColumns(10);
		Gmail.setBounds(264, 280, 298, 20);
		contentPane.add(Gmail);
		
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Khóa học đã thanh toán:");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_2.setBounds(62, 314, 184, 27);
		contentPane.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Số tiền đã thu:");
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1_1_1.setBounds(62, 350, 149, 27);
		contentPane.add(lblNewLabel_1_3_1_1_1_1);
		
		Tong = new JTextField();
		Tong.setEditable(false);
		Tong.setColumns(10);
		Tong.setBounds(264, 354, 298, 20);
		contentPane.add(Tong);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Người nộp tiền");
		lblNewLabel_1_3_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_3.setBounds(122, 388, 149, 27);
		contentPane.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Người thu tiền");
		lblNewLabel_1_3_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_3_1.setBounds(375, 388, 149, 27);
		contentPane.add(lblNewLabel_1_3_3_1);
		
		Ten = new JTextField();
		Ten.setEditable(false);
		Ten.setColumns(10);
		Ten.setBounds(62, 418, 219, 20);
		contentPane.add(Ten);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(323, 418, 219, 20);
		contentPane.add(textField_7);
		
		JButton btnNewButton = new JButton("IN BIÊN LAI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if((textField_1.getText().isEmpty()) || textField.getText().isEmpty() || Bienlai.getStudentid()==(null) || Studentname.getText()==(null) || Diachi.getText()==(null) || Sodienthoai.getText()==(null) || Gmail.getText()==(null) || CourseName==(null) || Tong.getText()==(null) || Ten.getText()==(null) || textField_7.getText().isEmpty()) {
						JOptionPane.showMessageDialog(Bienlai.this, "Khong duoc de trong!!", "",
								JOptionPane.INFORMATION_MESSAGE);
						;
					}else {
				Bienlaiduocin bl = new Bienlaiduocin();
				bl.Setnew(textField_1.getText(), textField.getText(), getStudentid(), Studentname.getText(), Diachi.getText(), Sodienthoai.getText(), Gmail.getText(), CourseName, Tong.getText(), Ten.getText(), textField_7.getText());
				bl.setVisible(true);
				}}
				
			
		});
		btnNewButton.setBounds(237, 464, 106, 37);
		contentPane.add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bienlai.this.dispose();
			}
		});
		btnReturn.setBounds(492, 471, 70, 22);
		contentPane.add(btnReturn);
		
		JLabel lblNewLabel_2_2 = new JLabel("Số: ");
		lblNewLabel_2_2.setBounds(399, 112, 25, 14);
		contentPane.add(lblNewLabel_2_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(465, 112, 59, 14);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("ID:");
		lblNewLabel_1_3_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_4.setBounds(62, 134, 149, 27);
		contentPane.add(lblNewLabel_1_3_4);
		
		Studentname = new JTextField();
		Studentname.setEditable(false);
		Studentname.setColumns(10);
		Studentname.setBounds(264, 166, 298, 20);
		contentPane.add(Studentname);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Quyển:");
		lblNewLabel_2_2_1.setBounds(399, 91, 56, 14);
		contentPane.add(lblNewLabel_2_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(465, 91, 59, 14);
		contentPane.add(textField_1);
	}
	
	
}