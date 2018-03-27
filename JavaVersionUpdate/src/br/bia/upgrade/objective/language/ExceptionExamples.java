package br.bia.upgrade.objective.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Objective 1: Language Enhancements
 * - Develop code that uses try-with-resources statements, including using classes that 
 * implement the AutoCloseable interface
 * - Develop code that handles multiple Exception types in a single catch block
 * 
 * - In a normal try block, the catch and the finally block are optional but either one of the must be present.
 * - When using a try-with-resources block, catch and finally are optional.
 * 
 * http://ocpj8.javastudyguide.com/ch19.html
 * 
 * @author Fabiana Araujo
 *
 */
public class ExceptionExamples {

	public static void runExceptionExamples() {
		ExceptionExamples examples = new ExceptionExamples();
		examples.multiCast();
		examples.tryResource();
		examples.myResouce();
		System.out.println();
	}
	
	private void multiCast() {
		try {
			int[] arr = new int[2];
			int res = (arr[1] != 0) ? 10 / arr[1] : 10 * arr[2];
			System.out.println("Sucess: " + res);
		} catch (ArithmeticException | IndexOutOfBoundsException e) {
			if (e instanceof ArithmeticException) {
				// Do something else if the exception type
				// is ArithmeticException
			}
			System.out.println("Exceptions throws: " + e.getClass());
		}
		// You cannot combine subclasses and their superclasses in the same multi-catch
		// block.
		// The code is redundant
		// catch (ArithmeticException | RuntimeException e) {
		// // The above line generates a compile-time error
		// // because ArithmeticException is a subclass of
		// // RuntimeException
		// e.printStackTrace();
		// return res;
		// }
		finally {
			//The finally block is ALWAYS executed
			// There's only one exception to this rule. If you call System.exit(), 
			//the program will terminate abnormally without executing the finally block.
			System.out.println("Finally block: ");
		}

	}
	
	/**
	 * The class(es) used in a try-with-resources block must implement one of the following interfaces:
	 * - java.lang.AutoClosable
	 * - java.io.Closable
	 */
	private void tryResource() {
		try (BufferedReader br = new BufferedReader(new FileReader("/file.txt"))) {
			int value = 0;
			while ((value = br.read()) != -1) {
				System.out.println((char) value);
			}
		} catch (IOException e) {
			System.out.println("Try Resource test exception: "+ e.getMessage());
		}
		// Using Try-with-resource this code is not necessary any more 
		// finally {
		// try {
		// if (br != null) br.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}
	
	private void myResouce() {
		try (MyResource r = new MyResource()) { // Problem gone!
		    r.useResource();
		    throw new Exception("Exception inside try");
		} catch (Exception e) {
			//The exception from the try block "wins" and the exceptions from the close() method are "suppressed".
		    System.out.println(e.getMessage());
		    Stream.of(e.getSuppressed())
		        .forEach(t -> System.out.println(t.getMessage()));
		}
	}
	
}

class MyResource implements AutoCloseable {
    public void close() throws Exception {
        int x = 0;
        //...
        if(x == 0) throw new Exception("Close Exception");
    }
    void useResource() {}
}

