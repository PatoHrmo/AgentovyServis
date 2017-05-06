package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentOkolia;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="3"
public class ManagerOkolia extends Manager
{
	public ManagerOkolia(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentModelu", id="10", type="Notice"
	public void processOdchodZakaznika(MessageForm message)
	{
	}

	//meta! sender="PlanovacPrichodu", id="24", type="Finish"
	public void processFinish(MessageForm message)
	{
		message.setAddressee(Id.agentModelu);
		message.setCode(Mc.prichodZakaznika);
		myAgent().pridajZakaznikaDoSystemu(((MyMessage)message).getZakaznik());
		notice(message);
	}

	//meta! sender="AgentModelu", id="8", type="Notice"
	public void processStustiTok(MessageForm message)
	{
		message.setAddressee(Id.planovacPrichodu);
		
		startContinualAssistant(message);
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
		case Mc.odchodZakaznika:
			processOdchodZakaznika(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.stustiTok:
			processStustiTok(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

}