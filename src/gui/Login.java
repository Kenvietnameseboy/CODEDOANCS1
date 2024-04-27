package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDatabaseMetaData;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class Login extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	private JPasswordField passwordField;
	Incomemanagement admin;
	STUDENTFORM st;
	public String id;
	public static String username;
	public static String password;
	
	Outcomemanagement out;
	Incomemanagement in;
	Equipment eq;
	Human a;
	Rental r;
	
	public static String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Login.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Login.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Login");
		setBounds(100, 100, 455, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel_1 = new JLabel("USERNAME");		lblNewLabel_1.setBounds(49, 69, 79, 17);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setBounds(184, 11, 89, 46);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setBounds(49, 128, 79, 17);
		contentPane.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(138, 67, 203, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(138, 126, 203, 20);
		contentPane.add(passwordField);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ADMIN", "STUDENT" }));
		comboBox.setBounds(264, 176, 77, 22);
		contentPane.add(comboBox);

//		showtable = new Table();
		
		st = new STUDENTFORM();
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String username = textField.getText();
				setUsername(username);
				String password = new String(passwordField.getPassword());
				setPassword(password);
				String right = (String) comboBox.getSelectedItem();
				
				
				if (right == "ADMIN") {
					if (username.equals("admin") && password.equals("admin123")){
					a = new Human();
					eq = new Equipment();
					r = new Rental();
					out = new Outcomemanagement(a, eq, r);
					in = new Incomemanagement();
					Administrator ad = new Administrator(out,in);
					ad.setVisible(true);
					
					
					} else {
						JOptionPane.showMessageDialog(Login.this, "Đăng nhập thất bại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (right == "STUDENT") {
					int i = 0;
					if (!username.isEmpty() && !password.isEmpty()) {
						Connection c = Connect.getConnection();

						try {
							PreparedStatement pst1 = c
									.prepareStatement("Select Username,Pass,Studentid from Studentform ");
							ResultSet rs = pst1.executeQuery();
							while (rs.next()) {
								if (username.equals(rs.getString("Username"))
										&& password.equals(rs.getString("Pass"))) {
									i = 0;
									JOptionPane.showMessageDialog(Login.this, "Đăng nhập thành công!!", "",
											JOptionPane.INFORMATION_MESSAGE);

									setId(rs.getString("Studentid"));
									
									st.studentform(username, password, id);

									st.setVisible(true);
									Login.this.dispose();
									break;
								} else
									i++;

							}

						} catch (Exception e2) {
							// TODO: handle exception
							e2.getMessage();

						}
						if (i >= 1) {
							JOptionPane.showMessageDialog(Login.this,
									"Mật khẩu hoặc tên đăng nhập bị sai vui lòng đăng nhập lại hoặc tạo tài khoản mới!!",
									"", JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(Login.this, "Không được để trống", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(49, 227, 109, 23);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(287, 227, 108, 23);
		contentPane.add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 43, 419, 15);
		contentPane.add(separator);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String username = textField.getText();
//				setUsername(username);
//				String password = new String(passwordField.getPassword());
//				setPassword(password);
//				String right = (String) comboBox.getSelectedItem();
//
//				if (right == "ADMIN") {
//
//					JOptionPane.showMessageDialog(Login.this, "Admin không được tạo tài khoản mới chỉ được đăng nhập!",
//							"", JOptionPane.WARNING_MESSAGE);
//
//				} else if (right == "STUDENT") {
//					int i = 0;
//					if (!username.isEmpty() && !password.isEmpty()) {
//						Connection c = Connect.getConnection();
//
////						
//
//						try {
//							PreparedStatement pst1 = c.prepareStatement("Select Username,Pass from Studentform ");
//							ResultSet rs = pst1.executeQuery();
//							while (rs.next()) {
//								if (username.equals(rs.getString("Username"))
//										&& password.equals(rs.getString("Pass"))) {
//									i = 0;
//									JOptionPane.showMessageDialog(Login.this, "Tài khoản đã tồn tại vui lòng đăng nhập",
//											"", JOptionPane.ERROR_MESSAGE);
//									break;
//								}
//
//								else
//									i++;
//
//							}
//							if (i >= 1) {
//								try {
//									PreparedStatement pst = c
//											.prepareStatement("INSERT INTO Studentform(Username,Pass) values (?,?) ");
//									pst.setString(1, username);
//									pst.setString(2, password);
//									pst.executeUpdate();
//									JOptionPane.showMessageDialog(Login.this, "Đăng kí tài khoản thành công", "",
//											JOptionPane.INFORMATION_MESSAGE);
//								} catch (Exception e2) {
//									// TODO: handle exception
//									e2.printStackTrace();
//								}
//							}
//						} catch (Exception e2) {
//							// TODO: handle exception
//							e2.getMessage();
//
//						}
//
//					} else {
//						JOptionPane.showMessageDialog(Login.this, "Không được để trống", "", JOptionPane.ERROR_MESSAGE);
//					}
//				}
				RegisterAccount re = new RegisterAccount();
				re.setVisible(true);
			}
		});
		btnRegister.setBounds(168, 227, 109, 23);
		contentPane.add(btnRegister);

	}

}
