package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;
import OSPRNG.UniformContinuousRNG;

//meta! id="47"
public class OdovzdavanieHotoveho extends Process
{
	private UniformContinuousRNG genDlzkyOdovzdavania;
	public OdovzdavanieHotoveho(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
		genDlzkyOdovzdavania = new UniformContinuousRNG(123d, 257d);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentVybavovaci", id="48", type="Start"
	public void processStart(MessageForm message)
	{
		((MyMessage)message).setCinnostRobotnika(Cinnosti.odovzdavaAuto);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.preberaOpraveneAuto);
		message.setCode(Mc.koniecOdovzdaniaOpravenehoAuta);
		hold(genDlzkyOdovzdavania.sample(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecOdovzdaniaOpravenehoAuta:
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