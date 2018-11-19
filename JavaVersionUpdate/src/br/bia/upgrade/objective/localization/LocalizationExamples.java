package br.bia.upgrade.objective.localization;

import java.util.Arrays;
import java.util.Locale;

/**
 * 1. Describe the advantages of localizing an application and developing code that defines, reads, and sets the locale with a Locale object	
 * 2. Build a resource bundle for a locale and call a resource bundle from an application
 * 3. Create and manage date‐and time‐based events by using LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration, including a combination of date and time in a single object
 * 4. Format dates, numbers, and currency values for localization with the NumberFormat and DateFormat classes, including number and date format patterns
 * 5. Work with dates and times across time zones and manage changes resulting from daylight savings
 * 
 * http://ocpj8.javastudyguide.com/ch30.html
 * @author fabiana
 *
 */
public class LocalizationExamples {

	public static void init() {
		System.out.println("\n### Localization ###\n");
		getDefaultLocale(Locale.getDefault());
		getAllLocaleSupportedByJava();
		setLocale();
	}
	
	private static void getDefaultLocale(Locale locale){
		System.out.println("Country Code: " + locale.getCountry());
		System.out.println("Country Name: " + locale.getDisplayCountry());
		System.out.println("Language Code: "+ locale.getLanguage());
		System.out.println("Language Name: "+ locale.getDisplayLanguage() +"\n");
	}
	
	private static void getAllLocaleSupportedByJava(){
		Locale [] locales = Locale.getAvailableLocales();
		Arrays.stream(locales).forEach(System.out::println);
	}
	
	private static void setLocale(){
		// 1. Using a constructor
		Locale chinese = new Locale("zh");
		getDefaultLocale(chinese);
		
		Locale CHINA = new Locale("zh", "CN");
		getDefaultLocale(CHINA);
		
		// 2. Using the forLanguageTag(String) factory method
		Locale german = Locale.forLanguageTag("de");
		getDefaultLocale(german);
		
		// 3. Using Locale.Builder
		Locale japan = new Locale.Builder()
                .setRegion("JP")
                .setLanguage("jp")
                .build();
		getDefaultLocale(japan);
		
		
	}

}
