package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDatabase.Connect;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Human extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Double SumLuong1month;
	
	public static Double getSumLuong1month() {
		return SumLuong1month;
	}

	public static void setSumLuong1month(Double sumLuong1month) {
		SumLuong1month = sumLuong1month;
	}

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
					Human frame = new Human();
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
	public Human() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 109, 759, 29);
		contentPane.add(separator);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel = new JLabel("Human Resources Management Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(150, 33, 534, 65);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateHumanForm uhf = new UpdateHumanForm("Insert form", Human.this, "", "", "", "", "", "", "");
				uhf.setVisible(true);
			}
		});
		btnNewButton.setBounds(177, 457, 116, 39);
		contentPane.add(btnNewButton);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector st = (Vector) vData.elementAt(selectedrow);
				UpdateHumanForm uhf = new UpdateHumanForm("Edit form", Human.this, (String) st.elementAt(0),
						(String) st.elementAt(1), (String) st.elementAt(2), (String) st.elementAt(3),
						(String) st.elementAt(4), (String) st.elementAt(5), (String) st.elementAt(6));
				uhf.setVisible(true);
			}
		});
		btnEdit.setBounds(325, 457, 116, 39);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(478, 457, 116, 39);
		contentPane.add(btnDelete);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 432, 759, 29);
		contentPane.add(separator_1);

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
			scrollPane.setBounds(10, 129, 759, 275);
			contentPane.add(scrollPane);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			PreparedStatement pst = c.prepareStatement("Select Sum(Salary)as [Tổng tiền lương trong năm] from HumanResourcesManagement");
			rst = pst.executeQuery();
			while(rst.next()) {
				SumLuong1month = Double.parseDouble(rst.getString("Tổng tiền lương trong năm"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void reload() {

		/* truy vấn dữ liệu từ bảng students */

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery("Select * from HumanResourcesManagement ");
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
	
	/* Xóa dữ liệu khi người dùng chọn hàng và ấn nút Delete */
	public void delete() {
		try {
			/* lấy nội dung hàng đã chọn */
			Vector st = (Vector) vData.elementAt(selectedrow);
			/* tạo câu lệnh SQL và xóa dữ liệu khỏi bảng students trong csdl */
			String sql1 = "Delete from HumanResourcesManagement where EmployeeID = '" + st.elementAt(0) + "'";
			
			stm.executeUpdate(sql1);
			
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
