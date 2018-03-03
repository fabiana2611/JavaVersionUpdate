package br.bia.upgrade.objective.lambda;

/**
 * - A functional interface is any interface that has exactly ONE ABSTRACT method.
 * - To make things easier, Java 8 also introduced the @FunctionalInterface annotation, 
 * which generates a compile-time error when the interface you have annotated is not a valid functional interface.
 * - Functional interfaces are the basis of lambda expressions.
 * 
 * Some example:
 * java.lang.Runnable
 * java.util.Comparator
 * java.util.concurrent.Callable
 * java.awt.event.ActionListener
 * 
 * http://ocpj8.javastudyguide.com/ch08.html
 * 
 * @author Fabiana Araujo	
 *
 */
public class FunctionalInterfacesExamples {

	/*
	 * This new funtionality in J8 is to simplify the old way or the use od strategy pattern:
	 * 
	 * List<Car> findCompactCars(List<Car> cars) {
	 * 		List<Car> twentyKCars = new ArrayList<Car>();
	 *	     for(Car car : cars) {
	 *	         if(car.getCostUSD() > 20000) {
	 *	             twentyKCars.add(car);
	 *	         }
	 *	     }
     *      return twentyKCars;
	 *	}
	 */
	
	public static void oneDefaultMethod() {
	 	A sum = a-> a+1;
        System.out.println(sum.method(2));
        System.out.println(sum.defaultMethod());
	}
	
	public static void twoDefaultMethodVoid() {
	 	B sum = ()-> System.out.println("2");
        sum.method();
        System.out.println(sum.defaultMethod());
        System.out.println(sum.anotherDefaultMethod());
	}
}
@FunctionalInterface
interface A {
    default int defaultMethod() {
        return 0;
    }
    Integer method(Integer a);
}

@FunctionalInterface
interface B {
    default int defaultMethod() {
        return 0;
    }
    default int anotherDefaultMethod() {
        return 1;
    }
    void method();
    
    // Methods from Object class
    boolean equals(Object o);
    int hashCode();
    String toString();
}
