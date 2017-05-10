package agents;

import java.util.ArrayList;
import java.util.List;

import OSPABA.Agent;
import OSPABA.Simulation;
import OSPStat.Stat;
import continualAssistants.PlanovacPrichodu;
import entity.Zakaznik;
import managers.ManagerOkolia;
import simulation.Id;
import simulation.Mc;

//meta! id="3"
public class AgentOkolia extends Agent
{	
	private List<Zakaznik> vsetciZakazniciAktualneVSysteme;
	private double investicia;
	
	private int pocetObsluzenychZakaznikov;
	private int pocetNeObsluzenychZakaznikov;
	private Stat replStatpocetObsluzenychZakaznikov;
	private Stat replStatpocetNeObsluzenychZakaznikov;
	public AgentOkolia(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.novyZakaznik);
		vsetciZakazniciAktualneVSysteme = new ArrayList<>();
		replStatpocetObsluzenychZakaznikov = new Stat();
		replStatpocetNeObsluzenychZakaznikov = new Stat();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		pocetObsluzenychZakaznikov = 0;
		pocetNeObsluzenychZakaznikov = 0;
		
	}
	public void koniecReplikacie() {
		replStatpocetObsluzenychZakaznikov.addSample(pocetObsluzenychZakaznikov);
		replStatpocetNeObsluzenychZakaznikov.addSample(pocetNeObsluzenychZakaznikov);
	}
	public void pridajZakaznikaDoSystemu(Zakaznik zak) {
		vsetciZakazniciAktualneVSysteme.add(zak);
	}
	public List<Zakaznik> getZakaznikovVSysteme() {
		return vsetciZakazniciAktualneVSysteme;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOkolia(Id.managerOkolia, mySim(), this);
		new PlanovacPrichodu(Id.planovacPrichodu, mySim(), this);
		addOwnMessage(Mc.odchodZakaznika);
		addOwnMessage(Mc.stustiTok);
	}
	//meta! tag="end"

	public void vymazZakaznikaZoSystemu(Zakaznik zakaznik) {
		if(zakaznik.bolObsluzeny()) {
			pocetObsluzenychZakaznikov++;
		} else {
			pocetNeObsluzenychZakaznikov++;
		}
		vsetciZakazniciAktualneVSysteme.remove(zakaznik);
	}

	public void nastavInvesticiu(double investicia) {
		this.investicia = investicia;
		double zvysenie = 1;
		zvysenie+=(investicia*0.016)/100;
		if(zvysenie>1.78)
			zvysenie = 1.78;
		((PlanovacPrichodu)findAssistant(Id.planovacPrichodu)).setZvysenie(zvysenie);
	}
	public double getInvesticia() {
		return investicia;
	}

	
}