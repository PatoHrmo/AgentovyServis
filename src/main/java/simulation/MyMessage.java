package simulation;

import OSPABA.*;
import entity.Zakaznik;

public class MyMessage extends MessageForm
{	
	private Zakaznik zakaznik;
	private double casPrejdeniaRampou;

	public MyMessage(Simulation sim, Zakaznik zak)
	{
		super(sim);
		zakaznik = zak;
	}

	public MyMessage(MyMessage original)
	{
		super(original);
		zakaznik = original.zakaznik;
		casPrejdeniaRampou = original.casPrejdeniaRampou;
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

	public double getCasPrejdeniaRampou() {
		return casPrejdeniaRampou;
	}

	public void setCasPrejdeniaRampou(double casPrejdeniaRampou) {
		this.casPrejdeniaRampou = casPrejdeniaRampou;
	}
}
