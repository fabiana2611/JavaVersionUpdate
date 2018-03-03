package br.bia.upgrade.objective.language;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * computeIfAbsent
 * computeIfPresent
 * merge
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 *
 */
public class MapNewsExamples {

	public static void runExamples() {
		System.out.println("--- Map news methods examples");
		MapNewsExamples example = new MapNewsExamples();
		example.computeIfAbsent();
		example.mapUpdatePutIfAbsen();
		example.mapComputIfAbsent();
		example.mapComputNull();
		example.mapComputIfPresent();
		example.computIfPresent();
		example.mapUpdatePut();
		example.mapMerge();
		example.mapMergeNull();
	}
	
	private void computeIfAbsent() {
		Map<String, String> letters = new HashMap<>();
		letters.put("a", "a");
		letters.put("b", null);

		Function<String, String> func = k -> {
		    if(k.startsWith("d")) return null;
		    return k.toUpperCase();
		};

		// Won't update,already in map
		letters.computeIfAbsent("a", func);
		// Will update
		 letters.computeIfAbsent("b", func);
		// Will add
		letters.computeIfAbsent("c", func);
		// Won't add
		letters.computeIfAbsent("d", func);
		// Yes, there's also a forEach in Maps
		letters.forEach( (key, value) ->
		    System.out.format("%s-%s;", key, value)
		);
		System.out.println();
	}
	
	//this method skips it if the value is already set to a non-null value
	private void mapUpdatePutIfAbsen() {
		Map<String, String> favorites = new HashMap<>(); 
		favorites.put("Jenny", "Bus Tour"); 
		favorites.put("Tom", null);
		
		favorites.putIfAbsent("Jenny", "Tram");
		favorites.putIfAbsent("Sam", "Tram");
		favorites.putIfAbsent("Tom", "Tram");
		
		System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
		System.out.println();
	}
	
	//functional interface runs only when the key isn’t present or is null
	private void mapComputIfAbsent() {
		Map<String, Integer> counts = new HashMap<>(); 
		counts.put("Jenny", 15);
		counts.put("Tom", null);
		
		Function<String, Integer> mapper = (k) -> 1;
		
		Integer jenny = counts.computeIfAbsent("Jenny", mapper); // 15 
		Integer sam = counts.computeIfAbsent("Sam", mapper); // 1 
		Integer tom = counts.computeIfAbsent("Tom", mapper); // 1 
		
		System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}
		System.out.println(jenny); 
		System.out.println(sam); 
		System.out.println(tom); 
		System.out.println();
	}
	
	//If the mapping function is called and returns null, the key 
	//is removed from the map for computeIfPresent(). 
	//For computeIfAbsent(), the key is never added to the map in the  rst place
	private void mapComputNull() {
		Map<String, Integer> counts = new HashMap<>(); 
		counts.put("Jenny", 1);
		
		counts.computeIfPresent("Jenny", (k, v) -> null); 
		counts.computeIfAbsent("Sam", k -> null); 
		System.out.println(counts); // {}
		System.out.println();
	}
	
	//the return value is the result of what changed in the map or null if that doesn’t apply.
	private void mapComputIfPresent() {
			Map<String, Integer> counts = new HashMap<>();
			counts.put("Jenny", 1);
			
			BiFunction<String, Integer, Integer> mapper = (k, v) -> v + 1; 
			
			Integer jenny = counts.computeIfPresent("Jenny", mapper); 
			Integer sam = counts.computeIfPresent("Sam", mapper); 
			
			System.out.println(counts); // {Jenny=2} 
			System.out.println(jenny); // 2
			System.out.println(sam); // null
			System.out.println();
	}
	
	private void computIfPresent() {
		Map<String, String> letters = new HashMap<>();
		letters.put("a", "a");
		letters.put("b", null);
		letters.put("d", "d");

		BiFunction<String, String, String> func = (k, v) -> {
		    if(k.startsWith("d")) return null;
		    return k.toUpperCase();
		};

		// Will update
		letters.computeIfPresent("a", func);
		// Won't update 
		letters.computeIfPresent("b", func);
		// Won't add 
		letters.computeIfPresent("c", func);  
		// Will remove
		letters.computeIfPresent("d", func);  
		letters.forEach( (key, value) ->
		    System.out.format("%s-%s;", key, value)
		);
		System.out.println();
	}
	
	//The  first is to replace the existing value unconditionally
	private void mapUpdatePut() {
		Map<String, String> favorites = new HashMap<>(); 
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Jenny", "Tram"); 
		System.out.println(favorites); // {Jenny=Tram}
		System.out.println();
	}
	
	private void mapMerge() {
		BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1: v2;
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", "Tram");
		favorites.put("Sam", null);
		
		//“Bus Tour” is longer than “Skyride,”
		String jenny = favorites.merge("Jenny", "Skyride", mapper);
		//“Tram” is not longer than “Skyride,” 
		String tom = favorites.merge("Tom", "Skyride", mapper);
		//it simply uses the new value
		String sam = favorites.merge("Sam", "Skyride", mapper);
		
		System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
		System.out.println(jenny); // Bus Tour
		System.out.println(tom); // Skyride
		System.out.println(sam); // Skyride
		System.out.println();
	}
	
	private void mapMergeNull() {
		BiFunction<String, String, String> mapper = (v1, v2) -> null;
		Map<String, String> favorites = new HashMap<>(); 
		
		favorites.put("Jenny", "Bus Tour"); 
		favorites.put("Tom", "Bus Tour");
		
		favorites.merge("Jenny", "Skyride", mapper); 
		favorites.merge("Sam", "Skyride", mapper);
		
		/*
		 * Tom was left alone since there was no merge() call for that key. 
		 * Sam was added since that key was not in the original list. 
		 * Jenny was removed because the mapping function returned null. 
		 */
		System.out.println(favorites); // {Tom=Bus Tour, Sam=Skyride}
		System.out.println();
	}

}
