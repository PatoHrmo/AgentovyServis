package agents;

import java.util.LinkedList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import continualAssistants.OdovzdavanieHotoveho;
import continualAssistants.PreparkovanieNaPark1;
import continualAssistants.PreparkovaniePredServis;
import continualAssistants.ZadavanieObjednavky;
import entity.Robotnik;
import managers.ManagerVybavovaci;
import simulation.Id;
import simulation.Mc;

//meta! id="15"
public class AgentVybavovaci extends Agent
{	
	private List<Robotnik> volnyPracovnici;
	private List<Robotnik> pracujuciPracovnici;
	private int pocetLudiCakajucichNaZadanieObjednavky;
	public AgentVybavovaci(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		volnyPracovnici = new LinkedList<>();
		pracujuciPracovnici = new LinkedList<>();
		pocetLudiCakajucichNaZadanieObjednavky = 0;
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
		new ZadavanieObjednavky(Id.zadavanieObjednavky, mySim(), this);
		new PreparkovanieNaPark1(Id.preparkovanieNaPark1, mySim(), this);
		new OdovzdavanieHotoveho(Id.odovzdavanieHotoveho, mySim(), this);
		new PreparkovaniePredServis(Id.preparkovaniePredServis, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.prisielZakaznik);
		addOwnMessage(Mc.vypytajMiestoParkoviska1);
	}
	//meta! tag="end"

	public void nastavPocetPracovnikov(int pocetPracovnikov) {
		for(int i = 0; i<pocetPracovnikov;i++) {
			volnyPracovnici.add(new Robotnik(mySim()));
		}
	}

	public boolean jeVolnyPracovnik() {
		return !volnyPracovnici.isEmpty();
	}

	public void zvysPocetLudiCakajucichNaZadanieObjednavky() {
		pocetLudiCakajucichNaZadanieObjednavky++;
	}
	public void znizPocetLudiCakajucichNaZadanieObjednavky() {
		pocetLudiCakajucichNaZadanieObjednavky++;
	}
	public boolean niektoCakaNaZadanieObjednavky() {
		if(pocetLudiCakajucichNaZadanieObjednavky==0)
			return false;
		return true;
	}
}
