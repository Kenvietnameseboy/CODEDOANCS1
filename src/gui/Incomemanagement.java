package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDatabase.Connect;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;

public class Incomemanagement extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField Finding;
	private JMenuBar menu = new JMenuBar();
	private JMenu accnew = new JMenu("Account");
	private JMenuItem F5 = new JMenuItem("Refresh");
	
	public static int soluong1;
	public static int soluong2;
	public static int soluong3;
	
	public static int sl1;
	public static int sl2;
	public static int sl3;
	
	public static int getSoluong2() {
		return soluong2;
	}

	public static void setSoluong2(int soluong2) {
		Incomemanagement.soluong2 = soluong2;
	}

	public static int getSoluong3() {
		return soluong3;
	}

	public static void setSoluong3(int soluong3) {
		Incomemanagement.soluong3 = soluong3;
	}

	public static int getSoluong1() {
		return soluong1;
	}

	public static void setSoluong1(int soluong1) {
		Incomemanagement.soluong1 = soluong1;
	}

	private static Double SumIn;
	public static Double getSumIn() {
		return SumIn;
	}

	public static void setSumIn(Double sumIn) {
		SumIn = sumIn;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incomemanagement frame = new Incomemanagement();
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
	public Incomemanagement() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1228, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		
		menu.add(accnew);
		accnew.add(F5);
		this.setJMenuBar(menu);
		F5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					reload();
					model.fireTableDataChanged();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(165, 63, 1047, 54);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("TUITION INCOME MANAGEMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(277, 11, 656, 41);
		contentPane.add(lblNewLabel);
		
	
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Offlinepayment of = new Offlinepayment();
				of.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 63, 140, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("FIND");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Studentid = Finding.getText();
					
					Connection c = Connect.getConnection();

					PreparedStatement pst = c.prepareStatement(
							"Select * from Studentcourses where StudentID='" + Studentid + "'");
					
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
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.show();
			}
		});
		
		btnNewButton_3.setBounds(10, 417, 140, 60);
		contentPane.add(btnNewButton_3);
		
		Finding = new JTextField();
		Finding.setBounds(10, 488, 140, 20);
		contentPane.add(Finding);
		Finding.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("STUDENT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMINTABLESTUDENT ams = new ADMINTABLESTUDENT();
				ams.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 321, 140, 60);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("COURSES");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMINTABLECOURSES amc = new ADMINTABLECOURSES();
				amc.setVisible(true);
			}
		});
		btnNewButton_4_1.setBounds(10, 250, 140, 60);
		contentPane.add(btnNewButton_4_1);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(10, 185, 140, 54);
		contentPane.add(btnDelete);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				COURSESREGISTER coreg = new COURSESREGISTER(new COURSESCHECK(), Incomemanagement.this);
				coreg.setVisible(true);
			}
		});
		btnAdd.setBounds(10, 120, 140, 54);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("(Enter student id)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 392, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		
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

			/* Gắng ống nghe khi ấn chuột chọn hàng */

			scrollPane = new JScrollPane();
			
			scrollPane.setBounds(165, 107, 1047, 304);
			contentPane.add(scrollPane);
			
						tb = new JTable(model);
						scrollPane.setViewportView(tb);
						tb.setFillsViewportHeight(true);
						tb.addMouseListener(this);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Đã thanh toán lần 1", "Đã thanh toán lần 2", "Đã thanh toán lần 3", "Đã thanh toán toàn bộ khóa học", "Còn nợ"}));
			comboBox.setBounds(165, 436, 140, 22);
			comboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String thongke = (String)comboBox.getSelectedItem();
					if(thongke.equals("Đã thanh toán lần 1")) {
						    Incomemanagement.this.dispose();
						    Incomemanagement.this.setVisible(true);
						    try {
								PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Pay1 > 0 and Debt > 0 and Pay2 is NULL and Pay3 is NULL ");
								rst = pst.executeQuery();
								while(rst.next()) {
									textField.setText(rst.getString("So luong"));
									sl1=Integer.parseInt(textField.getText());
									setSoluong1(sl1);
									
								}
								
							} catch (Exception ex) {
								// TODO: handle exception
							}
						    try {
								PreparedStatement pst = c.prepareStatement("Select  Sum(Pay1) as [So tien] from Studentcourses WHERE Pay1 > 0 and Debt > 0 and Pay2 is NULL and Pay3 is NULL ");
								rst = pst.executeQuery();
								while(rst.next()) {
									textField_1.setText(rst.getString("So tien"));
								}
								
							} catch (Exception ex) {
								// TODO: handle exception
							}
						    try {
								PreparedStatement pst = c.prepareStatement("Select  Sum(Debt) as [So tien no] from Studentcourses WHERE Pay1 > 0 and Debt > 0 and Pay2 is NULL and Pay3 is NULL ");
								rst = pst.executeQuery();
								while(rst.next()) {
									textField_2.setText(rst.getString("So tien no"));
								}
								
							} catch (Exception ex) {
								// TODO: handle exception
							}
							reload1();
					}
					if(thongke.equals("Đã thanh toán lần 2")) {
						Incomemanagement.this.dispose();
					    Incomemanagement.this.setVisible(true);
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt > 0 and Pay2 > 0 and Pay3 is NULL  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
								sl2=Integer.parseInt(textField.getText());
								setSoluong2(sl2);
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Pay1+Pay2) as [So tien] from Studentcourses WHERE Debt > 0 and Pay2 > 0 and Pay3 is NULL  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_1.setText(rst.getString("So tien"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Debt) as [So tien no] from Studentcourses WHERE Debt > 0 and Pay2 > 0 and Pay3 is NULL  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_2.setText(rst.getString("So tien no"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
						reload2();
				}
					if(thongke.equals("Đã thanh toán lần 3")) {
						Incomemanagement.this.dispose();
					    Incomemanagement.this.setVisible(true);
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt > 0  and Pay3 >0   ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
								sl3=Integer.parseInt(textField.getText());
								setSoluong3(sl3);
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Pay1+Pay2+Pay3) as [So tien] from Studentcourses WHERE Debt > 0  and Pay3 >0   ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_1.setText(rst.getString("So tien"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Debt) as [So tien no] from Studentcourses WHERE Debt > 0  and Pay3 >0  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_2.setText(rst.getString("So tien no"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
						reload3();
				}
					if(thongke.equals("Đã thanh toán toàn bộ khóa học")) {
						Incomemanagement.this.dispose();
					    Incomemanagement.this.setVisible(true);
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt = 0    ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Pay1+Pay2+Pay3) as [So tien] from Studentcourses WHERE Debt = 0    ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_1.setText(rst.getString("So tien"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Debt) as [So tien no] from Studentcourses WHERE Debt = 0   ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_2.setText(rst.getString("So tien no"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
						reload4();
				}
					if(thongke.equals("Còn nợ")) {
						Incomemanagement.this.dispose();
					    Incomemanagement.this.setVisible(true);
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt>0   ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(ISNULL(Pay1, 0) + ISNULL(Pay2, 0) + ISNULL(Pay3, 0)) as [So tien] from Studentcourses WHERE Debt>0");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_1.setText(rst.getString("So tien"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					    try {
							PreparedStatement pst = c.prepareStatement("Select  Sum(Debt) as [So tien no] from Studentcourses WHERE Debt>0  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField_2.setText(rst.getString("So tien no"));
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
						reload5();
				}
				}
			});
			contentPane.add(comboBox);
			
			JLabel lblNewLabel_1_2 = new JLabel("(Thống kê)");
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1_2.setBounds(165, 417, 97, 14);
			contentPane.add(lblNewLabel_1_2);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(315, 437, 140, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(465, 437, 140, 20);
			contentPane.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(615, 437, 140, 20);
			contentPane.add(textField_2);
			
			JButton btnNewButton_3_1 = new JButton("IN BIÊN LAI");
			btnNewButton_3_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Bienlai bl = new Bienlai();
					bl.setVisible(true);
				}
			});
			btnNewButton_3_1.setBounds(1065, 417, 140, 60);
			contentPane.add(btnNewButton_3_1);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("(Số lượng học sinh)");
			lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1_2_1.setBounds(315, 417, 140, 14);
			contentPane.add(lblNewLabel_1_2_1);
			
			JLabel lblNewLabel_1_2_1_1 = new JLabel("(Tổng tiền đã thanh toán)");
			lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1_2_1_1.setBounds(465, 417, 140, 14);
			contentPane.add(lblNewLabel_1_2_1_1);
			
			JLabel lblNewLabel_1_2_1_1_1 = new JLabel("(Tổng tiền Còn nợ)");
			lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1_2_1_1_1.setBounds(615, 417, 97, 14);
			contentPane.add(lblNewLabel_1_2_1_1_1);
			
			JButton btnNewButton_1 = new JButton("IN BIỂU ĐỒ");
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Pay1 > 0 and Debt > 0 and Pay2 is NULL and Pay3 is NULL ");
						rst = pst.executeQuery();
						while(rst.next()) {
							textField.setText(rst.getString("So luong"));
							sl1=Integer.parseInt(textField.getText());
							setSoluong1(sl1);
							
						}
						
					} catch (Exception ex) {
						// TODO: handle exception
					}
					
					 try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt > 0 and Pay2 > 0 and Pay3 is NULL  ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
								sl2=Integer.parseInt(textField.getText());
								setSoluong2(sl2);
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					 
					 try {
							PreparedStatement pst = c.prepareStatement("Select  Count(DISTINCT StudentID) as [So luong] from Studentcourses WHERE Debt > 0  and Pay3 >0   ");
							rst = pst.executeQuery();
							while(rst.next()) {
								textField.setText(rst.getString("So luong"));
								sl3=Integer.parseInt(textField.getText());
								setSoluong3(sl3);
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
						}
					
					
					new TestBarChart().createDataset(getSoluong1(),getSoluong2(),getSoluong3());
					
				}
			});
			btnNewButton_1.setBounds(765, 436, 116, 23);
			contentPane.add(btnNewButton_1);

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			PreparedStatement pst = c.prepareStatement("Select Sum(Tuitionfull) as Sumallincome from Studentcourses where Pay1 is not null and Debt = 0");
			rst = pst.executeQuery();
			while(rst.next()) {
				SumIn = Double.parseDouble(rst.getString("Sumallincome"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	
	/* Xóa dữ liệu khi người dùng chọn hàng và ấn nút Delete */
	public void delete() {
		try {
			/* lấy nội dung hàng đã chọn */
			Vector st = (Vector) vData.elementAt(selectedrow);
			/* tạo câu lệnh SQL và xóa dữ liệu khỏi bảng students trong csdl */
			String sql1 = "Delete from Studentcourses where Studentid = '" + st.elementAt(0) + "' and CourseID = '"+st.elementAt(2)+"'";
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
	
	public void reload() {
		/* truy vấn dữ liệu từ bảng students */

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"select * from Studentcourses");
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
	public void reload1() {

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select * from Studentcourses WHERE Pay1 > 0 and Debt > 0 and Pay2 is NULL and Pay3 is NULL ");
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
			
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("N");
	}
	
	}
	public void reload2() {

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select * from Studentcourses WHERE Debt > 0 and Pay2 > 0 and Pay3 is NULL ");
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
			
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("N");
	}}
	public void reload3() {

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select * from Studentcourses WHERE Debt > 0  and Pay3 >0 ");
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
			
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("N");
	}}
	public void reload4() {

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select * from Studentcourses WHERE Debt = 0 ");
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
			
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("N");
	}}
	public void reload5() {

		try {
			/* Xóa hết dữ liệu hiện có trong 2 vector */
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"Select * from Studentcourses WHERE Debt>0");
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
			
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("N");
	}}
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
