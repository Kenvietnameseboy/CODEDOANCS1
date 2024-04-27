package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectDatabase.Connect;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ADMINTABLESTUDENT extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Findstudent;

	Connection c;
	Statement stm;
	ResultSet rst;

	/* tạo bảng để chứa thông tin truy vấn từ csdl */
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	Vector vData2 = new Vector();
	Vector vTitle2 = new Vector();

	DefaultTableModel model;
	JTable tb = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	/* vị trí hàng đã chọn ở bảng dữ liệu */
	int selectedrow = 0;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMINTABLESTUDENT frame = new ADMINTABLESTUDENT();
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
	public ADMINTABLESTUDENT() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1017, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 11, 1001, 521);
		contentPane.add(contentPane_1);

		JLabel lblCourses = new JLabel("ALL STUDENT");
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCourses.setBounds(427, 11, 235, 25);
		contentPane_1.add(lblCourses);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 981, 19);
		contentPane_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 440, 981, 19);
		contentPane_1.add(separator_1);

		Connection c = Connect.getConnection();
		try {
			stm = c.createStatement();
			/*
			 * Nạp dữ liệu vào 2 Vector: vTitle (tên cột) và vData (các hàng dữ liệu) chuẩn
			 * bị tạo JTable
			 */
			reload();

			/* tạo bảng hiển thị thông tin lên cửa sổ */
			model = new DefaultTableModel(vData, vTitle);

			tb = new JTable(model);
			tb.setFillsViewportHeight(true);

			/* Gắng ống nghe khi ấn chuột chọn hàng */
			tb.addMouseListener(this);

			scrollPane = new JScrollPane(tb);
			scrollPane.setBounds(20, 64, 959, 365);
			contentPane_1.add(scrollPane);

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudentForm usf = new UpdateStudentForm("Insert form", ADMINTABLESTUDENT.this, "", "", "", "", "",
						"", "", "");
				usf.setVisible(true);
			}
		});
		btnNewButton.setBounds(20, 452, 145, 45);
		contentPane_1.add(btnNewButton);

		JButton btnFindCourses = new JButton("FIND STUDENT");
		btnFindCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Studentid = Findstudent.getText();
					Connection c = Connect.getConnection();

					PreparedStatement pst = c.prepareStatement(
							"Select a.Username,a.Pass,b.* from Studentform as a, StudentAcc as b where a.Studentid = '"
									+ Studentid + "' and b.Studentid = '" + Studentid + "'");
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
				DefaultTableModel model2 = new DefaultTableModel(vData2, vTitle2);

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
		btnFindCourses.setBounds(489, 452, 145, 45);
		contentPane_1.add(btnFindCourses);

		JButton btnEdit = new JButton("DELETE");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}

		});
		btnEdit.setBounds(334, 452, 145, 45);
		contentPane_1.add(btnEdit);

		JButton btnEdiy = new JButton("EDIT");
		btnEdiy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector st = (Vector) vData.elementAt(selectedrow);
				// Tạo cửa sổ để hiệu chỉnh hàng đã chọn
				UpdateStudentForm usf = new UpdateStudentForm("Edit form", ADMINTABLESTUDENT.this,
						(String) st.elementAt(0), (String) st.elementAt(1), (String) st.elementAt(2),
						(String) st.elementAt(3), (String) st.elementAt(4), (String) st.elementAt(5),
						(String) st.elementAt(6), (String) st.elementAt(7));

				usf.setVisible(true);
			}
		});
		btnEdiy.setBounds(175, 452, 145, 45);
		contentPane_1.add(btnEdiy);

		Findstudent = new JTextField();
		Findstudent.setColumns(10);
		Findstudent.setBounds(644, 463, 180, 25);
		contentPane_1.add(Findstudent);

		
		
		
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(834, 452, 145, 45);
		contentPane_1.add(btnCancel);

	}

	public void reload() {
		// TODO Auto-generated method stub

		/* truy vấn dữ liệu từ bảng students */

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select a.Username,a.Pass,b.* from Studentform as a, StudentAcc as b where a.Studentid = b.Studentid ");
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
			// TODO: handle exception
			e.getMessage();
		}
	}

	public boolean isNumericID(String id) {
		// Kiểm tra xem chuỗi id có rỗng hay không
		if (id == null || id.length() == 0) {
			return false;
		}

		// Kiểm tra từng ký tự trong chuỗi
		for (int i = 0; i < id.length(); i++) {
			// Kiểm tra xem ký tự tại vị trí i có phải là chữ số hay không
			if (!Character.isDigit(id.charAt(i))) {
				return false;
			}
		}

		// Nếu tất cả các ký tự đều là chữ số
		return true;
	}

	/* Xóa dữ liệu khi người dùng chọn hàng và ấn nút Delete */
	public void delete() {
		try {
			/* lấy nội dung hàng đã chọn */
			Vector st = (Vector) vData.elementAt(selectedrow);
			/* tạo câu lệnh SQL và xóa dữ liệu khỏi bảng students trong csdl */
			String sql1 = "Delete from Studentform where Studentid = '" + st.elementAt(2) + "'";
			String sql2 = "Delete from StudentAcc where Studentid = '" + st.elementAt(2) + "'";
			stm.executeUpdate(sql1);
			stm.executeUpdate(sql2);
			/* Xóa nội dung hàng tương ứng trong vData */
			vData.remove(selectedrow);
			/* Cập nhật lại nội dung bảng hiển thị trên màn hình */
			reload();
			model.fireTableDataChanged();
		} catch (Exception e) {

			System.out.println("Phai sua cai nay");
		}
	}

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
}
