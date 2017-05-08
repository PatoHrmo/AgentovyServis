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
	public AgentServisu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.prechodCezRampu);
		addOwnMessage(Mc.prichodOdRampyNaParkovisko1);
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
		new PrejazdRampaServis(Id.prejazdRampaServis, mySim(), this);
		new OdchodPoDlhomCakani(Id.odchodPoDlhomCakani, mySim(), this);
		new PrejazdRampou(Id.prejazdRampou, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.preparkujRampaServis);
		addOwnMessage(Mc.dajAutoNaParkovisko2);
		addOwnMessage(Mc.rezervujMiestoParkoviska1);
		addOwnMessage(Mc.autoBoloPreparkovaneNaParkovisko1);
		addOwnMessage(Mc.preparkujNaParkovisko1);
		addOwnMessage(Mc.umietniAutoNaParkovisko1);
		addOwnMessage(Mc.odchodObsluzenehoZakaznika);
		addOwnMessage(Mc.preparkujServisRampa);
		addOwnMessage(Mc.preparkujParkovisko2PredServis);
	}
	//meta! tag="end"
}