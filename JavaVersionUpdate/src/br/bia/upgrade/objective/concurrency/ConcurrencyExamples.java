package br.bia.upgrade.objective.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Objective 2: Concurrency
 * Use classes from the java.util.concurrent package including 
 * CyclicBarrier and CopyOnWriteArrayList with a focus on the 
 * advantages over and differences from the traditional java.util collections
 * 
 * http://ocpj8.javastudyguide.com/ch27.html
 * https://www.journaldev.com/1034/java-blockingqueue-example
 * http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
 * OCP Oracle Certified Professional Java SE 8 Programmer II
 * 
 * @author Fabiana Araujo
 *
 */
public class ConcurrencyExamples {

	static int n = 0;
	
	private int sheepCount = 0;
	
	private Map<String,Object> foodData = new HashMap<String,Object>();
	private Map<String,Object> foodData2 = new ConcurrentHashMap<String,Object>();
	
	public static void init() {
		
		ConcurrencyExamples examples = new ConcurrencyExamples();
		
		examples.atomicClass();
		examples.concurrentExecutor();
		examples.usingCollections();
		examples.usingCollectionsCouncurrency();
		examples.memoryConcistency();
		examples.councurrentClasses();
		examples.concurrentClassExceptionBlockingQueue();
		examples.concurrentClassExceptionBlockingDeque();
		examples.copyOnWrite();
		examples.syncronizedMethods();
		examples.parallelStream();
		examples.performanceImprovement();
		examples.concurrencyHashMap();
		examples.blockingQueue();
		examples.blockingDeque();
		examples.courrentyHashMap();
		examples.concurrentNavigableMap();
		examples.copyOnWriteArrayList();
		examples.compareCopyOnWriteAndCouncurrencySkipe();
		examples.cyclicBarrier();
		examples.staticSynchronizedTests();
		examples.invokAll();
		examples.fork();
	}

	// Test 0
	
	private void atomicClass() {
		System.out.println("### atomicClass");
		AtomicInteger n = new AtomicInteger(0);
	    System.out.println(n.incrementAndGet());
	    System.out.println(n.addAndGet(2));
	    System.out.println(n.compareAndSet(2, 5));
	    
	    AtomicBoolean b = new AtomicBoolean(true);
	    System.out.println(b.getAndSet(false));
	    
	    AtomicLong l = new AtomicLong(0L);
	    System.out.println(l.floatValue());
	}
	
	// ----- Test 1
	
	/*
	 * Using the concurrent collections is extremely convenient in practice. It
	 * also prevents us from introducing mistakes in own custom implementation, such as if we
	 * forgot to synchronize one of the accessor methods. In fact, the concurrent collections often
	 * include performance enhancements that avoid unnecessary synchronization. 
	 * 
	 * Reference: OCP Oracle Certified Professional Java SE 8 Programmer II
	 * 
	 */
	private void concurrentExecutor(){
		System.out.println("### concurrentExecutor");
		ExecutorService service = null;
		
		try{
			service = Executors.newFixedThreadPool(20);
			
			ConcurrencyExamples manager = new ConcurrencyExamples();
			
			for (int i = 0; i< 10; i++){
				service.submit(()-> manager.incrementAndReport());
			}
		} finally{
			if(service!=null){
				service.shutdown();
			}
		}
		
		System.out.println();
	}
	
	private synchronized void incrementAndReport(){
		//synchronized(this){
			System.out.print((++sheepCount)+ " ");
		//}
	}
	
	//------ Test 2
	
	private void usingCollections(){
		System.out.println("### usingCollections");
		ConcurrencyExamples manager = new ConcurrencyExamples();
		manager.put("a", "1");
		manager.put("b", "2");
		
		ConcurrencyExamples manager2 = new ConcurrencyExamples();
		manager2.put("a", "3");
		manager2.put("b", "4");
		
		System.out.print(manager.get("b")+" ");
		System.out.print(manager2.get("b")+" ");
		
		System.out.println();
	}

