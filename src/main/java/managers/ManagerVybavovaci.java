package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentVybavovaci;
import simulation.Id;
import simulation.Mc;

//meta! id="15"
public class ManagerVybavovaci extends Manager
{
	public ManagerVybavovaci(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentServisu", id="61", type="Response"
	public void processDajAutoZParkoviska2(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="63", type="Notice"
	public void processPrichodAutaNaParkovisko2(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="39", type="Notice"
	public void processPrisielZakaznik(MessageForm message)
	{
		if(myAgent().jeVolnyPracovnik()) {
			
		} else {
			myAgent().zvysPocetLudiCakajucichNaZadanieObjednavky();
		}
	}

	//meta! sender="PreparkovanieNaPark1", id="44", type="Finish"
	public void processFinishPreparkovanieNaPark1(MessageForm message)
	{
	}

	//meta! sender="ZadavanieObjednavky", id="42", type="Finish"
	public void processFinishZadavanieObjednavky(MessageForm message)
	{
	}

	//meta! sender="PreparkovaniePredServis", id="46", type="Finish"
	public void processFinishPreparkovaniePredServis(MessageForm message)
	{
	}

	//meta! sender="OdovzdavanieHotoveho", id="48", type="Finish"
	public void processFinishOdovzdavanieHotoveho(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="58", type="Response"
	public void processVypytajMiestoParkoviska1(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.preparkovanieNaPark1:
				processFinishPreparkovanieNaPark1(message);
			break;

			case Id.zadavanieObjednavky:
				processFinishZadavanieObjednavky(message);
			break;

			case Id.preparkovaniePredServis:
				processFinishPreparkovaniePredServis(message);
			break;

			case Id.odovzdavanieHotoveho:
				processFinishOdovzdavanieHotoveho(message);
			break;
			}
		break;

		case Mc.vypytajMiestoParkoviska1:
			processVypytajMiestoParkoviska1(message);
		break;

		case Mc.prichodAutaNaParkovisko2:
			processPrichodAutaNaParkovisko2(message);
		break;

		case Mc.prisielZakaznik:
			processPrisielZakaznik(message);
		break;

		case Mc.dajAutoZParkoviska2:
			processDajAutoZParkoviska2(message);
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
