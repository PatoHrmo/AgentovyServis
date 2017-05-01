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

	/**
	 * Create the dialog.
	 */
	public SledovacieOkno() {
		simulacia = new MySimulation(5,5);
		simulacia.registerDelegate(this);
		setTitle("Sledovacie okno");
		setBounds(100, 100, 1000, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btntart = new JButton("\u0160tart");
		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulacia.simulateAsync(5,60*60*24*10d);
				simulacia.setSimSpeed(15, 1);
			}
		});
		btntart.setBounds(10, 11, 89, 23);
		getContentPane().add(btntart);
		
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
		
		JLabel lblas = new JLabel("\u010Das:");
		lblas.setBounds(178, 65, 46, 14);
		getContentPane().add(lblas);
		
		lblCasSimulacie = new JLabel("");
		lblCasSimulacie.setBounds(255, 65, 46, 14);
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
		
	}

	public void refresh(Simulation sim) {
		lblCasSimulacie.setText(sim.currentTime()+"");
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
