package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import continualAssistants.Oprava;
import entity.Cinnosti;
import entity.Robotnik;
import managers.ManagerOpravary;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="17"
public class AgentOpravary extends Agent
{	
	private List<Robotnik> volnyPracovnici; 
	private List<Robotnik> vsetciPracovnici;
	private int pocetAutPredOpravovnou;
	public AgentOpravary(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecOpravy);
		volnyPracovnici = new LinkedList<>();
		vsetciPracovnici = new LinkedList<>();
		pocetAutPredOpravovnou = 0;
	}
	public void nastavPocetPracovnikov(int pocetPracovnikov) {
		for(int i = 0; i<pocetPracovnikov;i++) {
			Robotnik rob = new Robotnik(mySim(),i);
			volnyPracovnici.add(rob);
			vsetciPracovnici.add(rob);
		}
	}
	public List<Robotnik> getVsetciPracovnici() {
		return vsetciPracovnici;
	}
	public boolean jeVolnyPracovnik() {
		return !volnyPracovnici.isEmpty();
	}
	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOpravary(Id.managerOpravary, mySim(), this);
		new Oprava(Id.oprava, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.prichodAutaNaParkovisko1);
		addOwnMessage(Mc.vypytajMiestoParkoviska2);
	}
	//meta! tag="end"
	public void zvysPocetAutPredOpravovnou() {
		pocetAutPredOpravovnou++;
	}
	public void znizPocetAutPredOpravovnou() {
		pocetAutPredOpravovnou--;
	}
	public int getPocetAutPredOpravovnou() {
		return pocetAutPredOpravovnou;
	}
	public Robotnik getVolnyPracovnik() {
		return volnyPracovnici.remove(0);
	}
	public void uvolniPracovnika(MyMessage message) {
		message.getRobotnik().setCinnost(Cinnosti.necinny);
		message.getZakaznik().setCinnost(Cinnosti.cakaNaParkovisku2);
		volnyPracovnici.add(message.getRobotnik());
		message.setRobotnik(null);
	}
}