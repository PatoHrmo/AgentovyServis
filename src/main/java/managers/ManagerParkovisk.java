package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
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
	}

	//meta! sender="AgentServisu", id="90", type="Notice"
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

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.dajAutoZParkoviska1:
			processDajAutoZParkoviska1(message);
		break;

		case Mc.rezervujMiestoParkoviska1:
			processRezervujMiestoParkoviska1(message);
		break;

		case Mc.prichodAutaNaParkovisko1:
			processPrichodAutaNaParkovisko1(message);
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