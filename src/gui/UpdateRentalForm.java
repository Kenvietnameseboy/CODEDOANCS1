package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class UpdateRentalForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JTextField End;
	private JTextField Start;
	private JTextField Mon;
	private JTextField AR;
	private JTextField Loc;
	
	Rental ren;
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
					UpdateRentalForm frame = new UpdateRentalForm(new String(),new Rental(), new String(), new String(), new String(), new String(), new String(), new String());
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
	public UpdateRentalForm(String s, Rental re, String id, String lo,String ar,String mon, String start,String end) {
		
		ren = re;
		setTakeid(id);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		
		JLabel lblUpdateRentalSpace = new JLabel("UPDATE RENTAL SPACE");
		lblUpdateRentalSpace.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUpdateRentalSpace.setBounds(126, 11, 321, 24);
		contentPane.add(lblUpdateRentalSpace);
		
		JLabel lblNewLabel_1 = new JLabel("RentalID: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(51, 56, 135, 20);
		contentPane.add(lblNewLabel_1);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(230, 56, 175, 20);
		contentPane.add(ID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 418, 7);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("Location:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(51, 104, 135, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Area:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_2.setBounds(51, 163, 135, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Monthly Rent:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_3.setBounds(51, 219, 135, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_5 = new JLabel("Start end:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_5.setBounds(51, 334, 135, 20);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_4 = new JLabel("Start date:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1_4.setBounds(51, 279, 135, 20);
		contentPane.add(lblNewLabel_1_4);
		
		End = new JTextField();
		End.setColumns(10);
		End.setBounds(230, 334, 175, 20);
		contentPane.add(End);
		
		Start = new JTextField();
		Start.setColumns(10);
		Start.setBounds(230, 279, 175, 20);
		contentPane.add(Start);
		
		Mon = new JTextField();
		Mon.setColumns(10);
		Mon.setBounds(230, 219, 175, 20);
		contentPane.add(Mon);
		
		AR = new JTextField();
		AR.setColumns(10);
		AR.setBounds(230, 163, 175, 20);
		contentPane.add(AR);
		
		Loc = new JTextField();
		Loc.setColumns(10);
		Loc.setBounds(230, 104, 175, 20);
		contentPane.add(Loc);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDB();
			}
		});
		btnNewButton.setBounds(160, 383, 114, 33);
		contentPane.add(btnNewButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 365, 418, 7);
		contentPane.add(separator_1);
		this.setTitle(s);
		if(this.getTitle().equals("Edit form")) {
			ID.setText(id);
			ID.setEditable(false);
			Loc.setText(lo);
			AR.setText(ar);
			Mon.setText(mon);
			Start.setText(start);
			End.setText(end);
		}
	}
	public void insertDB() {
		try {
			String newlocation = Loc.getText();
			Integer newArea = Integer.parseInt(AR.getText());
			Double newMon = Double.parseDouble(Mon.getText());
			String newstart = Start.getText();
			validateDateInPast(newstart);
			String newend = End.getText();
			validateDateInPast(newend);
			if(newlocation.isEmpty()||newArea.equals(null)||newMon.equals(null)) {
				UpdateRentalForm eq = new UpdateRentalForm(new String(), new Rental(), new String(),
						new String(), new String(), new String(), new String(), new String());
				JOptionPane.showMessageDialog(eq.contentPane, "Không được để trống", "",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			String sql = "";
			if (this.getTitle().equals("Insert form")) {
				String newid = ID.getText();
				if(newid.isEmpty()) {
					UpdateRentalForm eq = new UpdateRentalForm(new String(), new Rental(), new String(),
							new String(), new String(), new String(), new String(), new String());
					JOptionPane.showMessageDialog(eq.contentPane, "Không được để trống", "",
							JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				sql = "Insert RentalSpace values ('" + newid + "',N'" + newlocation + "'," + newArea + "," + newMon
						+ ",'" + newstart + "','" + newend + "')";
			} else {
				sql = "Update RentalSpace set Location ='" + newlocation + "',Area =" + newArea + ",MonthlyRent ="
						+ newMon + ",StartDate ='" + newstart + "',EndDate = '" + newend + "' where RentalID ='"
						+ takeid + "'";
			}
			ren.stm.executeUpdate(sql);
			ren.reload();
			ren.model.fireTableDataChanged();
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
			UpdateRentalForm eq = new UpdateRentalForm(new String(), new Rental(), new String(),
					new String(), new String(), new String(), new String(), new String());
			JOptionPane.showMessageDialog(eq.contentPane, "Không được đặt là ngày trong quá khứ", "",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}}
}
