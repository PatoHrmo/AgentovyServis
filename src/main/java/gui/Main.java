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
	private TurboOkno turboOkno;
	private UrcovacieOkno urcovacieOkno;
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
		setBounds(100, 100, 310, 454);
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
		
		JButton btnTurboMd = new JButton("Turbo m\u00F3d");
		btnTurboMd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turboOkno = new TurboOkno();
				turboOkno.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				turboOkno.setVisible(true);
			}
		});
		btnTurboMd.setBounds(44, 106, 199, 87);
		contentPane.add(btnTurboMd);
		
		JButton btnUrcovacie = new JButton("Zis\u0165ovanie optim\u00E1lnej kombin\u00E1cie");
		btnUrcovacie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				urcovacieOkno = new UrcovacieOkno();
				urcovacieOkno.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				urcovacieOkno.setVisible(true);
			}
		});
		btnUrcovacie.setBounds(44, 204, 197, 87);
		contentPane.add(btnUrcovacie);
	}
}
