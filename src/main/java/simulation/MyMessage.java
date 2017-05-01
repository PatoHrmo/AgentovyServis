package simulation;

import OSPABA.*;
import entity.Robotnik;
import entity.Zakaznik;

public class MyMessage extends MessageForm
{	
	private Zakaznik zakaznik;
	private Robotnik robotnik;
	private double casZaciatkuJazdy;

	public MyMessage(Simulation sim, Zakaznik zak)
	{
		super(sim);
		zakaznik = zak;
	}

	public MyMessage(MyMessage original)
	{
		super(original);
		zakaznik = original.zakaznik;
		
	}

	@Override
	public MessageForm createCopy()
	{
		return new MyMessage(this);
	}

	@Override
	protected void copy(MessageForm message)
	{
		super.copy(message);
		MyMessage original = (MyMessage)message;
		// Copy attributes
	}
	public Zakaznik getZakaznik() {
		return zakaznik;
	}

	public void setZakaznik(Zakaznik zakaznik) {
		this.zakaznik = zakaznik;
	}

	

	public void setRobotnik(Robotnik volnyRobotnik) {
		this.robotnik = volnyRobotnik;
		
	}

	public void setCasZaciatkuJazdy(double cas) {
		casZaciatkuJazdy = cas;
	}

	public Robotnik getRobotnik() {
		return robotnik;
	}

	public void setCinnostZakaznika(String cinnost) {
		zakaznik.setCinnost(cinnost);
		
	}

	public void setCinnostRobotnika(String cinnost) {
		robotnik.setCinnost(cinnost);
		
	}
}