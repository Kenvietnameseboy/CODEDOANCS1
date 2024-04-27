package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class UpdateEquipForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JTextField Name;
	private JTextField Type;
	private JTextField Qu;
	private JTextField De;
	private JTextField Price;

	Equipment equip;
	private String takeid;

	public String getTakeid() {
		return takeid;
	}

	public void setTakeid(String takeid) {
		this.takeid = takeid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEquipForm frame = new UpdateEquipForm(new String(), new Equipment(), new String(),
							new String(), new String(), new String(), new String(), new String());
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
	public UpdateEquipForm(String s, Equipment e, String id, String na, String ty, String qu, String de, String pr) {

		equip = e;
		setTakeid(id);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel_1 = new JLabel("DeviceID: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(51, 69, 135, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblUpdateEquipmentAnd = new JLabel("UPDATE EQUIPMENT AND SOFTWARE");
		lblUpdateEquipmentAnd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUpdateEquipmentAnd.setBounds(77, 24, 321, 24);
		contentPane.add(lblUpdateEquipmentAnd);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 418, 7);
		contentPane.add(separator);

		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(230, 69, 175, 20);
		contentPane.add(ID);

		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(230, 117, 175, 20);
		contentPane.add(Name);

		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(51, 117, 135, 20);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Type:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_2.setBounds(51, 176, 135, 20);
		contentPane.add(lblNewLabel_1_2);

		Type = new JTextField();
		Type.setColumns(10);
		Type.setBounds(230, 176, 175, 20);
		contentPane.add(Type);

		Qu = new JTextField();
		Qu.setColumns(10);
		Qu.setBounds(230, 232, 175, 20);
		contentPane.add(Qu);

		JLabel lblNewLabel_1_3 = new JLabel("Quantity:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_3.setBounds(51, 232, 135, 20);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Department:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_4.setBounds(51, 292, 135, 20);
		contentPane.add(lblNewLabel_1_4);

		De = new JTextField();
		De.setColumns(10);
		De.setBounds(230, 292, 175, 20);
		contentPane.add(De);

		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(230, 347, 175, 20);
		contentPane.add(Price);

		JLabel lblNewLabel_1_5 = new JLabel("Price:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_5.setBounds(51, 347, 135, 20);
		contentPane.add(lblNewLabel_1_5);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 378, 418, 7);
		contentPane.add(separator_1);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDB();
			}
		});
		btnNewButton.setBounds(160, 396, 114, 33);
		contentPane.add(btnNewButton);
		this.setTitle(s);
		if(this.getTitle().equals("Edit form")) {
			ID.setText(id);
			ID.setEditable(false);
			Name.setText(na);
			Type.setText(ty);
			Qu.setText(qu);
			De.setText(de);
			Price.setText(pr);
		}
	}

	public void insertDB() {
		try {
			String newname = Name.getText();
			String newType = Type.getText();
			Double newQuan = Double.parseDouble(Qu.getText());
			String newDepart = De.getText();
			Double newPrice = Double.parseDouble(Price.getText());
			if(newname.isEmpty()||newType.isEmpty()||newQuan.equals(null)||newDepart.isEmpty()||newPrice.equals(null)) {
				UpdateEquipForm frame = new UpdateEquipForm(new String(), new Equipment(), new String(),
						new String(), new String(), new String(), new String(), new String());
				JOptionPane.showMessageDialog(frame.contentPane, "Không được để trống", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			String sql = "";
			if (this.getTitle().equals("Insert form")) {
				String newID = ID.getText();
				if(newID.isEmpty()) {
					UpdateEquipForm frame = new UpdateEquipForm(new String(), new Equipment(), new String(),
							new String(), new String(), new String(), new String(), new String());
					JOptionPane.showMessageDialog(frame.contentPane, "Không được để trống", "",
							JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				sql = "Insert EquipmentandSoftware values ('" + newID + "','" + newname + "','" + newType + "',"
						+ newQuan + ",'" + newDepart + "'," + newPrice + ")";
			} else {
				sql = "Update EquipmentandSoftware set DeviceName ='" + newname + "',DeviceType='" + newType
						+ "',Quantity=" + newQuan + ",Department='" + newDepart + "',Price=" + newPrice
						+ " where DeviceID ='" + takeid + "'";
			}

			equip.stm.executeUpdate(sql);
			equip.reload();
			equip.model.fireTableDataChanged();
			this.dispose();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
