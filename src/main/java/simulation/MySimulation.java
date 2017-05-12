package simulation;

import OSPABA.*;
import agents.*;

public class MySimulation extends Simulation {
	double cenaOdpisy;

	public MySimulation(int pocetPracovnikov1, int pocetPracovnikov2, double investicia) {
		init();
		((AgentVybavovaci) findAgent(Id.agentVybavovaci)).nastavPocetPracovnikov(pocetPracovnikov1);
		((AgentOpravary) findAgent(Id.agentOpravary)).nastavPocetPracovnikov(pocetPracovnikov2);
		agentOkolia().nastavInvesticiu(investicia);
		cenaOdpisy = pocetPracovnikov1 * 1150 + pocetPracovnikov2 * 1600 + investicia + 7000;

	}

	@Override
	public void prepareSimulation() {
		super.prepareSimulation();

	}

	@Override
	public void prepareReplication() {
		super.prepareReplication();
		setSimSpeed(Config.interval, Config.trvanie);
	}

	@Override
	public void replicationFinished() {
		agentOkolia().koniecReplikacie();
		agentModelu().koniecReplikacie();
		agentServisu().koniecReplikacie();
		agentVybavovaci().koniecReplikacie();
		agentOpravary().koniecReplikacie();
		agentParkovisk().koniecReplikacie();
		agentPohybu().koniecReplikacie();
		agentRampy().koniecReplikacie();
		super.replicationFinished();
	}

	@Override
	public void simulationFinished() {
		System.out.println(agentVybavovaci().getReplPriemCasNaCakanieVozidla());
		super.simulationFinished();
	}

	// meta! userInfo="Generated code: do not modify", tag="begin"
	private void init() {
		setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
		setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
		setAgentRampy(new AgentRampy(Id.agentRampy, this, agentModelu()));
		setAgentServisu(new AgentServisu(Id.agentServisu, this, agentRampy()));
		setAgentParkovisk(new AgentParkovisk(Id.agentParkovisk, this, agentServisu()));
		setAgentVybavovaci(new AgentVybavovaci(Id.agentVybavovaci, this, agentServisu()));
		setAgentOpravary(new AgentOpravary(Id.agentOpravary, this, agentServisu()));
		setAgentPohybu(new AgentPohybu(Id.agentPohybu, this, agentServisu()));
	}

	private AgentModelu _agentModelu;

	public AgentModelu agentModelu() {
		return _agentModelu;
	}

	public void setAgentModelu(AgentModelu agentModelu) {
		_agentModelu = agentModelu;
	}

	private AgentOkolia _agentOkolia;

	public AgentOkolia agentOkolia() {
		return _agentOkolia;
	}

	public void setAgentOkolia(AgentOkolia agentOkolia) {
		_agentOkolia = agentOkolia;
	}

	private AgentRampy _agentRampy;

	public AgentRampy agentRampy() {
		return _agentRampy;
	}

	public void setAgentRampy(AgentRampy agentRampy) {
		_agentRampy = agentRampy;
	}

	private AgentServisu _agentServisu;

	public AgentServisu agentServisu() {
		return _agentServisu;
	}

	public void setAgentServisu(AgentServisu agentServisu) {
		_agentServisu = agentServisu;
	}

	private AgentParkovisk _agentParkovisk;

	public AgentParkovisk agentParkovisk() {
		return _agentParkovisk;
	}

	public void setAgentParkovisk(AgentParkovisk agentParkovisk) {
		_agentParkovisk = agentParkovisk;
	}

	private AgentVybavovaci _agentVybavovaci;

	public AgentVybavovaci agentVybavovaci() {
		return _agentVybavovaci;
	}

	public void setAgentVybavovaci(AgentVybavovaci agentVybavovaci) {
		_agentVybavovaci = agentVybavovaci;
	}

	private AgentOpravary _agentOpravary;

	public AgentOpravary agentOpravary() {
		return _agentOpravary;
	}

	public void setAgentOpravary(AgentOpravary agentOpravary) {
		_agentOpravary = agentOpravary;
	}

	private AgentPohybu _agentPohybu;

	public AgentPohybu agentPohybu() {
		return _agentPohybu;
	}

	public void setAgentPohybu(AgentPohybu agentPohybu) {
		_agentPohybu = agentPohybu;
	}
	// meta! tag="end"

	public int getPocetLudiCakajucichNaObsluhu() {
		// TODO Auto-generated method stub
		return agentVybavovaci().getFrontaLudiNaZadavanieObjednavky().size();
	}

	public int getVelkostFrontuPredRampou() {
		// TODO Auto-generated method stub
		return 0;
	}
}