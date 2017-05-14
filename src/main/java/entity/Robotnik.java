package entity;

import OSPABA.Simulation;
import OSPStat.WStat;
import util.StringUtils;

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
		return "ID: "+id+" "+cinnost+System.lineSeparator()+"    vyùaûenosù "+StringUtils.getCislo(statVytazenosti.mean()*100)+"%";
	}
	public void zacniPracovat() {
		statVytazenosti.addSample(1);
	}
	public void prestanPracovat() {
		statVytazenosti.addSample(0);
		this.cinnost = Cinnosti.necinny;
	}
	public double getVytazenost() {
		// TODO Auto-generated method stub
		return statVytazenosti.mean();
	}
}
