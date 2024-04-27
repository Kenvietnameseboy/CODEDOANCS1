package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;
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

public class UpdateHumanForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JTextField Name;
	private JTextField Position;
	private JTextField Department;
	private JTextField Salary;
	private JTextField Start;
	private JTextField End;
	
	
	private String takeid;
	
	public String getTakeid() {
		return takeid;
	}

	public void setTakeid(String takeid) {
		this.takeid = takeid;
	}


	Human hum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateHumanForm frame = new UpdateHumanForm(new String(), new Human(), new String(), new String(),
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
	public UpdateHumanForm(String s, Human hu, String id, String na, String pos, String De, String Sa, String start,
			String end) {

		hum = hu;
		setTakeid(id);
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		JLabel lblNewLabel = new JLabel("UPDATE HUMAN RESOURCES");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(108, 11, 270, 24);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 418, 7);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("EmployeeID: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(51, 46, 135, 20);
		contentPane.add(lblNewLabel_1);

		ID = new JTextField();
		ID.setBounds(230, 46, 175, 20);
		contentPane.add(ID);
		ID.setColumns(10);

		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(230, 94, 175, 20);
		contentPane.add(Name);

		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(51, 94, 135, 20);
		contentPane.add(lblNewLabel_1_1);

		Position = new JTextField();
		Position.setColumns(10);
		Position.setBounds(230, 153, 175, 20);
		contentPane.add(Position);

		JLabel lblNewLabel_1_2 = new JLabel("Position:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_2.setBounds(51, 153, 135, 20);
		contentPane.add(lblNewLabel_1_2);

		Department = new JTextField();
		Department.setColumns(10);
		Department.setBounds(230, 209, 175, 20);
		contentPane.add(Department);

		JLabel lblNewLabel_1_3 = new JLabel("Department:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_3.setBounds(51, 209, 135, 20);
		contentPane.add(lblNewLabel_1_3);

		Salary = new JTextField();
		Salary.setColumns(10);
		Salary.setBounds(230, 269, 175, 20);
		contentPane.add(Salary);

		JLabel lblNewLabel_1_4 = new JLabel("Salary:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_4.setBounds(51, 269, 135, 20);
		contentPane.add(lblNewLabel_1_4);

		Start = new JTextField();
		Start.setColumns(10);
		Start.setBounds(230, 324, 175, 20);
		contentPane.add(Start);

		JLabel lblNewLabel_1_5 = new JLabel("Startdate:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_5.setBounds(51, 324, 135, 20);
		contentPane.add(lblNewLabel_1_5);

		End = new JTextField();
		End.setColumns(10);
		End.setBounds(230, 384, 175, 20);
		contentPane.add(End);

		JLabel lblNewLabel_1_6 = new JLabel("Enddate:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_6.setBounds(51, 384, 135, 20);
		contentPane.add(lblNewLabel_1_6);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDB();
			}
		});
		btnNewButton.setBounds(160, 433, 114, 33);
		contentPane.add(btnNewButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 415, 418, 7);
		contentPane.add(separator_1);
		this.setTitle(s);
		if (this.getTitle().equals("Edit form")) {
			ID.setText(id);
			ID.setEditable(false);
			Name.setText(na);
			Position.setText(pos);
			Department.setText(De);
			Salary.setText(Sa);
			Start.setText(start);
			End.setText(end);
		}
	}

	public void InsertDB() {
		try {
			String newname = Name.getText();
			String newpos = Position.getText();
			String newdepart = Department.getText();
			if(newname.isEmpty()||newpos.isEmpty() || newdepart.isEmpty()) {
				UpdateHumanForm hu = new UpdateHumanForm(new String(), new Human(), new String(),
						new String(), new String(),new String(), new String(), new String(),new String());
				JOptionPane.showMessageDialog(hu.contentPane, "Không được để trống", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			Double newSalary = Double.parseDouble(Salary.getText());
			String newstart = Start.getText();
			validateDateInPast(newstart);
			String newend = End.getText();
			validateDateInPast(newend);
			String sql = "";
			if (this.getTitle().equals("Insert form")) {
				String newid = ID.getText();
				if(newid.isEmpty()) {
					UpdateHumanForm hu = new UpdateHumanForm(new String(), new Human(), new String(),
							new String(), new String(),new String(), new String(), new String(),new String());
					JOptionPane.showMessageDialog(hu.contentPane, "Không được để trống", "",
							JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				sql = "Insert HumanResourcesManagement values ('" + newid + "','" + newname + "','" + newpos + "','"
						+ newdepart + "'," + newSalary + ",'" + newstart + "','" + newend + "')";
			} else {
				sql = "Update HumanResourcesManagement set Name = '" + newname + "',Position ='" + newpos
						+ "',Department ='" + newdepart + "',Salary=" + newSalary + ",Startdate ='" + newstart
						+ "',Enddate ='" + newend + "' where EmployeeID ='" +getTakeid()+"'";
			}
			hum.stm.executeUpdate(sql);
			hum.reload();
			hum.model.fireTableDataChanged();
			this.dispose();
		} catch (Exception e) {
			// TODO: handle exception
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
			UpdateHumanForm hu = new UpdateHumanForm(new String(), new Human(), new String(),
					new String(), new String(),new String(), new String(), new String(),new String());
			JOptionPane.showMessageDialog(hu.contentPane, "Không được đặt là ngày trong quá khứ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}}
}
