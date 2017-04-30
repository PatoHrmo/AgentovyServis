package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import continualAssistants.Oprava;
import entity.Robotnik;
import managers.ManagerOpravary;
import simulation.Id;
import simulation.Mc;

//meta! id="17"
public class AgentOpravary extends Agent
{	
	private List<Robotnik> volnyPracovnici;
	private List<Robotnik> pracujuciPracovnici;
	public AgentOpravary(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		volnyPracovnici = new LinkedList<>();
		pracujuciPracovnici = new LinkedList<>();
	}
	public void nastavPocetPracovnikov(int pocetPracovnikov) {
		for(int i = 0; i<pocetPracovnikov;i++) {
			volnyPracovnici.add(new Robotnik(mySim()));
		}
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
}
