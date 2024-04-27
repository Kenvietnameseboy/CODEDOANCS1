package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Administrator extends JFrame {
	private JMenuBar menu = new JMenuBar();
	private JMenu accnew = new JMenu("Account");
	private JMenuItem logout = new JMenuItem("Log out");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Income;
	private JTextField OUT;
	
	Outcomemanagement ou;
	Incomemanagement n;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator(new Outcomemanagement(new Human(), new Equipment(), new Rental()),new Incomemanagement());
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
	public Administrator(Outcomemanagement out,Incomemanagement in) {
		setLocationRelativeTo(null);
		ou = out;
		n = in;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);
		menu.add(accnew);
		accnew.add(logout);
		this.setJMenuBar(menu);
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Administrator.this.dispose();
					new Login().setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		JButton btnNewButton = new JButton("INCOME MANAGEMENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Incomemanagement im = new Incomemanagement();
				im.setVisible(true);
			}
		});
		btnNewButton.setBounds(82, 297, 164, 23);
		contentPane.add(btnNewButton);
		
		JButton btnOutcomeManagement = new JButton("OUTCOME MANAGEMENT");
		btnOutcomeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Outcomemanagement out = new Outcomemanagement(new Human(),new Equipment(), new Rental());
				out.setVisible(true);
			}
		});
		btnOutcomeManagement.setBounds(332, 297, 164, 23);
		contentPane.add(btnOutcomeManagement);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 107, 557, 49);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("ADMIN FORM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(180, 11, 258, 143);
		contentPane.add(lblNewLabel);
		
		Income = new JTextField();
		Income.setEditable(false);
		Income.setBounds(82, 197, 164, 34);
		contentPane.add(Income);
		Income.setColumns(10);
		
		String formattedNumber1 = String.format("%.2f tỷ",n.getSumIn() / 1_000_000_000L);
		Income.setText(formattedNumber1);
		
		OUT = new JTextField();
		OUT.setEditable(false);
		OUT.setColumns(10);
		OUT.setBounds(332, 197, 164, 34);
		contentPane.add(OUT);
		String formattedNumber2 = String.format("%.2f tỷ", ou.getSumall() / 1_000_000_000L);
	
		OUT.setText(formattedNumber2);
	
		
		JLabel lblNewLabel_1 = new JLabel("TOTAL INCOME");
		lblNewLabel_1.setBounds(123, 167, 164, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("TOTAL OUTCOME");
		lblNewLabel_1_1.setBounds(368, 167, 164, 19);
		contentPane.add(lblNewLabel_1_1);
	}

}
