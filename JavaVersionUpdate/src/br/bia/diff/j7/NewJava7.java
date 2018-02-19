package br.bia.diff.j7;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 
 * Class to test some news about version 7.
 * 
 * References:
 * https://www.oreilly.com/learning/java7-features
 * http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html
 * http://javatutorialsbyraj.blogspot.com.br/2015/03/java-16-vs-17.html
 * 
 * @author Fabiana Araujo
 *
 */
public class NewJava7 {
	
	private Logger logger = Logger.getAnonymousLogger();

	Map<String, List<Integer>> mapTest = new HashMap <>();
	
	public void executeVersion7(int option){
		
		switch (option) {
			case 1:
				equalNullObjetcs();
				break;
			case 2:
				genericDeclare();
				break;
			case 3:
				stringsInSwitch("b");
				stringsInSwitch("d");
				break;
			case 4:
				automaticResourceManagement("movies.txt");
				break;
			case 5:
				numericLiteralsUnderscores();
				break;
			case 6:
				improvedExceptionHandling();
				break;
			case 7:
				nioFileWorkingWithPath();
				break;
			case 8:
				nioFileChangeNotifications();
				break;
			case 9:
				invokedynamic();
				break;
			default:
				System.out.println("Select a option.");
		}
	}
	
	/**
	 * Test: java.util.Objects
	 * a two-argument static equals method that returned true if both
	 * arguments are null, returns false is one argument is null, and otherwise
	 * returns the result of calling arg1.equals(arg2)
	 */
	private void equalNullObjetcs(){
		String a = null;
		String b = null;
		System.out.println("Equal [null, null]: " + Objects.equals(a, b));
	}
	
	/**
	 * V < 7: Resources such as Connections, Files, Input/OutStreams, etc. should be 
	 * closed manually by the developer by writing bog-standard code. Usually 
	 * we use a try-finally block to close the respective resources. 
	 * 
	 * V7: Declare the resources in the try as follows
	 */
	private void automaticResourceManagement(String fileName) {
		
		//The try-with-resources Statement
		try (
				FileOutputStream fos = new FileOutputStream(fileName);
				DataOutputStream dos = new DataOutputStream(fos)
			) {

			dos.writeUTF("Java 7 Block Buster");

		} catch (IOException e) {
			logger.warning("Error to create file");
		}
	}
	
	private void nioFileWorkingWithPath(){
		NIOFile nio = new NIOFile();
		String fileName = "testeFile.txt";
		automaticResourceManagement(fileName);
		nio.workingWithPath(fileName);
	}
	
	private void nioFileChangeNotifications(){
		NIOFile nio = new NIOFile();
		String fileName = "testeNotification.txt";		
		nio.fileChangeNotifications(fileName);
	}
	

	private void invokedynamic() {
		
		InvokeDynamicNews news = new InvokeDynamicNews();
		
		news.staticMethod();
		news.privateMethod();
		news.publicMethod();
	      
	}
	
	private void numericLiteralsUnderscores(){
		
		int million_a  =  1000000;
		System.out.println("million_a [1000000]: "+ million_a);
		
		int million_b  =  1_000_000;
		System.out.println("million_b [1_000_000]: "+ million_b);
	}

	private void stringsInSwitch(String choise){
		
		switch (choise) {

			case "a":
				System.out.println("Choise A");
				break;
			case "b":
				System.out.println("Choise B");
				break;
			case "c":
				System.out.println("Choise C");
				break;
			default:
				System.out.println("Choise DEFAULT");
				break;
			}
		
	}
	
	/**
	 * V7: Java 7 introduced multi-catch functionality to catch multiple 
	 * exception types using a single catch block.
	 */
	private void improvedExceptionHandling(){
		
		try{
			String[] a = {"a", "b"};
			System.out.println(a[5]);
			
      } catch(IndexOutOfBoundsException | IllegalArgumentException e) {
    	  System.out.println("Exception: " + e.getClass());
      }
		
	}
	
	/**
	 * Version < 7: Map<String, List<Trade>> maps = new TreeMap<String, List<Trade>> ();
	 * Version 7: Map<String, List<Trade>> trades = new TreeMap<> (); 
	 */
	private void genericDeclare(){
		
		List<Integer> aList = createList(1, 5);
		List<Integer> bList = createList(6, 10);
		
		mapTest.put("a", aList);
		mapTest.put("b", bList);
		
		for (String key: mapTest.keySet()){
			System.out.println("Key: "+key);
			List<Integer> itemList = mapTest.get(key);
			printList(itemList);
		}
	}
	
	private List<Integer> createList(int begin, int end){

		List<Integer> result = new ArrayList<>();
		
		for (int index = begin; index <= end; index ++){
			result.add(index);
		}
		
		return result;
	}
	
	private void printList(List<Integer> list){
		for (Integer item: list){
			System.out.println(item);
		}
	}
}
