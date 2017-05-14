package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import simulation.Config;
import simulation.MySimulation;
import util.StringUtils;
import javax.swing.JProgressBar;

public class UrcovacieOkno extends JDialog implements ISimDelegate {
	
	private MySimulation simulacia;
	private JButton btntart;
	private JLabel lblPoetLudPred;
	private JLabel lblVelkostFrontyPredRampou;
	private JLabel lblCasSimulacie;
	private UrcovacieOkno self;
	private JButton btnPauza;
	private JButton btnPokracuj;
	private JButton btnStop;
	private JLabel lblZisk;
	private JLabel lblPocetNeobsluzenych;
	private JLabel lblPocetObsluzenych;
	private JLabel lblPercentualneVytazeniePark2;
	private JLabel lblPocetAutNaPark2;
	private JLabel lblPercentualneVytazeniePark1;
	private JLabel lblPocetAutPark1;
	private JLabel lblPriemDlzkaCakania;
	private JLabel lblPocetLudiPredServisom;
	private JProgressBar progressBar;
	private JTextField textFieldPocetReplikacii;

	/**
	 * Create the dialog.
	 */
	public UrcovacieOkno() {
		self = this;
		setTitle("Sledovacie okno");
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		simulacia = new MySimulation();
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pocetPracovnikov1;
				int pocetPracovnikov2;
				int pocetOdskusanych=0;
				double investicia;
				boolean najprvOdovzdavatOpravene;
				double najZisk=-5000;
				int pocetReplikacii = Integer.parseInt(textFieldPocetReplikacii.getText());
				simulacia.setMaxSpeed();
				for(int percentoToku=0; percentoToku<78;percentoToku++) {
					investicia = percentoToku*62.5;
					for(pocetPracovnikov1 = 1; pocetPracovnikov1<6;pocetPracovnikov1++) {
						for(pocetPracovnikov2 = 10; pocetPracovnikov2<30;pocetPracovnikov2++) {
							for(int i = 0; i<2;i++) {
								if(i==0) {
									najprvOdovzdavatOpravene = true;
								} else {
									najprvOdovzdavatOpravene = false;
								}
								simulacia.setParametre(pocetPracovnikov1,pocetPracovnikov2,investicia, najprvOdovzdavatOpravene);
								simulacia.simulate(pocetReplikacii,Config.DlzkaReplikacie);
								pocetOdskusanych++;
								if(pocetOdskusanych%100==0) {
									System.out.println("zisk");
								}
								if(simulacia.getPriemernyZisk()>najZisk) {
									najZisk = simulacia.getPriemernyZisk();
									System.out.println(najZisk);
								}
							}
						}
					}
				}
				
				
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
		
		JLabel lblas = new JLabel("\u010Das:");
		lblas.setBounds(380, 387, 46, 14);
		getContentPane().add(lblas);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(457, 387, 307, 14);
		getContentPane().add(lblCasSimulacie);
		
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
		lblistZisk.setBounds(380, 362, 114, 14);
		getContentPane().add(lblistZisk);
		
		lblZisk = new JLabel("New label");
		lblZisk.setBounds(457, 362, 46, 14);
		getContentPane().add(lblZisk);
		
		lblPriemDlzkaCakania = new JLabel("New label");
		lblPriemDlzkaCakania.setBounds(288, 412, 138, 14);
		getContentPane().add(lblPriemDlzkaCakania);
		
		lblPocetAutPark1 = new JLabel("New label");
		lblPocetAutPark1.setBounds(288, 437, 46, 14);
		getContentPane().add(lblPocetAutPark1);
		
		lblPercentualneVytazeniePark1 = new JLabel("New label");
		lblPercentualneVytazeniePark1.setBounds(288, 462, 46, 14);
		getContentPane().add(lblPercentualneVytazeniePark1);
		
		lblPocetAutNaPark2 = new JLabel("New label");
		lblPocetAutNaPark2.setBounds(288, 487, 46, 14);
		getContentPane().add(lblPocetAutNaPark2);
		
		lblPercentualneVytazeniePark2 = new JLabel("New label");
		lblPercentualneVytazeniePark2.setBounds(288, 512, 46, 14);
		getContentPane().add(lblPercentualneVytazeniePark2);
		
		lblPocetObsluzenych = new JLabel("New label");
		lblPocetObsluzenych.setBounds(288, 537, 46, 14);
		getContentPane().add(lblPocetObsluzenych);
		
		lblPocetNeobsluzenych = new JLabel("New label");
		lblPocetNeobsluzenych.setBounds(288, 562, 46, 14);
		getContentPane().add(lblPocetNeobsluzenych);
		
		lblPocetLudiPredServisom = new JLabel("New label");
		lblPocetLudiPredServisom.setBounds(288, 387, 46, 14);
		getContentPane().add(lblPocetLudiPredServisom);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 135, 406, 14);
		getContentPane().add(progressBar);
		
		JLabel lblProgresTestov = new JLabel("Progres testov:");
		lblProgresTestov.setBounds(10, 110, 114, 14);
		getContentPane().add(lblProgresTestov);
		
		textFieldPocetReplikacii = new JTextField();
		textFieldPocetReplikacii.setBounds(268, 37, 86, 20);
		getContentPane().add(textFieldPocetReplikacii);
		textFieldPocetReplikacii.setColumns(10);
		
		JLabel lblPoetReplikciNa = new JLabel("Po\u010Det replik\u00E1ci\u00ED na test: ");
		lblPoetReplikciNa.setBounds(268, 15, 148, 14);
		getContentPane().add(lblPoetReplikciNa);
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                simulacia.stopSimulation();
                btntart.setEnabled(true);
            }
        });
	}
	
	

	public void refresh(Simulation sim) {
	}

	public void simStateChanged(Simulation sim, SimState state) {
	
	}
}
