package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="83"
public class ManagerPohybu extends Manager
{
	public ManagerPohybu(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentServisu", id="85", type="Request"
	public void processPreparkujNaParkovisko1(MessageForm message)
	{
		message.setAddressee(Id.preparkujServisDoPark1);
		startContinualAssistant(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		
		}
	}

	//meta! sender="PreparkujPark2PredServis", id="100", type="Finish"
	public void processFinishPreparkujPark2PredServis(MessageForm message)
	{
		message.setCode(Mc.preparkujParkovisko2PredServis);
		response(message);
	}

	//meta! sender="PreparkujServisDoPark1", id="98", type="Finish"
	public void processFinishPreparkujServisDoPark1(MessageForm message)
	{
		message.setCode(Mc.preparkujNaParkovisko1);
		response(message);
	}

	//meta! sender="ChojOdRampyKServisu", id="102", type="Finish"
	public void processFinishChojOdRampyKServisu(MessageForm message)
	{
		message.setCode(Mc.preparkujRampaServis);
		response(message);
	}

	//meta! sender="ChojOdServisuKRampe", id="104", type="Finish"
	public void processFinishChojOdServisuKRampe(MessageForm message)
	{
		message.setCode(Mc.preparkujServisRampa);
		response(message);
	}

	//meta! sender="AgentServisu", id="115", type="Request"
	public void processPreparkujRampaServis(MessageForm message)
	{
		message.setAddressee(Id.chojOdRampyKServisu);
		startContinualAssistant(message);
	}

	//meta! sender="AgentServisu", id="114", type="Request"
	public void processPreparkujParkovisko2PredServis(MessageForm message)
	{
		message.setAddressee(Id.preparkujPark2PredServis);
		startContinualAssistant(message);
	}

	//meta! sender="AgentServisu", id="116", type="Request"
	public void processPreparkujServisRampa(MessageForm message)
	{
		message.setAddressee(Id.chojOdServisuKRampe);
		startContinualAssistant(message);
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
		case Mc.preparkujRampaServis:
			processPreparkujRampaServis(message);
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.preparkujServisDoPark1:
				processFinishPreparkujServisDoPark1(message);
			break;

			case Id.chojOdRampyKServisu:
				processFinishChojOdRampyKServisu(message);
			break;

			case Id.chojOdServisuKRampe:
				processFinishChojOdServisuKRampe(message);
			break;

			case Id.preparkujPark2PredServis:
				processFinishPreparkujPark2PredServis(message);
			break;
			}
		break;

		case Mc.preparkujNaParkovisko1:
			processPreparkujNaParkovisko1(message);
		break;

		case Mc.preparkujParkovisko2PredServis:
			processPreparkujParkovisko2PredServis(message);
		break;

		case Mc.preparkujServisRampa:
			processPreparkujServisRampa(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentPohybu myAgent()
	{
		return (AgentPohybu)super.myAgent();
	}

}