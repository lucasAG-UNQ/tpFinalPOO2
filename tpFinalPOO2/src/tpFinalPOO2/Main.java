package tpFinalPOO2;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import opinion.NoVinchuca;
import opinion.Opinion;
import opinion.Vinchuca;

public class Main {

	
	public static void main(String[] args) {
		Opinion test= Vinchuca.Gusayana;
		Opinion test2= NoVinchuca.ChincheFoliada;
		Calendar c= Calendar.getInstance();
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		System.out.println(c.getTime().toString());
		System.out.println(test.toString());
		System.out.println(test2.toString());
	}
	
}
