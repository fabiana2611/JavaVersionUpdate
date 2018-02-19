package br.bia.main;

import java.util.Scanner;
import java.util.logging.Logger;

import br.bia.diff.j7.Menu;
import br.bia.diff.j7.NewJava7;

/**
 * Class to build options to test some news about java 7
 * @author Fabiana . Araujo
 *
 */
public class Begining {
	
	private static final Logger log = Logger.getAnonymousLogger();

	private static Scanner read = new Scanner(System.in);
	private static Menu menu = new Menu(read);

	public static void main(String[] args) {
		log.info("*** It app execute some examples about the news in differents java versions. ***\n ");
		init();
	}
	
	private static void init(){
		int option = menu.mainMenu();
		menuVersion(option);
	}
	
	private static void menuVersion(int mainOption) {
		
		switch (mainOption) {
			
			case 1:
				executeVersion7();
			case 2:
				executeVersion8();
			case 0:
				exit();
				break;
			default:
				System.out.println("Select a option.");
		}
	}
	
	private static void executeVersion7(){
		
		int option7 = menu.menuVersion7();
		NewJava7 new7 = new NewJava7();
		
		while (true){	
			
			if(option7 == 0){
				exit();
			} else if( option7 == 99 ){
				clear();
				init();
				break;
			} else {
				new7.executeVersion7(option7);
			}
		
			System.out.println();
			System.out.printf("%s", "Select a new option:");
			option7 = read.nextInt();
		}
	}
	
	private static void executeVersion8(){
		
		int option8 = menu.menuVersion8();
		
		while (true){	
			
			if(option8 == 0){
				exit();
			} else if( option8 == 99 ){
				clear();
				init();
				break;
			} else {
				System.out.println("This option is been building...");
			}
		
			System.out.println();
			System.out.printf("%s", "Select a new option:");
			option8 = read.nextInt();
		}
	}

	private static void exit(){
		System.out.println("EXIST!!!");
		read.close();
		System.exit(0);
	}
	
	private static void clear(){
		for (int i = 0;i<5;i++){
			System.out.println();
		}
	}
}