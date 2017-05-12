package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentOpravary;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="17"
public class ManagerOpravary extends Manager
{
	
	public ManagerOpravary(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentServisu", id="65", type="Response"
	public void processDajAutoZParkoviska1(MessageForm message)
	{
		message.setAddressee(Id.oprava);
		((MyMessage)message).setRobotnik(myAgent().getVolnyPracovnik());
		startContinualAssistant(message);
	}

	//meta! sender="AgentServisu", id="64", type="Notice"
	public void processPrichodAutaNaParkovisko1(MessageForm message)
	{
		if(myAgent().jeVolnyPracovnik()) {
			message.setAddressee(Id.agentServisu);
			message.setCode(Mc.dajAutoZParkoviska1);
			request(message);
		} else {
			myAgent().pridajZakaznikaCakajucehoNaOpravu(message);
		}
	}

	//meta! sender="Oprava", id="51", type="Finish"
	public void processFinish(MessageForm message)
	{
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.dajAutoNaParkovisko2);
		myAgent().pridajZisk(((MyMessage)message).getZakaznik().getVyskaPlatby());
		request(message);
		
		
	}

	//meta! sender="AgentServisu", id="66", type="Response"
	public void processDajAutoNaParkovisko2(MessageForm message)
	{
		myAgent().uvolniPracovnika((MyMessage)message);
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.prichodAutaNaParkovisko2);
		notice(message);
		if(myAgent().getPocetAutPredOpravovnou()>0) {
			MyMessage mes = myAgent().getZakaznikaCakajucehoNaOpravu();
			mes.setAddressee(Id.agentServisu);
			mes.setCode(Mc.dajAutoZParkoviska1);
			request(mes);
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

		case Mc.prichodAutaNaParkovisko1:
			processPrichodAutaNaParkovisko1(message);
		break;

		case Mc.dajAutoNaParkovisko2:
			processDajAutoNaParkovisko2(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOpravary myAgent()
	{
		return (AgentOpravary)super.myAgent();
	}

}