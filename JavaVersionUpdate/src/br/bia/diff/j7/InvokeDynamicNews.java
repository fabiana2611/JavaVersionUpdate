package br.bia.diff.j7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.logging.Logger;

/**
 * Supporting Dynamism: - Java is a statically typed language. - Ruby, Python
 * and Clojure: Dynamically typed languages. The type information is unresolved
 * until runtime in these languages.
 * 
 * V7: A new package, java.lang.invoke, consisting of classes such as
 * MethodHandle, CallSite and others, has been created to extend the support of
 * dynamic languages
 * 
 * Thus Java 1.7 enables JVM support for the non-java languages. 
 * 
 * Invokedynamic works with method handles to facilitate dynamic method
 * invocation. A method handle is "a typed, directly executable reference to an
 * underlying method, constructor, field, or similar low-level operation, with
 * optional transformations of arguments or return values."
 * 
 * Based links:
 * https://docs.oracle.com/javase/7/docs/technotes/guides/vm/multiple-language-support.html
 * https://www.javaworld.com/article/2860079/learn-java/invokedynamic-101.html
 * 
 * 
 * @author Fabiana Araujo
 *
 */

public class InvokeDynamicNews {
	
	private Logger logger = Logger.getAnonymousLogger();

	public void staticMethod() {
		
		try{
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle mh = lookup.findStatic(InvokeDynamicNews.class, "staticHello",
                    MethodType.methodType(void.class));
			
			mh.invokeExact();
			
			System.out.println();
			
		}catch (Throwable e){
			logger.warning("Error to call static method");
		}
	      
	}
	
	/**
	 * Although invokeExact() and invoke() are designed to execute a method 
	 * handle (actually, the target code to which the method handle refers), 
	 * they differ when it comes to performing type conversions on arguments 
	 * and the return value. invokeExact() doesn't perform automatic compatible-type 
	 * conversion on arguments. Its arguments (or argument expressions) must be 
	 * an exact type match to the method signature, with each argument provided 
	 * separately, or all arguments provided together as an array. invoke() 
	 * requires its arguments (or argument expressions) to be a type-compatible 
	 * match to the method signature -- automatic type conversions are performed, 
	 * with each argument provided separately, or all arguments provided together as 
	 * an array.
	 * 
	 * Reference: https://www.javaworld.com/article/2860079/learn-java/invokedynamic-101.html
	 */
	public void privateMethod() {
		
		try {
			InvokeDynamicNews hw = new InvokeDynamicNews();
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle mh = lookup.findVirtual(InvokeDynamicNews.class, "privateHello",
					MethodType.methodType(void.class));

			mh.invoke(hw);
			
			System.out.println();
			
		} catch (Throwable e) {
			logger.warning("Error to call private method");
		}

	}
	
	public void publicMethod() {
		try {
			InvokeDynamicNews hw = new InvokeDynamicNews();
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle mh = lookup.findVirtual(InvokeDynamicNews.class, "publicHello",
					MethodType.methodType(void.class));
			mh.invoke(hw);
			
			System.out.println();
			
		} catch (Throwable e) {
			logger.warning("Error to call public method");
		}
	}
	
	public void publicHello(){
		System.out.println("Public Hello");
	}
	
	private void privateHello(){
		System.out.println("Private Hello");
	}
	
	private static void staticHello(){
		System.out.println("Static Hello");
	}
}