	private synchronized void put(String key, String value) {
		foodData.put(key, value);
	}
		
	private synchronized Object get(String key) {
		return foodData.get(key);
	}
	
	// ---- Test 3
	
	private void usingCollectionsCouncurrency(){
		System.out.println("### usingCollectionsCouncurrency");
		ConcurrencyExamples manager = new ConcurrencyExamples();
		manager.put("a", "1");
		manager.put("b", "2");
		
		ConcurrencyExamples manager2 = new ConcurrencyExamples();
		manager2.put("a", "3");
		manager2.put("b", "4");
		
		System.out.print(manager.get("b")+" ");
		System.out.print(manager2.get("b")+" ");
		
		System.out.println();
	}
	
	private void putCouncurrency(String key, String value) {
		foodData2.put(key, value);
		}
	
	private Map<String,Object> getFoodData2(){
		return foodData2;
	}
	
	// Test 4 - Memory Consistency
	
	/*
	 * To use councurrency is necerry. If it not happen then will throw a 
	 * ConcurrentModificationException at runtime, since the iterator keyset() 
	 * is not properly updated after the first element is removed. 
	 * 
	 * Although we don’t usually modify a loop variable, this example highlights the fact that
	 * the ConcurrentHashMap is ordering read/write access such that all access to the class is
	 * consistent. In this code snippet, the iterator created by keySet() is updated as soon as an
	 * object is removed from the Map .
	 */
	private void memoryConcistency(){
		System.out.println("### memoryConcistency");
		ConcurrencyExamples manager = new ConcurrencyExamples();
		manager.putCouncurrency("a", "1");
		manager.putCouncurrency("b", "2");
		
		System.out.println("Init: "+manager.getFoodData2());
		for(String key: manager.getFoodData2().keySet()){
			manager.getFoodData2().remove(key);
			System.out.println("Executing: "+manager.getFoodData2());
		}
		System.out.println("End: "+manager.getFoodData2());
		
		System.out.println();
	}
	
	// --- Test 5
	
	/*
	 * Examples How to use
	 */
	private void councurrentClasses(){
		System.out.println("### councurrentClasses");
		Map<String,Integer> map = new ConcurrentHashMap<>();
		map.put("zebra", 52);
		map.put("elephant", 10);
		System.out.println(map.get("elephant"));
		
		Queue<Integer> queue = new ConcurrentLinkedQueue<>();
		queue.offer(31);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		
		Deque<Integer> deque = new ConcurrentLinkedDeque<>();
		deque.offer(10);
		deque.push(4);
		System.out.println(deque.peek());
		System.out.println(deque.pop());
		
		System.out.println();
	}
	
	// ---- Test 6
	
