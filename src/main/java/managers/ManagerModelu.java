package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentModelu;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="1"
public class ManagerModelu extends Manager
{
	public ManagerModelu(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		
		

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentServisu", id="11", type="Response"
	public void processObsluzZakaznika(MessageForm message)
	{
	}

	//meta! sender="AgentOkolia", id="9", type="Notice"
	public void processPrichodZakaznika(MessageForm message)
	{
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.obsluzZakaznika);
		request(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.prichodZakaznika:
			processPrichodZakaznika(message);
		break;

		case Mc.obsluzZakaznika:
			processObsluzZakaznika(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentModelu myAgent()
	{
		return (AgentModelu)super.myAgent();
	}

}
