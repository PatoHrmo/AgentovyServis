package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private SledovacieOkno sledovacieOkno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Simul\u00E1cia servisu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 273);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSledovanie = new JButton("m\u00F3d sledovania");
		btnSledovanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sledovacieOkno = new SledovacieOkno();
				sledovacieOkno.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				sledovacieOkno.setVisible(true);
			}
		});
		btnSledovanie.setBounds(44, 11, 197, 84);
		contentPane.add(btnSledovanie);
	}
}
