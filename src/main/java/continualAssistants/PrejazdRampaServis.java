package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;

//meta! id="54"
public class PrejazdRampaServis extends Process
{
	public PrejazdRampaServis(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentServisu", id="55", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.prichodOdRampyNaParkovisko1);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.ideOdRampyDoServisu);
		hold(13.32,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{	case Mc.prichodOdRampyNaParkovisko1:
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaNaZadanieObjednavky);
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
	public AgentServisu myAgent()
	{
		return (AgentServisu)super.myAgent();
	}

}