package gui;

import javax.swing.JDialog;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import entity.Robotnik;
import entity.Zakaznik;
import simulation.Config;
import simulation.MyMessage;
import simulation.MySimulation;
import util.StringUtils;

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
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Config.interval = 2000;
				Config.trvanie = 0.1;
				simulacia = new MySimulation(5,5,1000000);
				simulacia.registerDelegate(self);
				simulacia.simulateAsync(3,Config.DlzkaReplikacie);
				simulacia.setSimSpeed(Config.interval, Config.trvanie);
				btntart.setEnabled(false);
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);
		
		lblPoetLudPred = new JLabel("Ve\u013Ekos\u0165 fronty pred rampov:");
		lblPoetLudPred.setBounds(27, 362, 227, 14);
		getContentPane().add(lblPoetLudPred);
		
		lblVelkostFrontyPredRampou = new JLabel("");
		lblVelkostFrontyPredRampou.setBounds(288, 362, 46, 14);
		getContentPane().add(lblVelkostFrontyPredRampou);
		
		JLabel lblPoetZkaznkovakajcich = new JLabel("Po\u010Det z\u00E1kazn\u00EDkov pred servisom:");
		lblPoetZkaznkovakajcich.setBounds(27, 387, 251, 14);
		getContentPane().add(lblPoetZkaznkovakajcich);
		
		lblPocetLudiCakajucichNaObsluhu = new JLabel("");
		lblPocetLudiCakajucichNaObsluhu.setBounds(288, 387, 46, 14);
		getContentPane().add(lblPocetLudiCakajucichNaObsluhu);
		
		JLabel lblas = new JLabel("\u010Das:");
		lblas.setBounds(273, 61, 46, 14);
		getContentPane().add(lblas);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(350, 61, 307, 14);
		getContentPane().add(lblCasSimulacie);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 108, 360, 225);
		getContentPane().add(scrollPane);
		
		textAreaZakaznici = new JTextArea();
		scrollPane.setViewportView(textAreaZakaznici);
		
		lblZkaznci = new JLabel("Z\u00E1kazn\u00EDci:");
		lblZkaznci.setBounds(27, 89, 72, 14);
		getContentPane().add(lblZkaznci);
		
		lblPracovnci = new JLabel("Pracovn\u00EDci 1:");
		lblPracovnci.setBounds(397, 89, 101, 14);
		getContentPane().add(lblPracovnci);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(397, 108, 227, 225);
		getContentPane().add(scrollPane_1);
		
		textAreaPracovnici1 = new JTextArea();
		scrollPane_1.setViewportView(textAreaPracovnici1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(648, 108, 235, 225);
		getContentPane().add(scrollPane_2);
		
		textAreaPracovnici2 = new JTextArea();
		scrollPane_2.setViewportView(textAreaPracovnici2);
		
		JLabel lblNewLabel = new JLabel("Pracovnici 2:");
		lblNewLabel.setBounds(648, 89, 116, 14);
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
		
		JLabel lblPoetutNa = new JLabel("Po\u010Det \u00E1ut na parkovisku 1:");
		lblPoetutNa.setBounds(27, 437, 212, 14);
		getContentPane().add(lblPoetutNa);
		
		JLabel lblPoetutNa_1 = new JLabel("Po\u010Det \u00E1ut na parkovisku 2:");
		lblPoetutNa_1.setBounds(27, 487, 171, 14);
		getContentPane().add(lblPoetutNa_1);
		
		JLabel lblPercentulneVyaenieParkoviska = new JLabel("Percentu\u00E1lne vy\u0165a\u017Eenie parkoviska 1:");
		lblPercentulneVyaenieParkoviska.setBounds(27, 462, 227, 14);
		getContentPane().add(lblPercentulneVyaenieParkoviska);
		
		JLabel lblPercentulneVyaenieParkoviska_1 = new JLabel("Percentu\u00E1lne vy\u0165a\u017Eenie parkoviska 2:");
		lblPercentulneVyaenieParkoviska_1.setBounds(27, 512, 227, 14);
		getContentPane().add(lblPercentulneVyaenieParkoviska_1);
		
		JLabel lblPoetObslenchZkaznkov = new JLabel("Po\u010Det obsl\u00FA\u017Een\u00FDch z\u00E1kazn\u00EDkov:");
		lblPoetObslenchZkaznkov.setBounds(27, 537, 227, 14);
		getContentPane().add(lblPoetObslenchZkaznkov);
		
		JLabel lblPoetNeobslenchZkaznkov = new JLabel("Po\u010Det neobsl\u00FA\u017Een\u00FDch z\u00E1kazn\u00EDkov:");
		lblPoetNeobslenchZkaznkov.setBounds(27, 562, 212, 14);
		getContentPane().add(lblPoetNeobslenchZkaznkov);
		
		JLabel lblPriemernDkaakania = new JLabel("Priemern\u00E1 d\u013A\u017Eka \u010Dakania:");
		lblPriemernDkaakania.setBounds(27, 412, 212, 14);
		getContentPane().add(lblPriemernDkaakania);
		
		JLabel lblistZisk = new JLabel("\u010Dist\u00FD zisk:");
		lblistZisk.setBounds(273, 36, 114, 14);
		getContentPane().add(lblistZisk);
		
	}

	public void refresh(Simulation sim) {
		lblCasSimulacie.setText(StringUtils.getCasVLudskejPodobe(sim.currentTime(), sim.currentReplication()+1));
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
		if(SimState.stopped == state) {
			btntart.setEnabled(true);
		}
		
		
	}
}
