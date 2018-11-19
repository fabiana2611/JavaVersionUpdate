package br.bia.upgrade.objective.localization;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeExamples {
	
	static LocalDateTime now = LocalDateTime.now();
	static LocalDateTime dt1 = LocalDateTime.of(2014, 9, 19, 14, 5);

	public static void init (){
		System.out.println("\n### LocalDateTime ###\n");
		declarations();
		getValues();
		compareInstance();
		newInstanceWith();
		plusMinus();
		instant();
	}
	
	private static void declarations() {
		// Setting seconds and nanoseconds to zero
		System.out.println("LocalDateTime.of(2014, 9, 19, 14, 5): " +dt1);
		// Setting nanoseconds to zero
		LocalDateTime dt2 = LocalDateTime.of(2014, 9, 19, 14, 5, 20);
		System.out.println("LocalDateTime.of(2014, 9, 19, 14, 5, 20): " +dt2);
		// Setting all fields
		LocalDateTime dt3 = LocalDateTime.of(2014, 9, 19, 14, 5, 20, 9);
		System.out.println("LocalDateTime.of(2014, 9, 19, 14, 5, 20, 9): " +dt3);
		
		LocalDate date = LocalDate.now();
		// Asumming this date LocalDate date = LocalDate.now();
		// And this time LocaDate time = LocalTime.now();
		// Combine the above date with the given time like this
		LocalDateTime dt4 = date.atTime(14, 30, 59, 999999);
		System.out.println("date.atTime(14, 30, 59, 999999): " +dt4);
		
		LocalTime time = LocalTime.now();
		LocalDateTime dt5 = date.atTime(time);
		System.out.println("date.atTime(time): " +dt5);
		// Or this LocalDateTime dt5 = date.atTime(time);
		// Combine this time with the given date. Notice that LocalTime
		// only has this constructor to be combined with a LocalDate
		LocalDateTime dt6 = time.atDate(date);
		System.out.println("time.atDate(date): " +dt6);
		
		System.out.println();
	}
	
	private static void getValues() {
		int year = now.getYear();
		System.out.println("year: "+ year);
		int dayYear = now.getDayOfYear();
		System.out.println("dayYear: "+ dayYear);
		int hour = now.getHour();
		System.out.println("hour: "+hour);
		int minute = now.getMinute();
		System.out.println("minute: "+minute);
		
		int month = now.get(ChronoField.MONTH_OF_YEAR);
		System.out.println("ChronoField.MONTH_OF_YEAR: "+month);
		int minuteHour = now.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println("ChronoField.MINUTE_OF_HOUR: "+minuteHour);
		
		System.out.println();
	}

	private static void compareInstance() {
		boolean after = now.isAfter(dt1); // true
		System.out.println("now.isAfter(dt1): "+after);
		boolean before = now.isBefore(dt1); // false
		System.out.println("now.isBefore(dt1): "+before);
		boolean equal = now.equals(dt1); // false
		System.out.println("now.equals(dt1): "+equal);
		
		System.out.println();
	}

	private static void newInstanceWith() {
		LocalDateTime dt7 = now.with(ChronoField.HOUR_OF_DAY, 10);
		System.out.println("now.with(ChronoField.HOUR_OF_DAY, 10): " +dt7);
		LocalDateTime dt8 = now.withMonth(8);
		System.out.println("now.withMonth(8): "+dt8);
		// Since these methods return a new instance, we can chain them!
		LocalDateTime dt9 = now.withYear(2013).withMinute(0);
		System.out.println("now.withYear(2013).withMinute(0): "+dt9);
		
		System.out.println();
	}

	private static void plusMinus() {
		// Adding
		LocalDateTime dt10 = now.plusYears(4);
		System.out.println("now.plusYears(4): " + dt10);
		LocalDateTime dt11 = now.plusWeeks(3);
		System.out.println("now.plusWeeks(3): " + dt11);
		
		LocalDateTime dt12 = now.plus(2, ChronoUnit.HOURS);
		System.out.println("now.plus(2, ChronoUnit.HOURS): " + dt12);

		// Subtracting
		LocalDateTime dt13 = now.minusMonths(2);
		System.out.println("now.minusMonths(2): "+ dt13);
		LocalDateTime dt14 = now.minusNanos(1);
		System.out.println("now.minusNanos(1): "+ dt14);
		LocalDateTime dt15 = now.minus(10, ChronoUnit.SECONDS);
		System.out.println("now.minus(10, ChronoUnit.SECONDS):" + dt15);
		
		System.out.println();
	}
	
	private static void instant(){
		// Setting seconds
		Instant fiveSecondsAfterEpoch = Instant.ofEpochSecond(5);
		System.out.println("Instant.ofEpochSecond(5): "+ fiveSecondsAfterEpoch);
		// Setting seconds and nanoseconds (can be negative)
		Instant sixSecTwoNanBeforeEpoch = Instant.ofEpochSecond(-6, -2);
		System.out.println("Instant.ofEpochSecond(-6, -2): " + sixSecTwoNanBeforeEpoch);
		// Setting milliseconds after (can be before also) epoch
		Instant fifthyMilliSecondsAfterEpoch = Instant.ofEpochMilli(50);
		System.out.println("Instant.ofEpochMilli(50): " + fifthyMilliSecondsAfterEpoch);
		
		Instant now = Instant.now();
		
		long seconds = now.getEpochSecond(); // Gets the seconds
		System.out.println("now.getEpochSecond(): " + seconds);
		int nanos1 = now.getNano(); // Gets the nanoseconds
		System.out.println("now.getNano(): " + nanos1);
		// Gets the value as an int
		int milis = now.get(ChronoField.MILLI_OF_SECOND);
		System.out.println("now.get(ChronoField.MILLI_OF_SECOND): " + milis);
		// Gets the value as a long
		long nanos2 = now.getLong(ChronoField.NANO_OF_SECOND);
		System.out.println("now.getLong(ChronoField.NANO_OF_SECOND): " + nanos2);
		
		System.out.println();
		
		// It is possibly use the same methods to compare (isAfter, isBefore, equal)
		// and aplus pr minus
	}
	
}
