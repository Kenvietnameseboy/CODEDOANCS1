package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Bienlaiduocin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDiachi;
	private JTextField textSodienthoai;
	private JTextField textEmail;
	private JTextField textSotien;
	private JTextField textNguoinop;
	private JTextField textNguoithu;
	private JTextField textso;
	private JTextField textHoten;
	private JTextField textquyen;
	private JTextField textKhoahoc;
	private JTextField textID;
	public static String Quyen;
	public static String So;
	public static String ID;
	public static String Hovaten;
	public static String Diachi;
	public static String Sodienthoai;
	public static String Email;
	public static String Khoahoc;
	public static String Sotien;
	public static String Nguoinop;
	public static String Nguoithu;

	public static String getQuyen() {
		return Quyen;
	}

	public static void setQuyen(String quyen) {
		Quyen = quyen;
	}

	public static String getSo() {
		return So;
	}

	public static void setSo(String so) {
		So = so;
	}

	public static String getID() {
		return ID;
	}

	public static void setID(String iD) {
		ID = iD;
	}

	public static String getHovaten() {
		return Hovaten;
	}

	public static void setHovaten(String hovaten) {
		Hovaten = hovaten;
	}

	public static String getDiachi() {
		return Diachi;
	}

	public static void setDiachi(String diachi) {
		Diachi = diachi;
	}

	public static String getSodienthoai() {
		return Sodienthoai;
	}

	public static void setSodienthoai(String sodienthoai) {
		Sodienthoai = sodienthoai;
	}

	public static String getEmail() {
		return Email;
	}

	public static void setEmail(String email) {
		Email = email;
	}

	public static String getKhoahoc() {
		return Khoahoc;
	}

	public static void setKhoahoc(String khoahoc) {
		Khoahoc = khoahoc;
	}

	public static String getSotien() {
		return Sotien;
	}

	public static void setSotien(String sotien) {
		Sotien = sotien;
	}

	public static String getNguoinop() {
		return Nguoinop;
	}

	public static void setNguoinop(String nguoinop) {
		Nguoinop = nguoinop;
	}

	public static String getNguoithu() {
		return Nguoithu;
	}

	public static void setNguoithu(String nguoithu) {
		Nguoithu = nguoithu;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienlaiduocin frame = new Bienlaiduocin();
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
	public Bienlaiduocin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 640, 571);
		contentPane.add(contentPane_1);
		
		JLabel lblReciept = new JLabel("BIÊN LAI THU PHÍ");
		lblReciept.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblReciept.setBounds(170, 11, 336, 60);
		contentPane_1.add(lblReciept);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 73, 557, 7);
		contentPane_1.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Đơn vị: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(25, 91, 78, 14);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(25, 113, 78, 14);
		contentPane_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mẫu số  06 - TT");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(419, 91, 106, 14);
		contentPane_1.add(lblNewLabel_1_2);
		
		JTextArea txtrBanHnhTheo = new JTextArea();
		txtrBanHnhTheo.setText("Ban hành theo Thông tư số 133/2016/TT-BTC\r\n ngày 26/8/2016 của Bộ Tài chính");
		txtrBanHnhTheo.setFont(new Font("Monospaced", Font.PLAIN, 10));
		txtrBanHnhTheo.setEditable(false);
		txtrBanHnhTheo.setBounds(346, 112, 256, 35);
		contentPane_1.add(txtrBanHnhTheo);
		
		JLabel lblNewLabel_1_3 = new JLabel("Họ và tên người nộp: ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(63, 229, 149, 27);
		contentPane_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1.setBounds(63, 267, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_1);
		
		textDiachi = new JTextField();
		textDiachi.setEditable(false);
		textDiachi.setColumns(10);
		textDiachi.setBounds(265, 271, 298, 20);
		contentPane_1.add(textDiachi);
		
		textSodienthoai = new JTextField();
		textSodienthoai.setEditable(false);
		textSodienthoai.setColumns(10);
		textSodienthoai.setBounds(265, 309, 298, 20);
		contentPane_1.add(textSodienthoai);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1.setBounds(63, 305, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Gmail:");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1_1.setBounds(63, 343, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_1_1_1);
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setBounds(265, 347, 298, 20);
		contentPane_1.add(textEmail);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Khóa học đã thanh toán:");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_2.setBounds(63, 381, 184, 27);
		contentPane_1.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Số tiền đã thu:");
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_1_1_1_1.setBounds(63, 417, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_1_1_1_1);
		
		textSotien = new JTextField();
		textSotien.setEditable(false);
		textSotien.setColumns(10);
		textSotien.setBounds(265, 421, 298, 20);
		contentPane_1.add(textSotien);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Người nộp tiền");
		lblNewLabel_1_3_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_3.setBounds(123, 455, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Người thu tiền");
		lblNewLabel_1_3_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_3_1.setBounds(376, 455, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_3_1);
		
		textNguoinop = new JTextField();
		textNguoinop.setEditable(false);
		textNguoinop.setColumns(10);
		textNguoinop.setBounds(63, 485, 219, 20);
		contentPane_1.add(textNguoinop);
		
		textNguoithu = new JTextField();
		textNguoithu.setEditable(false);
		textNguoithu.setColumns(10);
		textNguoithu.setBounds(324, 485, 219, 20);
		contentPane_1.add(textNguoithu);
		
		JLabel lblNewLabel_2 = new JLabel("Ngài Kiên");
		lblNewLabel_2.setBounds(123, 91, 196, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ngài Kiên");
		lblNewLabel_2_1.setBounds(123, 114, 196, 14);
		contentPane_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Số: ");
		lblNewLabel_2_2.setBounds(400, 179, 25, 14);
		contentPane_1.add(lblNewLabel_2_2);
		
		textso = new JTextField();
		textso.setEditable(false);
		textso.setColumns(10);
		textso.setBounds(466, 179, 59, 14);
		contentPane_1.add(textso);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("ID:");
		lblNewLabel_1_3_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3_4.setBounds(63, 201, 149, 27);
		contentPane_1.add(lblNewLabel_1_3_4);
		
		textHoten = new JTextField();
		textHoten.setEditable(false);
		textHoten.setColumns(10);
		textHoten.setBounds(265, 233, 298, 20);
		contentPane_1.add(textHoten);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Quyển:");
		lblNewLabel_2_2_1.setBounds(400, 158, 56, 14);
		contentPane_1.add(lblNewLabel_2_2_1);
		
		textquyen = new JTextField();
		textquyen.setEditable(false);
		textquyen.setColumns(10);
		textquyen.setBounds(466, 158, 59, 14);
		contentPane_1.add(textquyen);
		
		textKhoahoc = new JTextField();
		textKhoahoc.setEditable(false);
		textKhoahoc.setColumns(10);
		textKhoahoc.setBounds(265, 385, 298, 20);
		contentPane_1.add(textKhoahoc);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		textID.setBounds(265, 204, 298, 20);
		contentPane_1.add(textID);
	}
	public void Setnew(String newQuyen,String newso,String newID,String newHovaten,String newDiachi,String newsodienthoai,String newemail,String newkhoahoc,String newsotien,String newnguoinop,String newnguoithu) {
		setQuyen(newQuyen);
		setSo(newso);
		setID(newID);
		setHovaten(newHovaten);
		setDiachi(newDiachi);
		setSodienthoai(newsodienthoai);
		setEmail(newemail);
		setKhoahoc(newkhoahoc);
		setSotien(newsotien);
		setNguoinop(newnguoinop);
		setNguoithu(newnguoithu);
		textquyen.setText(Quyen);
		textso.setText(So);
		textID.setText(ID);
		textHoten.setText(Hovaten);
		textDiachi.setText(Diachi);
		textSodienthoai.setText(Sodienthoai);
		textEmail.setText(Email);
		textKhoahoc.setText(Khoahoc);
		textSotien.setText(Sotien);
		textNguoinop.setText(Nguoinop);
		textNguoithu.setText(Nguoithu);
	}
}
