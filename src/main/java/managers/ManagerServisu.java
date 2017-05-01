package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentServisu;
import entity.Cinnosti;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="4"
public class ManagerServisu extends Manager
{
	public ManagerServisu(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentOpravary", id="65", type="Request"
	public void processDajAutoZParkoviska1(MessageForm message)
	{
		((MyMessage)message).setZakaznik(myAgent().getZakaznikPredOpravovnou());
		response(message);
	}

	//meta! sender="AgentModelu", id="11", type="Request"
	public void processObsluzZakaznika(MessageForm message)
	{
		if(myAgent().getFrontaPredRampou().isEmpty() && !myAgent().isNaRampeSmeromDnuNiektoJe()) {
			message.setAddressee(Id.prejazdRampou);
			myAgent().setNaRampeSmeromDnuNiektoJe(true);
			startContinualAssistant(message);
		} else {
			myAgent().getFrontaPredRampou().enqueue((MyMessage)message);
		}
	}

	//meta! sender="AgentVybavovaci", id="61", type="Request"
	public void processDajAutoZParkoviska2(MessageForm message)
	{
	}

	//meta! sender="AgentOpravary", id="67", type="Notice"
	public void processPrichodAutaNaParkovisko2(MessageForm message)
	{
	}

	//meta! sender="AgentVybavovaci", id="60", type="Notice"
	public void processOdchodObsluzenehoZakaznika(MessageForm message)
	{
	}

	//meta! sender="PrejazdRampou", id="37", type="Finish"
	public void processFinishPrejazdRampou(MessageForm message)
	{
		((MyMessage)message).setCasZaciatkuJazdy(mySim().currentTime());
		message.setAddressee(Id.prejazdRampaServis);
		startContinualAssistant(message);
		myAgent().setNaRampeSmeromDnuNiektoJe(false);
		if(!myAgent().getFrontaPredRampou().isEmpty() && !myAgent().isNaRampeSmeromDnuNiektoJe()) {
			MyMessage m = myAgent().getFrontaPredRampou().dequeue();
			myAgent().setNaRampeSmeromDnuNiektoJe(true);
			m.setAddressee(Id.prejazdRampou);
			startContinualAssistant(m);
		}
	}

	//meta! sender="OdchodPoDlhomCakani", id="53", type="Finish"
	public void processFinishOdchodPoDlhomCakani(MessageForm message)
	{
	}

	//meta! sender="PrejazdRampaServis", id="55", type="Finish"
	public void processFinishPrejazdRampaServis(MessageForm message)
	{
		myAgent().getFrontaPredZadavanimObjednavky().enqueue((MyMessage)message);
		message.setAddressee(Id.agentVybavovaci);
		message.setCode(Mc.prisielZakaznik);
		notice(message);
		
	}

	//meta! sender="AgentVybavovaci", id="62", type="Notice"
	public void processAutoBoloPreparkovaneNaParkovisko1(MessageForm message)
	{
		myAgent().pridajAutoDoFrontyPredOpravovnou((MyMessage)message);
		message.setAddressee(Id.agentOpravary);
		message.setCode(Mc.prichodAutaNaParkovisko1);
		notice(message);
	}

	//meta! sender="AgentVybavovaci", id="58", type="Request"
	public void processVypytajMiestoParkoviska1(MessageForm message)
	{
		if(myAgent().existujeNerezervovaneMiestoParkoviska1()){
			myAgent().rezervujMiestoParkoviska1();
			response(message);
		} else {
			myAgent().pridajRobotnikaCakajucehoNaParkovisko1(message);
		}
	}

	//meta! sender="AgentOpravary", id="66", type="Request"
	public void processVypytajMiestoParkoviska2(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentVybavovaci", id="70", type="Request"
	public void processDajZakaznikaCakajucehoNaZadanieObjeddnavky(MessageForm message)
	{
		MyMessage mes = myAgent().getFrontaPredZadavanimObjednavky().dequeue();
		((MyMessage)message).setZakaznik(mes.getZakaznik());
		response(message);
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
		case Mc.vypytajMiestoParkoviska1:
			processVypytajMiestoParkoviska1(message);
		break;

		case Mc.obsluzZakaznika:
			processObsluzZakaznika(message);
		break;

		case Mc.prichodAutaNaParkovisko2:
			processPrichodAutaNaParkovisko2(message);
		break;

		case Mc.odchodObsluzenehoZakaznika:
			processOdchodObsluzenehoZakaznika(message);
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.prejazdRampou:
				processFinishPrejazdRampou(message);
			break;

			case Id.odchodPoDlhomCakani:
				processFinishOdchodPoDlhomCakani(message);
			break;

			case Id.prejazdRampaServis:
				processFinishPrejazdRampaServis(message);
			break;
			}
		break;

		case Mc.vypytajMiestoParkoviska2:
			processVypytajMiestoParkoviska2(message);
		break;

		case Mc.dajAutoZParkoviska1:
			processDajAutoZParkoviska1(message);
		break;

		case Mc.dajZakaznikaCakajucehoNaZadanieObjeddnavky:
			processDajZakaznikaCakajucehoNaZadanieObjeddnavky(message);
		break;

		case Mc.autoBoloPreparkovaneNaParkovisko1:
			processAutoBoloPreparkovaneNaParkovisko1(message);
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
	public AgentServisu myAgent()
	{
		return (AgentServisu)super.myAgent();
	}

}