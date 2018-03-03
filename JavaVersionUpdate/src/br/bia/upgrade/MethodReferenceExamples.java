package br.bia.upgrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * There are four formats for method references:
 * Static methods
 * Instance methods on a particular instance
 * Instance methods on an instance to be determined at runtime Constructors
 * 
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 *
 */
public class MethodReferenceExamples {

	public static void staticMethod() {
		/*
		 * Same: Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);
		 * Java knows that it should create a lambda with one parameter and pass it to the method
		 */
		Consumer<List<Integer>> methodRef1 = Collections::sort;
		
		// TODO
	}
	
	public static void instanceMethod() {
		
		//Ex1
		// Same: Predicate<String> lambda2 = s -> str.startsWith(s);
		String str = "abc";
		Predicate<String> methodRef2 = str::startsWith;
		
		// TODO
		
		//Ex2
		// Same: Predicate<String> lambda3 = s -> s.isEmpty();
		Predicate<String> methodRef3 = String::isEmpty;
		
		// TODO
	}
	
	public static void constructorMethod() {
		//Same: Supplier<ArrayList> lambda4 = () -> new ArrayList();
		Supplier<ArrayList> methodRef4 = ArrayList::new;
		
		// TODO
	}
}
