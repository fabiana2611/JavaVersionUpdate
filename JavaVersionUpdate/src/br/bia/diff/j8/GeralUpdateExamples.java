package br.bia.diff.j8;

import java.util.function.Function;

import br.bia.diff.j8.support.Shirt;

public class GeralUpdateExamples {

	//https://www.javabrahman.com/java-8/java-8-repeating-annotations-tutorial/
	public void repeatingAnnotation(){
		System.out.println("--- repeatingAnnotation");
		Shirt shirt = new Shirt();
		shirt.execute();
		System.out.println();
	}
	
	/**
	 * - andThen(Function): Returns a composed function that first applies this function to its input, and then applies the given function to the result.
	 * - compose(Function): Similar to andThen but in reversed order (first applies the given function to its input, and then this function).
	 * - identity(): Returns a function that always returns its input argument.
	 */
	public void functionalProgramming(){
		System.out.println("--- functionalProgramming");
		//The resulting function would take an Integer, multiply it by two, and then prepend “str” to it.
		Function<Integer,String> f = Function.<Integer>identity()
		       .andThen(i -> 2*i).andThen(i -> "str" + i);
		System.out.println("[f.apply(5)]: "+f.apply(5) );
		
		System.out.println();
	}
}
