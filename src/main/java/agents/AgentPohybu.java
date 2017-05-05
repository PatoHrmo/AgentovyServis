package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="83"
public class AgentPohybu extends Agent
{
	private final double pocetSekundNaPrejdenieMetra = 0.72;
	private final double vzdialenostPredservisDoPark1 = 107;
	private final double vzdialenostRampaPredServis = 18.5;
	private final double vzdialenostPark2PredServis = 143;
	public AgentPohybu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecPreparkovaniaNaParkovisko1);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}
	public double getPocetSekundNaPrejdenieMetra() {
		return pocetSekundNaPrejdenieMetra;
	}
	public double getVzdialenostPredservisDoPark1() {
		return vzdialenostPredservisDoPark1;
	}

	public double getVzdialenostRampaPredServis() {
		return vzdialenostRampaPredServis;
	}

	public double getVzdialenostPark2PredServis() {
		return vzdialenostPark2PredServis;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerPohybu(Id.managerPohybu, mySim(), this);
		new PreparkujPark2PredServis(Id.preparkujPark2PredServis, mySim(), this);
		new ChojOdRampyKServisu(Id.chojOdRampyKServisu, mySim(), this);
		new ChojOdServisuKRampe(Id.chojOdServisuKRampe, mySim(), this);
		new PreparkujServisDoPark1(Id.preparkujServisDoPark1, mySim(), this);
		addOwnMessage(Mc.preparkujNaParkovisko1);
	}
	//meta! tag="end"

	public double casPreparkovaniaServisParkovisko1() {
		return pocetSekundNaPrejdenieMetra*vzdialenostPredservisDoPark1;
	}
}