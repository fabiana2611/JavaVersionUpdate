package br.bia.upgrade.objective.localization;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DurationExamples {

	static Duration oneSecond = Duration.of(1, ChronoUnit.SECONDS);
	static Duration twoSeconds = Duration.ofSeconds(1, 1000000000);
	
	public static void init() {
			System.out.println("\n### Duration ###\n");
			declarations();
			differenceBetweenTwoDates();
			getValues();
			plusMinus();

	}

	private static void declarations() {
		Duration oneDay = Duration.ofDays(1); // 1 day = 86400 seconds
		System.out.println("Duration.ofDays(1): " + oneDay);
		Duration oneHour = Duration.ofHours(1); // 1 hour = 3600 seconds
		System.out.println("Duration.ofHours(1): " + oneHour);
		Duration oneMin = Duration.ofMinutes(1); // 1 minute = 60 seconds
		System.out.println("Duration.ofMinutes(1): " + oneMin);
		Duration tenSeconds = Duration.ofSeconds(10);
		System.out.println("Duration.ofSeconds(10): " + tenSeconds);
		// Set seconds and nanoseconds (if they are outside the range
		// 0 to 999,999,999, the seconds will be altered, like below)
		System.out.println("Duration.ofSeconds(1, 1000000000): " + twoSeconds);
		// Seconds and nanoseconds are extracted from the passed nanos
		Duration oneSecondFromNanos = Duration.ofNanos(1000000000);
		System.out.println("Duration.ofNanos(1000000000): " + oneSecondFromNanos);
		//Duration oneSecond = Duration.of(1, ChronoUnit.SECONDS);
		System.out.println("Duration.of(1, ChronoUnit.SECONDS): " + oneSecond);
		
	}

	private static void differenceBetweenTwoDates() {
		System.out.println("Duration.between( Instant.ofEpochSecond(123456789), Instant.ofEpochSecond(99999)): " 
				+ Duration.between( Instant.ofEpochSecond(123456789), Instant.ofEpochSecond(99999)));
		System.out.println();
	}

	private static void getValues() {
		// If the objects are of different types, then the duration is 
		// calculated based on the type of the first object. But this only 
		// works if the first argument is a LocalTime and the second is a 
		// LocalDateTime (because it can be converted to LocalTime). Otherwise, an exception is thrown.
		
		// Nanoseconds part the duration, from 0 to 999,999,999
		System.out.println("oneSecond.getNano(): "+ oneSecond.getNano());
		// Seconds part of the duration, positive or negative
		System.out.println("oneSecond.getSeconds(): "+oneSecond.getSeconds());
		// Supports SECONDS and NANOS.Other units throw an exception
		System.out.println("oneSecond.get(ChronoUnit.SECONDS): "+oneSecond.get(ChronoUnit.SECONDS));
		
		//Once an instance of Duration is created, we cannot modify it, but we can 
		// create another instance from an existing one.
		System.out.println("oneSecond.withNanos(8): "+oneSecond.withNanos(8));
		System.out.println("oneSecond.withSeconds(2).withNanos(1): "+ oneSecond.withSeconds(2).withNanos(1));
		System.out.println();
		
	}

	private static void plusMinus() {
		// Adding
		System.out.println("oneSecond.plusDays(4): "+oneSecond.plusDays(4));
		System.out.println("oneSecond.plusHours(3): "+oneSecond.plusHours(3));
		System.out.println("oneSecond.plusMinutes(3): "+oneSecond.plusMinutes(3));
		System.out.println("oneSecond.plusSeconds(3): "+oneSecond.plusSeconds(3));
		System.out.println("oneSecond.plusMillis(3): "+oneSecond.plusMillis(3));
		System.out.println("oneSecond.plusNanos(3): "+ oneSecond.plusNanos(3));
		System.out.println("oneSecond.plus(twoSeconds): "+ oneSecond.plus(twoSeconds));
		System.out.println("oneSecond.plus(1, ChronoUnit.DAYS): " + oneSecond.plus(1, ChronoUnit.DAYS)); 

		// Subtracting
		System.out.println("oneSecond.minusDays(4): "+ oneSecond.minusDays(4));
		System.out.println("oneSecond.minusHours(3): "+oneSecond.minusHours(3));
		System.out.println("oneSecond.minusMinutes(3): "+oneSecond.minusMinutes(3));
		System.out.println("oneSecond.minusSeconds(3): "+oneSecond.minusSeconds(3));
		System.out.println("oneSecond.minusMillis(3): "+oneSecond.minusMillis(3));
		System.out.println("oneSecond.minusNanos(3): "+oneSecond.minusNanos(3));
		System.out.println("oneSecond.minus(twoSeconds): "+oneSecond.minus(twoSeconds));
		System.out.println("oneSecond.minus(1, ChronoUnit.DAYS): "+oneSecond.minus(1, ChronoUnit.DAYS));
		System.out.println();
	}

}
