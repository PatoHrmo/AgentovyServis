package util;

import java.time.LocalTime;

public class StringUtils {

	public static String getCasVLudskejPodobe(double casSimulacie, int replikacia) {
		long cas = Math.round(casSimulacie);
		long dni = cas/(8*60*60);
		cas-=dni*(8*60*60);
		long hodiny = cas/(60*60);
		cas -= hodiny*(60*60);
		long minuty = cas/(60);
		cas -= minuty*60;
		long sekundy = cas;
		LocalTime lCas = LocalTime.of((int)hodiny+7, (int)minuty, (int)sekundy);
		return "Deò: "+(dni+1)+" èas : "+lCas.toString();
	}
	public String getIsSekundy(double[] Is) {
		return "< "+Math.round(Is[0]*100)/100+" ; "+Math.round(Is[0]*100)/100+" >";
	}
	public static String getCislo(double zisk) {
		// TODO Auto-generated method stub
		return (Math.round(zisk*100)/100)+"";
	}
}
