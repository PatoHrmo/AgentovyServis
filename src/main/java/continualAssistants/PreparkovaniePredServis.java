package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="45"
public class PreparkovaniePredServis extends Process
{
	public PreparkovaniePredServis(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentVybavovaci", id="46", type="Start"
	public void processStart(MessageForm message)
	{
		((MyMessage)message).setCasZaciatkuJazdy(mySim().currentTime());
		((MyMessage)message).setCinnostRobotnika(Cinnosti.preparkuvavaPredServis);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.idOdParkoviska2PredServis);
		message.setCode(Mc.koniecPreparkovaniaNaParkoviskoPredServisom);
		hold(110.16,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreparkovaniaNaParkoviskoPredServisom :
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