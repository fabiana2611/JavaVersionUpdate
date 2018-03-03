package br.bia.upgrade.objective.language;

/**
 * 
 * From Java 6/7 to Java 8; 
 * Exceptions; 
 * Interfaces
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 *
 */
public class LanguageEnhancementsObjectives {

	public static void init() {
		
		System.out.println("### From J 6/7 to J 8 examples:");
		Java67To8Examples.init();
		
		System.out.println("### Interfaces examples:");
		InterfaceExamples.init();
		
		System.out.println("### Exceptions examples:");
		ExceptionExamples.runExceptionExamples();
		
	}
	
}
