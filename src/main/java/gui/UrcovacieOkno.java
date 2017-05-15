package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import simulation.Config;
import simulation.MySimulation;
import util.StringUtils;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UrcovacieOkno extends JDialog implements ISimDelegate {
	
	private MySimulation simulacia;
	private JButton btntart;
	private JLabel lblCasSimulacie;
	private UrcovacieOkno self;
	private JButton btnStop;
	private JTextField textFieldPocetReplikacii;
	private SwingWorker<Void, double[]> worker;
	private JTextField textFieldInvesticiaOd;
	private JTextField textFieldIvesticiaDo;
	private JTextField textFieldInkrementacia;
	private JTextArea textAreaVysledky;
	/**
	 * Create the dialog.
	 */
	public UrcovacieOkno() {
		self = this;
		setTitle("Ur\u010Dovacie okno");
		setBounds(100, 100, 868, 441);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		simulacia = new MySimulation();
		simulacia.setMaxSpeed();
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean sposop[] = new boolean[]{true, false};
				btntart.setEnabled(false);
				btnStop.setEnabled(true);
				textAreaVysledky.setText("");
				worker = new SwingWorker<Void, double[]>() {
					double investicia;
					double minInvesticia = Double.parseDouble(textFieldInvesticiaOd.getText());
					double maxInvesticia = Double.parseDouble(textFieldIvesticiaDo.getText())+1;
					double inkrementaciaInvesticie = Double.parseDouble(textFieldInkrementacia.getText());
					int pocetPracovnikov1;
					int pocetPracovnikov2;
					int pocetReplikacii = Integer.parseInt(textFieldPocetReplikacii.getText());;
					double maxDlzkaCakaniaNaOpravu = 6*3600;
					List<double[]> vektoryPoctov = new LinkedList<>();
					double maxZiskPriInvesticii;
					boolean nasielSaPocet1;
					boolean nasielSaPocet2;
					double priemPocetPracujucich;
					int pocetPracujucichNaDalsiPokus;
					@Override
					protected Void doInBackground() throws Exception {
						for(investicia =minInvesticia;investicia<=maxInvesticia;investicia+=inkrementaciaInvesticie) {
							maxZiskPriInvesticii = 0;
							double vektorParametrov[] = new double[5];
							for(boolean sposobPrace : sposop) {
								pocetPracovnikov1 = 2;
								pocetPracovnikov2 = 44;
								nasielSaPocet1 = false;
								nasielSaPocet2 = false;
								while(!nasielSaPocet1 && !worker.isCancelled()) {
									simulacia.setParametre(pocetPracovnikov1, pocetPracovnikov2, investicia, sposobPrace);
									simulacia.simulate(pocetReplikacii,Config.DlzkaReplikacie);
									priemPocetPracujucich  = simulacia.agentVybavovaci().getReplPriemPocetPracujucich();
									pocetPracujucichNaDalsiPokus = (int)Math.round(Math.ceil(priemPocetPracujucich));
									if(pocetPracujucichNaDalsiPokus==pocetPracovnikov1) {
										nasielSaPocet1=true;
									} else {
										pocetPracovnikov1=pocetPracujucichNaDalsiPokus;
									}
								}
								while(!nasielSaPocet2 && !worker.isCancelled()) {
									simulacia.setParametre(pocetPracovnikov1, pocetPracovnikov2, investicia, sposobPrace);
									simulacia.simulate(pocetReplikacii,Config.DlzkaReplikacie);
									if(simulacia.agentVybavovaci().getReplPriemCakanieNaOpravu()<maxDlzkaCakaniaNaOpravu
											&& simulacia.getPriemernyZisk()>maxZiskPriInvesticii) {
										maxZiskPriInvesticii = simulacia.getPriemernyZisk();
										vektorParametrov[0] = pocetPracovnikov1;
										vektorParametrov[1] = pocetPracovnikov2;
										vektorParametrov[2] = investicia;
										if(sposobPrace) {
											vektorParametrov[3] = 1;
										} else {
											vektorParametrov[3] = 0;
										}
										vektorParametrov[4] = maxZiskPriInvesticii;
									}
									priemPocetPracujucich  = simulacia.agentOpravary().getReplPriemPocetPracujucich();
									pocetPracujucichNaDalsiPokus = (int)Math.round(Math.ceil(priemPocetPracujucich));
									if(pocetPracujucichNaDalsiPokus==pocetPracovnikov2) {
										nasielSaPocet2 = true;
									}
									else {
										pocetPracovnikov2=pocetPracujucichNaDalsiPokus;
									}
								}
								
							}
							publish(vektorParametrov);
							vektoryPoctov.add(vektorParametrov);
						}
						return null;
					}
					@Override
					protected void done() {
						btnStop.setEnabled(false);
						btntart.setEnabled(true);
					}
					@Override
					protected void process(List<double[]> kombinacie) {
						for(double[] pole : kombinacie) {
							String sprava = "Investícia: "+ pole[2]+" eur. Zisk "+StringUtils.getCislo(pole[4])+System.lineSeparator();
							 sprava += "Pracovníci 1 : "+StringUtils.getCislo(pole[0])+System.lineSeparator()+
									 " Pracovníci 2: "+
									StringUtils.getCislo(pole[1])+System.lineSeparator();
							 if(Math.round(pole[3])==1) {
								 sprava+="Spôsob práce: pokus o zadávanie objednávky";
							 } else {
								 sprava+="Spôsob práce: pokus o odovzdávanie opraveného auta";
							 }
							 textAreaVysledky.append(sprava+System.lineSeparator()+System.lineSeparator());		
									
						}
					}
				};
				worker.execute();
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(457, 387, 307, 14);
		getContentPane().add(lblCasSimulacie);
		
		btnStop = new JButton("stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				worker.cancel(true);
				btnStop.setEnabled(false);
			}
		});
		btnStop.setBounds(10, 45, 89, 23);
		getContentPane().add(btnStop);
		
		textFieldPocetReplikacii = new JTextField();
		textFieldPocetReplikacii.setBounds(268, 37, 86, 20);
		getContentPane().add(textFieldPocetReplikacii);
		textFieldPocetReplikacii.setColumns(10);
		
		JLabel lblPoetReplikciNa = new JLabel("Po\u010Det replik\u00E1ci\u00ED na test: ");
		lblPoetReplikciNa.setBounds(268, 15, 148, 14);
		getContentPane().add(lblPoetReplikciNa);
		
		JLabel lblInvestciaOd = new JLabel("Invest\u00EDcia od: ");
		lblInvestciaOd.setBounds(426, 15, 114, 14);
		getContentPane().add(lblInvestciaOd);
		
		textFieldInvesticiaOd = new JTextField();
		textFieldInvesticiaOd.setBounds(426, 37, 86, 20);
		getContentPane().add(textFieldInvesticiaOd);
		textFieldInvesticiaOd.setColumns(10);
		
		JLabel lblIvestciaDo = new JLabel("Ivest\u00EDcia do:");
		lblIvestciaDo.setBounds(550, 15, 147, 14);
		getContentPane().add(lblIvestciaDo);
		
		textFieldIvesticiaDo = new JTextField();
		textFieldIvesticiaDo.setBounds(550, 37, 86, 20);
		getContentPane().add(textFieldIvesticiaDo);
		textFieldIvesticiaDo.setColumns(10);
		
		textFieldInkrementacia = new JTextField();
		textFieldInkrementacia.setBounds(678, 37, 86, 20);
		getContentPane().add(textFieldInkrementacia);
		textFieldInkrementacia.setColumns(10);
		
		JLabel lblInkremetovaInvestciuPo = new JLabel("Inkremetova\u0165 invest\u00EDciu po:");
		lblInkremetovaInvestciuPo.setBounds(678, 15, 197, 14);
		getContentPane().add(lblInkremetovaInvestciuPo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 119, 513, 232);
		getContentPane().add(scrollPane);
		
		textAreaVysledky = new JTextArea();
		scrollPane.setViewportView(textAreaVysledky);
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	if(worker!=null)
                worker.cancel(true);
                btntart.setEnabled(true);
            }
        });
	}
	
	

	public void refresh(Simulation sim) {
	}

	public void simStateChanged(Simulation sim, SimState state) {
	
	}
}
