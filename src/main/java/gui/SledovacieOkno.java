package gui;

import javax.swing.JDialog;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import simulation.MySimulation;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SledovacieOkno extends JDialog implements ISimDelegate {
	
	private MySimulation simulacia;
	private JButton btntart;
	private JTable table;
	private JLabel lblPoetLudPred;
	private JLabel lblVelkostFrontyPredRampou;
	private JLabel lblPocetLudiCakajucichNaObsluhu;

	/**
	 * Create the dialog.
	 */
	public SledovacieOkno() {
		simulacia = new MySimulation(5,5);
		simulacia.registerDelegate(this);
		setTitle("Sledovacie okno");
		setBounds(100, 100, 654, 382);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.simulateAsync(5,60*60*24*10d);
				simulacia.setSimSpeed(5, 1);
				System.out.println(simulacia.currentReplication());
				
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);
		
		table = new JTable();
		table.setBounds(18, 60, 240, 244);
		getContentPane().add(table);
		
		lblPoetLudPred = new JLabel("Ve\u013Ekos\u0165 fronty pred rampov:");
		lblPoetLudPred.setBounds(178, 15, 227, 14);
		getContentPane().add(lblPoetLudPred);
		
		lblVelkostFrontyPredRampou = new JLabel("");
		lblVelkostFrontyPredRampou.setBounds(415, 15, 46, 14);
		getContentPane().add(lblVelkostFrontyPredRampou);
		
		JLabel lblPoetZkaznkovakajcich = new JLabel("Po\u010Det z\u00E1kazn\u00EDkov \u010Dakaj\u00FAcich na obsluhu:");
		lblPoetZkaznkovakajcich.setBounds(178, 40, 227, 14);
		getContentPane().add(lblPoetZkaznkovakajcich);
		
		lblPocetLudiCakajucichNaObsluhu = new JLabel("");
		lblPocetLudiCakajucichNaObsluhu.setBounds(415, 40, 46, 14);
		getContentPane().add(lblPocetLudiCakajucichNaObsluhu);
		
	}

	public void refresh(Simulation sim) {
		lblPoetLudPred.setText(sim.currentTime()+"");
		lblPocetLudiCakajucichNaObsluhu.setText(((MySimulation)sim).getPocetLudiCakajucichNaObsluhu()+"");
		lblVelkostFrontyPredRampou.setText(((MySimulation)sim).getVelkostFrontuPredRampou()+"");
		
	}

	public void simStateChanged(Simulation sim, SimState state) {
		// TODO Auto-generated method stub
		
	}
}
