package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentOpravary;
import simulation.Mc;

//meta! id="17"
public class ManagerOpravary extends Manager
{
	public ManagerOpravary(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentServisu", id="65", type="Response"
	public void processDajAutoZParkoviska1(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="64", type="Notice"
	public void processPrichodAutaNaParkovisko1(MessageForm message)
	{
	}

	//meta! sender="Oprava", id="51", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="66", type="Response"
	public void processVypytajMiestoParkoviska2(MessageForm message)
	{
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
		case Mc.dajAutoZParkoviska1:
			processDajAutoZParkoviska1(message);
		break;

		case Mc.prichodAutaNaParkovisko1:
			processPrichodAutaNaParkovisko1(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.vypytajMiestoParkoviska2:
			processVypytajMiestoParkoviska2(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOpravary myAgent()
	{
		return (AgentOpravary)super.myAgent();
	}

}
