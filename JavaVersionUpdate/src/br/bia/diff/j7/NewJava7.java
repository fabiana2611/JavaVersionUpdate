package br.bia.diff.j7;

import br.bia.main.NewJava;

/**
 * 
 * Class to test some news about version 7.
 * 
 * Java SE 7 was codenamed Dolphin
 * 
 * Major features of Java 7 include:
 * -    Language enhancements grouped under a Project Coin
 * -    String object in switch statement
 * -    Multiple exceptions handling to eliminate duplication of codes
 * -    Upgraded class-loader architecture
 * -    Improved type interference for generic instance
 * -    Library support for ECC (elliptic curve cryptography) algorithms
 * -    Upgraded Rowset 1.1 and JDBC 4.1
 * -    Improved Managed Beans
 * -    Automatic resource management in try-statement
 * -    Concurrency and collections updates
 * -    Compressed 64-bit pointers
 * -    JVM support for dynamically-typed languages
 * 
 * References:
 * https://www.oreilly.com/learning/java7-features
 * http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html
 * http://javatutorialsbyraj.blogspot.com.br/2015/03/java-16-vs-17.html
 * 
 * @author Fabiana Araujo
 *
 */
public class NewJava7 implements NewJava{
	
	public void executeVersion(int option){
		
		switch (option) {
			case 1:
				geralExamples();
				break;
			case 2:
				nioFile();
				break;
			case 3:
				nioFileChangeNotifications();
				break;
			case 4:
				invokedynamic();
				break;
			default:
				System.out.println("Select a option.");
		}
	}
	
	private void geralExamples(){
		GeralExamples geral = new GeralExamples();
		geral.objetcs();
		geral.numericLiteralsUnderscores();
		geral.stringsInSwitch("a");
		geral.stringsInSwitch("b");
		geral.improvedExceptionHandling();
		geral.genericDeclare();
	}

	private void nioFile(){
		NIOFile nio = new NIOFile();
		nio.automaticResourceManagement("test.txt");
		nio.nioFileWorkingWithPath();
	}
	
	private void nioFileChangeNotifications(){
		NIOFile nio = new NIOFile();
		String fileName = "testeNotification.txt";		
		nio.fileChangeNotifications(fileName);
	}
	
	/**
	 * One of the most notable features added in the Java 7 is the JVM support 
	 * for dynamically-typed languages plus small language enhancements (Project Coin)
	 */
	private void invokedynamic() {
		
		InvokeDynamicNews news = new InvokeDynamicNews();
		
		news.staticMethod();
		news.privateMethod();
		news.publicMethod();
	      
	}
}
