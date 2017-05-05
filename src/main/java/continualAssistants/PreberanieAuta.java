package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;
import OSPRNG.UniformContinuousRNG;

//meta! id="94"
public class PreberanieAuta extends Process
{	
	private UniformContinuousRNG genDlzkyPreberania;
	public PreberanieAuta(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
		genDlzkyPreberania = new UniformContinuousRNG(80d, 160d);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentVybavovaci", id="95", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.koniecPreberaniaAutaOdZakaznika);
		((MyMessage)message).setCinnostRobotnika(Cinnosti.preberaAutoOdZakaznika);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.odovzdavaAuto);
		hold(genDlzkyPreberania.sample(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecPreberaniaAutaOdZakaznika:
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