	private void concurrentClassExceptionBlockingQueue() {
		System.out.println("### concurrentClassExceptionBlockingQueue");
		try {
			BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
			blockingQueue.offer(39);
			blockingQueue.offer(3, 4, TimeUnit.SECONDS);
			System.out.println(blockingQueue.poll());
			System.out.println(blockingQueue.poll(10, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// Handle interruption
		}
	}
	
	// ----- Test 7
	
	private void concurrentClassExceptionBlockingDeque() {
		System.out.println("### concurrentClassExceptionBlockingDeque");
		try {
			BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
			blockingDeque.offer(91);
			blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
			blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
			blockingDeque.offer(3, 4, TimeUnit.SECONDS);
			System.out.println(blockingDeque.poll());
			System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
			System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
			System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// Handle interruption
		}
	}
	
	// --- Test 8
	
	/*
	 * Despite adding elements to the array while iterating over it, only those elements in the
	 * collection at the time the for() loop was created were accessed. Alternatively, if we had
	 * used a regular ArrayList object, a ConcurrentModificationException would have been
	 * thrown at runtime.
	 * 
	 * They are commonly used in multi-threaded
	 * environment situations where reads are far more common than writes
	 */
	private void copyOnWrite() {
		System.out.println("### copyOnWrite");
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
		System.out.println(list);
		System.out.println("Size init: " + list.size());
		for (Integer item : list) {
			System.out.println("Item: " + item +" | Size executing: " + list.size() + " | list: " + list);
			list.add(9);
		}
		System.out.println();
		System.out.println("Size end: " + list.size());
		System.out.println(list);
		
		System.out.println();
	}
	
	// --- Test 9
	
	/*
	 * Concurrency API also includes methods for obtaining synchronized versions of 
	 * existing non-concurrent collection objects. These methods, defined in the 
	 * Collections class, contain synchronized methods that operate on the inputted 
	 * collection and return a reference that is the same type as the underlying collection.
	 * 
	 * While that methods synchronize access to the data elements, such as the get() and set() methods, 
	 * they do not synchronize access on any iterators that you may create from the 
	 * synchronized collection. Therefore, it is imperative that you use a
	 * synchronization block if you need to iterate over any of the returned collections
	 */
	private void syncronizedMethods(){
		System.out.println("### syncronizedMethods");
		
		List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(4, 3, 52)));
		
		synchronized (list) {
			for (int data : list)
				System.out.print(data + " ");
		}
		
		System.out.println();
	}

	// --- Test 10
	
	private void parallelStream(){
		System.out.println("### parallelStream");
		Arrays.asList(1,2,3,4,5,6)
			.parallelStream()
			.forEach(s -> System.out.print(s+" "));
		
		System.out.println();
		
		//forces a parallel stream to process the results in order at the
		//cost of performance
		Arrays.asList(1,2,3,4,5,6)
			.parallelStream()
			.forEachOrdered(s -> System.out.print(s+" "));
		
		System.out.println();
	}
	
	// --- Test 11
	
	private void performanceImprovement(){
		System.out.println("### performanceImprovement");
		ConcurrencyExamples calculator = new ConcurrencyExamples();
		// Define the data
		List<Integer> data = new ArrayList<Integer>();
		for(int i=0; i<4000; i++){
			data.add(i);
		}
		
		// Process the data (paralell)
		long start = System.currentTimeMillis();
		calculator.processAllDataSerial(data);
		double time = (System.currentTimeMillis() - start) / 1000.0;
		// Report results
		System.out.println("\nSerial Tasks completed in: " + time + " seconds");
				
		// Process the data (paralell)
		long start2 = System.currentTimeMillis();
		calculator.processAllDataParalell(data);
		double time2 = (System.currentTimeMillis() - start2)/1000.0;
		// Report results
		System.out.println("\nParalell Tasks completed in: "+time2+" seconds");
		
		System.out.println();
	}
	
