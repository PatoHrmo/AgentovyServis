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

public class TurboOkno extends JDialog implements ISimDelegate {

	private MySimulation simulacia;
	private JButton btntart;
	private TurboOkno self;
	private JButton btnPauza;
	private JButton btnPokracuj;
	private JButton btnStop;
	private JLabel lblZisk;
	private JLabel lblPocetNeobsluzenych;
	private JLabel lblPocetObsluzenych;
	private JLabel lblPercentualneVytazeniePark2;
	private JLabel lblPercentualneVytazeniePark1;
	private JLabel lblPriemDlzkaCakania;
	private JLabel lblPocetLudiPredServisom;
	private JLabel lblPoetPracovnkov;
	private JTextField textFieldPocet1;
	private JTextField textFieldPocet2;
	private JTextField textFieldInvesticia;
	private JCheckBox chckbxNajprvOdovzdvaHotov;
	private JTextField textFieldPocetReplikacii;
	private JLabel lblPoetReplikci;
	private JLabel lblDlzkaOpravy;
	private JLabel lblPriemernPoetPracujcich;
	private JLabel lblPriemPocetPracujucich1;
	private JLabel lblPriemernPoetPracujcich_1;
	private JLabel lblPriemPocetPracujucich2;

	/**
	 * Create the dialog.
	 */
	public TurboOkno() {
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
				int pocetPracovnikov1 = Integer.parseInt(textFieldPocet1.getText());
				int pocetPracovnikov2 = Integer.parseInt(textFieldPocet2.getText());
				double investicia = Double.parseDouble(textFieldInvesticia.getText());
				boolean najprvOdovzdavatOpravene = chckbxNajprvOdovzdvaHotov.isSelected();
				int pocetReplikacii = Integer.parseInt(textFieldPocetReplikacii.getText());
				simulacia.setParametre(pocetPracovnikov1, pocetPracovnikov2, investicia, najprvOdovzdavatOpravene);
				simulacia.setMaxSpeed();
				simulacia.simulateAsync(pocetReplikacii, Config.DlzkaReplikacie);

				btntart.setEnabled(false);
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);

		JLabel lblPoetZkaznkovakajcich = new JLabel("Po\u010Det z\u00E1kazn\u00EDkov pred servisom:");
		lblPoetZkaznkovakajcich.setBounds(10, 185, 251, 14);
		getContentPane().add(lblPoetZkaznkovakajcich);

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

		JLabel lblPercentulneVyaenieParkoviska = new JLabel("Percentu\u00E1lne vy\u0165a\u017Eenie parkoviska 1:");
		lblPercentulneVyaenieParkoviska.setBounds(10, 260, 227, 14);
		getContentPane().add(lblPercentulneVyaenieParkoviska);

		JLabel lblPercentulneVyaenieParkoviska_1 = new JLabel("Percentu\u00E1lne vy\u0165a\u017Eenie parkoviska 2:");
		lblPercentulneVyaenieParkoviska_1.setBounds(10, 310, 227, 14);
		getContentPane().add(lblPercentulneVyaenieParkoviska_1);

		JLabel lblPoetObslenchZkaznkov = new JLabel("Po\u010Det obsl\u00FA\u017Een\u00FDch z\u00E1kazn\u00EDkov:");
		lblPoetObslenchZkaznkov.setBounds(10, 335, 227, 14);
		getContentPane().add(lblPoetObslenchZkaznkov);

		JLabel lblPoetNeobslenchZkaznkov = new JLabel("Po\u010Det neobsl\u00FA\u017Een\u00FDch z\u00E1kazn\u00EDkov:");
		lblPoetNeobslenchZkaznkov.setBounds(10, 360, 212, 14);
		getContentPane().add(lblPoetNeobslenchZkaznkov);

		JLabel lblPriemernDkaakania = new JLabel("D\u013A\u017Eka \u010Dakania na zadanie objedn\u00E1vky:");
		lblPriemernDkaakania.setBounds(10, 210, 257, 14);
		getContentPane().add(lblPriemernDkaakania);

		JLabel lblistZisk = new JLabel("\u010Dist\u00FD zisk:");
		lblistZisk.setBounds(10, 160, 114, 14);
		getContentPane().add(lblistZisk);

		lblZisk = new JLabel("New label");
		lblZisk.setBounds(253, 163, 212, 14);
		getContentPane().add(lblZisk);

		lblPriemDlzkaCakania = new JLabel("New label");
		lblPriemDlzkaCakania.setBounds(253, 213, 212, 14);
		getContentPane().add(lblPriemDlzkaCakania);

		lblPercentualneVytazeniePark1 = new JLabel("New label");
		lblPercentualneVytazeniePark1.setBounds(253, 263, 212, 14);
		getContentPane().add(lblPercentualneVytazeniePark1);

		lblPercentualneVytazeniePark2 = new JLabel("New label");
		lblPercentualneVytazeniePark2.setBounds(253, 313, 212, 14);
		getContentPane().add(lblPercentualneVytazeniePark2);

