package entity;

import OSPABA.Simulation;
import OSPStat.WStat;

public class Robotnik {
	private WStat statVytazenosti;
	private String cinnost;
	
	public Robotnik(Simulation sim) {
		this.statVytazenosti = new WStat(sim);
		cinnost = Cinnosti.necinny;
	}
}
