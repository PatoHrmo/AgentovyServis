package agents;

import java.util.ArrayList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import continualAssistants.PlanovacPrichodu;
import entity.Zakaznik;
import managers.ManagerOkolia;
import simulation.Id;
import simulation.Mc;

//meta! id="3"
public class AgentOkolia extends Agent
{	
	private List<Zakaznik> vsetciZakazniciAktualneVSysteme;
	public AgentOkolia(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.novyZakaznik);
		vsetciZakazniciAktualneVSysteme = new ArrayList<>();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}
	public void pridajZakaznikaDoSystemu(Zakaznik zak) {
		vsetciZakazniciAktualneVSysteme.add(zak);
	}
	public List<Zakaznik> getZakaznikovVSysteme() {
		return vsetciZakazniciAktualneVSysteme;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOkolia(Id.managerOkolia, mySim(), this);
		new PlanovacPrichodu(Id.planovacPrichodu, mySim(), this);
		addOwnMessage(Mc.odchodZakaznika);
		addOwnMessage(Mc.stustiTok);
	}
	//meta! tag="end"
}