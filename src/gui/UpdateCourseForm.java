package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDatabase.Connect;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class UpdateCourseForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JTextField NAME;
	private JTextField START;
	private JTextField END;
	private JTextField NUM;
	private JTextField PER;
	private JTextField FULL;

	public static String Courseid;

	public static String getCoursename() {
		return Coursename;
	}

	public static void setCoursename(String coursename) {
		Coursename = coursename;
	}

	public static String getDatestart() {
		return Datestart;
	}

	public static void setDatestart(String datestart) {
		Datestart = datestart;
	}

	public static String getDateend() {
		return Dateend;
	}

	public static void setDateend(String dateend) {
		Dateend = dateend;
	}

	public static String getNumber() {
		return Number;
	}

	public static void setNumber(String number) {
		Number = number;
	}

	public static String getTuitionper() {
		return Tuitionper;
	}

	public static void setTuitionper(String tuitionper) {
		Tuitionper = tuitionper;
	}

	public static String getTuitionfull() {
		return Tuitionfull;
	}

	public static void setTuitionfull(String tuitionfull) {
		Tuitionfull = tuitionfull;
	}

	public static String Coursename;
	public static String Datestart;
	public static String Dateend;
	public static String Number;
	public static String Tuitionper;
	public static String Tuitionfull;

	public static String getCourseid() {
		return Courseid;
	}

	public static void setCourseid(String courseid) {
		Courseid = courseid;
	}

	/* nhận đối tượng cửa sổ chính truyền đến */
	ADMINTABLECOURSES acc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourseForm frame = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
							new String(), new String(), new String(), new String(), new String(), new String());
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
	public UpdateCourseForm(String s, ADMINTABLECOURSES ac, String i, String na, String datestart, String dateend,
			String num, String tuiper, String tuifull) {
		/*
		 * nhận đối tượng cửa sổ chính truyền đến để gọi hàm cập nhật dữ liệu giao diện
		 * cửa sổ chính và cập nhật csdl
		 */
		acc = ac;

		/*
		 * nhận id được truyền đến từ cửa sổ chính để phục vụ việc cập nhật thông tin
		 * vào csdl
		 */
		setCourseid(i);

		setCoursename(na);
		setDatestart(datestart);
		setDateend(dateend);
		setNumber(num);
		setTuitionper(tuiper);
		setTuitionfull(tuifull);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel = new JLabel(" COURSE ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 13, 180, 37);
		contentPane.add(lblNewLabel);

		ID = new JTextField();
		ID.setBounds(232, 19, 246, 29);
		contentPane.add(ID);
		ID.setColumns(10);

		NAME = new JTextField();
		NAME.setColumns(10);
		NAME.setBounds(232, 59, 246, 29);
		contentPane.add(NAME);

		START = new JTextField();
		START.setColumns(10);
		START.setBounds(232, 99, 246, 29);
		contentPane.add(START);

		END = new JTextField();
		END.setColumns(10);
		END.setBounds(232, 139, 246, 29);
		contentPane.add(END);

		NUM = new JTextField();
		NUM.setColumns(10);
		NUM.setBounds(232, 179, 246, 29);
		contentPane.add(NUM);

		PER = new JTextField();
		PER.setColumns(10);
		PER.setBounds(232, 221, 246, 29);
		contentPane.add(PER);

		FULL = new JTextField();
		FULL.setColumns(10);
		FULL.setBounds(232, 261, 246, 29);
		contentPane.add(FULL);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDB();
			}
		});
		btnNewButton.setBounds(163, 315, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblCoursename = new JLabel("COURSE NAME:");
		lblCoursename.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCoursename.setBounds(10, 53, 180, 37);
		contentPane.add(lblCoursename);

		JLabel lblDate = new JLabel("DATE START:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(10, 93, 180, 37);
		contentPane.add(lblDate);

		JLabel lblDateend = new JLabel("DATE END:");
		lblDateend.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDateend.setBounds(10, 133, 180, 37);
		contentPane.add(lblDateend);

		JLabel lblNumberOfSession = new JLabel("NUMBER OF SESSION");
		lblNumberOfSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumberOfSession.setBounds(10, 173, 180, 37);
		contentPane.add(lblNumberOfSession);

		JLabel lblNewLabel_4_1 = new JLabel("TUITION PER SESSION");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(10, 215, 180, 37);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("TUITION FULL");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(10, 255, 180, 37);
		contentPane.add(lblNewLabel_4_1_1);
		this.setTitle(s);
		if (this.getTitle().equals("Editform")) {
			ID.setText(getCourseid());
			NAME.setText(getCoursename());
			START.setText(getDatestart());
			END.setText(getDateend());
			NUM.setText(getNumber());
			PER.setText(getTuitionper());
			FULL.setText(getTuitionfull());
			FULL.setEditable(false);
		}
	}

	public void insertDB() {

		try {
			// Lấy nội dung đã nhập ở giao diện

			String na = NAME.getText();
			if (na.isEmpty()) {
				UpdateCourseForm cf = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
						new String(), new String(), new String(), new String(), new String(), new String());
				JOptionPane.showMessageDialog(cf.contentPane, "Không được để trống", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			String datestart = START.getText();
			validateDateInPast(datestart);
			String dateend = END.getText();
			validateDateInPast(dateend);
			int num = Integer.parseInt(NUM.getText());
			validateSession(num);
			double per = Double.parseDouble(PER.getText());
			validateAmount(per);
			double full = per * num;
			FULL.setText(String.valueOf(full));
			String sql = "";
			String sql2 = "";
			// Nếu là nhập mới
			if (this.getTitle().equals("Insert form")) {
				String newid = ID.getText();
				if(newid.isEmpty()) {
					UpdateCourseForm cf = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
							new String(), new String(), new String(), new String(), new String(), new String());
					JOptionPane.showMessageDialog(cf.contentPane, "Không được để trống", "",
							JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				sql = "insert into Courses values ('" + newid + "',N'" + na + "','" + datestart + "','" + dateend + "',"
						+ num + "," + per + "," + full + ")";
			} else // Nếu là hiệu chỉnh
			{

				sql = "update Courses set CourseName= N'" + na + "', DateStart= '" + datestart + "', DateEnd= '"
						+ dateend + "',Numberofsession=" + num + ",Tuitionpersession=" + per + ",Tuitionfull=" + full
						+ "  where CourseID='" + Courseid + "'";
				sql2 = "update Studentcourses set CourseName= N'" + na + "', DateStart= '" + datestart + "', DateEnd= '"
						+ dateend + "',Numberofsession=" + num + ",Tuitionfull=" + full + "  where CourseID='"
						+ Courseid + "'";
			}
			// cập nhật vào csld
			acc.stm.executeUpdate(sql);
			acc.stm.executeUpdate(sql2);
			// cập nhật giao diện cửa sổ chính
			acc.reload();
			acc.model.fireTableDataChanged();
			// tắt cửa sổ
			this.dispose();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void validateDateInPast(String dateString) throws Exception {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate date = LocalDate.parse(dateString, formatter);
			LocalDate currentDate = LocalDate.now();
			if (date.isBefore(currentDate)) {
				throw new Exception("Không được đặt là ngày trong quá khứ");

			}
		} catch (Exception e) {
			// TODO: handle exception
			UpdateCourseForm cf = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
					new String(), new String(), new String(), new String(), new String(), new String());
			JOptionPane.showMessageDialog(cf.contentPane, "Không được đặt là ngày trong quá khứ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}

	}
	public static void validateSession(int sessionCount) throws Exception {
        try {
			if (sessionCount < 30 || sessionCount > 150) {
				throw new Exception("Số buổi học không hợp lệ");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			UpdateCourseForm cf = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
					new String(), new String(), new String(), new String(), new String(), new String());
			JOptionPane.showMessageDialog(cf.contentPane, "Không được đặt số buổi học dưới 30 và lớn hơn 150", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
    }
	  public static void validateAmount(double amount) throws Exception {
	        try {
				if (amount < 20000 || amount > 100000) {
					throw new Exception("Số tiền không hợp lệ");
				} 
			} catch (Exception e) {
				// TODO: handle exception
				UpdateCourseForm cf = new UpdateCourseForm(new String(), new ADMINTABLECOURSES(), new String(),
						new String(), new String(), new String(), new String(), new String(), new String());
				JOptionPane.showMessageDialog(cf.contentPane, "Không được đặt số tiền/ buổi học dưới 20000 và lớn hơn 100 nghìn", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
	    }
}
