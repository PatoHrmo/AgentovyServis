package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="97"
public class PreparkujServisDoPark1 extends Process
{
	public PreparkujServisDoPark1(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPohybu", id="98", type="Start"
	public void processStart(MessageForm message)
	{
		((MyMessage)message).setCinnostRobotnika(Cinnosti.preparkuvavaPredParkovisko1);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.ideNaParkovisko1);
		((MyMessage)message).setCasZaciatkuJazdy(mySim().currentTime());
		message.setCode(Mc.koniecPreparkovaniaNaParkovisko1);
		hold(myAgent().casPreparkovaniaServisParkovisko1(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreparkovaniaNaParkovisko1 :
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaNaParkovisku1);
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
	public AgentPohybu myAgent()
	{
		return (AgentPohybu)super.myAgent();
	}

}