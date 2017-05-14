package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
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
	private Stat replStatFrontaParkovisko1;
	private Stat replStatFrontaParkovisko2;
	public AgentParkovisk(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		frontaParkovisko1 = new SimQueue<>(new WStat(mySim));
		frontaCakajucichNaRezervaciuParkoviska1 = new SimQueue<>(new WStat(mySim));
		frontaCakajucichNaUvolnenieParkoviska2 = new SimQueue<>(new WStat(mySim));
		frontaParkovisko2 = new SimQueue<>(new WStat(mySim));
		pocetRezervovanychMiestParkoviska1 = 0;
		replStatFrontaParkovisko1 = new Stat();
		replStatFrontaParkovisko2 = new Stat();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		frontaParkovisko1 = new SimQueue<>(new WStat(mySim()));
		frontaCakajucichNaRezervaciuParkoviska1 = new SimQueue<>(new WStat(mySim()));
		frontaCakajucichNaUvolnenieParkoviska2 = new SimQueue<>(new WStat(mySim()));
		frontaParkovisko2 = new SimQueue<>(new WStat(mySim()));
		pocetRezervovanychMiestParkoviska1 = 0;
	}
	public void koniecReplikacie() {
		frontaParkovisko2.lengthStatistic().addSample(frontaParkovisko2.size());
		frontaParkovisko1.lengthStatistic().addSample(frontaParkovisko1.size());
		replStatFrontaParkovisko1.addSample(frontaParkovisko1.lengthStatistic().mean());
		replStatFrontaParkovisko2.addSample(frontaParkovisko2.lengthStatistic().mean());
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

	public int getPocetAutPark1() {
		// TODO Auto-generated method stub
		return frontaParkovisko1.size();
	}
	public int getPocetAutPark2() {
		// TODO Auto-generated method stub
		return frontaParkovisko2.size();
	}

	public double getVytazenie1() {
		
		return (frontaParkovisko1.lengthStatistic().mean()/VELKOST_PARKOVISKA1)*100;
	}
	public double getVytazenie2() {
		// TODO Auto-generated method stub
		return (frontaParkovisko2.lengthStatistic().mean()/VELKOST_PARKOVISKA2)*100;
	}

	public double[] getReplVytazenie1() {
		double is[] = new double[2];
		if(replStatFrontaParkovisko1.sampleSize()>2) {
			is= replStatFrontaParkovisko1.confidenceInterval_90();
			is[0] = (is[0]/VELKOST_PARKOVISKA1)*100;
			is[1] = (is[1]/VELKOST_PARKOVISKA1)*100;
		}
		return is;
	}
	public double[] getReplVytazenie2() {
		double is[] = new double[2];
		if(replStatFrontaParkovisko2.sampleSize()>2) {
			is= replStatFrontaParkovisko2.confidenceInterval_90();
			is[0] = (is[0]/VELKOST_PARKOVISKA2)*100;
			is[1] = (is[1]/VELKOST_PARKOVISKA2)*100;
		}
		return is;
	}

	public void resetujReplikacneStatistiky() {
		replStatFrontaParkovisko1.clear();
		replStatFrontaParkovisko2.clear();
		
	}

	
}