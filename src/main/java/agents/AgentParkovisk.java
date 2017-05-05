package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="77"
public class AgentParkovisk extends Agent
{	
	private SimQueue<MyMessage> frontaParkovisko1;
	private SimQueue<MyMessage> frontaCakajucichNaRezervaciuParkoviska1;
	private int pocetRezervovanychMiestParkoviska1;
	private final int VELKOST_PARKOVISKA1 = 6;
	private final int VELKOST_PARKOVISKA2 = 10;
	
	public AgentParkovisk(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		frontaParkovisko1 = new SimQueue<>();
		frontaCakajucichNaRezervaciuParkoviska1 = new SimQueue<>();
		pocetRezervovanychMiestParkoviska1 = 0;
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}
	public void pridajZakaznikaNaParkovisko(MyMessage sprava) {
		frontaParkovisko1.add(sprava);
		if(frontaParkovisko1.size()==11) {
			System.out.println("na parkovisku 1 je 11 aut!!!!");
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerParkovisk(Id.managerParkovisk, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.prichodAutaNaParkovisko1);
		addOwnMessage(Mc.rezervujMiestoParkoviska1);
	}
	//meta! tag="end"

	public boolean jeVolneMiestoNaParkovisku1() {
		if(pocetRezervovanychMiestParkoviska1+frontaParkovisko1.size()<VELKOST_PARKOVISKA1)
			return true;
		return false;
	}

	public void rezervujMiesto() {
		pocetRezervovanychMiestParkoviska1++;
	}

	public void pridajPracovnikaCakajucehoNaRezervaciuParkoviska1(MessageForm message) {
		frontaCakajucichNaRezervaciuParkoviska1.enqueue((MyMessage)message);
	}
}