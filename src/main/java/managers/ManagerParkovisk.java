package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import entity.Zakaznik;
import instantAssistants.*;

//meta! id="77"
public class ManagerParkovisk extends Manager
{
	public ManagerParkovisk(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentServisu", id="88", type="Request"
	public void processDajAutoZParkoviska1(MessageForm message)
	{	
		Zakaznik zak = myAgent().getZakaznikNaParkovisku1();
		((MyMessage)message).setZakaznik(zak);
		response(message);
		if(myAgent().niektoCakaNaRezervaciuParkoviska1()) {
			myAgent().rezervujMiesto();
			response(myAgent().getCakajucehoNaParkovisku1());
		}
	}

	//meta! userInfo="Removed from model"
	public void processPrichodAutaNaParkovisko1(MessageForm message)
	{
	}

	//meta! sender="AgentServisu", id="80", type="Request"
	public void processRezervujMiestoParkoviska1(MessageForm message)
	{
		if(myAgent().jeVolneMiestoNaParkovisku1()) {
			myAgent().rezervujMiesto();
			response(message);
		} else {
			myAgent().pridajPracovnikaCakajucehoNaRezervaciuParkoviska1(message);
		}
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentServisu", id="106", type="Request"
	public void processUmietniAutoNaParkovisko1(MessageForm message)
	{
		myAgent().pridajZakaznikaNaParkovisko((MyMessage)message);
		response(message);
	}

	//meta! sender="AgentServisu", id="109", type="Request"
	public void processDajAutoNaParkovisko2(MessageForm message)
	{
		if(myAgent().jeVolneMiestoNaParkovisku2()) {
			myAgent().pridajAutoNaPark2((MyMessage)message);
			response(message);
		} else {
			myAgent().pridajPracovnika2CakajucehoNaUvolneniePark2((MyMessage)message);
		}
	}

	//meta! sender="AgentServisu", id="112", type="Request"
	public void processDajAutoZParkoviska2(MessageForm message)
	{
		Zakaznik zak = myAgent().getAutoZParkoviska2();
		((MyMessage)message).setZakaznik(zak);
		response(message);
		if(myAgent().niektoCakaNaParkovisko2()) {
			MyMessage mess = myAgent().getCakajucehoNaParkovisko2();
			myAgent().pridajAutoNaPark2(mess);
			response(mess);
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
		case Mc.dajAutoNaParkovisko2:
			processDajAutoNaParkovisko2(message);
		break;

		case Mc.dajAutoZParkoviska2:
			processDajAutoZParkoviska2(message);
		break;

		case Mc.umietniAutoNaParkovisko1:
			processUmietniAutoNaParkovisko1(message);
		break;

		case Mc.rezervujMiestoParkoviska1:
			processRezervujMiestoParkoviska1(message);
		break;

		case Mc.dajAutoZParkoviska1:
			processDajAutoZParkoviska1(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentParkovisk myAgent()
	{
		return (AgentParkovisk)super.myAgent();
	}

}