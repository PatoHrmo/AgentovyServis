package util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DobaOpravyGen {
	Random genSeedov;
	Random genNarocnostiOpravy;
	Random genJednoducha;
	Random genTazka;
	EmpGenerator stredneTazka;
	public DobaOpravyGen() {
		genSeedov = new Random();
		genNarocnostiOpravy = new Random(genSeedov.nextLong());
		genJednoducha = new Random(genSeedov.nextLong());
		genTazka = new Random(genSeedov.nextLong());
		List<double[]> dlzkyStredneTazkej = new LinkedList<>();
		dlzkyStredneTazkej.add(new double[]{10,40,0.1});
		dlzkyStredneTazkej.add(new double[]{41,61,0.6});
		dlzkyStredneTazkej.add(new double[]{62,100,0.3});
		stredneTazka = new EmpGenerator(genSeedov.nextLong(), dlzkyStredneTazkej);
	}
	public int nextDlzkaOpravy() {
		double typOpravy = genNarocnostiOpravy.nextDouble();
		if(typOpravy<0.7) {
			// jednoducha oprava
			return (genJednoducha.nextInt(19)+2);
		} 
		if(typOpravy<0.9) {
			//stredne tazka oprava
			return (int)Math.round(stredneTazka.next());
		} else {
			//tazka oprava
			return (genTazka.nextInt(141)+120);
		}
	}

}
