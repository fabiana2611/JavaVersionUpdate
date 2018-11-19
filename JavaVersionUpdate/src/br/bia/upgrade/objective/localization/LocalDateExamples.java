package br.bia.upgrade.objective.localization;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateExamples {

	public static void init (){
		System.out.println("\n### LocalDate ###\n");
		declarations();
		getValues();
		compareInstance();
		newInstanceWith();
		plusMinus();
	}
	
	private static void declarations(){
		// With year(-999999999 to 999999999),
		// month (1 to 12), day of the month (1 - 31)}
		LocalDate newYear2001 = LocalDate.of(2001, 1, 1);
		System.out.println("LocalDate.of(2001, 1, 1): " + newYear2001);
		// This version uses the enum java.time.Month
		LocalDate newYear2002 = LocalDate.of(2002, Month.JANUARY, 1);
		System.out.println("LocalDate.of(2002, Month.JANUARY, 1): " + newYear2002 +"\n");
		System.out.println();
	}
	
	private static void getValues(){
		LocalDate today = LocalDate.now();
		
		System.out.println("today.getYear():" +today.getYear());
		System.out.println("today.getMonthValue(): "+today.getMonthValue());
		System.out.println("today.getMonth(): "+today.getMonth());
		System.out.println("today.getDayOfYear(): " + today.getDayOfYear());
		System.out.println("today.getDayOfMonth(): " + today.getDayOfMonth());
		System.out.println("today.getDayOfWeek(): " + today.getDayOfWeek());
		System.out.println();
		System.out.println("today.get(ChronoField.YEAR):" +today.get(ChronoField.YEAR));
		System.out.println("today.get(ChronoField.MONTH_OF_YEAR):" +today.get(ChronoField.MONTH_OF_YEAR));
		System.out.println("today.get(ChronoField.DAY_OF_YEAR):" +today.get(ChronoField.DAY_OF_YEAR));
		System.out.println("today.get(ChronoField.DAY_OF_MONTH):" +today.get(ChronoField.DAY_OF_MONTH));
		System.out.println("today.get(ChronoField.DAY_OF_WEEK:" +today.get(ChronoField.DAY_OF_WEEK));
		System.out.println("today.getLong(ChronoField.EPOCH_DAY): " +today.getLong(ChronoField.EPOCH_DAY));
		System.out.println();
	}
	
	private static void compareInstance(){
		LocalDate newYear2001 = LocalDate.of(2001, 1, 1);
		System.out.println("newYear2001: " + newYear2001);
		// This version uses the enum java.time.Month
		LocalDate newYear2002 = LocalDate.of(2002, Month.JANUARY, 1);
		System.out.println("newYear2002: " + newYear2002 +"\n");
		
		System.out.println("newYear2001.isAfter(newYear2002): " +newYear2001.isAfter(newYear2002)); // false
		System.out.println("newYear2001.isBefore(newYear2002): "+newYear2001.isBefore(newYear2002)); // true
		System.out.println("newYear2001.equals(newYear2002): "+newYear2001.equals(newYear2002)); // false
		System.out.println("newYear2001.isLeapYear(): " + newYear2001.isLeapYear()); // false
		System.out.println();
	}
	
	private static void newInstanceWith(){
		LocalDate newYear2001 = LocalDate.of(2001, 1, 1);
		System.out.println("newYear2001: " + newYear2001);
		// This version uses the enum java.time.Month
		LocalDate newYear2002 = LocalDate.of(2002, Month.JANUARY, 1);
		System.out.println("newYear2002: " + newYear2002 +"\n");
		
		LocalDate newYear2003 = newYear2001.with(ChronoField.YEAR, 2003);
		System.out.println("newYear2001.with(ChronoField.YEAR, 2003): "+ newYear2003);
		LocalDate newYear2004 = newYear2001.withYear(2004);
		System.out.println("newYear2001.withYear(2004): "+ newYear2004);
		LocalDate december2001 = newYear2001.withMonth(12);
		System.out.println("newYear2001.withMonth(12)[december2001]: "+ december2001);
		LocalDate february2001 = newYear2001.withDayOfYear(32);
		System.out.println("newYear2001.withDayOfYear(32)[february2001]: "+ february2001);
		// Since these methods return a new instance, we can chain them!
		LocalDate xmas2001 = newYear2001.withMonth(12).withDayOfMonth(25);
		System.out.println("newYear2001.withMonth(12).withDayOfMonth(25): "+ xmas2001);
		System.out.println();
	}
	
	private static void plusMinus(){
		LocalDate newYear2001 = LocalDate.of(2001, 1, 1);
		System.out.println("newYear2001: " + newYear2001);
		// This version uses the enum java.time.Month
		LocalDate newYear2002 = LocalDate.of(2002, Month.JANUARY, 1);
		System.out.println("newYear2002: " + newYear2002 +"\n");
		
		// Adding
		LocalDate newYear2005 = newYear2001.plusYears(4);
		System.out.println("newYear2001.plusYears(4): " + newYear2005);
		LocalDate march2001 = newYear2001.plusMonths(2);
		System.out.println("newYear2001.plusMonths(2): " + march2001);
		LocalDate january15_2001 = newYear2001.plusDays(14);
		System.out.println("newYear2001.plusDays(14): " + january15_2001);
		LocalDate lastWeekJanuary2001 = newYear2001.plusWeeks(3);
		System.out.println("newYear2001.plusWeeks(3): " + lastWeekJanuary2001);
		LocalDate newYear2006 = newYear2001.plus(5, ChronoUnit.YEARS); 
		System.out.println("newYear2001.plus(5, ChronoUnit.YEARS): " + newYear2006 + "\n");

		// Subtracting
		LocalDate newYear2000 = newYear2001.minusYears(1);
		System.out.println("newYear2001.minusYears(1): " + newYear2000);
		LocalDate nov2000 = newYear2001.minusMonths(2);
		System.out.println("newYear2001.minusMonths(2): " + nov2000);
		LocalDate dec30_2000 = newYear2001.minusDays(2);
		System.out.println("newYear2001.minusDays(2): " + dec30_2000);
		LocalDate lastWeekDec2001 = newYear2001.minusWeeks(1);
		System.out.println("newYear2001.minusWeeks(1): " + lastWeekDec2001);
		LocalDate newYear1999 = newYear2001.minus(2, ChronoUnit.YEARS);
		System.out.println("newYear2001.minus(2, ChronoUnit.YEARS): " + newYear1999);
		System.out.println();
	}
}