		lblPocetObsluzenych = new JLabel("New label");
		lblPocetObsluzenych.setBounds(253, 338, 212, 14);
		getContentPane().add(lblPocetObsluzenych);

		lblPocetNeobsluzenych = new JLabel("New label");
		lblPocetNeobsluzenych.setBounds(253, 363, 212, 14);
		getContentPane().add(lblPocetNeobsluzenych);

		lblPocetLudiPredServisom = new JLabel("New label");
		lblPocetLudiPredServisom.setBounds(253, 188, 251, 14);
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

		chckbxNajprvOdovzdvaHotov = new JCheckBox("Najprv zad\u00E1va\u0165 objedn\u00E1vku");
		chckbxNajprvOdovzdvaHotov.setSelected(true);
		chckbxNajprvOdovzdvaHotov.setBounds(670, 36, 256, 23);
		getContentPane().add(chckbxNajprvOdovzdvaHotov);

		textFieldPocetReplikacii = new JTextField();
		textFieldPocetReplikacii.setBounds(243, 99, 86, 20);
		getContentPane().add(textFieldPocetReplikacii);
		textFieldPocetReplikacii.setColumns(10);

		lblPoetReplikci = new JLabel("Po\u010Det replik\u00E1ci\u00ED");
		lblPoetReplikci.setBounds(243, 74, 107, 14);
		getContentPane().add(lblPoetReplikci);
		
		JLabel lblDkaakaniaNa = new JLabel("D\u013A\u017Eka \u010Dakania na opravu:");
		lblDkaakaniaNa.setBounds(10, 235, 188, 14);
		getContentPane().add(lblDkaakaniaNa);
		
		lblDlzkaOpravy = new JLabel("New label");
		lblDlzkaOpravy.setBounds(253, 235, 251, 14);
		getContentPane().add(lblDlzkaOpravy);
		
		lblPriemernPoetPracujcich = new JLabel("Priemern\u00FD po\u010Det pracuj\u00FAcich 1:");
		lblPriemernPoetPracujcich.setBounds(10, 389, 227, 14);
		getContentPane().add(lblPriemernPoetPracujcich);
		
		lblPriemPocetPracujucich1 = new JLabel("New label");
		lblPriemPocetPracujucich1.setBounds(253, 388, 46, 14);
		getContentPane().add(lblPriemPocetPracujucich1);
		
		lblPriemernPoetPracujcich_1 = new JLabel("Priemern\u00FD po\u010Det pracuj\u00FAcich 2:");
		lblPriemernPoetPracujcich_1.setBounds(10, 414, 235, 14);
		getContentPane().add(lblPriemernPoetPracujcich_1);
		
		lblPriemPocetPracujucich2 = new JLabel("New label");
		lblPriemPocetPracujucich2.setBounds(253, 413, 46, 14);
		getContentPane().add(lblPriemPocetPracujucich2);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				simulacia.stopSimulation();
				btntart.setEnabled(true);
			}
		});
	}

	public void refresh(Simulation sim) {

	}

	public void simStateChanged(Simulation sim, SimState state) {
		if (SimState.stopped == state) {
			btntart.setEnabled(true);
		} else if (SimState.replicationStopped == state) {
			lblPocetLudiPredServisom
					.setText(StringUtils.getIsSekundy(((MySimulation) sim).getPocetLudiCakajucichNaObsluhuRepl()) + "");
			lblZisk.setText(StringUtils.getIsSekundy(((MySimulation) sim).getReplZisk())+" eur");
			lblPriemDlzkaCakania.setText(
					StringUtils.getIsSekundy(((MySimulation) sim).agentVybavovaci().getIsDlzkaCakania()) + " sekúnd");
			lblDlzkaOpravy.setText(StringUtils.getIsSekundy(((MySimulation) sim).agentVybavovaci().getIsCakanieNaOpravu()));
			lblPercentualneVytazeniePark1
					.setText(StringUtils.getIsSekundy(((MySimulation) sim).agentParkovisk().getReplVytazenie1()) + "%");
			lblPercentualneVytazeniePark2
				.setText(StringUtils.getIsSekundy(((MySimulation) sim).agentParkovisk().getReplVytazenie2()) + "%");
			lblPocetObsluzenych.setText(StringUtils.getIsSekundy(((MySimulation)sim).agentOkolia().getReplPocetObsluzenych()));
			lblPocetNeobsluzenych.setText(StringUtils.getIsSekundy(((MySimulation)sim).agentOkolia().getReplPocetNeObsluzenych()));
			lblPriemPocetPracujucich2.setText(((MySimulation)sim).agentOpravary().getReplPriemPocetPracujucich()+"");
			lblPriemPocetPracujucich1.setText(((MySimulation)sim).agentVybavovaci().getReplPriemPocetPracujucich()+"");
		}

	}
}
