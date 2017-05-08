package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="128"
public class PrechodRampouDnu extends Process
{
	private final int dlzkaPrechoduRampou = 7;
	public PrechodRampouDnu(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentRampy", id="129", type="Start"
	public void processStart(MessageForm message)
	{
		((MyMessage)message).setCode(Mc.koniecPreparkovaniaRampaServis);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.prechadzaRampouDnu);
		hold(dlzkaPrechoduRampou,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreparkovaniaRampaServis :
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
	public AgentRampy myAgent()
	{
		return (AgentRampy)super.myAgent();
	}

}