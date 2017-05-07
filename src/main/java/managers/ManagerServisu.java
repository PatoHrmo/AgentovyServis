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
	public void processDajAutoZParkoviska1AgentOpravary(MessageForm message)
	{
		message.setAddressee(Id.agentParkovisk);
		message.setCode(Mc.dajAutoZParkoviska1);
		request(message);
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
	public void processDajAutoZParkoviska2AgentVybavovaci(MessageForm message)
	{
		message.setAddressee(Id.agentParkovisk);
		request(message);
	}

	//meta! sender="AgentOpravary", id="67", type="Notice"
	public void processPrichodAutaNaParkovisko2(MessageForm message)
	{
		message.setAddressee(Id.agentVybavovaci);
		message.setCode(Mc.prichodAutaNaParkovisko2);
		notice(message);
	}

	//meta! sender="AgentVybavovaci", id="60", type="Notice"
	public void processOdchodObsluzenehoZakaznika(MessageForm message)
	{
		message.setAddressee(Id.agentPohybu);
		message.setCode(Mc.preparkujServisRampa);
		request(message);
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
	public void processRezervujMiestoParkoviska1AgentVybavovaci(MessageForm message)
	{
		message.setAddressee(Id.agentParkovisk);
		message.setCode(Mc.rezervujMiestoParkoviska1);
		request(message);
	}

	//meta! sender="AgentOpravary", id="66", type="Request"
	public void processDajAutoNaParkovisko2AgentOpravary(MessageForm message)
	{
		message.setAddressee(Id.agentParkovisk);
		request(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Removed from model"
	public void processDajZakaznikaCakajucehoNaZadanieObjeddnavky(MessageForm message)
	{
		//MyMessage mes = myAgent().getFrontaPredZadavanimObjednavky().dequeue();
		//((MyMessage)message).setZakaznik(mes.getZakaznik());
		//response(message);
	}

	//meta! sender="AgentParkovisk", id="88", type="Response"
	public void processDajAutoZParkoviska1AgentParkovisk(MessageForm message)
	{
		response(message);
	}

	//meta! sender="AgentParkovisk", id="80", type="Response"
	public void processRezervujMiestoParkoviska1AgentParkovisk(MessageForm message)
	{
		response(message);
	}

	//meta! sender="AgentVybavovaci", id="89", type="Request"
	public void processPreparkujNaParkovisko1AgentVybavovaci(MessageForm message)
	{
		message.setAddressee(Id.agentPohybu);
		message.setCode(Mc.preparkujNaParkovisko1);
		request(message);
	}

	//meta! sender="AgentPohybu", id="85", type="Response"
	public void processPreparkujNaParkovisko1AgentPohybu(MessageForm message)
	{
		// na uvolnenie prac 1
		response(message);
		
		// na parkovisko umiestni auto
		MyMessage sprava1 = new MyMessage((MyMessage)message);
		sprava1.setAddressee(Id.agentParkovisk);
		sprava1.setCode(Mc.umietniAutoNaParkovisko1);
		request(sprava1);
	}

	//meta! sender="AgentParkovisk", id="106", type="Response"
	public void processUmietniAutoNaParkovisko1(MessageForm message)
	{
		message.setAddressee(Id.agentOpravary);
		message.setCode(Mc.prichodAutaNaParkovisko1);
		notice(message);
	}

	//meta! sender="AgentParkovisk", id="109", type="Response"
	public void processDajAutoNaParkovisko2AgentParkovisk(MessageForm message)
	{
		response(message);
	}

	//meta! sender="AgentParkovisk", id="112", type="Response"
	public void processDajAutoZParkoviska2AgentParkovisk(MessageForm message)
	{
		message.setAddressee(Id.agentPohybu);
		message.setCode(Mc.preparkujParkovisko2PredServis);
		request(message);
	}

	//meta! sender="AgentPohybu", id="115", type="Response"
	public void processPreparkujRampaServis(MessageForm message)
	{
	}

	//meta! sender="AgentPohybu", id="116", type="Response"
	public void processPreparkujServisRampa(MessageForm message)
	{
		message.setCode(Mc.obsluzZakaznika);
		response(message);
	}

	//meta! sender="AgentPohybu", id="114", type="Response"
	public void processPreparkujParkovisko2PredServis(MessageForm message)
	{
		message.setCode(Mc.dajAutoZParkoviska2);
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

		case Mc.obsluzZakaznika:
			processObsluzZakaznika(message);
		break;

		case Mc.odchodObsluzenehoZakaznika:
			processOdchodObsluzenehoZakaznika(message);
		break;

		case Mc.umietniAutoNaParkovisko1:
			processUmietniAutoNaParkovisko1(message);
		break;

		case Mc.rezervujMiestoParkoviska1:
			switch (message.sender().id())
			{
			case Id.agentVybavovaci:
				processRezervujMiestoParkoviska1AgentVybavovaci(message);
			break;

			case Id.agentParkovisk:
				processRezervujMiestoParkoviska1AgentParkovisk(message);
			break;
			}
		break;

		case Mc.dajAutoZParkoviska1:
			switch (message.sender().id())
			{
			case Id.agentOpravary:
				processDajAutoZParkoviska1AgentOpravary(message);
			break;

			case Id.agentParkovisk:
				processDajAutoZParkoviska1AgentParkovisk(message);
			break;
			}
		break;

		case Mc.dajAutoZParkoviska2:
			switch (message.sender().id())
			{
			case Id.agentParkovisk:
				processDajAutoZParkoviska2AgentParkovisk(message);
			break;

			case Id.agentVybavovaci:
				processDajAutoZParkoviska2AgentVybavovaci(message);
			break;
			}
		break;

		case Mc.preparkujServisRampa:
			processPreparkujServisRampa(message);
		break;

		case Mc.prichodAutaNaParkovisko2:
			processPrichodAutaNaParkovisko2(message);
		break;

		case Mc.preparkujRampaServis:
			processPreparkujRampaServis(message);
		break;

		case Mc.autoBoloPreparkovaneNaParkovisko1:
			processAutoBoloPreparkovaneNaParkovisko1(message);
		break;

		case Mc.preparkujNaParkovisko1:
			switch (message.sender().id())
			{
			case Id.agentPohybu:
				processPreparkujNaParkovisko1AgentPohybu(message);
			break;

			case Id.agentVybavovaci:
				processPreparkujNaParkovisko1AgentVybavovaci(message);
			break;
			}
		break;

		case Mc.dajAutoNaParkovisko2:
			switch (message.sender().id())
			{
			case Id.agentOpravary:
				processDajAutoNaParkovisko2AgentOpravary(message);
			break;

			case Id.agentParkovisk:
				processDajAutoNaParkovisko2AgentParkovisk(message);
			break;
			}
		break;

		case Mc.preparkujParkovisko2PredServis:
			processPreparkujParkovisko2PredServis(message);
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