package agents;

import OSPABA.Agent;
import OSPABA.Simulation;
import managers.ManagerModelu;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="1"
public class AgentModelu extends Agent
{
	public AgentModelu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
	    spustiTok();
		
		
	}
	public void koniecReplikacie() {
		// TODO Auto-generated method stub
		
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerModelu(Id.managerModelu, mySim(), this);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.prichodZakaznika);
	}
	//meta! tag="end"

	
	public void spustiTok() {
		MyMessage sprava = new MyMessage(mySim(),null);
		sprava.setAddressee(Id.agentOkolia);
		sprava.setCode(Mc.stustiTok);
		manager().notice(sprava);
	}

	
}