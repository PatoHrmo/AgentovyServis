package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="120"
public class AgentRampy extends Agent
{
	SimQueue<MyMessage> frontaPredRampouDnu;
	SimQueue<MyMessage> frontaPredRampouVon;
	private boolean rampaDnuSaPouziva;
	private boolean rampaVonSaPouziva;
	public AgentRampy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		addOwnMessage(Mc.koniecPrejazduRampouDnu);
		addOwnMessage(Mc.koniecPrejazduRampouVon);
		frontaPredRampouDnu = new SimQueue<>();
		frontaPredRampouVon = new SimQueue<>();
		rampaDnuSaPouziva = false;
		rampaVonSaPouziva = false;
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		frontaPredRampouDnu = new SimQueue<>();
		frontaPredRampouVon = new SimQueue<>();
		rampaDnuSaPouziva = false;
		rampaVonSaPouziva = false;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerRampy(Id.managerRampy, mySim(), this);
		new PrechodRampouDnu(Id.prechodRampouDnu, mySim(), this);
		new PrechodRampouVon(Id.prechodRampouVon, mySim(), this);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.koniecPreparkovaniaRampaServis);
		addOwnMessage(Mc.koniecPreparkovaniaServisRampa);
	}
	//meta! tag="end"

	public void obsadRampuDnu() {
		rampaDnuSaPouziva = true;
		
	}
	public boolean rampaDnuJeVolna() {
		return !rampaDnuSaPouziva;
	}
	public void obsadRampuVon() {
		rampaVonSaPouziva = true;
	}
	public void uvolniRampuVon() {
		rampaVonSaPouziva = false;
	}
	public void uvolniRampuDnu() {
		rampaDnuSaPouziva = false;
	}
	public boolean rampaVonJeVolna() {
		return !rampaVonSaPouziva;
	}

	public void pridajZakaznikaDoFrontyPredRampouDnu(MyMessage message) {
		frontaPredRampouDnu.enqueue(message);
	}
	public void pridajZakaznikaDoFrontyPredRampouVon(MyMessage message) {
		frontaPredRampouVon.enqueue(message);
	}
	public MyMessage getZakaznikPredRampouVon() {
		return frontaPredRampouVon.dequeue();
	}
	public MyMessage getZakaznikPredRampouDnu() {
		return frontaPredRampouDnu.dequeue();
	}
	public boolean predRampouVonNiektoJe() {
		return !frontaPredRampouVon.isEmpty();
	}
	public boolean predRampouDnuNiektoJe() {
		return !frontaPredRampouDnu.isEmpty();
	}
}