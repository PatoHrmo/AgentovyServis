package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import entity.Zakaznik;

//meta! id="23"
public class PlanovacPrichodu extends Scheduler
{
	private double casPrichoduZakaznikov;
	private ExponentialRNG dobaPrichodu;
	public PlanovacPrichodu(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
		casPrichoduZakaznikov = 20;
		dobaPrichodu = new ExponentialRNG(casPrichoduZakaznikov);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
	}

	//meta! sender="AgentOkolia", id="24", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.novyZakaznik);
		hold(dobaPrichodu.sample(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.novyZakaznik:
			MyMessage novaSprava = new MyMessage((MyMessage)message);
			hold(dobaPrichodu.sample(),novaSprava);
			
			Zakaznik zak = new Zakaznik();
			((MyMessage)message).setZakaznik(zak);
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaPredRampou);
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
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

}