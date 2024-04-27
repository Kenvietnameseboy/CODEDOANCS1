package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import ConnectDatabase.Connect;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class COURSESCHECK extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Findcourse;
	
	public static String StudentID;

	public static String getStudentID() {
		return StudentID;
	}

	public void setStudentID(String studentID) {
		COURSESCHECK.StudentID = studentID;
	}

	JScrollPane scrollPane = new JScrollPane();

	/* tạo bảng để chứa thông tin truy vấn từ csdl */
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	
	Vector vData2 = new Vector();
	Vector vTitle2 = new Vector(); 

	DefaultTableModel model;
	JTable tb = new JTable();
	

	

	Connection c;
	Statement stm;
	ResultSet rst;

	/* vị trí hàng đã chọn ở bảng dữ liệu */
	int selectedrow = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					COURSESCHECK frame = new COURSESCHECK();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param id 
	 */
	public COURSESCHECK() {
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1211, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JLabel lblCourses = new JLabel("YOUR COURSES");
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCourses.setBounds(482, 11, 235, 25);
		contentPane.add(lblCourses);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 1175, 19);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(56, 486, 1110, 19);
		contentPane.add(separator_1);

		Findcourse = new JTextField();
		Findcourse.setBounds(806, 528, 151, 20);
		contentPane.add(Findcourse);
		Findcourse.setColumns(10);
		
		
		

		JButton btnFindCourses = new JButton("FIND COURSES");
		btnFindCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					String CourseID = Findcourse.getText();
					Connection c = Connect.getConnection();
					
					PreparedStatement pst  = c.prepareStatement( "Select * from Studentcourses where CourseID = '" + CourseID + "'and StudentID = '"+StudentID+"'");
					ResultSet rs = pst.executeQuery();
					ResultSetMetaData rstmeta = rs.getMetaData();
					int num_column = rstmeta.getColumnCount();
					/*
					 * Chuẩn bị dữ liệu để tạo bảng (JTable) Tạo các tên cột cho bảng
					 */
					vTitle2 = new Vector(num_column);
					for (int i = 1; i <= num_column; i++) {
						vTitle2.add(rstmeta.getColumnLabel(i));
					}

					/*
					 * Tạo dữ liệu các hàng cho bảng: mỗi phần tử của Vector vData là một Vector
					 */
					vData2 = new Vector(10, 10);
					while (rs.next()) {
						Vector row = new Vector(num_column);
						for (int i = 1; i <= num_column; i++)
							row.add(rs.getString(i));
						vData2.add(row);
					}
				
					rs.close();

				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				/* tạo bảng hiển thị thông tin lên cửa sổ */
				DefaultTableModel model2 = new DefaultTableModel(vData2,vTitle2);
				
				JTable tb2 = new JTable(model2);
				tb2.setEnabled(false);
				
				
				/* tạo bảng để chứa thông tin truy vấn từ csdl */
				JScrollPane tableResult = new JScrollPane(tb2);
				/* tạo cửa sổ chứa bảng dữ liệu */
				JFrame f = new JFrame();
				f.setSize(1000, 200);
				f.setContentPane(tableResult);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.show();
				
			}
		});
		btnFindCourses.setBounds(651, 516, 145, 45);
		contentPane.add(btnFindCourses);

		
		JButton btnTuitionPay = new JButton("TUITION PAYMENT");
		btnTuitionPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnlinePayment cpt = new OnlinePayment(StudentID);
				cpt.setVisible(true);
			}
		});
		btnTuitionPay.setBounds(496, 516, 145, 45);
		contentPane.add(btnTuitionPay);

		try {
			Connection c = Connect.getConnection();

			stm = c.createStatement();
			/*
			 * Nạp dữ liệu vào 2 Vector: vTitle (tên cột) và vData (các hàng dữ liệu) chuẩn
			 * bị tạo JTable
			 */
			reload();

			/* tạo bảng hiển thị thông tin lên cửa sổ */
			model = new DefaultTableModel(vData, vTitle);

			tb = new JTable(model);
			tb.setEnabled(false);

			/* Gắng ống nghe khi ấn chuột chọn hàng */
			tb.addMouseListener(this);

			/* tạo đối tượng JScrollPane để chứa bảng */
			scrollPane = new JScrollPane(tb);

			/* Đặt bảng ở vùng trên của cửa sổ */
			scrollPane.setBounds(10, 64, 1175, 411);
			contentPane.add(scrollPane);

			JLabel lblNewLabel = new JLabel("(Enter CourseID)");
			lblNewLabel.setBounds(967, 531, 93, 14);
			contentPane.add(lblNewLabel);
			
			JButton F5 = new JButton("REFRESH");
			F5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reload();
					model.fireTableDataChanged();
				}
			});
			F5.setBounds(242, 527, 89, 23);
			contentPane.add(F5);

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	/*
	 * Đọc dữ liệu từ bảng students và điền dữ liệu vào 2 Vector: vTitle và vData để
	 * chuẩn bị xây dựng đối tượng JTable
	 */
	public void reload() {
		try {

			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();

			/* truy vấn dữ liệu từ bảng students */

			ResultSet rst = stm.executeQuery("Select * from Studentcourses where StudentID = '"+getStudentID()+"'");
			/* Tạo đối tượng rstmeta để lấy thông tin của ResultSet */
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			/*
			 * Chuẩn bị dữ liệu để tạo bảng (JTable) Tạo các tên cột cho bảng
			 */
			for (int i = 1; i <= num_column; i++) {
				vTitle.add(rstmeta.getColumnLabel(i));
			}

			/*
			 * Tạo dữ liệu các hàng cho bảng: mỗi phần tử của Vector vData là một Vector
			 */
			while (rst.next()) {
				Vector row = new Vector(num_column);
				for (int i = 1; i <= num_column; i++)
					row.add(rst.getString(i));
				vData.add(row);
			}
			rst.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		COURSESREGISTER coreg = new COURSESREGISTER(this, new Incomemanagement());
		JButton btnNewButton = new JButton("REGISTER COURSES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coreg.StudentID(StudentID);
				coreg.setVisible(true);
			}
		});
		btnNewButton.setBounds(341, 516, 145, 45);
		contentPane.add(btnNewButton);

	}

//		scrollPane.setViewportView(new JTable(vData,vTitle));
	/* Xử lý khi người dùng ấn các nút trên cửa số */
//	public void actionPerformed(ActionEvent e)
//	{
//		COURSESREGISTER coreg = new COURSESREGISTER();
//		//xử lý khi ấn nút Insert
//		if(e.getActionCommand().equals("REGISTER COURSES"))
//		{
	// Tạo cửa sổ để nhập mới
//	     COURSESREGISTER coreg = COURSESREGISTER(this);
//	     coreg.setVisible(true);
//			
//			coreg.setVisible(true);
//		 }
	// xử lý khi ấn nút Edit
//		if(e.getActionCommand().equals("Edit"))
//		{
	// Lấy nội dung hàng đã chọn
//		Vector st = (Vector)vData.elementAt(selectedrow);
	// Tạo cửa sổ để hiệu chỉnh hàng đã chọn
//		new coursesregis("Editform",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4));
//		}
//		}

	/*
	 * Lấy vị trí hàng ở bảng JTable khi người dùng ấn chuột
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedrow = tb.getSelectedRow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void StudentID(String id) {
		setStudentID(id);
	}
}
