package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
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
	private Stat replStatDlzkaFrontuNaObsluhu;
	SimQueue<MyMessage> frontaZakaznikovSOpravenymAutom;
	private boolean najprvZadavanie;
	private Stat statDlzkaCakaniaNaOpravuVozidla;
	private Stat replStatDlzkaCakaniaNaOpravuVozidla;
	private Stat statDlzkaCakaniaNaZadanieObjednavky;
	private Stat replStatDlzkaCakaniaNaZadanieObjednavky;
	private Stat replStatPriemernyPocetPracujucich;
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
		frontZakaznikovNaZadavanieObjednavky = new SimQueue<>(new WStat(mySim));
		frontaZakaznikovSOpravenymAutom = new SimQueue<>(new WStat(mySim));
		replStatDlzkaCakaniaNaOpravuVozidla = new Stat();
		replStatDlzkaCakaniaNaZadanieObjednavky = new Stat();
		replStatDlzkaFrontuNaObsluhu = new Stat();
		replStatPriemernyPocetPracujucich = new Stat();
		najprvZadavanie = true;
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		spustiPlanovacDna();
		int pocetPracovnikov = vsetciPracovnici.size();
		vsetciPracovnici = new LinkedList<>();
		volnyPracovnici = new LinkedList<>();
		frontaZakaznikovSOpravenymAutom = new SimQueue<>(new WStat(_mySim));
		frontZakaznikovNaZadavanieObjednavky = new SimQueue<>(new WStat(_mySim));
		nastavPocetPracovnikov(pocetPracovnikov);
		statDlzkaCakaniaNaOpravuVozidla = new Stat();
		statDlzkaCakaniaNaZadanieObjednavky = new Stat();
		
	}
	
	public void koniecReplikacie() {
		
		replStatDlzkaCakaniaNaOpravuVozidla.addSample(statDlzkaCakaniaNaOpravuVozidla.mean());
		replStatDlzkaCakaniaNaZadanieObjednavky.addSample(statDlzkaCakaniaNaZadanieObjednavky.mean());
		replStatDlzkaFrontuNaObsluhu.addSample(frontZakaznikovNaZadavanieObjednavky.lengthStatistic().mean());
		//System.out.println(replStatDlzkaFrontuNaObsluhu.sampleSize());
		double pocetPracujucich = 0;
		for(Robotnik rob : vsetciPracovnici) {
			pocetPracujucich+=rob.getVytazenost();
		}
		replStatPriemernyPocetPracujucich.addSample(pocetPracujucich);
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
		volnyPracovnici.clear();
		vsetciPracovnici.clear();
		for(int i = 0; i<pocetPracovnikov;i++) {
			Robotnik rob = new Robotnik(mySim(),i);
			volnyPracovnici.add(rob);
			vsetciPracovnici.add(rob);
		}
	}
	public void nastavNajprvZadavanieObjednavky(boolean zadavanie) {
		this.najprvZadavanie = zadavanie;
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
		robotnik.zacniPracovat();
		return robotnik;
	}

	public void uvolnenieRobotnika(MyMessage myMessage) {
		volnyPracovnici.add(myMessage.getRobotnik());
		myMessage.getRobotnik().prestanPracovat();
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

	
	
	//--------------------------------------------------------------
	// statisticke veci
	public double getReplPriemCasNaCakanieVozidla() {
		return replStatDlzkaCakaniaNaOpravuVozidla.mean();
	}

	public void pridajCakanieNaOpravu(double dlzkaCakaniaNaOpravu) {
		statDlzkaCakaniaNaOpravuVozidla.addSample(dlzkaCakaniaNaOpravu);
		
	}
	public void pridajDlzkuCakaniaNaZadavanieObjednavky(double dlzkaCakaniaNaZadavanieObjednavky) {
		statDlzkaCakaniaNaZadanieObjednavky.addSample(dlzkaCakaniaNaZadavanieObjednavky);
	}

	public double getPriemDlzkaCakania() {
		// TODO Auto-generated method stub
		return statDlzkaCakaniaNaZadanieObjednavky.mean();
	}
	public boolean getSposobPrace() {
		return najprvZadavanie;
	}

	public double[] getIsPocetLudiCakajucichNaObsluhu() {
		if(replStatDlzkaFrontuNaObsluhu.sampleSize()>2)
			return replStatDlzkaFrontuNaObsluhu.confidenceInterval_90();
		return new double[2];
	}

	public double[] getIsDlzkaCakania() {
		if(replStatDlzkaCakaniaNaZadanieObjednavky.sampleSize()>2) 
			return replStatDlzkaCakaniaNaZadanieObjednavky.confidenceInterval_90();
		return new double[2];
	}

	public void resetujReplikacneStatistiky() {
		replStatDlzkaCakaniaNaOpravuVozidla.clear();
		replStatDlzkaCakaniaNaZadanieObjednavky.clear();
		replStatDlzkaFrontuNaObsluhu.clear();
		replStatPriemernyPocetPracujucich.clear();
		
	}

	public double[] getIsCakanieNaOpravu() {
		if(replStatDlzkaCakaniaNaOpravuVozidla.sampleSize()>2) {
			return replStatDlzkaCakaniaNaOpravuVozidla.confidenceInterval_90();
		}
		return new double[2];
	}

	public double getPriemCakanieNaOpravu() {
		return statDlzkaCakaniaNaOpravuVozidla.mean();
	}
	public double getReplPriemCakanieNaOpravu() {
		return replStatDlzkaCakaniaNaOpravuVozidla.mean();
	}

	public boolean vBlizkejDobeNiektoOdide() {
		for(MyMessage sprava : frontZakaznikovNaZadavanieObjednavky) {
			if((mySim().currentTime()-sprava.getZakaznik().getZaciatokCakaniaNaZadavanieObjednavky())>400) {
				return true;
			}
		}
		return false;
	}

	public boolean cakaVelaLudiNaOdovzdanie() {
		if(frontaZakaznikovSOpravenymAutom.size()>3) {
			return true;
		}
		return false;
	}

	public double getReplPriemPocetPracujucich() {
		return replStatPriemernyPocetPracujucich.mean();
	}

	
}