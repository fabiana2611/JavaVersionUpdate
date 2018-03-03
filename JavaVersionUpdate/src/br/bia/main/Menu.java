package br.bia.main;

import java.util.Scanner;

import br.bia.diff.j7.NewJava7;

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
				+ "(6) Upgrade   	| \n" 
				+ "(7) Version 7 	| \n" 
				+ "(8) Version 8 	| \n" 
				+ "(0) exit 	| \n"
				+ "================= "
				);

		System.out.printf("Select the version: ");
		
		return read.nextInt();
	}
	
	public int createMenu(NewJava newJava){
		if(newJava instanceof NewJava7){
			return menuVersion7();
		} else {
			return menuVersion8();
		}
	}
	
	public int menuVersion7() {
		System.out.println("\n=== Version 7 === \n"
				+ "1: geralExamples \n" 
				+ "2: nioFile \n"
				+ "3: nioFileChangeNotifications \n"
				+ "4: invokedynamic \n"
				+ "99: <- back to main menu \n"
				+ "0: exit"
				);
		System.out.println();

		System.out.printf("Select the new funcionality: ");
		
		return read.nextInt();

	}
	
	public int menuVersion8() {
		System.out.println("\n=== Version 8 === \n"
				+ "1: Stream \n"
				+ "2: optional \n" 
				+ "3: scripts \n" 
				+ "4: dateTimeApi \n" 
				+ "5: geralUpdate \n" 
				+ "6: lambdaExpression\n" 
				+ "99: <- back to main menu \n" 
				+ "0: exit"
				);
		System.out.println();

		System.out.printf("Select the new funcionality: ");
		
		return read.nextInt();

	}
}
