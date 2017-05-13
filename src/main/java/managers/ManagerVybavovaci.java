package managers;

import OSPABA.Agent;
import OSPABA.Manager;
import OSPABA.MessageForm;
import OSPABA.Simulation;
import agents.AgentVybavovaci;
import entity.Cinnosti;
import simulation.Id;
import simulation.Mc;
import simulation.MyMessage;

//meta! id="15"
public class ManagerVybavovaci extends Manager
{
	public ManagerVybavovaci(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentServisu", id="61", type="Response"
	public void processDajAutoZParkoviska2(MessageForm message)
	{
		((MyMessage)message).setAddressee(Id.odovzdavanieHotoveho);
		startContinualAssistant(message);
	}

	//meta! sender="AgentServisu", id="63", type="Notice"
	public void processPrichodAutaNaParkovisko2(MessageForm message)
	{
		if(myAgent().jeVolnyPracovnik()) {
			((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
			message.setAddressee(Id.agentServisu);
			message.setCode(Mc.dajAutoZParkoviska2);
			request(message);
		} else {
			myAgent().pridajZakaznikaSOpravenymAutom((MyMessage)message);
		}
	}

	//meta! sender="AgentServisu", id="39", type="Notice"
	public void processPrisielZakaznik(MessageForm message)
	{
		((MyMessage)message).getZakaznik().setZaciatokCakaniaNaZadavanieObjednavky(mySim().currentTime());
		if(myAgent().jeVolnyPracovnik()) {
			message.setAddressee(Id.zadavanieObjednavky);
			((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
			((MyMessage)message).getZakaznik().setKoniecCakaniaNaZadavanieObjednavky(mySim().currentTime());
			startContinualAssistant(message);
		} else {
			((MyMessage)message).setCinnostZakaznika(Cinnosti.cakaNaZadanieObjednavky);
			//FIXME pre fungovanie staci odstranit nasledovna dva riadky
//			message.setAddressee(Id.odchodPoDlhomCakani);
//			startContinualAssistant(message);
			MyMessage mess = new MyMessage((MyMessage)message);
			mess.setAddressee(Id.odchodPoDlhomCakani);
			startContinualAssistant(mess);
			myAgent().getFrontaLudiNaZadavanieObjednavky().enqueue((MyMessage)message);
		}
	}

	//meta! sender="PreparkovanieNaPark1", id="44", type="Finish"
	public void processFinishPreparkovanieNaPark1(MessageForm message)
	{
		
		myAgent().uvolnenieRobotnika(((MyMessage)message));
		cinnostPriUvolneniRobotnika();
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.autoBoloPreparkovaneNaParkovisko1);
		notice(message);
	}

	private void cinnostPriUvolneniRobotnika() {
//		if(myAgent().niektoCakaNaZadanieObjednavky()) {
//			myAgent().znizPocetLudiCakajucichNaZadanieObjednavky();
//			MyMessage message = new MyMessage(_mySim, null);
//			message.setAddressee(Id.agentServisu);
//			message.setCode(Mc.dajZakaznikaCakajucehoNaZadanieObjeddnavky);
//			((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
//			request(message);
//		} else if(myAgent().getPocetLudiNaParkovisku2()>0) {
//			myAgent().znizPocetAutNaParkovisku2();
//			MyMessage message = new MyMessage(_mySim, null);
//			message.setAddressee(Id.agentServisu);
//			message.setCode(Mc.dajAutoZParkoviska2);
//			((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
//			request(message);
//		}
		if(myAgent().getSposobPrace()) {
			if(myAgent().getPocetLudiNaParkovisku2()>0) {
				MyMessage message = myAgent().getZakaznikaCakajucehoSOpravenymAutom();
				((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
				message.setAddressee(Id.agentServisu);
				message.setCode(Mc.dajAutoZParkoviska2);
				request(message);
			} else if(myAgent().getFrontaLudiNaZadavanieObjednavky().size()>0) {
				MyMessage message = myAgent().getFrontaLudiNaZadavanieObjednavky().dequeue();
				message.setAddressee(Id.zadavanieObjednavky);
				((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
				((MyMessage)message).getZakaznik().setKoniecCakaniaNaZadavanieObjednavky(mySim().currentTime());
				startContinualAssistant(message);
			}
		} else {
			if(myAgent().getFrontaLudiNaZadavanieObjednavky().size()>0) {
				MyMessage message = myAgent().getFrontaLudiNaZadavanieObjednavky().dequeue();
				message.setAddressee(Id.zadavanieObjednavky);
				((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
				((MyMessage)message).getZakaznik().setKoniecCakaniaNaZadavanieObjednavky(mySim().currentTime());
				startContinualAssistant(message);
			} else if(myAgent().getPocetLudiNaParkovisku2()>0) {
				MyMessage message = myAgent().getZakaznikaCakajucehoSOpravenymAutom();
				((MyMessage)message).setRobotnik(myAgent().getVolnyRobotnik());
				message.setAddressee(Id.agentServisu);
				message.setCode(Mc.dajAutoZParkoviska2);
				request(message);
			}
		}
		
		
	}

	//meta! sender="ZadavanieObjednavky", id="42", type="Finish"
	public void processFinishZadavanieObjednavky(MessageForm message)
	{
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.rezervujMiestoParkoviska1);
		myAgent().pridajDlzkuCakaniaNaZadavanieObjednavky(
				((MyMessage)message).getZakaznik().getDlzkaCakaniaNaZadavanieObjednavky());
		request(message);
	}

	//meta! sender="PreparkovaniePredServis", id="46", type="Finish"
	public void processFinishPreparkovaniePredServis(MessageForm message)
	{
		message.setAddressee(Id.odovzdavanieHotoveho);
		startContinualAssistant(message);
	}

	//meta! sender="OdovzdavanieHotoveho", id="48", type="Finish"
	public void processFinishOdovzdavanieHotoveho(MessageForm message)
	{
		myAgent().uvolnenieRobotnika(((MyMessage)message));
		cinnostPriUvolneniRobotnika();
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.odchodObsluzenehoZakaznika);
		((MyMessage)message).getZakaznik().setKoniecCakaniaNaOpravu(mySim().currentTime());
		((MyMessage)message).getZakaznik().setObsluzenyNormalne();
		myAgent().pridajCakanieNaOpravu(((MyMessage)message).getZakaznik().getDlzkaCakaniaNaOpravu());
		notice(message);
		
	}

	//meta! sender="AgentServisu", id="58", type="Response"
	public void processRezervujMiestoParkoviska1(MessageForm message)
	{
		message.setAddressee(Id.preberanieAuta);
		startContinualAssistant(message);
	}
	private void processFinishOdchodPoDlhomCakani(MessageForm message) {
		MyMessage mess = myAgent().getZakaznikaKtoremuDoslaTrpezlivost(((MyMessage)message));
		if(mess!=null) {
			mess.setAddressee(Id.agentServisu);
			mess.setCode(Mc.odchodObsluzenehoZakaznika);
			notice(mess);
		}
		
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Removed from model"
	public void processDajZakaznikaCakajucehoNaZadanieObjeddnavky(MessageForm message)
	{
		message.setAddressee(Id.zadavanieObjednavky);
		startContinualAssistant(message);
	}

	//meta! sender="AgentServisu", id="89", type="Response"
	public void processPreparkujNaParkovisko1(MessageForm message)
	{
		myAgent().uvolnenieRobotnika(((MyMessage)message));
		cinnostPriUvolneniRobotnika();
	}

	//meta! sender="PreberanieAuta", id="95", type="Finish"
	public void processFinishPreberanieAuta(MessageForm message)
	{
		message.setAddressee(Id.agentServisu);
		message.setCode(Mc.preparkujNaParkovisko1);
		((MyMessage)message).getZakaznik().setZaciatokCakaniaNaOpravu(mySim().currentTime());
		request(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.zadavanieObjednavky:
				processFinishZadavanieObjednavky(message);
			break;

			case Id.preparkovanieNaPark1:
				processFinishPreparkovanieNaPark1(message);
			break;

			case Id.preparkovaniePredServis:
				processFinishPreparkovaniePredServis(message);
			break;

			case Id.preberanieAuta:
				processFinishPreberanieAuta(message);
			break;

			case Id.odovzdavanieHotoveho:
				processFinishOdovzdavanieHotoveho(message);
			break;
			case Id.odchodPoDlhomCakani:
				processFinishOdchodPoDlhomCakani(message);
			break;
			case Id.planovacNovehoDna:
				processFinnishPlanovacNovehoDna(message);
				break;
			}
		break;

		case Mc.prichodAutaNaParkovisko2:
			processPrichodAutaNaParkovisko2(message);
		break;

		case Mc.rezervujMiestoParkoviska1:
			processRezervujMiestoParkoviska1(message);
		break;

		case Mc.prisielZakaznik:
			processPrisielZakaznik(message);
		break;

		case Mc.preparkujNaParkovisko1:
			processPreparkujNaParkovisko1(message);
		break;

		case Mc.dajAutoZParkoviska2:
			processDajAutoZParkoviska2(message);
		break;
		case Mc.spustiPlanovacDna:
			processSpustiPlanovacDna(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	

	private void processFinnishPlanovacNovehoDna(MessageForm message) {
		int pocetLudiCakajucichNaZadanieObjednavky = myAgent().getFrontaLudiNaZadavanieObjednavky().size();
		for(int i = 0; i<pocetLudiCakajucichNaZadanieObjednavky;i++) {
			MyMessage mess = myAgent().getFrontaLudiNaZadavanieObjednavky().dequeue();
			mess.setAddressee(Id.agentServisu);
			mess.setCode(Mc.odchodObsluzenehoZakaznika);
			notice(mess);
		}
		
	}

	private void processSpustiPlanovacDna(MessageForm message) {
		message.setAddressee(Id.planovacNovehoDna);
		startContinualAssistant(message);
		
	}

	@Override
	public AgentVybavovaci myAgent()
	{
		return (AgentVybavovaci)super.myAgent();
	}

}