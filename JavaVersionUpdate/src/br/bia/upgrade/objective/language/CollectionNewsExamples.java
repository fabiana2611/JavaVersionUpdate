package br.bia.upgrade.objective.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * removeIf
 * replaceAll
 * forEach
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 *
 */
public class CollectionNewsExamples {

	public static void runExamples() {
		System.out.println("--- List Collections Examples");
		CollectionNewsExamples examples = new CollectionNewsExamples();
		examples.removeIf();
		examples.listRemoveIf();
		examples.listReplaceAll();
		examples.replaceAll();
		examples.forEach1();
		examples.forEach2();
	}
	
	private void removeIf() {
		/*
		 * Old Versions:
		 * Iterator<String> it = list.iterator();
		 * while (it.hasNext()) {
		 *   String s = it.next();
		 *   if(s != null && s.length() < 3) {
		 *       it.remove();
		 *   }
		 * }
		 */
		//New Version	
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.removeIf( s -> s != null && s.length() < 3);
		System.out.println();
	}
	
	private void listRemoveIf() {
		List<String> list = new ArrayList<>();
		list.add("Magician");
		list.add("Assistant");
		System.out.println(list); // [Magician, Assistant]
		/*
		 * It`s not possible replace lambda by method reference. 
		 * Since startsWith takes a parameter that isn’t s, it 
		 * needs to be speci ed “the long way.”
		 */
		list.removeIf(s -> s.startsWith("A"));
		System.out.println(list); // [Magician]
		System.out.println();
	}
	
	private void listReplaceAll() {
		List<Integer> list = Arrays.asList(1, 2, 3); 
		list.replaceAll(x -> x*2); 
		System.out.println(list); // [2, 4, 6]
		System.out.println();
	}
	
	private void replaceAll() {
		/*
		 * Old version
		 * Iterator<String> it = list.iterator();
		 *	while (it.hasNext()) {
		 *	    String s = it.next();
		 *	    it.set(s.toUpperCase());
		 *	}
		 */
		//New version
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.replaceAll(s -> s.toUpperCase());
		System.out.println();
	}
	
	public void forEach1() {
		List<String> cats = Arrays.asList("Annie", "Ripley"); 
		cats.forEach(c -> System.out.println(c));
		System.out.println();
	}
	
	private void forEach2() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		//without creating a stream
		list.forEach(System.out::println);
		System.out.println();
	}
	
}
