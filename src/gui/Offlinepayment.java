package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Offlinepayment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dateend;
	private JTextField pay;
	private JTextField number;
	private JTextField Studentname;
	private JTextField datestart;
	private JTextField debt;
	JComboBox comboBoxCourse;

	private String Studentid;
	private String CourseName;
	private Double Amountofmoney;
	private Double Bigdebt;
	private Double Payment;

	public Double getPayment() {
		return Payment;
	}

	public void setPayment(Double payment) {
		Payment = payment;
	}

	public Double getBigdebt() {
		return Bigdebt;
	}

	public void setBigdebt(Double bigdebt) {
		Bigdebt = bigdebt;
	}

	public Double getAmountofmoney() {
		return Amountofmoney;
	}

	public void setAmountofmoney(Double amountofmoney) {
		Amountofmoney = amountofmoney;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	private JTextField full;

	public String getStudentid() {
		return Studentid;
	}

	public void setStudentid(String studentid) {
		Studentid = studentid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Offlinepayment frame = new Offlinepayment();
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
	public Offlinepayment() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1017, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel_3 = new JLabel("Mã học sinh");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(44, 123, 110, 29);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_8 = new JLabel("Số buổi");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(277, 216, 110, 29);
		contentPane.add(lblNewLabel_8);

		dateend = new JTextField();
		dateend.setEditable(false);
		dateend.setColumns(10);
		dateend.setBounds(44, 256, 223, 29);
		contentPane.add(dateend);

		JLabel lblNewLabel_11_1 = new JLabel("Số tiền đóng");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_11_1.setBounds(274, 314, 110, 29);
		contentPane.add(lblNewLabel_11_1);

		pay = new JTextField();
		pay.setColumns(10);
		pay.setBounds(277, 354, 223, 29);
		contentPane.add(pay);

		JLabel lblNewLabel_11_2 = new JLabel("Còn nợ");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_11_2.setBounds(510, 314, 110, 29);
		contentPane.add(lblNewLabel_11_2);

		number = new JTextField();
		number.setEditable(false);
		number.setColumns(10);
		number.setBounds(277, 256, 223, 29);
		contentPane.add(number);

		JLabel lblNewLabel_7 = new JLabel("Khóa học của học sinh");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(510, 123, 167, 29);
		contentPane.add(lblNewLabel_7);

		comboBoxCourse = new JComboBox();
		comboBoxCourse.setBounds(510, 163, 223, 29);
		contentPane.add(comboBoxCourse);

		JLabel lblNewLabel_5 = new JLabel("Ngày vào học");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(743, 123, 110, 29);
		contentPane.add(lblNewLabel_5);

		Studentname = new JTextField();
		Studentname.setEditable(false);
		Studentname.setColumns(10);
		Studentname.setBounds(277, 163, 223, 29);
		contentPane.add(Studentname);

		datestart = new JTextField();
		datestart.setEditable(false);
		datestart.setColumns(10);
		datestart.setBounds(743, 163, 223, 29);
		contentPane.add(datestart);

		JLabel lblNewLabel_6 = new JLabel("Ngày kết thúc");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(44, 216, 110, 29);
		contentPane.add(lblNewLabel_6);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 981, 18);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("OFFLINE PAYMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(369, 34, 308, 37);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("COMPLETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = Connect.getConnection();
				try {
					String pay1 = pay.getText();
					if(pay1.equals(null)) {
						pay1 = "0";
					}
					double payment = Double.parseDouble(pay1);
					Payment = Payment + payment;
					if (Bigdebt > 0 && Payment != 0) {
						Amountofmoney = Bigdebt;
					}
					double debt = Amountofmoney - payment;
					if (debt <= 0) {
						debt = 0;
					}

					PreparedStatement pst = c.prepareStatement(
							"Update Studentcourses set Pay1 = ?,Debt = ? Where StudentID = ? and CourseName = ?");
					pst.setDouble(1, Payment);
					pst.setDouble(2, debt);
					pst.setString(3, Studentid);
					pst.setString(4, CourseName);
					pst.executeUpdate();
					Offlinepayment.this.dispose();

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(351, 422, 138, 43);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(510, 422, 138, 43);
		contentPane.add(btnCancel);

		debt = new JTextField();
		debt.setEditable(false);
		debt.setColumns(10);
		debt.setBounds(510, 354, 223, 29);
		contentPane.add(debt);

		JLabel lblNewLabel_10_1 = new JLabel("Học phí tổng");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10_1.setBounds(510, 216, 155, 29);
		contentPane.add(lblNewLabel_10_1);

		JComboBox comboBoxID = new JComboBox();
		comboBoxID.setBounds(44, 163, 223, 29);
		contentPane.add(comboBoxID);

		full = new JTextField();
		full.setEditable(false);
		full.setColumns(10);
		full.setBounds(510, 256, 223, 29);
		contentPane.add(full);

		JLabel lblNewLabel_3_1 = new JLabel("Tên học sinh");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(277, 123, 110, 29);
		contentPane.add(lblNewLabel_3_1);

		
		Connection c = Connect.getConnection();
		try {
			PreparedStatement pstm = c.prepareStatement("Select distinct StudentID from Studentcourses");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				comboBoxID.addItem(rs.getString("StudentID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		comboBoxID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setStudentid((String) comboBoxID.getSelectedItem());
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
				Active(Studentid);
			}
		});

	}

	public void Active(String id) {
		comboBoxCourse.removeAllItems();
		Connection c = Connect.getConnection();
		try {
			PreparedStatement pstm = c.prepareStatement("Select distinct CourseName from Studentcourses where StudentID ='" + id + "'");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				comboBoxCourse.addItem(rs.getString("CourseName"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		comboBoxCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String coursesName = (String) comboBoxCourse.getSelectedItem();
				setCourseName(coursesName);
				{
//					setCourseName(coursesName);
					try {
						PreparedStatement pstm = c.prepareStatement(
								"Select DateStart,DateEnd,Numberofsession,Tuitionfull,Pay1,Pay2,Pay3,Debt from Studentcourses WHERE CourseName =N'"
										+ coursesName + "' and StudentID ='" + getStudentid() + "'");
						ResultSet rs = pstm.executeQuery();
						while (rs.next()) {
							datestart.setText(rs.getString("DateStart"));
							dateend.setText(rs.getString("DateEnd"));
							number.setText(rs.getString("Numberofsession"));
							full.setText(rs.getString("Tuitionfull"));

							
							String debt1 = rs.getString("Debt");
							String pay1 = rs.getString("Pay1");
							String pay2 = rs.getString("Pay2");
							String pay3 = rs.getString("Pay3");
							String amount = rs.getString("Tuitionfull");
						
							
							if (rs.getString("Debt") == null) {
								debt1 = "0";
							}
							if (rs.getString("Tuitionfull") == null) {
								amount = "0";
							}
							if (rs.getString("Pay1") == null) {
								pay1 = "0";
							}
							if (rs.getString("Pay2") == null) {
								pay2 = "0";
							}
							if (rs.getString("Pay3") == null) {
								pay3 = "0";
							}
							
							double thepay1 = Double.parseDouble(pay1);
							double thepay2 = Double.parseDouble(pay2);
							double thepay3 = Double.parseDouble(pay3);
							double payment = thepay1 + thepay2 + thepay3;
							double theamount = Double.parseDouble(amount);
							setAmountofmoney(theamount);
							double thedebt = Double.parseDouble(debt1);
							setBigdebt(thedebt);

							
							pay.setText(payment+"");
							setPayment(payment);
							debt.setText(rs.getString("Debt"));
//						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("N");
					}

				}
			}
		});
	}
}
