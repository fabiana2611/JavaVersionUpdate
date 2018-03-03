package br.bia.upgrade;

import java.util.ArrayList;

import br.bia.upgrade.objective.lambda.FunctionalInterfacesExamples;
import br.bia.upgrade.objective.language.CollectionNewsExamples;
import br.bia.upgrade.objective.language.LanguageEnhancementsObjectives;

/**
 * Objective
 * 1. Language Enhancements 
 * 2. Concurrency
 * 3. Localization
 * 4. Java File I/O (NIO.2)
 * 5. Lambda
 * 6. Java Collections
 * 7. Java Streams
 * 
 * Main references:
 * Oracle® Certified Professional Java® SE 8 Programmer II Study Guide
 * Java 8 Programmer II Study Guide [http://ocpj8.javastudyguide.com/]
 * 
 * @author Fabiana Araujo
 *
 */
public class UpgradeExamples {

	public static void main(String[] args) {
		
		// Objective 1
		LanguageEnhancementsObjectives.init();
		
		// Objective 2
		//Concurrency.init();
		
		//Objective 3
		//Localization.init();
		
		//Objective 4
		//Java File I/O (NIO.2)
		
		//Objective 5
//		LambdaObjectives.init();
		
		// Objective 6
//		JavaCollectionsObjective.init();
		
		// Objective 7
		//JavaStreamsObjectives.init();

//		runCollectionNewsExamples();
//		runFunctionInterfaceExamples();
		
//		runMehtodReferenceExamples();
//		runLambdaExpressionsExamples();
	}
	
	private static void runMehtodReferenceExamples() {
		MethodReferenceExamples.staticMethod();
		MethodReferenceExamples.instanceMethod();
		MethodReferenceExamples.constructorMethod();
	}
	
	private static void runFunctionInterfaceExamples() {
		FunctionalInterfacesExamples.oneDefaultMethod();
		FunctionalInterfacesExamples.twoDefaultMethodVoid();
	}
	
	private static void runLambdaExpressionsExamples() {
		// TODO
	}

}
