package br.bia.main;

import java.util.Scanner;
import java.util.logging.Logger;

import br.bia.diff.j7.NewJava7;
import br.bia.diff.j8.NewJava8;

/**
 * Class to build options to test some news about java 7 and 8
 * 
 * @author Fabiana Araujo
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
			
			case 7:
				NewJava7 news7 = new NewJava7();
				executeVersion(news7);
				break;
			case 8:
				NewJava8 news8 = new NewJava8();
				executeVersion(news8);
				break;
			case 0:
				exit();
			default:
				System.out.println("Select a option.");
		}
	}
	
	private static void executeVersion(NewJava news){
		
		int option = menu.createMenu(news);
		
		while (true){	
			
			if(option == 0){
				exit();
			} else if( option == 99 ){
				clear();
				init();
				break;
			} else {
				news.executeVersion(option);
			}
		
			System.out.println();
			System.out.printf("%s", "Select a new option:");
			option = read.nextInt();
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