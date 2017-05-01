package simulation;

import OSPABA.*;
import agents.*;

public class MySimulation extends Simulation
{
	public MySimulation(int pocetPracovnikov1, int pocetPracovnikov2)
	{
		init();
		((AgentVybavovaci)findAgent(Id.agentVybavovaci)).nastavPocetPracovnikov(pocetPracovnikov1);
		((AgentOpravary)findAgent(Id.agentOpravary)).nastavPocetPracovnikov(pocetPracovnikov2);
		
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Reset entities, queues, local statistics, etc...
	}

	@Override
	public void replicationFinished()
	{
		// Collect local statistics into global, update UI, etc...
		super.replicationFinished();
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
		setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
		setAgentServisu(new AgentServisu(Id.agentServisu, this, agentModelu()));
		setAgentVybavovaci(new AgentVybavovaci(Id.agentVybavovaci, this, agentServisu()));
		setAgentOpravary(new AgentOpravary(Id.agentOpravary, this, agentServisu()));
	}

	private AgentModelu _agentModelu;

public AgentModelu agentModelu()
	{ return _agentModelu; }

	public void setAgentModelu(AgentModelu agentModelu)
	{_agentModelu = agentModelu; }

	private AgentOkolia _agentOkolia;

public AgentOkolia agentOkolia()
	{ return _agentOkolia; }

	public void setAgentOkolia(AgentOkolia agentOkolia)
	{_agentOkolia = agentOkolia; }

	private AgentServisu _agentServisu;

public AgentServisu agentServisu()
	{ return _agentServisu; }

	public void setAgentServisu(AgentServisu agentServisu)
	{_agentServisu = agentServisu; }

	private AgentVybavovaci _agentVybavovaci;

public AgentVybavovaci agentVybavovaci()
	{ return _agentVybavovaci; }

	public void setAgentVybavovaci(AgentVybavovaci agentVybavovaci)
	{_agentVybavovaci = agentVybavovaci; }

	private AgentOpravary _agentOpravary;

public AgentOpravary agentOpravary()
	{ return _agentOpravary; }

	public void setAgentOpravary(AgentOpravary agentOpravary)
	{_agentOpravary = agentOpravary; }
	//meta! tag="end"

	public int getPocetLudiCakajucichNaObsluhu() {
		// TODO Auto-generated method stub
		return agentServisu().getFrontaPredZadavanimObjednavky().size();
	}

	public int getVelkostFrontuPredRampou() {
		// TODO Auto-generated method stub
		return agentServisu().getFrontaPredRampou().size();
	}
}