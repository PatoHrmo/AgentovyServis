package entity;

import simulation.MySimulation;

public class Zakaznik {
	public static int nextId = 0;
	private String cinnost;
	private final int id;
	private boolean jazdi;
	private double casZaciatkuJazdy;
	private MySimulation mySim;
	private double zaciatokCakaniaNaOpravu;
	private double koniecCakaniaNaOpravu;
	private double zaciatokCakaniaNaZadavanieObjednavky;
	private double koniecCakaniaNaZadavanieObjednavky;
	private boolean obsluzeny;
	private double dlzkaOpravy;
	public Zakaznik(MySimulation sim) {
		id = nextId++;
		cinnost = Cinnosti.cakaPredRampou;
		setJazdi(false);
		mySim = sim;
		obsluzeny = false;
	}
	public String getCinnost() {
		return cinnost;
	}

	public void setCinnost(String cinnost) {
		this.cinnost = cinnost;
		
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		String str = "ID: "+getId()+" "+getCinnost();
		if(jazdi) 
			str+=" "+Math.round((mySim.currentTime()-casZaciatkuJazdy)*1.388)+"m";
		return str;
	}
	public boolean isJazdi() {
		return jazdi;
	}
	public void setJazdi(boolean jazdi) {
		if(jazdi==true) {
			casZaciatkuJazdy = mySim.currentTime();
		}
		this.jazdi = jazdi;
	}
	public double getZaciatokCakaniaNaOpravu() {
		return zaciatokCakaniaNaOpravu;
	}
	public void setZaciatokCakaniaNaOpravu(double zaciatokCakaniaNaOpravu) {
		this.zaciatokCakaniaNaOpravu = zaciatokCakaniaNaOpravu;
	}
	public void setKoniecCakaniaNaOpravu(double koniec) {
		koniecCakaniaNaOpravu = koniec;
		
	}
	public void setObsluzenyNormalne() {
		obsluzeny = true;
		
	}
	public boolean bolObsluzeny() {
		return obsluzeny;
	}
	public void setDlzkaOpravy(double dlzkaOpravy) {
		this.dlzkaOpravy = dlzkaOpravy;
	}
	public double getVyskaPlatby() {
		if(obsluzeny)
			return (dlzkaOpravy/3600)*25;
		return 0;
	}
	public double getDlzkaCakaniaNaOpravu() {
		return koniecCakaniaNaOpravu-zaciatokCakaniaNaOpravu;
		
	}
	public double getKoniecCakaniaNaZadavanieObjednavky() {
		return koniecCakaniaNaZadavanieObjednavky;
	}
	public void setKoniecCakaniaNaZadavanieObjednavky(double koniecCakaniaNaZadavanieObjednavky) {
		this.koniecCakaniaNaZadavanieObjednavky = koniecCakaniaNaZadavanieObjednavky;
	}
	public double getZaciatokCakaniaNaZadavanieObjednavky() {
		return zaciatokCakaniaNaZadavanieObjednavky;
	}
	public void setZaciatokCakaniaNaZadavanieObjednavky(double zaciatokCakaniaNaZadavanieObjednavky) {
		this.zaciatokCakaniaNaZadavanieObjednavky = zaciatokCakaniaNaZadavanieObjednavky;
	}
	public double getDlzkaCakaniaNaZadavanieObjednavky() {
		return koniecCakaniaNaZadavanieObjednavky - zaciatokCakaniaNaZadavanieObjednavky;
		
	}

}
