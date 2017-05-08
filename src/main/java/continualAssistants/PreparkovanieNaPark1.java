package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="43"
public class PreparkovanieNaPark1 extends Process
{
	public PreparkovanieNaPark1(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentVybavovaci", id="44", type="Start"
	public void processStart(MessageForm message)
	{
//		message.setCode(Mc.koniecPreparkovaniaNaParkovisko1);
//		((MyMessage)message).setCinnostZakaznika(Cinnosti.ideNaParkovisko1);
//		((MyMessage)message).setCinnostRobotnika(Cinnosti.preparkuvavaPredParkovisko1);
//		hold(84.24,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{ case Mc.koniecPreparkovaniaNaParkovisko1 :
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaNaOpravuAuta);
			((MyMessage)message).setCinnostRobotnika(Cinnosti.necinny);
			assistantFinished(message);
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentVybavovaci myAgent()
	{
		return (AgentVybavovaci)super.myAgent();
	}

}