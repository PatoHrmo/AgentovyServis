package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;
import OSPRNG.UniformContinuousRNG;

//meta! id="41"
public class ZadavanieObjednavky extends Process
{
	private UniformContinuousRNG genDobyZadavaniaObjednavky;
	private UniformContinuousRNG genDobyPreberaniaAutaOdZakaznika;
	public ZadavanieObjednavky(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
		genDobyZadavaniaObjednavky = new UniformContinuousRNG(70d, 310d);
		genDobyPreberaniaAutaOdZakaznika = new UniformContinuousRNG(80d, 160d);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentVybavovaci", id="42", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.koniecZadavaniaObjednavky);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.diktujeObjednavku);
		((MyMessage)message).setCinnostRobotnika(Cinnosti.zapisujeObjednavku);
		hold(genDobyZadavaniaObjednavky.sample(),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecZadavaniaObjednavky :
			message.setCode(Mc.koniecPreberaniaAutaOdZakaznika);
			((MyMessage)message).setCinnostZakaznika(Cinnosti.odovzdavaAuto);
			((MyMessage)message).setCinnostRobotnika(Cinnosti.preberaAutoOdZakaznika);
			hold(genDobyPreberaniaAutaOdZakaznika.sample(),message);
			break;
		case Mc.koniecPreberaniaAutaOdZakaznika :
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaNaUvolnenieParkoviska1);
			((MyMessage)message).setCinnostRobotnika(Cinnosti.cakaNaUvolnenieParkoviska1);
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