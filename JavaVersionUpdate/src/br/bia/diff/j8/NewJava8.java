package br.bia.diff.j8;

import java.util.logging.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import br.bia.main.NewJava;

/**
 * Class to test some updates in Java 8
 * 
 * Code name for Java SE 8 is Spider.
 * 
 * Update Java SE 8 (codename “Spider”) brings some major tweaks and upgrades to the Java programming language including :
 *  - New JavaScript Engine, Nashorn
 *  - New Date and Time API
 *  - Improved and faster JVM
 *  - Language-level support for Lambda Expressions
 *  - Interface default and Static Methods
 *  - Unsigned Integer Arithmetic
 *  - Concurrent API enhancements
 *  - Parallel Sorting
 *  - Null Reference Template
 *  - New and improved Stream API
 *  - Repeating annotations.
 *  - Statically-linked JNI libraries
 *  - Default methods (virtual extension methods) which make multiple inheritance possible in Java.
 *  - PermGen Error Removed: Most allocations for the class metadata are now allocated out of 
 *    native memory (you won’t have to set the “XX:PermSize” options anymore (they don’t exist).
 *  
 * Mains References:
 * http://www.differencebetween.net/technology/difference-between-java-7-and-java-8/
 * http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * https://leanpub.com/whatsnewinjava8/read
 * 
 * @author Fabiana Araujo
 *
 */
public class NewJava8 implements NewJava {
	
	private static final Logger log = Logger.getAnonymousLogger();

	public void executeVersion(int option){
		
		switch (option) {
			case 1:
				stream();
				break;
			case 2: 
				optional();
				break;
			case 3:
				scripts();
				break;
			case 4:
				dateTimeApi();
				break;
			case 5:
				geralUpdate();
				break;
			case 6:
				lambdaExpression();
				break;
			default:
				System.out.println("Select a option.");
		}
	}
	
	private void stream(){
		StreamExamples test = new StreamExamples();
		test.streamingTextPattern();
		test.streamIterate();
		test.rangeNumber();
		test.rangeClosedNumber();
		test.streamingAnything();
		test.streamSortLimit();
		test.streamSortedComparator();
		test.streamMach();
		test.reduce();
		test.mapToInt();
		test.streamJoinString();
		test.streamJoinObject();
		test.groupingBy1();
		test.groupingBy2();
		test.partitioningBy();
		test.findingMaximum();
		test.average();
	}
		
	private void optional(){
		OptionalExamples optional = new OptionalExamples();
		optional.nullValues();
		optional.isPresent();
		optional.defaultValue();
	}
	
	/**
	 * Java 8 is introducing a completely new JVM JavaScript engine – Nashorn, which allows 
	 * developers to run the script on a JVM. The idea was to implement a lightweight 
	 * JavaScript runtime in the programming language with a native JVM.
	 * 
	 * This engine makes unique use of some of the new features introduced in Java 7 such as 
	 * invokeDynamic to provide JVM-level speed to JavaScript execution right there with 
	 * the likes of V8 and SpiderMonkey.
	 * 
	 * Project Nashorn, a JavaScript runtime which allows developers to embed JavaScript 
	 * code within applications
	 */
	public void scripts(){
		try{
			System.out.println("--- ScriptEngine: ");
			ScriptEngineManager engineManager = new ScriptEngineManager();
			ScriptEngine engine = engineManager.getEngineByName("nashorn");
			
			engine.eval("function p(s) { print(s) }");
			engine.eval("p('Hello Nashorn');");
			
			System.out.println("--- ScriptInvockeFunction: ");
			Invocable inv = (Invocable) engine;
			engine.eval("function p(s) { print(s) }");
			inv.invokeFunction("p", "hello invoke");
			
		}catch (ScriptException | NoSuchMethodException e){
			log.warning("Error to execute JavaScript!!!");
		}
		
	}
	
	public void dateTimeApi(){
		DateTimeApiExamples dt = new DateTimeApiExamples();
		dt.plusHours();
		dt.immutatble();
		dt.declaration();
		dt.clock();
		dt.period();
		dt.duration();
		dt.temporalAjust();
		dt.backwardsCompatibility();
	}
	
	public void geralUpdate() {
		GeralUpdateExamples geral = new GeralUpdateExamples();
		geral.repeatingAnnotation();
		geral.functionalProgramming();
	}
	
	public void lambdaExpression(){
		LambdaExamples lambda = new LambdaExamples();
		lambda.syntaxSortList();
		lambda.noParameter();
		lambda.singleParam();
		lambda.multipleParam();
		lambda.returnLambda();
		lambda.iteratinCollections();
		lambda.geralTests();
	}
}
