package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import continualAssistants.OdovzdavanieHotoveho;
import continualAssistants.PreparkovanieNaPark1;
import continualAssistants.PreparkovaniePredServis;
import continualAssistants.ZadavanieObjednavky;
import entity.Robotnik;
import managers.ManagerVybavovaci;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="15"
public class AgentVybavovaci extends Agent
{	
	private List<Robotnik> volnyPracovnici;
	private List<Robotnik> vsetciPracovnici;
	SimQueue<MyMessage> frontZakaznikovNaZadavanieObjednavky;
	private int pocetAutNaParkovisku2;
	public AgentVybavovaci(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecZadavaniaObjednavky);
		addOwnMessage(Mc.koniecPreberaniaAutaOdZakaznika);
		addOwnMessage(Mc.koniecPreparkovaniaNaParkovisko1);
		addOwnMessage(Mc.koniecPreparkovaniaNaParkoviskoPredServisom);
		addOwnMessage(Mc.koniecOdovzdaniaOpravenehoAuta);
		volnyPracovnici = new LinkedList<>();
		vsetciPracovnici = new LinkedList<>();
		pocetAutNaParkovisku2 = 0;
		frontZakaznikovNaZadavanieObjednavky = new SimQueue<>();
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
		new ManagerVybavovaci(Id.managerVybavovaci, mySim(), this);
		new OdovzdavanieHotoveho(Id.odovzdavanieHotoveho, mySim(), this);
		new ZadavanieObjednavky(Id.zadavanieObjednavky, mySim(), this);
		new PreparkovaniePredServis(Id.preparkovaniePredServis, mySim(), this);
		new PreparkovanieNaPark1(Id.preparkovanieNaPark1, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.prisielZakaznik);
		addOwnMessage(Mc.vypytajMiestoParkoviska1);
	}
	//meta! tag="end"

	public void nastavPocetPracovnikov(int pocetPracovnikov) {
		for(int i = 0; i<pocetPracovnikov;i++) {
			Robotnik rob = new Robotnik(mySim(),i);
			volnyPracovnici.add(rob);
			vsetciPracovnici.add(rob);
		}
	}
	public List<Robotnik> getVsetciPracovnici() {
		return vsetciPracovnici;
	}

	public boolean jeVolnyPracovnik() {
		return !volnyPracovnici.isEmpty();
	}

	public SimQueue<MyMessage> getFrontaLudiNaZadavanieObjednavky() {
		return frontZakaznikovNaZadavanieObjednavky;
	}
	/**
	 * metoda vrati volneho robotnika - odoberie ho zo zonamu volnychPracovnikov
	 * @return robotnik ktory je volny
	 */
	public Robotnik getVolnyRobotnik() {
		Robotnik robotnik = volnyPracovnici.remove(0);
		
		return robotnik;
	}

	public void uvolnenieRobotnika(MyMessage myMessage) {
		volnyPracovnici.add(myMessage.getRobotnik());
		myMessage.setRobotnik(null);
		
	}

	public void zvysPocetAutNaParkovisku2() {
		pocetAutNaParkovisku2++;
	}
	public void znizPocetAutNaParkovisku2() {
		pocetAutNaParkovisku2--;
	}
	public int getPocetLudiNaParkovisku2() {
		return pocetAutNaParkovisku2;
	}
}