package agents;

import OSPABA.Agent;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import OSPStat.WStat;
import continualAssistants.OdchodPoDlhomCakani;
import continualAssistants.PrejazdRampaServis;
import continualAssistants.PrejazdRampou;
import entity.Zakaznik;
import managers.ManagerServisu;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="4"
public class AgentServisu extends Agent
{	
	private SimQueue<MyMessage> frontaPredRampou;
	private SimQueue<MyMessage> frontaPredZadavanimObjednavky;
	private SimQueue<MyMessage> frontaAutPredOpravovnou;
	private SimQueue<MyMessage> frontaRobotnikovCakajucichNaUvolnenieParkoviska1;
	private final int velkostParkoviska1 = 6;
	private int pocetRezervovanychMiestNaParkovisku1;
	private boolean naRampeSmeromDnuNiektoJe;
	public AgentServisu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.prechodCezRampu);
		addOwnMessage(Mc.prichodOdRampyNaParkovisko1);
		frontaPredRampou = new SimQueue<>(new WStat(_mySim));
		frontaPredZadavanimObjednavky = new SimQueue<>(new WStat(_mySim));
		frontaAutPredOpravovnou = new SimQueue<>(new WStat(_mySim));
		frontaRobotnikovCakajucichNaUvolnenieParkoviska1 = new SimQueue<>(new WStat(_mySim));
		naRampeSmeromDnuNiektoJe = false;
		pocetRezervovanychMiestNaParkovisku1 = 0;
		
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerServisu(Id.managerServisu, mySim(), this);
		new OdchodPoDlhomCakani(Id.odchodPoDlhomCakani, mySim(), this);
		new PrejazdRampou(Id.prejazdRampou, mySim(), this);
		new PrejazdRampaServis(Id.prejazdRampaServis, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.odchodObsluzenehoZakaznika);
		addOwnMessage(Mc.dajZakaznikaCakajucehoNaZadanieObjeddnavky);
		addOwnMessage(Mc.autoBoloPreparkovaneNaParkovisko1);
		addOwnMessage(Mc.vypytajMiestoParkoviska1);
		addOwnMessage(Mc.vypytajMiestoParkoviska2);
	}
	//meta! tag="end"

	public SimQueue<MyMessage> getFrontaPredRampou() {
		return frontaPredRampou;
	}

	public SimQueue<MyMessage> getFrontaPredZadavanimObjednavky() {
		return frontaPredZadavanimObjednavky;
	}

	public boolean isNaRampeSmeromDnuNiektoJe() {
		return naRampeSmeromDnuNiektoJe;
	}

	public void setNaRampeSmeromDnuNiektoJe(boolean naRampeSmeromDnuNiektoJe) {
		this.naRampeSmeromDnuNiektoJe = naRampeSmeromDnuNiektoJe;
	}

	public boolean existujeNerezervovaneMiestoParkoviska1() {
		int pocetObsadenych = frontaAutPredOpravovnou.size()+pocetRezervovanychMiestNaParkovisku1;
		if(pocetObsadenych>=velkostParkoviska1) {
			return false;
		}
		return true;
	}

	public void rezervujMiestoParkoviska1() {
		pocetRezervovanychMiestNaParkovisku1++;
	}

	public void pridajRobotnikaCakajucehoNaParkovisko1(MessageForm message) {
		frontaRobotnikovCakajucichNaUvolnenieParkoviska1.enqueue((MyMessage)message);
	}

	public void pridajAutoDoFrontyPredOpravovnou(MyMessage message) {
		pocetRezervovanychMiestNaParkovisku1--;
		frontaAutPredOpravovnou.add(message);
	}

	public Zakaznik getZakaznikPredOpravovnou() {
		return frontaAutPredOpravovnou.dequeue().getZakaznik();
	}
}