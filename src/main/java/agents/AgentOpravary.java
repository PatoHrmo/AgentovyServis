package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import continualAssistants.Oprava;
import entity.Cinnosti;
import entity.Robotnik;
import managers.ManagerOpravary;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="17"
public class AgentOpravary extends Agent
{	
	private SimQueue<MyMessage> zakazniciCakajuciNaOpravu;
	private List<Robotnik> volnyPracovnici; 
	private List<Robotnik> vsetciPracovnici;
	
	private double zisk;
	private Stat replStatZisk;
	private Stat replStatPriemernyPocetPracujucich;
	public AgentOpravary(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecOpravy);
		volnyPracovnici = new LinkedList<>();
		vsetciPracovnici = new LinkedList<>();
		zakazniciCakajuciNaOpravu = new SimQueue<>();
		replStatZisk = new Stat();
		replStatPriemernyPocetPracujucich = new Stat();
	}
	public void nastavPocetPracovnikov(int pocetPracovnikov) {
		volnyPracovnici.clear();
		vsetciPracovnici.clear();
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
	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		int pocetPracovnikov = vsetciPracovnici.size();
		vsetciPracovnici = new LinkedList<>();
		volnyPracovnici = new LinkedList<>();
		zakazniciCakajuciNaOpravu = new SimQueue<>();
		nastavPocetPracovnikov(pocetPracovnikov);
		zisk = 0;
	}
	
	public void koniecReplikacie() {
		replStatZisk.addSample(zisk);
		double pocetPracujucich = 0;
		for(Robotnik rob : vsetciPracovnici) {
			pocetPracujucich+=rob.getVytazenost();
		}
		replStatPriemernyPocetPracujucich.addSample(pocetPracujucich);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOpravary(Id.managerOpravary, mySim(), this);
		new Oprava(Id.oprava, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.prichodAutaNaParkovisko1);
		addOwnMessage(Mc.dajAutoNaParkovisko2);
	}
	//meta! tag="end"
	
	public Robotnik getVolnyPracovnik() {
		Robotnik rob = volnyPracovnici.remove(0);
		rob.zacniPracovat();
		return rob;
	}
	public void uvolniPracovnika(MyMessage message) {
		message.getRobotnik().setCinnost(Cinnosti.necinny);
		message.getRobotnik().prestanPracovat();
		message.getZakaznik().setCinnost(Cinnosti.cakaNaParkovisku2);
		volnyPracovnici.add(message.getRobotnik());
		message.setRobotnik(null);
	}
	public void pridajZakaznikaCakajucehoNaOpravu(MessageForm message) {
		zakazniciCakajuciNaOpravu.enqueue((MyMessage)message);
	}
	public int getPocetAutPredOpravovnou() {
		return zakazniciCakajuciNaOpravu.size();
	}
	public MyMessage getZakaznikaCakajucehoNaOpravu() {
		return zakazniciCakajuciNaOpravu.dequeue();
	}
	public void pridajZisk(double vyskaPlatby) {
		zisk+= vyskaPlatby;
	}
	public double getZisk() {
		return zisk;
	}
	public double getPriemernyZisk() {
		return replStatZisk.mean();
	}
	public double[] getIsZisk(double cenaOdpisy) {
		double is[] = new double[2];
		if(replStatZisk.sampleSize()>2) {
			is = replStatZisk.confidenceInterval_90();
			is[0] -=cenaOdpisy;
			is[1] -=cenaOdpisy;
		}
			
		return is;
	}
	public void resetujReplikacneStatistiky() {
		replStatZisk.clear();
		replStatPriemernyPocetPracujucich.clear();
	}
	public double getReplPriemPocetPracujucich() {
		return replStatPriemernyPocetPracujucich.mean();
	}
	
}