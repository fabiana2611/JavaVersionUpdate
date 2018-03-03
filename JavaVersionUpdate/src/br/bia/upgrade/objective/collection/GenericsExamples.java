package br.bia.upgrade.objective.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Generics are a mechanism for type checking at compile-time. 
 *  At runtime, Java doesn't know about generics.
 *  The process of replacing all references to generic types with Object is called type erasure.
 *  Generics only work with objects. Something like the following won't compile.
 *  
 *  Generic limitations: 
 *  - Generics don't work with primitive types
 *  - You cannot create an instance of a type parameter: class Test<T> { T var = new T();}
 *  - You cannot declare static fields of a type parameter
 *  - Due to type erasure, you cannot use instanceof with generic types
 *  - You cannot instantiate an array of generic types
 *  		T[] array; // OK
 *  		T[] array1 = new T[100]; // Error
 *  		List<String>[] array2 = new List<String>[10]; // Error
 *  - You cannot create, catch, or throw generic types, However, you can use a type parameter in a throws clause
 *  - You cannot overload a method where type erasure will leave the parameters with the same type
 *  
 *  http://ocpj8.javastudyguide.com/ch06.html
 *  
 * @author Fabiana Araujo
 *
 */
public class GenericsExamples {
    
	/*
	 * Compile error: List<int> list = new ArrayList<int>();
	 * Able since J7 - diamond operator (short form)
	 */
	public static void usingDiamondOperator() {
		
		List<List<String>> generic = new ArrayList<>();
		generic.add(Arrays.asList(new String[]{"Test1", "Teste2"}));
		generic.stream().forEach(System.out::println);
	}
	
	public static void typeInference() {
	    // In Java 7, this line generates a compile error
	    // In Java 8, this line compiles fine
	    testGenericParam(new ArrayList<>());
	}
	
	static void testGenericParam(List<String> list) { 
		System.out.println("Teste Type Inference");
	}
	
	public static void genericClass() {
		Holder<String> h1 = new Holder<>("Hi");
		Holder<Integer> h2 = new Holder<>(1);
		Holder h3 = new Holder("Hi again");
		
		String s = h1.getObject();
		h1.printObject();
		Integer i = h2.getObject();
		h2.printObject();
		Object o = h3.getObject();
		h3.printObject();
		
	}
	
	public static <T> void genericMethod(T t) {
		 System.out.println(t);
	}
	
	/*
	 * Compile Error: List<Object> list = new ArrayList<String>();
	 * An ArrayList<String> cannot be cast to List<Object> because when working with generics, 
	 * you cannot assign a derived type to a base type; both types should be the same (either explicitly or by using the diamond operator).
	 * 
	 * The unbounded wildcard type (<?>) means that the type of the list is unknown so that it can match ANY type.
	 * List<?> list = new ArrayList<String>();
	 */
	public static void wildcards() {
		List<String> stringList = new ArrayList<>();
		stringList.add("String1");
		// No problem
		List<?> unknownTypeList = stringList;
		for(Object o : unknownTypeList) { // Object?
			System.out.println(o);
		}
				
		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		// No problem either
		List<?> unknownTypeList2 = intList;
		for(Object o : unknownTypeList2) { // Object?
		   System.out.println(o);
		}
		
	}
	
	/*
	 * List<?> list = new ArrayList<String>(); list.add("Hi"); // Compile-time error
	 * 
	 * Since the compiler cannot infer the type of the elements, it can't assure
	 * type safety (you can only insert null because it doesn't have a type).
	 * 
	 * To avoid this problem, the compiler generates an error when you try to modify
	 * the list. This way, when using an unbounded wildcard the list becomes
	 * IMMUTABLE.
	 * 
	 * This wildcard is used in arguments of methods where the code just uses
	 * methods of the generic class or from Object, not of a particular type
	 */
	public static void wildcardsParam(List<?> list) {
		System.out.println("size: "+list.size());
	}
	
	public static void wildcardsBound() {
		Printer<String> p1 = new Printer<>(); // OK
		// Error, Byte is not a String
		//Printer<Byte> p2 = new Printer<>(); 
		p1.print("Teste Printer");
		
		// List<Object> list = new ArrayList<String>(); // Error
		//List<? extends Object> list2 = new ArrayList<String>(); // OK!
		//list2.add("Hi"); // Compile-time error
		
		List<Integer> listInteger = new ArrayList<>();
		List<Float> listFloat = new ArrayList<>();
		List<Number> listNumber = new ArrayList<>();
		listNumber.add(new Integer(1)); // OK
		listNumber.add(new Float(1.0F)); // OK
//		listNumber = listInteger; // Error
//		listNumber = listFloat; // Error
		listNumber.stream().forEach(System.out::println);

		List<? extends Number> listExtendsNum = new ArrayList<>();
		//listExtendsNum.add(new Integer(1)); // Error
		listInteger.add(5);
		listExtendsNum = listInteger; // OK
		listExtendsNum.stream().forEach(n->System.out.println("List<? extends Number> = "+n));
		listFloat.add(5.0F);
		listExtendsNum = listFloat; // OK
		listExtendsNum.stream().forEach(n->System.out.println("List<? extends Number> = "+n));
		
	}
	
	public static void praticTest() {
        List<Integer> l = new ArrayList<>();
        l.add(20);
        l.add(30);
        m1(l);
    }
    private static void m1(List<?> l) {
        m2(l); // 1
    }
    private static <T> void m2(List<T> l) {
        l.set(1, l.get(0)); // 2
        System.out.println(l);
    }
    
    public static void praticTest2() {
//    		int i = 0;
//    		float f = 1.0f;
//    		i = f; // error
    	
    		WildcardExample q = new WildcardExample<Integer>(); // 1
    	    q.t = new Float(1); // 2
    	    System.out.println(q.t);
    	    
    }
    
    public static void praticTest3() {
    		List<? super Number> list = new ArrayList<Object>(); // 1
	    list.add(new Integer(2)); // 2
	    list.add(new Float(2)); // 2
	    System.out.println("List<? super Number> list = new ArrayList<Object>();");
	    list.stream().forEach(System.out::println);
	    
	    List<? super Integer> list2 = new ArrayList<Object>(); // 1
	    list2.add(new Integer(3)); // 2
	    System.out.println("List<? super Integer> list = new ArrayList<Object>();");
	    list2.stream().forEach(System.out::println);
	    
    }
	
}

class Holder<T> {
    private T t;
    public Holder(T t) {
        this.t = t;
    }
    public T getObject() {
        return t;
    }
    public void printObject() {
        System.out.println("Generic "+ t.getClass() + ": " + t);
    }
}

class Printer<T extends String> {
	public void print(T t) {
		System.out.println(t.toUpperCase());// OK!
	}
}

class WildcardExample <T extends Number> {
	T t;
}
