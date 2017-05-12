package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;
import entity.Zakaznik;
import instantAssistants.*;

//meta! id="77"
public class AgentParkovisk extends Agent
{	
	private SimQueue<MyMessage> frontaParkovisko1;
	private SimQueue<MyMessage> frontaCakajucichNaRezervaciuParkoviska1;
	private SimQueue<MyMessage> frontaParkovisko2;
	private SimQueue<MyMessage> frontaCakajucichNaUvolnenieParkoviska2;
	private int pocetRezervovanychMiestParkoviska1;
	private final int VELKOST_PARKOVISKA1 = 6;
	private final int VELKOST_PARKOVISKA2 = 10;
	
	public AgentParkovisk(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		frontaParkovisko1 = new SimQueue<>();
		frontaCakajucichNaRezervaciuParkoviska1 = new SimQueue<>();
		frontaCakajucichNaUvolnenieParkoviska2 = new SimQueue<>();
		frontaParkovisko2 = new SimQueue<>();
		pocetRezervovanychMiestParkoviska1 = 0;
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		frontaParkovisko1 = new SimQueue<>();
		frontaCakajucichNaRezervaciuParkoviska1 = new SimQueue<>();
		frontaCakajucichNaUvolnenieParkoviska2 = new SimQueue<>();
		frontaParkovisko2 = new SimQueue<>();
		pocetRezervovanychMiestParkoviska1 = 0;
	}
	public void koniecReplikacie() {
		// TODO Auto-generated method stub
		
	}
	public void pridajZakaznikaNaParkovisko(MyMessage sprava) {
		frontaParkovisko1.add(sprava);
		pocetRezervovanychMiestParkoviska1--;
		if(frontaParkovisko1.size()==11) {
			System.out.println("na parkovisku 1 je 11 aut!!!!");
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerParkovisk(Id.managerParkovisk, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.dajAutoNaParkovisko2);
		addOwnMessage(Mc.umietniAutoNaParkovisko1);
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

	public Zakaznik getZakaznikNaParkovisku1() {
		return frontaParkovisko1.dequeue().getZakaznik();
	}

	public MessageForm getCakajucehoNaParkovisku1() {
		return frontaCakajucichNaRezervaciuParkoviska1.dequeue();
	}

	public boolean niektoCakaNaRezervaciuParkoviska1() {
		return !frontaCakajucichNaRezervaciuParkoviska1.isEmpty();
	}

	public boolean jeVolneMiestoNaParkovisku2() {
		if(frontaParkovisko2.size()<VELKOST_PARKOVISKA2) {
			return true;
		}
		return false;
	}

	public void pridajAutoNaPark2(MyMessage message) {
		frontaParkovisko2.enqueue(message);
	}

	public void pridajPracovnika2CakajucehoNaUvolneniePark2(MyMessage message) {
		frontaCakajucichNaUvolnenieParkoviska2.enqueue(message);
	}

	public Zakaznik getAutoZParkoviska2() {
		// TODO Auto-generated method stub
		return frontaParkovisko2.dequeue().getZakaznik();
	}

	public boolean niektoCakaNaParkovisko2() {
		return !frontaCakajucichNaUvolnenieParkoviska2.isEmpty();
	}

	public MyMessage getCakajucehoNaParkovisko2() {
		return frontaCakajucichNaUvolnenieParkoviska2.dequeue();
	}

	
}