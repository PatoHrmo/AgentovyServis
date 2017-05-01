package entity;

public class Zakaznik {
	public static int nextId = 0;
	private String cinnost;
	private final int id;
	
	public Zakaznik() {
		id = nextId++;
		cinnost = Cinnosti.cakaPredRampou;
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
		return "ID: "+getId()+" "+getCinnost();
	}

}
