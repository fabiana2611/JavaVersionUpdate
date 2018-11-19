package br.bia.upgrade.objective.localization;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalTimeExamples {
	
	static LocalTime now = LocalTime.now();
	// With hour (0-23) and minutes (0-59)
	static LocalTime fiveThirty = LocalTime.of(5, 30);
	// With hour, minutes, and seconds (0-59)
	static LocalTime noon = LocalTime.of(12, 0, 0);
	// With hour, minutes, seconds, and nanoseconds (0-999,999,999)
	static LocalTime almostMidnight = LocalTime.of(23, 59, 59, 999999);

	public static void init (){
		System.out.println("\n#### LocalTime ####\n");
		declarations();
		getValues();
		compareInstance();
		newInstanceWith();
		plusMinus();
	}
	
	private static void declarations() {
		System.out.println("LocalTime.now(): "+ now);
		System.out.println("LocalTime.of(5, 30): " + fiveThirty);
		System.out.println("LocalTime.of(12, 0, 0): " + noon);
		System.out.println("LocalTime.of(23, 59, 59, 999999): " + almostMidnight);
	}
	
	private static void getValues(){
		int hour = now.getHour();
		System.out.println("now.getHour(): "+ hour);
		int minute = now.getMinute();
		System.out.println("now.getMinute(): "+ minute);
		int second = now.getSecond();
		System.out.println("now.getSecond(): "+ second);
		int nanosecond = now.getNano();
		System.out.println("now.getNano(): "+ nanosecond);
		
		int hourAMPM = now.get(ChronoField.HOUR_OF_AMPM); // 0 - 11
		System.out.println("now.get(ChronoField.HOUR_OF_AMPM): "+ hourAMPM);
		int hourDay = now.get(ChronoField.HOUR_OF_DAY); // 0 - 23
		System.out.println("now.get(ChronoField.HOUR_OF_DAY): "+ hourDay);
		int minuteDay = now.get(ChronoField.MINUTE_OF_DAY); // 0 - 1,439
		System.out.println("now.get(ChronoField.MINUTE_OF_DAY): "+ minuteDay);
		int minuteHour = now.get(ChronoField.MINUTE_OF_HOUR); // 0 - 59
		System.out.println("now.get(ChronoField.MINUTE_OF_HOUR): "+ minuteHour);
		int secondDay = now.get(ChronoField.SECOND_OF_DAY); // 0 - 86,399
		System.out.println("now.get(ChronoField.SECOND_OF_DAY): "+ secondDay);
		int secondMinute = now.get(ChronoField.SECOND_OF_MINUTE);// 0 - 59
		System.out.println("now.get(ChronoField.SECOND_OF_MINUTE): "+ secondMinute);
		long nanoDay = now.getLong(ChronoField.NANO_OF_DAY);//0-86399999999
		System.out.println("now.getLong(ChronoField.NANO_OF_DAY): "+ nanoDay);
		int nanoSecond = now.get(ChronoField.NANO_OF_SECOND);//0-999999999
		System.out.println("now.get(ChronoField.NANO_OF_SECOND): "+ nanoSecond);
	}
	
	private static void compareInstance(){
		boolean after = fiveThirty.isAfter(noon); // false
		System.out.println("fiveThirty.isAfter(noon): "+after);
		boolean before = fiveThirty.isBefore(noon); // true
		System.out.println("fiveThirty.isBefore(noon): " + before);
		boolean equal = noon.equals(almostMidnight); // false
		System.out.println("noon.equals(almostMidnight): " + equal);
	}
	
	private static void newInstanceWith(){
		LocalTime ten = noon.with(ChronoField.HOUR_OF_DAY, 10);
		System.out.println("noon.with(ChronoField.HOUR_OF_DAY, 10) :" + ten);
		LocalTime eight = noon.withHour(8);
		System.out.println("noon.withHour(8) :" + eight);
		LocalTime twelveThirty = noon.withMinute(30);
		System.out.println("noon.withMinute(30) :" + twelveThirty);
		LocalTime thirtyTwoSeconds = noon.withSecond(32);
		System.out.println("noon.withSecond(32) :" + thirtyTwoSeconds);
		// Since these methods return a new instance, we can chain them!
		LocalTime secondsNano = noon.withSecond(20).withNano(999999);
		System.out.println("noon.withSecond(20).withNano(999999) :" + secondsNano);
	}
	
	private static void plusMinus(){
		// Adding
		LocalTime sixThirty = fiveThirty.plusHours(1);
		System.out.println("fiveThirty.plusHours(1): " + sixThirty);
		LocalTime fiveForty = fiveThirty.plusMinutes(10);
		System.out.println("fiveThirty.plusMinutes(10): " + fiveForty);
		LocalTime plusSeconds = fiveThirty.plusSeconds(14);
		System.out.println("fiveThirty.plusSeconds(14): " + plusSeconds);
		LocalTime plusNanos = fiveThirty.plusNanos(99999999);
		System.out.println("fiveThirty.plusNanos(99999999): " + plusNanos);
		LocalTime sevenThirty = fiveThirty.plus(2, ChronoUnit.HOURS);
		System.out.println("fiveThirty.plus(2, ChronoUnit.HOURS): " + sevenThirty);

		// Subtracting
		LocalTime fourThirty = fiveThirty.minusHours(1);
		System.out.println("fiveThirty.minusHours(1: "+ fourThirty);
		LocalTime fiveTen = fiveThirty.minusMinutes(20);
		System.out.println("fiveThirty.minusMinutes(20): "+fiveTen);
		LocalTime minusSeconds = fiveThirty.minusSeconds(2);
		System.out.println("fiveThirty.minusSeconds(2): "+ minusSeconds);
		LocalTime minusNanos = fiveThirty.minusNanos(1);
		System.out.println(" fiveThirty.minusNanos(1): "+ minusNanos);
		LocalTime fiveTwenty = fiveThirty.minus(10, ChronoUnit.MINUTES);
		System.out.println("fiveThirty.minus(10, ChronoUnit.MINUTES): "+fiveTwenty);
	}
}
