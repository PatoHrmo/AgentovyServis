package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import continualAssistants.OdchodPoDlhomCakani;
import continualAssistants.OdovzdavanieHotoveho;
import continualAssistants.PlanovacNovehoDna;
import continualAssistants.PreberanieAuta;
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
	SimQueue<MyMessage> frontaZakaznikovSOpravenymAutom;
	public AgentVybavovaci(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecZadavaniaObjednavky);
		addOwnMessage(Mc.koniecPreberaniaAutaOdZakaznika);
		addOwnMessage(Mc.koniecPreparkovaniaNaParkovisko1);
		addOwnMessage(Mc.koniecPreparkovaniaNaParkoviskoPredServisom);
		addOwnMessage(Mc.koniecOdovzdaniaOpravenehoAuta);
		addOwnMessage(Mc.koniecTolerancieCakania);
		addOwnMessage(Mc.spustiPlanovacDna);
		addOwnMessage(Mc.novyDen);
		volnyPracovnici = new LinkedList<>();
		vsetciPracovnici = new LinkedList<>();
		frontZakaznikovNaZadavanieObjednavky = new SimQueue<>();
		frontaZakaznikovSOpravenymAutom = new SimQueue<>();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		spustiPlanovacDna();
		int pocetPracovnikov = vsetciPracovnici.size();
		vsetciPracovnici = new LinkedList<>();
		volnyPracovnici = new LinkedList<>();
		frontaZakaznikovSOpravenymAutom = new SimQueue<>();
		frontZakaznikovNaZadavanieObjednavky = new SimQueue<>();
		nastavPocetPracovnikov(pocetPracovnikov);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerVybavovaci(Id.managerVybavovaci, mySim(), this);
		new PreparkovanieNaPark1(Id.preparkovanieNaPark1, mySim(), this);
		new ZadavanieObjednavky(Id.zadavanieObjednavky, mySim(), this);
		new OdovzdavanieHotoveho(Id.odovzdavanieHotoveho, mySim(), this);
		new PreparkovaniePredServis(Id.preparkovaniePredServis, mySim(), this);
		new PreberanieAuta(Id.preberanieAuta, mySim(), this);
		new OdchodPoDlhomCakani(Id.odchodPoDlhomCakani, mySim(), this);
		new PlanovacNovehoDna(Id.planovacNovehoDna, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.prisielZakaznik);
		addOwnMessage(Mc.rezervujMiestoParkoviska1);
		addOwnMessage(Mc.preparkujNaParkovisko1);
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

	
	public int getPocetLudiNaParkovisku2() {
		return frontaZakaznikovSOpravenymAutom.size();
	}
	public MyMessage getZakaznikaCakajucehoSOpravenymAutom() {
		return frontaZakaznikovSOpravenymAutom.dequeue();
	}
	public void pridajZakaznikaSOpravenymAutom(MyMessage message) {
		frontaZakaznikovSOpravenymAutom.enqueue(message);
	}

	public MyMessage getZakaznikaKtoremuDoslaTrpezlivost(MyMessage myMessage) {
		for(MyMessage zakaznikCakajuciNaObsluhu : frontZakaznikovNaZadavanieObjednavky) {
			if(zakaznikCakajuciNaObsluhu.getZakaznik().getId()== myMessage.getZakaznik().getId()) {
				frontZakaznikovNaZadavanieObjednavky.remove(zakaznikCakajuciNaObsluhu);
				return zakaznikCakajuciNaObsluhu;
			}
		}
		return null;
	}
	public void spustiPlanovacDna() {
		MyMessage sprava = new MyMessage(mySim(),null);
		sprava.setAddressee(Id.agentVybavovaci);
		sprava.setCode(Mc.spustiPlanovacDna);
		manager().notice(sprava);
	}
}