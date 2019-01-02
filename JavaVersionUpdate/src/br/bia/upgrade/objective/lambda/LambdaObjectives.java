package br.bia.upgrade.objective.lambda;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Functional Interface:
 * - Define and write functional interfaces and describe the interfaces of the java.util.function package
 * Lambda Expression:
 * - Describe a lambda expression; refactor the code that uses an anonymous inner class to use a lambda expression; describe type inference and target typing
 * Jva Built-in Lambda Interfaces
 * - Develop code that uses the built-in interfaces included in the java.util.function package, such as Function, Consumer, Supplier, UnaryOperator, Predicate, and Optional APIs, including the primitive and binary variations of the interfaces
 * http://ocpj8.javastudyguide.com/ch09.html
 * Method REference
 * - Develop code that uses a method reference, including refactoring a lambda expression to a method reference
 * 
 * @author fabiana
 *
 */
public class LambdaObjectives {

	public static void init() {
//		supply();
//		consumer();
//		predicate();
//		functional();
//		unary();
		option();
	}
	
	/*
	 * A Supplier is used when you want to generate or supply values without taking any input.
	 * */
	private static void supply() {
		
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = () -> LocalDate.now();
		LocalDate d1 = s1.get();
		LocalDate d2 = s2.get();
		System.out.println(d1);
		System.out.println(d2);
	}
	
	/*
	 *You use a Consumer when you want to do something with a parameter but not return anything.
	 *BiConsumer does the same thing except that it takes two parameters 
	 */
	private static void consumer() {
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);
		c1.accept("Annie");
		c2.accept("Annie");
		
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> b1 = map::put;
		BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
		b1.accept("chicken", 7);
		b2.accept("chick", 1);
		System.out.println(map);
		
		Map<String, String> map2 = new HashMap<>();
		BiConsumer<String, String> b3 = map2::put;
		BiConsumer<String, String> b4 = (k, v) -> map2.put(k, v);
		b3.accept("chicken", "Cluck");
		b4.accept("chick", "Tweep");
		System.out.println(map2);
	}
	
	/*
	 * Predicate is often used when filtering or matching. Both are
	 * very common operations. A BiPredicate is just like a Predicate except that it takes two
	 * parameters instead of one.
	 */
	private static void predicate() {
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> p2 = x -> x.isEmpty();
		System.out.println(p1.test(""));
		System.out.println(p2.test(""));
		
		BiPredicate<String, String> b1 = String::startsWith;
		BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
		System.out.println(b1.test("chicken", "chick"));
		System.out.println(b2.test("chicken", "chick"));
	}
	
	/*
	 * A Function is responsible for turning one parameter into a value of a potentially different
	 * type and returning it. Similarly, a BiFunction is responsible for turning two parameters
	 * into a value and returning it.
	 */
	private static void functional() {
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();
		System.out.println(f1.apply("cluck")); // 5
		System.out.println(f2.apply("cluck")); // 5
		
		BiFunction<String, String, String> b1 = String::concat;
		BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);
		System.out.println(b1.apply("baby ", "chick")); // baby chick
		System.out.println(b2.apply("baby ", "chick")); // baby chick
	}
	
	/*
	 * UnaryOperator and BinaryOperator are a special case of a function. They require all type
	 * parameters to be the same type. A UnaryOperator transforms its value into one of the
	 * same type.
	 */
	private static void unary() {
		UnaryOperator<String> u1 = String::toUpperCase;
		UnaryOperator<String> u2 = x -> x.toUpperCase();
		System.out.println(u1.apply("chirp"));
		System.out.println(u2.apply("chirp"));
		
		BinaryOperator<String> b1 = String::concat;
		BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);
		System.out.println(b1.apply("baby ", "chick")); // baby chick
		System.out.println(b2.apply("baby ", "chick")); // baby chick
	}
	
	private static void option() {
		Optional result = average(1,2,3,4,5);
		
		System.out.println(average(90, 100)); // Optional[95.0]
		System.out.println(average()); // Optional.empty
		
		Optional o = Optional.ofNullable(null);
		System.out.println(o);
		
		Optional<Double> opt = average(90, 100);
		opt.ifPresent(System.out::println);
		
		Optional<Double> opt2 = average();
		opt2.ifPresent(System.out::println);
	}
	
	public static Optional<Double> average(int... scores) {
		if (scores.length == 0) {
			return Optional.empty();
		}
		int sum = 0;
		for (int score: scores) {
			sum += score;
		}
		
		Double result = (double) sum / scores.length;
		
		return (result== null) ? Optional.empty(): Optional.of(result);
	}
}
