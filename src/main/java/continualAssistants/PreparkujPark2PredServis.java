package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="99"
public class PreparkujPark2PredServis extends Process
{
	public PreparkujPark2PredServis(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPohybu", id="100", type="Start"
	public void processStart(MessageForm message)
	{
		
		((MyMessage)message).setCinnostRobotnika(Cinnosti.preparkuvavaPredServis);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.idOdParkoviska2PredServis);
		message.setCode(Mc.koniecPreparkovaniaPark2PredServis);
		hold(myAgent().casPreparkovaniaPark2PredServis(), message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreparkovaniaPark2PredServis :
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