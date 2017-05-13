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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

public class SledovacieOkno extends JDialog implements ISimDelegate {
	
	private MySimulation simulacia;
	private JButton btntart;
	private JLabel lblPoetLudPred;
	private JLabel lblVelkostFrontyPredRampou;
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
	private JLabel lblZisk;
	private JLabel lblPocetNeobsluzenych;
	private JLabel lblPocetObsluzenych;
	private JLabel lblPercentualneVytazeniePark2;
	private JLabel lblPocetAutNaPark2;
	private JLabel lblPercentualneVytazeniePark1;
	private JLabel lblPocetAutPark1;
	private JLabel lblPriemDlzkaCakania;
	private JLabel lblPocetLudiPredServisom;
	private JLabel lblPoetPracovnkov;
	private JTextField textFieldPocet1;
	private JTextField textFieldPocet2;
	private JTextField textFieldInvesticia;
	private JSlider slider;
	private JCheckBox chckbxNajprvOdovzdvaHotov;

	/**
	 * Create the dialog.
	 */
	public SledovacieOkno() {
		self = this;
		setTitle("Sledovacie okno");
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		simulacia = new MySimulation();
		simulacia.registerDelegate(self);
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRychlost();
				Config.trvanie = 0.2;
				int pocetPracovnikov1 = Integer.parseInt(textFieldPocet1.getText());
				int pocetPracovnikov2 = Integer.parseInt(textFieldPocet2.getText());
				double investicia = Double.parseDouble(textFieldInvesticia.getText());
				boolean najprvOdovzdavatOpravene = chckbxNajprvOdovzdvaHotov.isSelected();
				simulacia.setParametre(pocetPracovnikov1,pocetPracovnikov2,investicia, najprvOdovzdavatOpravene);
				simulacia.simulateAsync(1,Config.DlzkaReplikacie);
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
		
		JLabel lblas = new JLabel("\u010Das:");
		lblas.setBounds(380, 387, 46, 14);
		getContentPane().add(lblas);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(457, 387, 307, 14);
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
		
		lblPoetPracovnkov = new JLabel("Po\u010Det pracovn\u00EDkov 1:");
		lblPoetPracovnkov.setBounds(243, 15, 144, 14);
		getContentPane().add(lblPoetPracovnkov);
		
		textFieldPocet1 = new JTextField();
		textFieldPocet1.setBounds(243, 37, 86, 20);
		getContentPane().add(textFieldPocet1);
		textFieldPocet1.setColumns(10);
		
		JLabel lblPoetPracovnkov_1 = new JLabel("Po\u010Det pracovn\u00EDkov 2:");
		lblPoetPracovnkov_1.setBounds(416, 15, 161, 14);
		getContentPane().add(lblPoetPracovnkov_1);
		
		textFieldPocet2 = new JTextField();
		textFieldPocet2.setBounds(416, 37, 86, 20);
		getContentPane().add(textFieldPocet2);
		textFieldPocet2.setColumns(10);
		
		JLabel lblInvestcia = new JLabel("Invest\u00EDcia:");
		lblInvestcia.setBounds(578, 15, 150, 14);
		getContentPane().add(lblInvestcia);
		
		textFieldInvesticia = new JTextField();
		textFieldInvesticia.setBounds(578, 37, 86, 20);
		getContentPane().add(textFieldInvesticia);
		textFieldInvesticia.setColumns(10);
		
		JLabel lblRchlos = new JLabel("R\u00FDchlos\u0165:");
		lblRchlos.setBounds(714, 15, 126, 14);
		getContentPane().add(lblRchlos);
		
		slider = new JSlider();
		slider.setMaximum(1000);
		slider.setValue(1);
		slider.setMinimum(1);
		slider.setBounds(714, 36, 200, 23);
		getContentPane().add(slider);
		
		chckbxNajprvOdovzdvaHotov = new JCheckBox("Najprv odovzd\u00E1va\u0165 hotov\u00E9 aut\u00E1");
		chckbxNajprvOdovzdvaHotov.setSelected(true);
		chckbxNajprvOdovzdvaHotov.setBounds(920, 36, 256, 23);
		getContentPane().add(chckbxNajprvOdovzdvaHotov);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				getRychlost();
				simulacia.setSimSpeed(Config.interval, Config.trvanie);
			}
		});
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
	
	private void getRychlost() {
		Config.interval = slider.getValue();
		if(Config.interval==slider.getMaximum()) {
			Config.interval = 100000;
		}
		if(Config.interval == slider.getMinimum()) {
			Config.interval = 0.2;
		}
	}

	public void refresh(Simulation sim) {
		lblCasSimulacie.setText(StringUtils.getCasVLudskejPodobe(sim.currentTime(), sim.currentReplication()+1));
		lblPocetLudiPredServisom.setText(((MySimulation)sim).getPocetLudiCakajucichNaObsluhu()+"");
		lblVelkostFrontyPredRampou.setText(((MySimulation)sim).getVelkostFrontuPredRampou()+"");
		lblZisk.setText(StringUtils.getCislo(((MySimulation)sim).getZisk()));
		lblPocetLudiPredServisom.setText(((MySimulation)sim).agentVybavovaci().getFrontaLudiNaZadavanieObjednavky().size()+"");
		lblPriemDlzkaCakania.setText(StringUtils.getCislo(((MySimulation)sim).agentVybavovaci().getPriemDlzkaCakania())+" sekúnd");
		lblPocetAutPark1.setText(((MySimulation)sim).agentParkovisk().getPocetAutPark1()+"");
		lblPocetAutNaPark2.setText(((MySimulation)sim).agentParkovisk().getPocetAutPark2()+"");
		lblPercentualneVytazeniePark1.setText(StringUtils.getCislo(((MySimulation)sim).agentParkovisk().getVytazenie1())+"%");
		lblPercentualneVytazeniePark2.setText(StringUtils.getCislo(((MySimulation)sim).agentParkovisk().getVytazenie2())+"%");
		lblPocetObsluzenych.setText(((MySimulation)sim).agentOkolia().getPocetObsluzenych()+"");
		lblPocetNeobsluzenych.setText(((MySimulation)sim).agentOkolia().getPocetNeObsluzenych()+"");
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
