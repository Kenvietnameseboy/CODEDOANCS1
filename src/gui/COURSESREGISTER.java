package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class COURSESREGISTER extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField textField;
	private JTextField TUITION;

	private String courseName;
	private String courseid;
	private String DateStart;

	public String getDateStart() {
		return DateStart;
	}

	public void setDateStart(String dateStart) {
		DateStart = dateStart;
	}

	public String getDateEnd() {
		return DateEnd;
	}

	public void setDateEnd(String dateEnd) {
		DateEnd = dateEnd;
	}

	public String getNumberofsession() {
		return Numberofsession;
	}

	public void setNumberofsession(String numberofsession) {
		Numberofsession = numberofsession;
	}

	public String getTuitionfull() {
		return Tuitionfull;
	}

	public void setTuitionfull(String tuitionfull) {
		Tuitionfull = tuitionfull;
	}

	public String getPay1() {
		return Pay1;
	}

	public void setPay1(String pay1) {
		Pay1 = pay1;
	}

	public String getPay2() {
		return Pay2;
	}

	public void setPay2(String pay2) {
		Pay2 = pay2;
	}

	public String getPay3() {
		return Pay3;
	}

	public void setPay3(String pay3) {
		Pay3 = pay3;
	}

	public String getDebt() {
		return Debt;
	}

	public void setDebt(String debt) {
		Debt = debt;
	}

	private String DateEnd;
	private String Numberofsession;
	private String Tuitionfull;
	private String Pay1;
	private String Pay2;
	private String Pay3;
	private String Debt;

	private String studentName;
	public static String Studentid;

	/* nhận đối tượng cửa sổ chính truyền đến */

	public static String getStudentid() {
		return Studentid;
	}

	public static void setStudentid(String studentid) {
		COURSESREGISTER.Studentid = studentid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/* dùng để hiển thị lỗi khi người dùng nhập dữ liệu sai */
	JLabel errorlb;
	JLabel errordetails;
	COURSESCHECK mst;
	Incomemanagement ast;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					COURSESREGISTER frame = new COURSESREGISTER(new COURSESCHECK(), new Incomemanagement());
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
	public COURSESREGISTER(COURSESCHECK co,Incomemanagement ad) {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		/*
		 * nhận đối tượng cửa sổ chính truyền đến để gọi hàm cập nhật dữ liệu giao diện
		 * cửa sổ chính và cập nhật csdl
		 */
		mst = co;
		ast = ad;
		/*
		 * nhận id được truyền đến từ cửa sổ chính để phục vụ việc cập nhật thông tin
		 * vào csdl
		 */

		JLabel lblRegisterCourses = new JLabel("REGISTER COURSES");
		lblRegisterCourses.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegisterCourses.setBounds(163, 11, 235, 25);
		contentPane.add(lblRegisterCourses);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 505, 13);
		contentPane.add(separator);

		JLabel lblCourses = new JLabel("COURSES");
		lblCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCourses.setBounds(81, 108, 146, 25);
		contentPane.add(lblCourses);

		TUITION = new JTextField();
		TUITION.setText("2.000.000");
		TUITION.setEditable(false);
		TUITION.setColumns(10);
		TUITION.setBounds(237, 156, 227, 25);
		contentPane.add(TUITION);
		
		
		JComboBox comboBox_1 = new JComboBox();

		Connection c = Connect.getConnection();
		try {
			PreparedStatement pstm = c.prepareStatement("Select CourseName,CourseID,Tuitionfull from Courses");
			
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				comboBox_1.addItem(rs.getString("CourseName"));
				


			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		comboBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Connection c = Connect.getConnection();
				String coursesName = (String) comboBox_1.getSelectedItem();

				{
					setCourseName(coursesName);
					try {
						PreparedStatement pstm = c.prepareStatement(
								"Select CourseID,DateStart,DateEnd,Numberofsession,Tuitionfull from Courses WHERE CourseName =N'"
										+ coursesName + "'");
						ResultSet rs = pstm.executeQuery();
						while (rs.next()) {
							String CourseID = rs.getString("CourseID");
							setCourseid(CourseID);
							setDateStart(rs.getString("DateStart"));
							setDateEnd(rs.getString("DateEnd"));
							setNumberofsession(rs.getString("Numberofsession"));
							setTuitionfull(rs.getString("Tuitionfull"));
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("N");
					}
					try {
			         	PreparedStatement pstm = c.prepareStatement("Select Tuitionfull from Courses where CourseID ='"+getCourseid()+"'");
						
						ResultSet rs = pstm.executeQuery();
						while (rs.next()) {
							TUITION.setText(rs.getString("Tuitionfull"));
							
					}} catch (Exception ex) {
						// TODO: handle exception
					}

				}
			}
		});
		comboBox_1.setBounds(237, 108, 227, 25);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_1 = new JLabel("NOTE");
		lblNewLabel_1.setBounds(34, 192, 90, 13);
		contentPane.add(lblNewLabel_1);

		JTextArea txtrMtKhi = new JTextArea();
		txtrMtKhi.setText("Một khi đã đăng kí thì bắt buộc phải nộp tiền, hãy chắc chắn với lựa chọn của bạn");
		txtrMtKhi.setBounds(34, 216, 448, 101);
		contentPane.add(txtrMtKhi);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 392, 505, 13);
		contentPane.add(separator_1);

		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String studentid = textField.getText();

					if (studentid.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Không được để trống", "",
								JOptionPane.INFORMATION_MESSAGE);

					} else if (getCourseName() == null) {
						JOptionPane.showMessageDialog(contentPane, "Bạn phải chọn một khóa học mới đăng kí được", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						try {
							Connection c = Connect.getConnection();
							PreparedStatement pst = c.prepareStatement(
									"Select Studentname from StudentAcc WHERE Studentid ='" + studentid + "'");
							ResultSet rs = pst.executeQuery();
							while (rs.next()) {
								String studentname = rs.getString("Studentname");
								setStudentName(studentname);
							}

						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
						if (studentName == null) {
							JOptionPane.showMessageDialog(contentPane, "Student id không tồn tại", "",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String sql1 = "INSERT INTO Studentcourses(Studentid,StudentName,CourseID,CourseName,DateStart,DateEnd,Numberofsession,Tuitionfull,Pay1,Pay2,Pay3,Debt) VALUES ('"
									+ studentid + "',N'" + studentName + "','" + courseid + "',N'" + courseName + "','"
									+ DateStart + "','" + DateEnd + "'," + Numberofsession + "," + Tuitionfull + ","
									+ Pay1 + ","+Pay2+","+Pay3+","+Tuitionfull+")";

//						pstm1.setString(1, StudentID);
//						pstm1.setString(2, coursesID);
//						pstm1.setString(3, coursesName);
							mst.StudentID(studentid);
							
							// cập nhật vào csld
							mst.stm.executeUpdate(sql1);
							

//						pstm1.executeUpdate();
							// cập nhật giao diện cửa sổ chính
							mst.reload();
							mst.model.fireTableDataChanged();
							ast.reload();
							ast.model.fireTableDataChanged();
							JOptionPane.showMessageDialog(contentPane, "Đăng kí khóa học thành công", "",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}

				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane, "Bạn đã đăng kí khóa học này rồi", "",
							JOptionPane.WARNING_MESSAGE);

				}

			}

		});
		btnNewButton.setBounds(106, 416, 120, 33);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(277, 416, 120, 33);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("STUDENT ID");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2.setBounds(81, 59, 126, 25);
		contentPane.add(lblNewLabel_1_1_1_2);

		textField = new JTextField();

		textField.setColumns(10);
		textField.setBounds(237, 59, 227, 25);
		contentPane.add(textField);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("TUITION ");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1.setBounds(81, 156, 126, 25);
		contentPane.add(lblNewLabel_1_1_1_2_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("TÔI CAM KẾT SẼ NỘP TIỀN KHI ĐÃ ĐĂNG KÍ");
		rdbtnNewRadioButton.setBounds(139, 340, 282, 34);
		contentPane.add(rdbtnNewRadioButton);

	}

	public void StudentID(String id) {
		setStudentid(id);
		textField.setText(getStudentid());
		textField.setEditable(false);
	}
}