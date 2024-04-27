package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateStudentForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField name;
	private JTextField phonenumber;
	private JTextField email;
	private JTextField school;
	private JTextField province;
	private static final List<String> validCities = (List<String>) Arrays.asList(
            "Hà Nội",
            "Hồ Chí Minh",
            "Đà Nẵng",
            "Hải Phòng",
            "Cần Thơ",
            "An Giang",
            "Bà Rịa - Vũng Tàu",
            "Bạc Liêu",
            "Bắc Kạn",
            "Bắc Giang",
            "Bắc Ninh",
            "Bến Tre",
            "Bình Dương",
            "Bình Định",
            "Bình Phước",
            "Bình Thuận",
            "Cà Mau",
            "Cao Bằng",
            "Đắk Lắk",
            "Đắk Nông",
            "Điện Biên",
            "Đồng Nai",
            "Đồng Tháp",
            "Gia Lai",
            "Hà Giang",
            "Hà Nam",
            "Hà Tĩnh",
            "Hải Dương",
            "Hậu Giang",
            "Hòa Bình",
            "Hưng Yên",
            "Khánh Hòa",
            "Kiên Giang",
            "Kon Tum",
            "Lai Châu",
            "Lâm Đồng",
            "Lạng Sơn",
            "Lào Cai",
            "Long An",
            "Nam Định",
            "Nghệ An",
            "Ninh Bình",
            "Ninh Thuận",
            "Phú Thọ",
            "Quảng Bình",
            "Quảng Nam",
            "Quảng Ngãi",
            "Quảng Ninh",
            "Quảng Trị",
            "Sóc Trăng",
            "Sơn La",
            "Tây Ninh",
            "Thái Bình",
            "Thái Nguyên",
            "Thanh Hóa",
            "Thừa Thiên Huế",
            "Tiền Giang",
            "Trà Vinh",
            "Tuyên Quang",
            "Vĩnh Long",
            "Vĩnh Phúc",
            "Yên Bái"
    );

	private String studentuser;
	private String studentpassword;
	private String id;
	private String studentname;
	private String studentphone;

	public String getStudentuser() {
		return studentuser;
	}

	public void setStudentuser(String studentuser) {
		this.studentuser = studentuser;
	}

	public String getStudentpassword() {
		return studentpassword;
	}

	public void setStudentpassword(String studentpassword) {
		this.studentpassword = studentpassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStudentphone() {
		return studentphone;
	}

	public void setStudentphone(String studentphone) {
		this.studentphone = studentphone;
	}

	public String getStudentemail() {
		return studentemail;
	}

	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}

	public String getStudentschool() {
		return studentschool;
	}

	public void setStudentschool(String studentschool) {
		this.studentschool = studentschool;
	}

	public String getStudentprovince() {
		return studentprovince;
	}

	public void setStudentprovince(String studentprovince) {
		this.studentprovince = studentprovince;
	}

	private String studentemail;
	private String studentschool;
	private String studentprovince;

	ADMINTABLESTUDENT ass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentForm frame = new UpdateStudentForm(new String(), new ADMINTABLESTUDENT(), new String(),
							new String(), new String(), new String(), new String(), new String(), new String(),
							new String());
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
	public UpdateStudentForm(String s, ADMINTABLESTUDENT as, String studentuser, String studentpassword, String id,
			String studentname, String studentphone, String studentemail, String studentschool,
			String studentprovince) {

		/*
		 * nhận đối tượng cửa sổ chính truyền đến để gọi hàm cập nhật dữ liệu giao diện
		 * cửa sổ chính và cập nhật csdl
		 */
		ass = as;
		
		setStudentuser(studentuser);
		setStudentpassword(studentpassword);
		setId(id);
		setStudentname(studentname);
		setStudentphone(studentphone);
		setStudentemail(studentemail);
		setStudentschool(studentschool);
		setStudentprovince(studentprovince);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 95, 670, 14);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(123, 35, 196, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewPassword = new JLabel("Password :");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewPassword.setBounds(123, 65, 224, 14);
		contentPane.add(lblNewPassword);

		username = new JTextField();
		username.setBounds(327, 34, 271, 20);
		contentPane.add(username);
		username.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(327, 64, 271, 20);
		contentPane.add(password);

		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(33, 120, 145, 25);
		contentPane.add(lblNewLabel_1_1);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(223, 120, 444, 25);
		contentPane.add(name);

		phonenumber = new JTextField();
		phonenumber.setColumns(10);
		phonenumber.setBounds(223, 160, 444, 25);
		contentPane.add(phonenumber);

		JLabel lblNewLabel_1_1_1 = new JLabel("PHONE NUMBER");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(33, 156, 145, 25);
		contentPane.add(lblNewLabel_1_1_1);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(223, 196, 444, 25);
		contentPane.add(email);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("EMAIL ADDRESS");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(33, 192, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("SCHOOL");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_2.setBounds(33, 228, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1_2);

		school = new JTextField();
		school.setColumns(10);
		school.setBounds(223, 232, 444, 25);
		contentPane.add(school);

		province = new JTextField();
		province.setColumns(10);
		province.setBounds(223, 272, 444, 25);
		contentPane.add(province);

		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("PROVINCE");
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_2_1.setBounds(33, 268, 145, 25);
		contentPane.add(lblNewLabel_1_1_1_1_2_1);

		JButton btnUpdate = new JButton("OK");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDB();
			}
		});
		btnUpdate.setBounds(199, 335, 125, 33);
		contentPane.add(btnUpdate);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(363, 335, 125, 33);
		contentPane.add(btnCancel);
		this.setTitle(s);
		if (this.getTitle().equals("Edit form")) {
			username.setText(studentuser);
			password.setText(studentpassword);
			name.setText(studentname);
			phonenumber.setText(studentphone);
			email.setText(studentemail);
			school.setText(studentschool);
			province.setText(studentprovince);
		}
	}

	public void insertDB() {
		try {
			String username1 = username.getText();
			String password1 = password.getText();
			String name1 = name.getText();
			String phonenumber1 = phonenumber.getText();
			if(username1.isEmpty()||password1.isEmpty()||name1.isEmpty()||phonenumber1.isEmpty()) {
				UpdateStudentForm frame = new UpdateStudentForm(new String(), new ADMINTABLESTUDENT(), new String(),
						new String(), new String(), new String(), new String(), new String(), new String(),
						new String());
				JOptionPane.showMessageDialog(frame.contentPane, "Không được để trống", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			validatePhoneNumber(phonenumber1);
			String email1 = email.getText();
			validateEmail(email1);
			String school1 = school.getText();
			String province1 = province.getText();
			validateCity(province1);
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			// Nếu là nhập mới
			if (this.getTitle().equals("Insert form")) {
				String newid = random() + "";
				sql1 = "insert into StudentAcc values ('" + newid + "',N'" + name1 + "','" + phonenumber1 + "','"
						+ email1 + "','" + school1 + "','" + province1 + "')";
				sql2 = "insert into Studentform values ('" + username1 + "','" + password1 + "','" + newid + "')";
			} else // Nếu là hiệu chỉnh
			{

				sql1 = "update StudentAcc set Studentname= N'" + name1 + "', Studentphonenumber= '" + phonenumber1
						+ "', Studentemail= '" + email1 + "',Studentschool='" + school1 + "',Studentprovince='"
						+ province1 + "' where Studentid='" + getId() + "'";
				sql2 = "update Studentform set Username='" + username1 + "',Pass = '" + password1
						+ "' where Studentid='" + getId() + "'";
				sql3 = "update Studentcourses set StudentName= N'" + name1 + "'where StudentID='" + getId() + "'";
			}
			// cập nhật vào csld
			ass.stm.executeUpdate(sql1);
			ass.stm.executeUpdate(sql2);
			ass.stm.executeUpdate(sql3);
			// cập nhật giao diện cửa sổ chính
			ass.reload();
			ass.model.fireTableDataChanged();
			// tắt cửa sổ
			this.dispose();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static int random() {
		int min = 10000;
		int max = 99999;
		int a = (int) (Math.random() * (max - min + 1) + min);
		return a;

	};
	public static void validateEmail(String email) throws Exception {
        
 	   try {
			if (!email.matches( "^(.+)@(\\S+)$")) {
				throw new Exception("Email không hợp lệ");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			UpdateStudentForm frame = new UpdateStudentForm(new String(), new ADMINTABLESTUDENT(), new String(),
					new String(), new String(), new String(), new String(), new String(), new String(),
					new String());
			JOptionPane.showMessageDialog(frame.contentPane, "Email không hợp lệ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
     }
 
 public static void validatePhoneNumber(String phoneNumber) throws Exception {
     try {
			if (!phoneNumber.matches("^[0-9]{10}$")) {
				throw new Exception("Số điện thoại không hợp lệ");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			UpdateStudentForm frame = new UpdateStudentForm(new String(), new ADMINTABLESTUDENT(), new String(),
					new String(), new String(), new String(), new String(), new String(), new String(),
					new String());
			JOptionPane.showMessageDialog(frame.contentPane, "Số đt không hợp lệ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
 }
 public static void validateCity(String cityName) throws Exception {
     try {
			if (!validCities.contains(cityName)) {
				throw new Exception("Tên tỉnh/thành phố không hợp lệ");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			UpdateStudentForm frame = new UpdateStudentForm(new String(), new ADMINTABLESTUDENT(), new String(),
					new String(), new String(), new String(), new String(), new String(), new String(),
					new String());
			JOptionPane.showMessageDialog(frame.contentPane, "Tỉnh không hợp lệ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
 }

	
}
