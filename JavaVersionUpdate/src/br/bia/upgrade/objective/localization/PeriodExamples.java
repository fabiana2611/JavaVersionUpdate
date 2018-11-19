package br.bia.upgrade.objective.localization;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PeriodExamples {

	static Period period5y4m3d = Period.of(5, 4, 3);
	static Period period2d = Period.ofDays(2);
	static Period period2y = Period.ofYears(2);
	
	public static void init (){
		System.out.println("\n### Period ###\n");
		declarations();
		differenceBetweenTwoDates();
		getValues();
		newInstanceWith();
		plusMinus();
	}

	private static void declarations() {
		// Setting years, months, days (can be negative)
		System.out.println("Period.of(5, 4, 3): " + period5y4m3d);
		// Setting days (can be negative), years and months will be zero
		System.out.println("Period.ofDays(2): "+period2d);
		// Setting months (can be negative), years and days will be zero
		Period period2m = Period.ofMonths(2);
		System.out.println("Period.ofMonths(2): "+period2m );
		// Setting weeks (can be negative). The resulting period will
		// be in days (1 week = 7 days). Years and months will be zero
		Period period14d = Period.ofWeeks(2);
		System.out.println("Period.ofWeeks(2): "+period14d );
		// Setting years (can be negative), days and months will be zero
		Period period2y = Period.ofDays(2);
		System.out.println("Period.ofDays(2): "+period2y );
		System.out.println();
	}
	
	//The start date is INCLUDED, but NOT the end date.
	private static void differenceBetweenTwoDates(){
		LocalDate march2003 = LocalDate.of(2003, 3, 1);
		LocalDate may2003 = LocalDate.of(2003, 5, 1);
		Period dif = Period.between(march2003, may2003); // 2 months
		System.out.println("Period [march2003, may2003]: "+dif);
		
		// dif1 will be 1 year 2 months 2 days
		Period dif1 = Period.between( LocalDate.of(2000, 2, 10), LocalDate.of(2001, 4, 12));
		System.out.println("Period [(2000, 2, 10), (2001, 4, 12)]: "+dif1);
		// dif2 will be 25 days
		Period dif2 = Period.between( LocalDate.of(2013, 5, 9), LocalDate.of(2013, 6, 3));
		System.out.println("Period [(2013, 5, 9), (2013, 6, 3)]: "+dif2);
		// dif3 will be -2 years -3 days
		Period dif3 = Period.between( LocalDate.of(2014, 11, 3), LocalDate.of(2012, 10, 31));
		System.out.println("Period [(2014, 11, 3), (2012, 10, 31)]: "+dif3);
		System.out.println();
	}
	
	private static void getValues() {
		System.out.println("period5y4m3d.getDays(): " + period5y4m3d.getDays());
		System.out.println( "period5y4m3d.getMonths(): " + period5y4m3d.getMonths());
		System.out.println("period5y4m3d.getYears(): " + period5y4m3d.getYears());
		System.out.println("period5y4m3d.get(ChronoUnit.DAYS): " + period5y4m3d.get(ChronoUnit.DAYS));
		System.out.println();
	}

	private static void newInstanceWith() {
		System.out.println("period2d.withDays(8): "+period2d.withDays(8));
		// Since these methods return a new instance, we can chain them!
		System.out.println("period2d.withYears(2).withMonths(1): "+period2d.withYears(2).withMonths(1));
		System.out.println();
	}
	
	private static void plusMinus() {
		// Adding
		System.out.println("period5y4m3d.plusYears(4): "+ period5y4m3d.plusYears(4));
		System.out.println("period5y4m3d.plusMonths(3): "+period5y4m3d.plusMonths(3));
		System.out.println("period5y4m3d.plusDays(3): "+period5y4m3d.plusDays(3));
		System.out.println("period5y4m3d.plus(period2y): "+period5y4m3d.plus(period2y));

		// Subtracting
		System.out.println("period5y4m3d.minusYears(2): "+ period5y4m3d.minusYears(2));
		System.out.println("period5y4m3d.minusMonths(1)+ "+ period5y4m3d.minusMonths(1));
		System.out.println("period5y4m3d.minusDays(1): "+ period5y4m3d.minusDays(1));
		System.out.println("period5y4m3d.minus(period2y): "+ period5y4m3d.minus(period2y));
		System.out.println();
		
	}
}
