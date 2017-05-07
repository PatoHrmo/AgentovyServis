package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="120"
public class AgentRampy extends Agent
{
	public AgentRampy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
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
		new ManagerRampy(Id.managerRampy, mySim(), this);
		new PrechodRampouDnu(Id.prechodRampouDnu, mySim(), this);
		new PrechodRampouVon(Id.prechodRampouVon, mySim(), this);
		addOwnMessage(Mc.obsluzZakaznika);
	}
	//meta! tag="end"
}