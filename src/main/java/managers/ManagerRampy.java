package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="120"
public class ManagerRampy extends Manager
{
	public ManagerRampy(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentServisu", id="124", type="Response"
	public void processObsluzZakaznikaAgentServisu(MessageForm message)
	{
		if(myAgent().rampaVonJeVolna()) {
			myAgent().obsadRampuVon();
			message.setAddressee(Id.prechodRampouVon);
			startContinualAssistant(message);
		} else {
			myAgent().pridajZakaznikaDoFrontyPredRampouVon((MyMessage)message);
		}
		
		
	}

	//meta! sender="AgentModelu", id="122", type="Request"
	public void processObsluzZakaznikaAgentModelu(MessageForm message)
	{
		if(myAgent().rampaDnuJeVolna()) {
			message.setAddressee(Id.prechodRampouDnu);
			startContinualAssistant(message);
			myAgent().obsadRampuDnu();
		} else {
			myAgent().pridajZakaznikaDoFrontyPredRampouDnu((MyMessage)message);
		}
		
	}

	//meta! sender="PrechodRampouDnu", id="129", type="Finish"
	public void processFinishPrechodRampouDnu(MessageForm message)
	{
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.obsluzZakaznika);
		request(message);
		if(myAgent().predRampouDnuNiektoJe()) {
			MyMessage mess = myAgent().getZakaznikPredRampouDnu();
			mess.setAddressee(Id.prechodRampouDnu);
			startContinualAssistant(mess);
		} else {
			myAgent().uvolniRampuDnu();
		}
	}

	//meta! sender="PrechodRampouVon", id="127", type="Finish"
	public void processFinishPrechodRampouVon(MessageForm message)
	{
		message.setCode(Mc.obsluzZakaznika);
		response(message);
		if(myAgent().predRampouVonNiektoJe()) {
			MyMessage mess = myAgent().getZakaznikPredRampouVon();
			mess.setAddressee(Id.prechodRampouVon);
			startContinualAssistant(mess);
		} else {
			myAgent().uvolniRampuVon();
		}
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
		case Mc.obsluzZakaznika:
			switch (message.sender().id())
			{
			case Id.agentServisu:
				processObsluzZakaznikaAgentServisu(message);
			break;

			case Id.agentModelu:
				processObsluzZakaznikaAgentModelu(message);
			break;
			}
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.prechodRampouDnu:
				processFinishPrechodRampouDnu(message);
			break;

			case Id.prechodRampouVon:
				processFinishPrechodRampouVon(message);
			break;
			}
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentRampy myAgent()
	{
		return (AgentRampy)super.myAgent();
	}

}