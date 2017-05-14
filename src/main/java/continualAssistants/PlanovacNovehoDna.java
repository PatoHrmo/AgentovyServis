package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import entity.Zakaznik;

//meta! id="23"
public class PlanovacNovehoDna extends Scheduler
{
	private final double dlzkaDnaVSekundach = 60*60*8;
	public PlanovacNovehoDna(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
	}

	//meta! sender="AgentOkolia", id="24", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.novyDen);
		hold(dlzkaDnaVSekundach,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.novyDen :
			MyMessage sprava = new MyMessage((MyMessage)message);
			hold(dlzkaDnaVSekundach,sprava);
			assistantFinished(message);
			break;
		
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