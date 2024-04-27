package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDatabase.Connect;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rental extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Double Sum1month;
	
	public static Double getSum1month() {
		return Sum1month;
	}

	public static void setSum1month(Double sum1month) {
		Sum1month = sum1month;
	}

	Connection c;
	ResultSet rs;
	Statement stm;
	
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	
	JTable tb;
	DefaultTableModel model;
	JScrollPane scrollPane;
	
	int selectedrow = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rental frame = new Rental();
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
	public Rental() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		
		JLabel lblRentalSpaceManagement = new JLabel("Rental Space Management Table");
		lblRentalSpaceManagement.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRentalSpaceManagement.setBounds(182, 11, 611, 65);
		contentPane.add(lblRentalSpaceManagement);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 87, 759, 29);
		contentPane.add(separator);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 407, 759, 29);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateRentalForm urf = new UpdateRentalForm("Insert form", Rental.this, "", "", "","","", "");
				urf.setVisible(true);
			
			}
		});
		btnNewButton.setBounds(175, 435, 116, 39);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector st = (Vector) vData.elementAt(selectedrow);
				UpdateRentalForm urf = new UpdateRentalForm("Edit form", Rental.this, (String) st.elementAt(0),
						(String) st.elementAt(1), (String) st.elementAt(2), (String) st.elementAt(3),
						(String) st.elementAt(4), (String) st.elementAt(5));
				urf.setVisible(true);
			}
		});
		btnEdit.setBounds(323, 435, 116, 39);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(476, 435, 116, 39);
		contentPane.add(btnDelete);
		
		Connection c = Connect.getConnection();
		try {
			stm = c.createStatement();
			reload();
			model = new DefaultTableModel(vData,vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			scrollPane = new JScrollPane(tb);
			scrollPane.setBounds(10, 108, 759, 275);
			contentPane.add(scrollPane);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		try {
			PreparedStatement pst = c.prepareStatement("Select Sum(MonthlyRent) as [Tổng tiền thuê mặt bằng trong năm] from RentalSpace");
			rs = pst.executeQuery();
			while(rs.next()) {
				Sum1month = Double.parseDouble(rs.getString("Tổng tiền thuê mặt bằng trong năm"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void  reload() {
		try {
			vData.clear();
			vTitle.clear();
			rs = stm.executeQuery("Select * from RentalSpace");
			ResultSetMetaData rsmeta = rs.getMetaData();
			int num_column = rsmeta.getColumnCount();
			
			for (int i =1 ; i<= num_column;i++) {
				vTitle.add(rsmeta.getColumnLabel(i));
			}
			while(rs.next()) {
				Vector row = new Vector(num_column);
				for(int i = 1;i<=num_column;i++) {
					row.add(rs.getString(i));
				}
				vData.add(row);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/* Xóa dữ liệu khi người dùng chọn hàng và ấn nút Delete */
	public void delete() {
		try {
			/* lấy nội dung hàng đã chọn */
			Vector st = (Vector) vData.elementAt(selectedrow);
			/* tạo câu lệnh SQL và xóa dữ liệu khỏi bảng students trong csdl */
			String sql1 = "Delete from RentalSpace where RentalID = '" + st.elementAt(0) + "'";
			
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
