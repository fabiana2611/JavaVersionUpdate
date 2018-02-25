package br.bia.diff.j8;

import static java.time.temporal.TemporalAdjusters.next;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Examples of new Date/Time API
 * 
 * Main Reference:
 * https://leanpub.com/whatsnewinjava8/read
 * 
 * @author Fabiana Araujo
 *
 */
public class DateTimeApiExamples {

	public void plusHours(){
		System.out.println("Plus Hour [8]:");
		LocalTime now = LocalTime.now();
		LocalTime later = now.plus(8, ChronoUnit.HOURS);
		System.out.println(now);
		System.out.println(later);
		
		System.out.println();
	}
	
	public void immutatble(){
		System.out.println("--- Immutable:");
		LocalDate today = LocalDate.now();
		System.out.println("Now :"+today);
		LocalDate thirtyDaysFromNow = today.plusDays(30);
		System.out.println("thirtyDaysFromNow :"+thirtyDaysFromNow);
		LocalDate nextMonth = today.plusMonths(1);
		System.out.println("nextMonth :"+nextMonth);
		LocalDate aMonthAgo = today.minusMonths(1);
		System.out.println("aMonthAgo :"+aMonthAgo);
		System.out.println("Now [immutable]:"+today);
		
		System.out.println();
	}
	
	public void declaration(){
		System.out.println("--- Declaration: ");
		LocalDate date = LocalDate.of(2014, 3, 15);
		System.out.println("date [LocalDate.of(2014, 3, 15);]: "+date);
		date = LocalDate.of(2014, Month.MARCH, 15);
		System.out.println("date [LocalDate.of(2014, Month.MARCH, 15)]: "+date);
		
		LocalTime time = LocalTime.of(12, 15, 0);
		System.out.println("time [LocalTime.of(12, 15, 0)]: "+time);
		LocalDateTime datetime = date.atTime(time);
		System.out.println("time [date.atTime(time)]: "+datetime);
		
		System.out.println();
	}
	
	public void clock(){
		Clock clock = Clock.systemDefaultZone();
		LocalTime time = LocalTime.now(clock);
		System.out.println("Clock: "+time);
		
		System.out.println();
	}
	
	/**
	 * Period is a date-based amount of time, such as ‘2 years, 3 months and 4 days’.
	 */
	public void period() {
		System.out.println("--- period: ");
		LocalDate date1 = LocalDate.of(2014, 3, 15);
		LocalDate date2 = LocalDate.of(2015, 4, 15);
		Period p = Period.between(date1, date2);
		System.out.println("period [2014, 3, 15][2015, 4, 15] {Total Months}: "+p.getMonths());
	}
	
	/**
	 * Duration is a time-based amount of time, such as ‘34.5 seconds’.
	 */
	public void duration() {
		System.out.println("--- duration: ");
		LocalTime time1 = LocalTime.of(2, 59, 59);
		LocalTime time2 = LocalTime.of(12, 59 , 59);
		Duration d = Duration.between(time1, time2);
		System.out.println("duration [2, 59, 59][12, 59 , 59]seconds: "+d.getSeconds());

		Duration twoHours = Duration.ofHours(2);
		System.out.println("Duration.ofHours(2): "+twoHours);
		Duration tenMinutes = Duration.ofMinutes(10);
		System.out.println("Duration.ofMinutes(10): "+tenMinutes);
		Duration thirtySecs = Duration.ofSeconds(15);
		System.out.println("Duration.ofSeconds(15): "+thirtySecs);

		LocalTime t2 = time2.plus(twoHours);
		System.out.println("time.plus(twoHours): "+t2);
		
		System.out.println();
	}
	
	public void temporalAjust(){
		LocalDate nextTuesday = LocalDate.now().with(next(DayOfWeek.TUESDAY));
		System.out.println("--- TEmporal Adjust: : "+nextTuesday);
		
		System.out.println();
	}
	
	/**
	 * The original Date and Calendar objects have the toInstant() method to 
	 * convert them to the new Date-Time API. You can then use an ofInstant(Insant,ZoneId) 
	 * method to get a LocalDateTime or ZonedDateTime object; for example:
	 */
	public void backwardsCompatibility(){
		System.out.println("--- backwardsCompatibility: ");
		Date date = new Date();
		ZoneId myZone = ZoneId.systemDefault();
		Instant now = date.toInstant();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now, myZone);
		System.out.println("LocalDateTime.ofInstant(now, myZone): "+dateTime);
		ZonedDateTime zdt = ZonedDateTime.ofInstant(now, myZone);
		System.out.println("ZonedDateTime.ofInstant(now, myZone): "+zdt);
		
		System.out.println();
	}
}
