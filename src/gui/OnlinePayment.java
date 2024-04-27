package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.IDN;
import java.security.IdentityScope;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JTextArea;

public class OnlinePayment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField ID;
	private JTextField Amount;
	private JTextField YOURPAY;
	DefaultListModel<String> listModel;

	public String Courseid;

	public String getCourseid() {
		return Courseid;
	}

	public void setCourseid(String courseid) {
		Courseid = courseid;
	}

	public String Studentid;

	public String getStudentid() {
		return Studentid;
	}

	public void setStudentid(String studentid) {
		this.Studentid = studentid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlinePayment frame = new OnlinePayment(new String());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param studentID2
	 */
	public OnlinePayment(String studentID2) {
		
		setStudentid(studentID2);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel = new JLabel("ONLINE PAYMENT");
		lblNewLabel.setBounds(104, 5, 213, 100);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 84, 381, 21);

		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setBounds(37, 116, 109, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel_1_1 = new JLabel("Courses");
		lblNewLabel_1_1.setBounds(37, 174, 109, 21);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel_1_1_1 = new JLabel("Amount");
		lblNewLabel_1_1_1.setBounds(37, 228, 109, 21);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		Amount = new JTextField();
		Amount.setEditable(false);
		Amount.setBounds(156, 230, 213, 19);
		Amount.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Vui lòng thanh toán bằng hình thức chuyển khoản");
		lblNewLabel_2.setBounds(37, 314, 342, 49);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(37, 374, 342, 22);
		horizontalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBounds(37, 414, 342, 23);
		horizontalBox_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JLabel lblNewLabel_2_1_1 = new JLabel("Ngân hàng: Vietcombank ");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("Lưu ý chuyển khoản theo cú pháp hello");
		lblNewLabel_2_2.setBounds(37, 443, 342, 79);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("COMPLETE");
		btnNewButton.setBounds(147, 533, 85, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = Connect.getConnection();
				try {
					PreparedStatement pstm = c
							.prepareStatement("Select Pay1,Pay2,Pay3,Debt from StudentCourses where StudentID = '"
									+ getStudentid() + "' and CourseID ='" + Courseid + "'");
					ResultSet rs = pstm.executeQuery();

					while (rs.next()) {
						if (!(rs.getString("Pay1") == null)) {
							String textDebt1 = rs.getString("Debt");
							try {
								double Debt = Double.parseDouble(textDebt1);
								if (Debt > 0) {

								} else {
									JOptionPane.showMessageDialog(contentPane,
											"Khóa học này đã được thanh toán đầy đủ!!", "",
											JOptionPane.INFORMATION_MESSAGE);
									break;
								}
							} catch (NumberFormatException e3) {
								e3.printStackTrace();
							}
							if (!(rs.getString("Pay2") == null)) {
								String textDebt2 = rs.getString("Debt");
								try {
									double Debt = Double.parseDouble(textDebt2);
									if (Debt > 0) {

									} else {
										JOptionPane.showMessageDialog(contentPane,
												"Khóa học này đã được thanh toán đầy đủ!!", "",
												JOptionPane.INFORMATION_MESSAGE);
										break;
									}
								} catch (NumberFormatException e3) {
									e3.printStackTrace();
								}
								if (!(rs.getString("Pay3") == null)) {
									String textDebt3 = rs.getString("Debt");
									try {
										double Debt = Double.parseDouble(textDebt3);
										if (Debt > 0) {
											JOptionPane.showMessageDialog(contentPane,
													"Bạn đã thanh toán quá 3 lần mà vẫn chưa trả hết tiền học vậy nên hãy đến nộp tiền trực tiếp!!",
													"", JOptionPane.WARNING_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(contentPane,
													"Khóa học này đã được thanh toán đầy đủ!!", "",
													JOptionPane.INFORMATION_MESSAGE);
											break;
										}
									} catch (NumberFormatException e3) {
										e3.printStackTrace();

									}
									;
								} else {
									String textPay = YOURPAY.getText();
									String textDebt = rs.getString("Debt");
									try {
										double yourpay = Double.parseDouble(textPay);
										double Debt = Double.parseDouble(textDebt);
										double Debt1 = Debt - yourpay;
										if (Debt1 > 0) {
											JOptionPane.showMessageDialog(contentPane, "Hoàn tất thanh toán lần 3!!",
													"", JOptionPane.INFORMATION_MESSAGE);
										} else {
											Debt1 = 0.0;
											JOptionPane.showMessageDialog(contentPane,
													"Hoàn tất thanh toán khóa học này!!", "",
													JOptionPane.INFORMATION_MESSAGE);
										}
										PreparedStatement pstm2 = c.prepareStatement(
												"UPDATE StudentCourses SET Pay3 = ?,Debt = ? where StudentID = ? and CourseID = ?");
										pstm2.setDouble(1, yourpay);
										pstm2.setDouble(2, Debt1);
										pstm2.setString(3, getStudentid());
										pstm2.setString(4, Courseid);
										pstm2.executeUpdate();
									} catch (NumberFormatException e1) {
										JOptionPane.showMessageDialog(contentPane, "Nhập vào 1 số thực!!", "",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							} else {

								String textPay = YOURPAY.getText();
								String textDebt = rs.getString("Debt");
								try {
									double yourpay = Double.parseDouble(textPay);
									double Debt = Double.parseDouble(textDebt);
									double Debt1 = Debt - yourpay;
									if (Debt1 > 0) {
										JOptionPane.showMessageDialog(contentPane, "Hoàn tất thanh toán lần 2!!", "",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										Debt1 = 0.0;
										JOptionPane.showMessageDialog(contentPane, "Hoàn tất thanh toán khóa học này!!",
												"", JOptionPane.INFORMATION_MESSAGE);
									}
									PreparedStatement pstm2 = c.prepareStatement(
											"UPDATE StudentCourses SET Pay2 = ?,Debt = ? where StudentID = ? and CourseID = ?");
									pstm2.setDouble(1, yourpay);
									pstm2.setDouble(2, Debt1);
									pstm2.setString(3, getStudentid());
									pstm2.setString(4, Courseid);
									pstm2.executeUpdate();
								} catch (NumberFormatException e1) {
									JOptionPane.showMessageDialog(contentPane, "Nhập vào 1 số thực!!", "",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						} else {
							String textAmount = Amount.getText();
							String textPay = YOURPAY.getText();
							try {
								double Amount = Double.parseDouble(textAmount); // Chuyển đổi văn bản thành số thực
								double yourpay = Double.parseDouble(textPay);
								double Debt1 = Amount - yourpay;

								if (Debt1 > 0) {
									JOptionPane.showMessageDialog(contentPane, "Hoàn tất thanh toán lần 1!!", "",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									Debt1 = 0.0;
									JOptionPane.showMessageDialog(contentPane, "Hoàn tất thanh toán khóa học này!!", "",
											JOptionPane.INFORMATION_MESSAGE);
								}
								PreparedStatement pstm2 = c.prepareStatement(
										"UPDATE StudentCourses SET Pay1 = ?,Debt = ? where StudentID = ? and CourseID = ?");
								pstm2.setDouble(1, yourpay);
								pstm2.setDouble(2, Debt1);
								pstm2.setString(3, getStudentid());
								pstm2.setString(4, Courseid);
								pstm2.executeUpdate();

							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(contentPane, "Nhập vào 1 số thực!!", "",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}

					COURSESCHECK co = new COURSESCHECK();
					co.reload();
					co.model.fireTableDataChanged();
					dispose();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();

				}
			}
		});

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Your payment ");
		lblNewLabel_1_1_1_1.setBounds(37, 282, 109, 21);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		YOURPAY = new JTextField();
		YOURPAY.setBounds(156, 284, 213, 19);
		YOURPAY.setColumns(10);

		ID = new JTextField();
		ID.setText(getStudentid());
		ID.setEditable(false);
		ID.setBounds(156, 118, 213, 20);
		ID.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("STK: 1029548360");
		horizontalBox.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(separator);
		contentPane.add(lblNewLabel_1);
		contentPane.add(ID);
		contentPane.add(lblNewLabel_1_1);

		JComboBox comboBox1 = new JComboBox();

		Connection c = Connect.getConnection();
		try {
			PreparedStatement pstm = c.prepareStatement(
					"Select CourseName from StudentCourses where StudentID = '" + getStudentid() + "'");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				comboBox1.addItem(rs.getString("CourseName"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Connection c = Connect.getConnection();
				String coursesName = (String) comboBox1.getSelectedItem();

				{

					try {
						PreparedStatement pstm = c.prepareStatement(
								"Select CourseID,Tuitionfull from Courses WHERE CourseName =N'" + coursesName + "'");
						ResultSet rs = pstm.executeQuery();
						while (rs.next()) {
							Amount.setText(rs.getString("Tuitionfull"));
							setCourseid(rs.getString("CourseID"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("N");
					}

				}
			}
		});

		contentPane.add(lblNewLabel_1_1_1);
		contentPane.add(Amount);
		contentPane.add(lblNewLabel_2_2);
		contentPane.add(horizontalBox_1);
		contentPane.add(horizontalBox);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1_1_1_1);
		contentPane.add(YOURPAY);
		contentPane.add(btnNewButton);

		comboBox1.setBounds(156, 175, 213, 20);
		contentPane.add(comboBox1);

	}
}
