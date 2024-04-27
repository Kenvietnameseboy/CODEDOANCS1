package gui;

import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class RegisterAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	public String username;
	public String password;
	private JTextField textField_1;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAccount frame = new RegisterAccount();
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
	public RegisterAccount() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 378);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color	(224,255,255));
		setLocationRelativeTo(contentPane);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRegister.setBounds(194, 11, 194, 46);
		contentPane.add(lblRegister);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 466, 20);
		contentPane.add(separator);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 95, 224, 20);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(10, 95, 79, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ENTER PASSWORD");
		lblNewLabel_1_1.setBounds(10, 154, 139, 17);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 152, 224, 20);
		contentPane.add(passwordField);
		
		JButton btnRegister = new JButton("REGISTER");
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				setUsername(username);
				String password = new String(passwordField.getPassword());
				setPassword(password);
				String reenter = new String(passwordField_1.getPassword());
				String eop = textField_1.getText();
					int i = 0;
					if (!username.isEmpty() && !password.isEmpty() && !reenter.isEmpty()&&!eop.isEmpty() ) {
						Connection c = Connect.getConnection();

//						

						try {
							PreparedStatement pst1 = c.prepareStatement("Select Username,Pass from Studentform ");
							ResultSet rs = pst1.executeQuery();
							while (rs.next()) {
								if (username.equals(rs.getString("Username"))
										&& password.equals(rs.getString("Pass"))) {
									i = 0;
									JOptionPane.showMessageDialog(RegisterAccount.this, "Tài khoản đã tồn tại vui lòng đăng nhập",
											"", JOptionPane.ERROR_MESSAGE);
									break;
								}
								else if(!(password.equals(reenter))) {
									JOptionPane.showMessageDialog(RegisterAccount.this, "Mật khẩu không khớp vui lòng nhập lại", "", JOptionPane.ERROR_MESSAGE);
									i = 0;
									break;
								}
								else
									i++;

							}
							if (i >= 1) {
								try {
									PreparedStatement pst = c
											.prepareStatement("INSERT INTO Studentform(Username,Pass) values (?,?) ");
									pst.setString(1, username);
									pst.setString(2, password);
									pst.executeUpdate();
									JOptionPane.showMessageDialog(RegisterAccount.this, "Đăng kí tài khoản thành công", "",
											JOptionPane.INFORMATION_MESSAGE);
								} catch (Exception e2) {
									// TODO: handle exception
									e2.printStackTrace();
								}
							}
						} catch (Exception e2) {
							// TODO: handle exception
							e2.getMessage();

						}

					} else {
						JOptionPane.showMessageDialog(RegisterAccount.this, "Không được để trống", "", JOptionPane.ERROR_MESSAGE);
					}
				
			}
		});
		btnRegister.setBounds(83, 305, 109, 23);
		contentPane.add(btnRegister);
		
		

	    
		
		JButton btnCancel = new JButton("RETURN");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterAccount.this.dispose();
				Login log = new Login();
				log.setVisible(true);
			}
		});
		btnCancel.setBounds(254, 305, 108, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("RE-ENTER PASSWORD");
		lblNewLabel_1_1_1.setBounds(10, 214, 139, 17);
		contentPane.add(lblNewLabel_1_1_1);
		
		passwordField_1 = new JPasswordField();
		
		passwordField_1.setBounds(200, 212, 224, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("EMAIL OR PHONE");
		lblNewLabel_1_1_1_1.setBounds(10, 267, 139, 17);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 265, 224, 20);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			private int clickCount = 0;
			public void actionPerformed(ActionEvent e) {
				if (clickCount % 2 == 0) {
                    // Hiển thị mật khẩu
                    passwordField.setEchoChar('\0');
                } else {
                    // Ẩn mật khẩu
                    passwordField.setEchoChar('\u2022');
                }
                clickCount++;
            
			}
		});
		btnNewButton.setBounds(434, 151, 24, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			private int Count = 0;
			public void actionPerformed(ActionEvent e) {
				
				if (Count % 2 == 0) {
                    // Hiển thị mật khẩu
                    passwordField_1.setEchoChar('\0');
                } else {
                    // Ẩn mật khẩu
                    passwordField_1.setEchoChar('\u2022');
                }
                Count++;
			}
		});
		btnNewButton_1.setBounds(434, 211, 24, 23);
		contentPane.add(btnNewButton_1);
	}
}
