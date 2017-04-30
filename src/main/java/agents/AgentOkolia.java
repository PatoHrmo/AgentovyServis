package agents;

import OSPABA.Agent;
import OSPABA.Simulation;
import continualAssistants.PlanovacPrichodu;
import managers.ManagerOkolia;
import simulation.Id;
import simulation.Mc;

//meta! id="3"
public class AgentOkolia extends Agent
{
	public AgentOkolia(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.novyZakaznik);
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
		new ManagerOkolia(Id.managerOkolia, mySim(), this);
		new PlanovacPrichodu(Id.planovacPrichodu, mySim(), this);
		addOwnMessage(Mc.odchodZakaznika);
		addOwnMessage(Mc.stustiTok);
	}
	//meta! tag="end"
}
