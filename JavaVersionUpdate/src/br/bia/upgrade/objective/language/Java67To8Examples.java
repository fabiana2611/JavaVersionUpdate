package br.bia.upgrade.objective.language;

/**
 * 
 * Map
 * List
 * Switch
 * Numeric Literal
 * 
 * http://ocpj8.javastudyguide.com/ch31.html
 * 
 * @author fabiana
 *
 */
public class Java67To8Examples {

	public static void init() {
		CollectionNewsExamples.runExamples();
		MapNewsExamples.runExamples();
		switchCase();
		numericLiterals();
		numericLiteraisUndescore();
		System.out.println();
	}
	
	/**
	 * 1. The switch expression must evaluate to one of the following types:
	 * - byte, short, char, int
	 * - enum
	 * - String
	 * 
	 * 2. each case needs either a constant value or a final variable initialized at declaration
	 */
	private static void switchCase() {
		String s = "Jack";
		switch(s) {
		   case "Mike":
		      System.out.println("Good morning Mr. " + s); 
		      break;
		   case "Laura":
		      System.out.println("Good morning Mrs. " + s); 
		      break;
		   case "a":
			   System.out.println("a"); 
			   break;
		   case "A":
			   System.out.println("A"); 
			   break;
			   
		   default:
		      System.out.println("Good morning " + s); 
		}
		System.out.println();
	}
	
	/**
	 *  Integers numbers are by default of type int and floating-point numbers are by default of type double
	 *  
	 *  Hexadecimal numbers consist of numbers 0 through 9 and letters A through F. To specify a hexadecimal number, you add the 0x or 0X prefix.
	 *  Ex: int hex = 0x2B; // The number 43
	 *  Binary numbers consist of the numbers 0 and 1. To specify a binary number, you add the 0b or 0B prefix.
	 *  Ex: int bin = 0b0010110; // The number 22
	 */
	private static void numericLiterals() {
		float goodFloat = 0xF2;             // OK, number 242.0
		double goodDouble = 0b11110110; ;   // OK, number 246.0
//		float badFloat = 0xF2f;             // Compilation error!
//		double badDouble = 0b11110110d; ;   // Compilation error!
		
		System.out.println("goodFloat [ 0xF2]: " + goodFloat); 
		System.out.println("goodDouble [0b11110110]: " + goodDouble); 
		System.out.println();
	}
	
	/**
	 * From Java 7, you can place any number of underscores between the digits of numbers to separate groups of digits and improve readability.
	 * You cannot put underscores in the following places:
	 * - At the beginning or end of a number
	 * - Adjacent to a decimal point in a floating point literal
	 * - Before an F or L suffix
	 * - In positions where a string of digits is expected
	 */
	private static void numericLiteraisUndescore() {
		int i = 34_765;             // OK
		float f = 43.987_876f;       // OK
		short s = 0b0000____0101;   // OK
//		int i2 = 100_;              // Compilation error!
//		int i3 = 0_x1D;             // Compilation error!
//		int i4 = 0x_1D;             // Compilation error!
//		float f2 = 8945.40_f;       // Compilation error!
//		float f3 = 8945_.40f;       // Compilation error!
		
		System.out.println("[int i = 34_765]: " + i); 
		System.out.println("[float f = 43.987_876f]: " + f); 
		System.out.println("[short s = 0b0000____0101]: " + s); 
	}
	
}
