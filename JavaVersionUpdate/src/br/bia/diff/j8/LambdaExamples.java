package br.bia.diff.j8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java 8 is a major update to the programming language which introduced a
 * significant upgrade to the functional programming called the Lambda
 * Expressions.
 * 
 * The Lambda Expressions in Java 8 allow you to code local functions as method
 * arguments.
 * 
 * Syntax:
 * To create a lambda expression, we specify input parameters (if there are any) 
 * on the left side of the lambda operator ->, and place the expression or block 
 * of statements on the right side of lambda operator.
 * 
 * (parameter_list) -> {function_body}
 * 
 * A lambda expression in Java has these main parts: Lambda expression only has body and parameter list.

 * Declaring the types of the parameters is optional. Using parentheses around
 * the parameter is optional if you have only one parameter. Using curly braces
 * is optional (unless you need multiple statements). The “return” keyword is
 * optional if you have a single expression that returns a value.
 * 
 * - () -> System.out.println(this) 
 * - (String str) -> System.out.println(str) 
 * - str -> System.out.println(str) 
 * - (String s1, String s2) -> { return s2.length() - s1.length(); } 
 * - (s1, s2) -> s2.length() - s1.length()
 * 
 * You can use lambda expression instead of anonymous inner classes.
 * 
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * https://blog.takipi.com/5-features-in-java-8-that-will-change-how-you-code/
 * https://beginnersbook.com/2017/10/java-lambda-expressions-tutorial-with-examples/
 * https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
 * 
 */
public class LambdaExamples {

	public void syntaxSortList(){
		System.out.println("--- sysntaxSortList {cleide,francisca,ana,maria}: ");
		String[] array = {"cleide","francisca","ana","maria"};
		Arrays.sort(array, 
				  (String s1, String s2) -> s2.length() - s1.length());
		 
		List<String> lista = Arrays.asList(array);
		lista.stream().forEach(System.out::println);
		
		System.out.println();
	}
	
	public void noParameter(){
		MyFunctionalInterface msg = () -> { return "Hello"; };
        System.out.println(msg.sayHello());
        
        System.out.println();
	}
	
	public void singleParam(){
		 // lambda expression with single parameter num
		MyFunctionalInterface2 f = (num) -> num+5;
        System.out.println(f.incrementByFive(22));
        
        System.out.println();
	}
	
	public void multipleParam(){
		// lambda expression with multiple arguments
    	StringConcat s = (str1, str2) -> str1 + str2;
        System.out.println("Result: "+s.sconcat("Hello ", "World"));
        
        System.out.println();
	}
	
	public void returnLambda(){
		StringConcat s = (str1,str2) -> {
			    	System.out.println("str1: " + str1);
			    	System.out.println("str2: " + str2);
			    	return str1 +" - " + str2;
			  		};
	    System.out.println("Result: "+s.sconcat("Hello ", "World"));
	    
	    StringConcat s2 = (str1,str2) -> {	return str1 +" - " + str2; };
	  	System.out.println("Result: "+s2.sconcat("Hello2 ", "World2"));
	  	
		System.out.println();
	}
	
	public void iteratinCollections(){
		List<String> list=new ArrayList<String>();  
	       list.add("Rick");         
	       list.add("Negan");       
	       list.add("Daryl");         
	       list.add("Glenn");         
	       list.add("Carl");                
	       list.forEach((names)->System.out.println(names));  
	       
	       System.out.println();
	}
	
	// Example get from:
	// https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
	public void geralTests() {
		// with type declaration
		MathOperation addition = (int a, int b) -> a + b;

		// with out type declaration
		MathOperation subtraction = (a, b) -> a - b;

		// with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> {return a * b;};

		// without return statement and without curly braces
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + operate(10, 5, addition));
		System.out.println("10 - 5 = " + operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + operate(10, 5, division));

		// without parenthesis
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// with parenthesis
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Mahesh");
		greetService2.sayMessage("Suresh");
	}
	
	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
	
}

// --------------------------------

@FunctionalInterface
interface MyFunctionalInterface {

	//A method with no parameter
    public String sayHello();
}

@FunctionalInterface
interface MyFunctionalInterface2 {

	//A method with single parameter
    public int incrementByFive(int a);
}

interface StringConcat {
    public String sconcat(String a, String b);
}
