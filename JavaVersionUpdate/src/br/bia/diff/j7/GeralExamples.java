package br.bia.diff.j7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GeralExamples {

	Map<String, List<Integer>> mapTest = new HashMap <>();
	
	/**
	 * Test: java.util.Objects
	 * a two-argument static equals method that returned true if both
	 * arguments are null, returns false is one argument is null, and otherwise
	 * returns the result of calling arg1.equals(arg2)
	 */
	public void objetcs(){
		String a = null;
		String b = null;
		System.out.println("Equal [null, null]: " + Objects.equals(a, b));
		
		System.out.println("nonNull [null]: " + Objects.nonNull(a));
		
		try{
			Objects.requireNonNull(a, "Value can't be null");
		} catch ( NullPointerException e){
			System.out.println("requireNonNull [null]: "+e.getMessage());
		}
		
		System.out.println();
	}
	
	public void numericLiteralsUnderscores(){
		
		int million_a  =  1000000;
		System.out.println("million_a [1000000]: "+ million_a);
		
		int million_b  =  1_000_000;
		System.out.println("million_b [1_000_000]: "+ million_b);
		
		System.out.println();
	}

	public void stringsInSwitch(String choise){
		
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
		
		System.out.println();
	}
	
	/**
	 * V7: Java 7 introduced multi-catch functionality to catch multiple 
	 * exception types using a single catch block.
	 * 
	 * Users can catch multiple exception types in one catch block.
	 */
	public void improvedExceptionHandling(){
		
		try{
			String[] a = {"a", "b"};
			System.out.println(a[5]);
			
      } catch(IndexOutOfBoundsException | IllegalArgumentException e) {
    	  System.out.println("Exception: " + e.getClass());
      }
		
		System.out.println();
		
	}
	
	/**
	 * Version < 7: Map<String, List<Trade>> maps = new TreeMap<String, List<Trade>> ();
	 * Version 7: Map<String, List<Trade>> trades = new TreeMap<> (); 
	 */
	public void genericDeclare(){
		
		List<Integer> aList = createList(1, 5);
		List<Integer> bList = createList(6, 10);
		
		mapTest.put("a", aList);
		mapTest.put("b", bList);
		
		for (String key: mapTest.keySet()){
			System.out.println("Key: "+key);
			List<Integer> itemList = mapTest.get(key);
			printList(itemList);
		}
		
		System.out.println();
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
