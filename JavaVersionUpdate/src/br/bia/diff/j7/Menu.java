package br.bia.diff.j7;

import java.util.Scanner;

/**
 * Class to build menus 
 * @author Fabiana Araujo
 *
 */
public class Menu {

	private Scanner read;
	
	public Menu (Scanner read){
		this.read = read;
	}
	
	public int mainMenu() {
		System.out.println(
				  "=== MENU ======== \n"
				+ "(1) Version 7 	| \n" 
				+ "(2) Version 8 	| \n" 
				+ "(0) exit 	| \n"
				+ "================= "
				);

		System.out.printf("Select the version: ");
		
		return read.nextInt();
	}
	
	public int menuVersion7() {
		System.out.println("\n=== Version 7 === \n"
				+ "1: equalNullObjetcs \n" 
				+ "2: genericDeclare \n" 
				+ "3: stringsInSwitch \n"
				+ "4: automaticResourceManagement \n"
				+ "5: numericLiteralsUnderscores \n"
				+ "6: improvedExceptionHandling \n"
				+ "7: nioFileWorkingWithPath \n"
				+ "8: nioFileChangeNotifications \n"
				+ "9: invokedynamic \n"
				+ "99: <- back to main menu \n"
				+ "0: exit"
				);
		System.out.println();

		System.out.printf("Select the new funcionality: ");
		
		return read.nextInt();

	}
	
	public int menuVersion8() {
		System.out.println("\n=== Version 8 === \n"
				+ "1: In build \n" 
				+ "99: <- back to main menu \n" 
				+ "0: exit"
				);
		System.out.println();

		System.out.printf("Select the new funcionality: ");
		
		return read.nextInt();

	}
}
