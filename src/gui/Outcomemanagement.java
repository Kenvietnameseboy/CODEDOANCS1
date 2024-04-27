package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Outcomemanagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SAL;
	private JTextField EQUIP;
	private JLabel lblTngTinPhi;
	private JTextField RENT;
	private JLabel lblTngTinThu;
	private JButton btnNewButton;
	private JButton btnEquipmeng;
	private JButton btnRental;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	
	public static Double salary;
	public static Double equipment;
	public static Double rental;
	private static Double Sumall;
	public static Double getSumall() {
		return Sumall;
	}

	public static void setSumall(Double sumall) {
		Sumall = sumall;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		Outcomemanagement.salary = salary;
	}

	public Double getEquipment() {
		return equipment;
	}

	public void setEquipment(Double equipment) {
		Outcomemanagement.equipment = equipment;
	}

	public Double getRental() {
		return rental;
	}

	public void setRental(Double rental) {
		Outcomemanagement.rental = rental;
	}
	
	Human hum;
	Equipment equ;
	Rental renta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Outcomemanagement frame = new Outcomemanagement(new Human(),new Equipment(), new Rental());
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
	public Outcomemanagement(Human hu,Equipment eq,Rental r) {
		hum = hu;
		equ = eq;
		renta  = r;
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		
		SAL = new JTextField();
		SAL.setBounds(10, 105, 86, 20);
		contentPane.add(SAL);
		SAL.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tổng tiền lương trong năm");
		lblNewLabel.setBounds(10, 80, 198, 14);
		contentPane.add(lblNewLabel);
		
		EQUIP = new JTextField();
		EQUIP.setColumns(10);
		EQUIP.setBounds(10, 178, 86, 20);
		contentPane.add(EQUIP);
		
		lblTngTinPhi = new JLabel("Tổng tiền phải trả cho thiết bị");
		lblTngTinPhi.setBounds(10, 153, 198, 14);
		contentPane.add(lblTngTinPhi);
		
		RENT = new JTextField();
		RENT.setColumns(10);
		RENT.setBounds(10, 249, 86, 20);
		contentPane.add(RENT);
		
		lblTngTinThu = new JLabel("Tổng tiền thuê mặt bằng trong năm");
		lblTngTinThu.setBounds(10, 224, 198, 14);
		contentPane.add(lblTngTinThu);
		
		btnNewButton = new JButton("Human");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Human hu = new Human();
				hu.setVisible(true);
			}
		});
		btnNewButton.setBounds(308, 104, 89, 23);
		contentPane.add(btnNewButton);
		
		btnEquipmeng = new JButton("Equipment");
		btnEquipmeng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment eq = new Equipment();
				eq.setVisible(true);
			}
		});
		btnEquipmeng.setBounds(308, 177, 89, 23);
		contentPane.add(btnEquipmeng);
		
		btnRental = new JButton("Rental");
		btnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rental rt = new Rental();
				rt.setVisible(true);
			}
		});
		btnRental.setBounds(308, 248, 89, 23);
		contentPane.add(btnRental);
		
		separator = new JSeparator();
		separator.setBounds(10, 67, 414, 2);
		contentPane.add(separator);
		
		lblNewLabel_1 = new JLabel(" TUITION OUTCOME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(84, 11, 324, 66);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(168, 294, 89, 23);
		contentPane.add(btnNewButton_1);

		String textsal = String.valueOf(hum.getSumLuong1month());
		SAL.setText(textsal);
		SAL.setEditable(false);
		String textequip = String.valueOf(equ.getSumEquipment1month());
		EQUIP.setText(textequip);
		EQUIP.setEditable(false);
		String textrent = String.valueOf(renta.getSum1month());
		RENT.setText(textrent);
		RENT.setEditable(false);
		Sumall =hum.getSumLuong1month() + equ.getSumEquipment1month()+renta.getSum1month();
	}

}
