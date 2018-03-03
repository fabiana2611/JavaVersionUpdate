package br.bia.upgrade.objective.collection;

import java.util.ArrayList;

/**
 * 
 * Generics
 * Iterating and Filtering Collections
 * Data Search
 * Peeking, Mapping, Reducing and Collecting
 * From Java 6/7 to Java 8
 * Peeking, Mapping, Reducing and Collecting
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 */
public class JavaCollectionsObjective {

	public static void init() {
		JavaCollectionsObjective objective = new JavaCollectionsObjective();
		objective.runGenericsExamples();
	}
	
	private void runGenericsExamples() {
		System.out.println("### Generics examples");
		GenericsExamples.usingDiamondOperator();
		GenericsExamples.typeInference();
		GenericsExamples.genericClass();
		GenericsExamples.genericMethod("Teste generic method");
		// CAn explicitly specifying the type
		GenericsExamples.<Integer>genericMethod(123);
		GenericsExamples.wildcards();
		GenericsExamples.wildcardsParam(new ArrayList<>());
		GenericsExamples.wildcardsBound();
		GenericsExamples.praticTest();
		GenericsExamples.praticTest2();
		GenericsExamples.praticTest3();
	}
}
