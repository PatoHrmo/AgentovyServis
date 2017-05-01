package util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EmpGenerator {
	Random seedGenerator;
	Random rndU;
	List<Trieda> triedyNahodnychCisel;

	public EmpGenerator(long seed, List<double[]> poliaTried) {
		seedGenerator = new Random(seed);
		rndU = new Random(seedGenerator.nextLong());
		triedyNahodnychCisel = new LinkedList<>();
		double celkovaPpdobnost = 0;
		for(double[] pole : poliaTried) {
			if(pole.length==2) {
				celkovaPpdobnost+=pole[1];
				Trieda trieda = new Trieda(pole[0], celkovaPpdobnost);
				triedyNahodnychCisel.add(trieda);
			} else {
				celkovaPpdobnost+=pole[2];
				Trieda trieda = new Trieda(seedGenerator.nextLong(), (int)Math.round(pole[0]), (int)Math.round(pole[1]), celkovaPpdobnost);
				triedyNahodnychCisel.add(trieda);
			}
			
		}
		if(celkovaPpdobnost!=1) {
			System.out.println("zle nastaveny EmpGenerator resetujem");
			seedGenerator = null;
			rndU = null;
			triedyNahodnychCisel = null;
		}
	}

	public double next() {
		double generated = rndU.nextDouble();
		for(Trieda t : triedyNahodnychCisel) {
			if(generated<t.pravdepopodbnost){
				return t.next();
			}
		}
		System.out.println("zle konfigurovanie v EmpGenerator");
		return 0;
	}
	class Trieda {
		Random gen;
		int rozsahOd;
		int rozsahDo;
		double cislo;
		double pravdepopodbnost;
		boolean jednaHodnota;
		public Trieda(long seed, int rozsahOd, int rozsahDo, double prpdbnst) {
			gen = new Random(seed);
			this.rozsahOd = rozsahOd;
			this.rozsahDo = rozsahDo;
			this.pravdepopodbnost = prpdbnst;
			this.jednaHodnota = false;
		}
		public Trieda(double cislo, double prvadepodobnost) {
			this.cislo = cislo;
			this.pravdepopodbnost = prvadepodobnost;
			this.jednaHodnota = true;
		}
		double getPravdepodobnost() {
			return this.pravdepopodbnost;
		}
		double next() {
			if(jednaHodnota) {
				return cislo;
			}
			return gen.nextInt(rozsahDo-rozsahOd+1)+rozsahOd;
		}
	}
}
