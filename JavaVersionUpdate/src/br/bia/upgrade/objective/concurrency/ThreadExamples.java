package br.bia.upgrade.objective.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * However, it's better to implement Runnable on your own because with 
 * the new concurrency API, you don't have to create Thread objects 
 * directly anymore (not to mention that implementing an interface is the 
 * recommended object-oriented way to do it).
 * 
 * Reference:
 * http://ocpj8.javastudyguide.com/ch26.html
 * 
 * @author fabiana
 *
 */
public class ThreadExamples {

	public static void init (){
		
		implementInterface();
		anomymous();
		functionalInterface();
		subclassThread();
		executorsSingleThread();
		executorsSleep();
		executorsSleepShutdownNow();
		submitRunnable();
		submitCallable();
		invokyAny();
		invokyAll();
	}
	
	private static void implementInterface(){
		Thread thread = new Thread(new RunnableTask());
		// it might not start immediately
		thread.start();
	}
	
	private static void anomymous(){
		Thread thread = new Thread(new RunnableTask() {
		    public void run() {
		        System.out.println("Running anonymous");
		    }
		 });
		thread.start();
	}
	
	private static void functionalInterface(){
		Thread thread = new Thread( () -> {
		        System.out.println("Running functional interface");
		 });
		thread.start();
	}
	
	//discouraged
	private static void subclassThread(){
		Thread thread = new ThreadTask();
		thread.start();
	}
	
	/*
	 * Since there's only one thread (in addition to the main thread, don't forget that), 
	 * tasks are guaranteed to be executed in the order they were submitted, and no more 
	 * than one task will be active at any given time. 
	 */
	private static void executorsSingleThread(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable r = () -> { 
			IntStream.rangeClosed(1, 4)
			.forEach(System.out::println); 
		};
		
		System.out.println("before executing");
		executor.execute(r);
		System.out.println("after executing");
		executor.shutdown();
	}
	
	private static void executorsSleep(){
		executorsShutDown(false);
	}
	
	private static void executorsSleepShutdownNow(){
		executorsShutDown(true);
	}
	
	private static void executorsShutDown(boolean shutdownNow){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable r = () -> {
		    try {
		        Thread.sleep(5_000);
		    } catch (InterruptedException e) {
		        System.out.println("Interrupted");
		    }
		};
		System.out.println("before executing");
		executor.execute(r);
		System.out.println("before shutdown");
		
		if(shutdownNow){
			executor.shutdownNow();
		}else {
			executor.shutdown();
		}
		
		long start = System.currentTimeMillis();
		
		if(executor.isShutdown()){
			System.out.println("isShutdown ... ");
		}
		
		int count = 0;
		while (!executor.isTerminated()){
			if(count == 0){
				
				count ++;
			}
		}
		
		long delay = (System.currentTimeMillis() - start)/1000;
		
		System.out.println("\n[isTerminated]: " + delay +"segundos");
		
		System.out.println();
		
	}
	
	//When the submit() method is called with a Runnable, the returned 
	//Future object returns null (because Runnable doesn't return a result)
	private static void submitRunnable(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable r = () ->{
			IntStream.range(1, 1_000_000).forEach(System.out::println);
		};
		Future<?> future = executor.submit(r);
		try{
			future.get();
		} catch(InterruptedException | ExecutionException e){
			System.out.println("Error!!!");
		}
		
		System.out.println("Sucess!!!");
	}
	
	//When this method is called with a Callable, the returned Future object 
	//contains the result when it has finished executing:
	private static void submitCallable(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable c = () -> LongStream.rangeClosed(1, 1_000_000).sum();
		Future<Long> future = executor.submit(c);
		try{
			Long result = future.get(1, TimeUnit.SECONDS);
			System.out.println("Result: " + result);
		} catch(InterruptedException | ExecutionException | TimeoutException e){
			System.out.println("Error!!!");
		}
		
		System.out.println("Sucess!!!");
	}
	
	// invokeAny() executes the given tasks returning the result of one that has 
	//completed successfully. You have no guarantee about which of the Callable's 
	//results you'll get, just one of the ones that finish
	private static void invokyAny(){
		List<Callable<String>> callables = new ArrayList<>();
		callables.add(() -> "Callable 1");
		callables.add(() -> "Callable 2");
		callables.add(() -> "Callable 3");
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
		    String result = executor.invokeAny(callables);
		    System.out.println(result);
		} catch(InterruptedException | ExecutionException e) {/** ... */}
	}
	
	// invokeAll() executes the given tasks returning a list of Future objects that 
	//will hold the status and results until all tasks are completed. Future.isDone() 
	//returns true for each element of the returned list
	private static void invokyAll(){
		List<Callable<String>> callables = new ArrayList<>();
		callables.add(() -> "Callable 1");
		callables.add(() -> "Callable 2");
		callables.add(() -> "Callable 3");
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
		    List<Future<String>> futures = executor.invokeAll(callables);
		    for(Future<String> f : futures){
		    	System.out.format("%s - %s%n", f.get(), f.isDone());
		    }
		    
		} catch(InterruptedException | ExecutionException e) {/** ... */}
	}
}
