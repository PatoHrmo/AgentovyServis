package entity;

import OSPABA.Simulation;
import OSPStat.WStat;

public class Robotnik {
	private WStat statVytazenosti;
	private String cinnost;
	
	private final int id;
	public Robotnik(Simulation sim, int cisloRobotnika) {
		this.statVytazenosti = new WStat(sim);
		cinnost = Cinnosti.necinny;
		id = cisloRobotnika;
	}
	public int getId() {
		return id;
	} 
	public String getCinnost() {
		return cinnost;
	}
	public void setCinnost(String cinnost) {
		this.cinnost = cinnost;
	}
	@Override
	public String toString() {
		return "ID: "+id+" "+cinnost;
	}
}
