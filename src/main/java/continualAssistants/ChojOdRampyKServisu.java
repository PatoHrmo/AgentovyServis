package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="101"
public class ChojOdRampyKServisu extends Process
{
	public ChojOdRampyKServisu(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPohybu", id="102", type="Start"
	public void processStart(MessageForm message)
	{
		((MyMessage)message).setCinnostZakaznika(Cinnosti.ideOdRampyDoServisu);
		message.setCode(Mc.koniecPreparkovaniaRampaServis);
		hold(myAgent().casPrechoduRampaServis(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreparkovaniaRampaServis:
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