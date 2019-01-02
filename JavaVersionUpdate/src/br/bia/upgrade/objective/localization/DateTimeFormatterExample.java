package br.bia.upgrade.objective.localization;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Reference:
 * http://pdf.th7.cn/down/files/1603/Oracle%20Certified%20Professional%20Java%20SE%208%20Programmer%20Exam%201Z0-809.pdf
 * 
 * @author fabiana
 *
 */
public class DateTimeFormatterExample {

	public static void main(String[] args) {
		//formatDate();
		formatTime();
	}
	
	private static void formatDate() {
		// patterns from simple to complex ones
		String[] dateTimeFormats = { "dd/MM/yyyy", /* d is day (in month), M is month, y is year of era */
				"dd/mm/yyyy", /* d is day (in month), m is minute, y is year */
				"dd/MM/YYYY", /* d is day (in month), m is minute, Y week-based year */
				"d '('E')' MMM, YYYY", /* E is name of the day (in week), Y is year */
				"w'th week of' YYYY", /* w is the week of the year */
				"W'th week of' YYYY", /* W is the week in month */
				"EEEE, dd'th' MMMM, YYYY" /* E is day name in the week */
		};
		LocalDateTime now = LocalDateTime.now();
		for (String dateTimeFormat : dateTimeFormats) {
			System.out.printf("Pattern \"%s\" is %s %n", dateTimeFormat,
					DateTimeFormatter.ofPattern(dateTimeFormat).format(now));

		}
		System.out.println();
	}
	
	private static void formatTime() {
		// patterns from simple to complex ones
		String[] timeFormats = { 
				"h:mm", /* h is hour in am/pm (1-12), m is minute */
				"k:mm", /* k is hour with range 1–24 */
				"hh 'o''clock'", /* '' is the escape sequence to print a single quote */
				"H:mm a", /* H is hour in day (0-23), a is am/pm */ 
				"hh:mm:ss:SS", /* s is seconds, S is milliseconds */ 
				"K:mm:ss a" /* K is hour in am/pm(0-11) */
		};
		LocalTime now = LocalTime.now();
		for (String timeFormat : timeFormats) {
			System.out.printf("Time in pattern \"%s\" is %s %n", timeFormat,
					DateTimeFormatter.ofPattern(timeFormat).format(now));
		}

	}
	
}