	private int processRecord(int input) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// Handle interrupted exception
		}
		return input + 1;
	}

	private void processAllDataSerial(List<Integer> data) {
		// With serial, Tasks completed in: 40.044 seconds
		data.stream().map(a -> processRecord(a)).count();
	}
	
	private void processAllDataParalell(List<Integer> data) {
		//Tasks completed in: 10.542 seconds
		data.parallelStream().map(a -> processRecord(a)).count();
	}
	
	
	// ---- Test 12: Static Synchronized Methods
	
	private void staticSynchronizedTests() {
		System.out.println("### staticSynchronizedTests");
		notSinchronizedTest();
		sinchronizedTest();
	}
	
	private static void notSinchronizedTest() {
		System.out.println("### Test static NOT sinchronized test...");
		ExecutorService service = Executors.newFixedThreadPool(4);
		Runnable r = () -> add();
		for (int i = 0; i < 4; i++) {
			service.execute(r);
		}
		service.shutdown();
	}

	private static void add() {
		n++;
		System.out.println(n);
	}

	private void sinchronizedTest() {
		System.out.println("### Test static sinchronized test 1...");
		ExecutorService service = Executors.newFixedThreadPool(4);
		Runnable r = () -> addSynchronized();
		for (int i = 0; i < 4; i++) {
			service.execute(r);
		}
		service.shutdown();
		
		System.out.println("### Test static sinchronized test 2...");
		ExecutorService service2 = Executors.newFixedThreadPool(4);
		Runnable r2 = () -> addSynchronized2();
		for (int i = 0; i < 4; i++) {
			service2.execute(r2);
		}
		
		service2.shutdown();
	}

	private synchronized void addSynchronized() {
		n++;
		System.out.println(n);
	}
	
	// Is equivalent to
	static void addSynchronized2() {
		synchronized (ConcurrencyExamples.class) {
			n++;
			System.out.println(n);
		}
	}
	
	//------------ Test 13: Collections
	
	private void concurrencyHashMap() {
		System.out.println("### concurrencyHashMap");
		Map<String, Integer> map = new ConcurrentHashMap<>();
		map.putIfAbsent("a", 0);
		System.out.println(map.get("a"));
		
		map.put("one", 1);
		Integer val = map.get("one");
		System.out.println(val);
	}
	
	// --- TEst 14
	
	//doesn’t accept null
	//thread-safe
	//All queuing methods are atomic in nature and use internal locks or other forms of concurrency control.
	//https://www.journaldev.com/1034/java-blockingqueue-example
	//http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
	//Java provides several BlockingQueue implementations such as ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue etc.
	private void blockingQueue() {
		System.out.println("### blockingQueue ...");
		
		linkedBlockingQueue();
		
		//Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
	}
	
	private void linkedBlockingQueue() {
		BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
		BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);

		try {
			bounded.put("Value");
			System.out.println(bounded.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// --- Test 15
	
	private void blockingDeque() {
		System.out.println("### blockingDeque ...");
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

		deque.addFirst("1");
		deque.addLast("2");

		try {
			String two = deque.takeLast();
			String one = deque.takeFirst(); 
			System.out.println(two);
			System.out.println(one);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//---- Test 16
	
	//https://www.journaldev.com/122/java-concurrenthashmap-example-iterator
	private void courrentyHashMap() {
		System.out.println("### courrentyHashMap ...");
		
		// ConcurrentHashMap
		Map<String, String> myMap = new ConcurrentHashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		myMap.put("6", "1");
		System.out.println("ConcurrentHashMap before iterator: " + myMap);
		Iterator<String> it = myMap.keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			if (key.equals("3")) {
				myMap.put(key + "new", "new3");
			}
		}
		System.out.println("ConcurrentHashMap after iterator: " + myMap);

		// HashMap
		myMap = new HashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		myMap.put("6", "1");
		System.out.println("HashMap before iterator: " + myMap);
		Iterator<String> it1 = myMap.keySet().iterator();

		while (it1.hasNext()) {
			String key = it1.next();
			if (key.equals("3")) {
				//myMap.put(key + "new", "new3");
				myMap.put(key, "new3");
				break;
			}
		}
		System.out.println("HashMap after iterator: " + myMap);
	}
	
	// --- Test 17
	
	private void concurrentNavigableMap() {
		System.out.println("### concurrentNavigableMap ...");
		
		ConcurrentNavigableMap map = new ConcurrentSkipListMap();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		
		System.out.println("before: " + map);

		ConcurrentNavigableMap headMap = map.headMap("2");
		System.out.println("headMap[2]: " + headMap);
		ConcurrentNavigableMap headMap3 = map.headMap("3");
		System.out.println("headMap[3]: " + headMap3);
		
		ConcurrentNavigableMap tailMap = map.tailMap("2");
		System.out.println("tailMap[2]: " + tailMap);
		ConcurrentNavigableMap tailMap3 = map.tailMap("3");
		System.out.println("tailMap[3]: " + tailMap3);
		
		ConcurrentNavigableMap subMap = map.subMap("2", "3");
		System.out.println("subMap: " + subMap);
		
		
	}
	
	// --- TEst 18
	
	private void copyOnWriteArrayList() {
		System.out.println("### copyOnWriteArrayList");
		List<Integer> list = new CopyOnWriteArrayList<>();
		list.add(10); list.add(20); list.add(30);
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
		    int i = it.next();
		    System.out.print(i + " ");
		    // No exception thrown
		    list.set(list.size() -1, i * 10);
		    // it.remove(); throws an exception
		}
		System.out.println(list);
	}
	
	private void compareCopyOnWriteAndCouncurrencySkipe() {
		System.out.println("### compareCopyOnWriteAndCouncurrencySkipe");
		List<Integer> l1 = Arrays.asList(1, 2, 3);
		List<Integer> l2 = new CopyOnWriteArrayList<>(l1);
		Set<Integer> s3 = new ConcurrentSkipListSet<>();
		s3.addAll(l1);

		// The CopyOnWriteArrrayList class is designed to preserve the original list on
		// iteration, so the first loop will be executed exactly three times
		for (Integer item : l2) {
			l2.add(4); // x1
		}

		System.out.println("l1[after loop1]: " + l1);
		System.out.println("l2[after loop1]: " + l2);
		System.out.println("s3[after loop1]: " + s3);

		System.out.println();

		// The ConcurrentSkipListSet class allows modifications while iterating, so it
		// is possible that the second loop could generate an infinite loop. In this
		// case,
		// though, the second loop executes exactly four times, since elements in a set
		// are
		// unique and 5 can be added only once.
		for (Integer item : s3) {
			s3.add(5); // x2
		}

		System.out.println("l1[after loop2]: " + l1);
		System.out.println("l2[after loop2]: " + l2);
		System.out.println("s3[after loop2]: " + s3);

		System.out.println();
		
		/* 
		 * Finally, despite using the elements of l1 to populate the collections, l2 and
		 *
		 * s3 are not backed by the original list, so the size of l1 is 3. Likewise, the
		 * size of l2 is 6 and the size of s3 is 4
		 * 
		 * It outputs 3 6 4.
		 */

		System.out.println(l1.size() + " " + l2.size() + " " + s3.size());
	}
	
	// --- Test 19
	
	private void cyclicBarrier() {
		System.out.println("### cyclicBarrier ...");
		
		String[] steps = { "Read the recipe", "Gather the ingredients", "Wash hands" };
		System.out.println("Preparing everything:");

		Runnable allSet = () -> System.out.println("Everything's ready. Let's begin.");

		ExecutorService executor = Executors.newFixedThreadPool(steps.length);
		CyclicBarrier barrier = new CyclicBarrier(steps.length, allSet);

		for (String step : steps) {
			executor.submit(() -> checkStep(barrier, step));
		}

		executor.shutdown();
	}
	
	private void checkStep(CyclicBarrier barrier, String step) {
		// Do something to prepare the step
		System.out.println(step + " is ready");
		try {
			// Wait the other threads
			barrier.await();
		} catch (Exception e) {
			/** ... */
		}
	}
	
	// --- Test 20
	
	private void invokAll() {
		System.out.println("### invokAll ...");
		Double[] weights = new Double[10];
		ForkJoinTask<?> task = new WeighAnimalAction(weights, 0, weights.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		// Print results
		System.out.println();
		System.out.print("Weights: ");
		Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));
	}
	
	private void fork() {
		System.out.println("### fork ...");
		Double[] weights = new Double[10];
		ForkJoinTask<Double> task = new WeighAnimalTask(weights,0,weights.length);
		ForkJoinPool pool = new ForkJoinPool();
		Double sum = pool.invoke(task);
		System.out.println("Sum: "+sum);
	}
	
	

	
}
