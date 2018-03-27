package br.bia.upgrade.objective.language;

/**
 * Objective 1: Language Enhancements
 * Use static and default methods of an interface including inheritance rules for a default method
 * 
 * - An interface has either public or default accessibility
 * - Interfaces are abstract by default (you don't have to specify it)
 * - You can't instantiate an interface directly, it has to be implemented by a class to use it.
 * - An interface cannot be marked as final.
 * - The methods defined in an interface are by default PUBLIC and ABSTRACT, the compiler will treat them as such even if you don't specify it.
 * - Fields declared in an interface are by default PUBLIC, STATIC, and FINAL. 
 * - A class can implement (not extend from) any number of interfaces.
 * - An interface can extend any number of interfaces, but it cannot extend from a class.
 * - An interface can only extend other interfaces.
 * - DEFAUKT METHOD: It was to support interface evolution, to add new functionality to interfaces and 
 *   at the same time, ensuring compatibility with the code written for older versions.
 *   -- Difference with abstract classes: 
 *   --- A class can only extend from ONE abstract class, but it can implement MULTIPLE interfaces.
 *   --- An abstract class can have a state through INSTANCE variables (fields). An interface CAN'T.
 *   -- Multiple interface inheritance with default methods: COMPILER ERROR. Task has to provide an implementation to override the interface default methods and solve the issue
 * - Static methods in interfaces are defined just like static methods in classes, with the keyword static.  must have a body

 * 
 * http://ocpj8.javastudyguide.com/ch04.html
 * 
 * @author Fabiana Araujo
 *
 */

interface Monitorable {
	int ID = 0;

	// For the compiler == public abstract void monitor();
	void monitor();

	void management();

	// J8: They were added to assist default methods and to better organize helper
	// methods
	static void log(String log) {
		System.out.println(log);
	}

	default void usingStaticMethod() {
		log("Default using static method.");
	}

    /**
     * So if we add another method to Monitorable, 
     * We have to update the class to avoid a compilation error,
     * it has to implement ALL the methods of that interface.
     * 
     * Java 8 gives us default methods. We don't have to provide implementations for them because they are non-abstract methods.
     * In other words, interfaces now allow methods with a BODY.
     * 
     *  An interface can have any number of abstract and default methods.
     *  All methods with the keyword default must have a body.
     *  Default methods are public implicitly, just as any other method of an interface.
     *  
     *  Default methods cannot be final.
     *  Default methods cannot be synchronized.
     *  Default methods are always public.
     *  You cannot have default methods for the Object's class methods.
     *  
     */
	default void processInParallel() {
		System.out.println("Interface Processing in parallel");
	}

	default void secondDefault() {
		System.out.println("Second default Interface");
	}
}

interface Manageble {
	void management();
}

class Process {
	public void processInParallel() {
		System.out.println("Process Class parallel");
	}
}

class Disk implements Monitorable, Manageble {
	/*
	 * @Override indicates that a method overrides a method declaration in a
	 * supertype, either in an interface or a parent class. If the annotated method
	 * doesn't override or implement the method correctly, the compiler will
	 * generate an error. This is the only function of the annotation.(non-Javadoc)
	 * 
	 * @see br.bia.upgrade.Monitorable#monitor()
	 */
	@Override
	public void monitor() {
		System.out.println("Monitoring Disk");
	}

	@Override
	public void management() {
		System.out.println("Management Disk");
	}

	/**
	 * Classes always WIN over interfaces
	 */
	public void processInParallel() {
		System.out.print("Call super ...");
		Monitorable.super.processInParallel();
		System.out.println("Disc Class parallel default method");
	}

}

class Server extends Process implements Monitorable {
	public void monitor() {
		System.out.println("Monitoring Server");
	}

	public void management() {
		System.out.println("Management Server");
	}
}

public class InterfaceExamples {

	public static void init() {

		System.out.println("ID: " + Monitorable.ID);

		Monitorable m = new Disk();
		m.monitor();
		m.management();
		m.processInParallel();
		m.secondDefault();
		m = new Server(); // Change implementation
		m.monitor();
		m.management();
		m.processInParallel(); // use the override method
		m.secondDefault();
		m.usingStaticMethod();
		// m.log("The end"); Doesn't compile
		// InterfaceExamples.log("The end"); Doesn't compile either
		Monitorable.log("The end");
		System.out.println();
	}
	
}
