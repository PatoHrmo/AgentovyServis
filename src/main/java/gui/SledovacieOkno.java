package gui;

import javax.swing.JDialog;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import entity.Robotnik;
import entity.Zakaznik;
import simulation.MyMessage;
import simulation.MySimulation;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SledovacieOkno extends JDialog implements ISimDelegate {
	
	private MySimulation simulacia;
	private JButton btntart;
	private JLabel lblPoetLudPred;
	private JLabel lblVelkostFrontyPredRampou;
	private JLabel lblPocetLudiCakajucichNaObsluhu;
	private JLabel lblCasSimulacie;
	private JTextArea textAreaZakaznici;
	private JLabel lblZkaznci;
	private JLabel lblPracovnci;
	private JScrollPane scrollPane_1;
	private JTextArea textAreaPracovnici1;
	private JScrollPane scrollPane_2;
	private JTextArea textAreaPracovnici2;
	private SledovacieOkno self;
	private JButton btnPauza;
	private JButton btnPokracuj;
	private JButton btnStop;

	/**
	 * Create the dialog.
	 */
	public SledovacieOkno() {
		self = this;
		setTitle("Sledovacie okno");
		setBounds(100, 100, 1000, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia = new MySimulation(5,5);
				simulacia.registerDelegate(self);
				btntart.setEnabled(false);
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);
		
		lblPoetLudPred = new JLabel("Ve\u013Ekos\u0165 fronty pred rampov:");
		lblPoetLudPred.setBounds(273, 11, 227, 14);
		getContentPane().add(lblPoetLudPred);
		
		lblVelkostFrontyPredRampou = new JLabel("");
		lblVelkostFrontyPredRampou.setBounds(510, 11, 46, 14);
		getContentPane().add(lblVelkostFrontyPredRampou);
		
		JLabel lblPoetZkaznkovakajcich = new JLabel("Po\u010Det z\u00E1kazn\u00EDkov \u010Dakaj\u00FAcich na obsluhu:");
		lblPoetZkaznkovakajcich.setBounds(273, 36, 227, 14);
		getContentPane().add(lblPoetZkaznkovakajcich);
		
		lblPocetLudiCakajucichNaObsluhu = new JLabel("");
		lblPocetLudiCakajucichNaObsluhu.setBounds(510, 36, 46, 14);
		getContentPane().add(lblPocetLudiCakajucichNaObsluhu);
		
		JLabel lblas = new JLabel("\u010Das:");
		lblas.setBounds(273, 61, 46, 14);
		getContentPane().add(lblas);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(350, 61, 141, 14);
		getContentPane().add(lblCasSimulacie);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 108, 227, 225);
		getContentPane().add(scrollPane);
		
		textAreaZakaznici = new JTextArea();
		scrollPane.setViewportView(textAreaZakaznici);
		
		lblZkaznci = new JLabel("Z\u00E1kazn\u00EDci:");
		lblZkaznci.setBounds(27, 89, 72, 14);
		getContentPane().add(lblZkaznci);
		
		lblPracovnci = new JLabel("Pracovn\u00EDci 1:");
		lblPracovnci.setBounds(265, 89, 101, 14);
		getContentPane().add(lblPracovnci);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(264, 108, 227, 225);
		getContentPane().add(scrollPane_1);
		
		textAreaPracovnici1 = new JTextArea();
		scrollPane_1.setViewportView(textAreaPracovnici1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(501, 108, 235, 225);
		getContentPane().add(scrollPane_2);
		
		textAreaPracovnici2 = new JTextArea();
		scrollPane_2.setViewportView(textAreaPracovnici2);
		
		JLabel lblNewLabel = new JLabel("Pracovnici 2:");
		lblNewLabel.setBounds(501, 89, 116, 14);
		getContentPane().add(lblNewLabel);
		
		btnPauza = new JButton("pauza");
		btnPauza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.pauseSimulation();
				btnPauza.setEnabled(false);
				btnPokracuj.setEnabled(true);
			}
		});
		btnPauza.setBounds(10, 36, 89, 23);
		getContentPane().add(btnPauza);
		
		btnStop = new JButton("stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStop.setBounds(10, 61, 89, 23);
		getContentPane().add(btnStop);
		
		btnPokracuj = new JButton("pokracuj");
		btnPokracuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPauza.setEnabled(true);
				btnPokracuj.setEnabled(false);
				simulacia.resumeSimulation();
			}
		});
		btnPokracuj.setBounds(109, 36, 89, 23);
		getContentPane().add(btnPokracuj);
		
	}

	public void refresh(Simulation sim) {
		lblCasSimulacie.setText(sim.currentTime()+""+" "+sim.currentReplication());
		lblPocetLudiCakajucichNaObsluhu.setText(((MySimulation)sim).getPocetLudiCakajucichNaObsluhu()+"");
		lblVelkostFrontyPredRampou.setText(((MySimulation)sim).getVelkostFrontuPredRampou()+"");
		
		List<Zakaznik> zoznamZakaznikov = ((MySimulation)sim).agentOkolia().getZakaznikovVSysteme();
		textAreaZakaznici.setText("");
		for(Zakaznik zak : zoznamZakaznikov) {
			textAreaZakaznici.append(zak.toString()+System.lineSeparator());
		}
		
		List<Robotnik> zoznamPraconikov1 = ((MySimulation)sim).agentVybavovaci().getVsetciPracovnici();
		textAreaPracovnici1.setText("");
		for(Robotnik rob : zoznamPraconikov1) {
			textAreaPracovnici1.append(rob.toString()+System.lineSeparator());
		}
		
		List<Robotnik> zoznamPracovnikov2 = ((MySimulation)sim).agentOpravary().getVsetciPracovnici();
		textAreaPracovnici2.setText("");
		for(Robotnik rob : zoznamPracovnikov2) {
			textAreaPracovnici2.append(rob.toString()+System.lineSeparator());
		}
	}

	public void simStateChanged(Simulation sim, SimState state) {
		// TODO Auto-generated method stub
		
	}
}
