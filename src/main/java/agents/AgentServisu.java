package agents;

import OSPABA.Agent;
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
	private SimQueue<MyMessage> frontaPredRampou;
	private SimQueue<MyMessage> frontaPredZadavanimObjednavky;
	private boolean naRampeSmeromDnuNiektoJe;
	public AgentServisu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.prechodCezRampu);
		addOwnMessage(Mc.prichodOdRampyNaParkovisko1);
		frontaPredRampou = new SimQueue<>(new WStat(_mySim));
		frontaPredZadavanimObjednavky = new SimQueue<>(new WStat(_mySim));
		naRampeSmeromDnuNiektoJe = false;
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
		new OdchodPoDlhomCakani(Id.odchodPoDlhomCakani, mySim(), this);
		new PrejazdRampou(Id.prejazdRampou, mySim(), this);
		new PrejazdRampaServis(Id.prejazdRampaServis, mySim(), this);
		addOwnMessage(Mc.dajAutoZParkoviska1);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.dajAutoZParkoviska2);
		addOwnMessage(Mc.prichodAutaNaParkovisko2);
		addOwnMessage(Mc.odchodObsluzenehoZakaznika);
		addOwnMessage(Mc.autoBoloPreparkovaneNaParkovisko1);
		addOwnMessage(Mc.vypytajMiestoParkoviska1);
		addOwnMessage(Mc.vypytajMiestoParkoviska2);
	}
	//meta! tag="end"

	public SimQueue<MyMessage> getFrontaPredRampou() {
		return frontaPredRampou;
	}

	public SimQueue<MyMessage> getFrontaPredZadavanimObjednavky() {
		return frontaPredZadavanimObjednavky;
	}

	public boolean isNaRampeSmeromDnuNiektoJe() {
		return naRampeSmeromDnuNiektoJe;
	}

	public void setNaRampeSmeromDnuNiektoJe(boolean naRampeSmeromDnuNiektoJe) {
		this.naRampeSmeromDnuNiektoJe = naRampeSmeromDnuNiektoJe;
	}
}
