package continualAssistants;

import OSPABA.*;
import simulation.*;
import util.DobaOpravyGen;
import agents.*;
import entity.Cinnosti;
import OSPABA.Process;
import OSPRNG.EmpiricPair;
import OSPRNG.EmpiricRNG;
import OSPRNG.UniformDiscreteRNG;

//meta! id="50"
public class Oprava extends Process
{	
	EmpiricRNG genPocetOprav;
	DobaOpravyGen genDobaJednejOpravy;
	public Oprava(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
		genPocetOprav = new EmpiricRNG(
				new EmpiricPair(new UniformDiscreteRNG(1, 1),0.05442176870748299),
				new EmpiricPair(new UniformDiscreteRNG(2, 2),0.320578231292517),
				new EmpiricPair(new UniformDiscreteRNG(3, 3),0.30612244897959184),
				new EmpiricPair(new UniformDiscreteRNG(4, 4),0.2108843537414966),
				new EmpiricPair(new UniformDiscreteRNG(5, 5),0.09863945578231292),
				new EmpiricPair(new UniformDiscreteRNG(6, 6),0.00935374149659864));
		genDobaJednejOpravy = new DobaOpravyGen();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOpravary", id="51", type="Start"
	public void processStart(MessageForm message)
	{
		double dlzkaOpravy = 0;
		int pocetOprav = (int)genPocetOprav.sample();
		for(int i = 0; i<pocetOprav;i++) {
			dlzkaOpravy+=genDobaJednejOpravy.nextDlzkaOpravy();
		}
		dlzkaOpravy*=60;
		((MyMessage)message).getZakaznik().setDlzkaOpravy(dlzkaOpravy);
		((MyMessage)message).setCinnostRobotnika(Cinnosti.opravujeAuto);
		((MyMessage)message).setCinnostZakaznika(Cinnosti.jeVOpravovni);
		message.setCode(Mc.koniecOpravy);
		hold(dlzkaOpravy,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecOpravy : 
			((MyMessage)message).setCinnostRobotnika(Cinnosti.cakaNaUvolnenieParkoviska2);
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaVOpravovniNaParkovisko2);
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
	public AgentOpravary myAgent()
	{
		return (AgentOpravary)super.myAgent();
	}

}