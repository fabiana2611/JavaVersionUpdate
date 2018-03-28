package br.bia.upgrade.objective.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * a synchronized block or method supports only a limited set of functionality.
 * 
 * The Concurrency API includes the Lock framework that is conceptually similar to using
 * the synchronized keyword, but with a lot more features and options available than the
 * standard monitor
 * 
 * The Lock framework ensures that once a thread has called the lock() method, all other
 * threads that call lock() will wait until the thread that acquired the lock calls the unlock()
 * method
 * 
 * While the lock() method allows you to wait for a lock, it suffers from the same problem
 * as the synchronized keyword. A thread could end up waiting a significantly long amount
 * of time, perhaps forever, to obtain a lock. Luckily, the Lock interface includes two methods
 * that allow the thread to test for a lock.
 * 
 * fairness , and it corresponds to a FIFO
 * 
 * Reference: OCP Oracle Certified Professional Java SE 8 Programmer II
 * 
 * @author Fabiana Araujo
 *
 */
public class LockExamples {

	private int sheepCount = 0;
	private Lock lock = new ReentrantLock();

	public static void init() {
		oneLock();
		twoLock();
		twoLockOneUnlock();
		readWriteLock();
	}
	
	private static void oneLock() {
		System.out.println("### Test one lock");
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);

			LockExamples manager = new LockExamples();
			for (int i = 0; i < 10; i++)
				service.submit(() -> manager.incrementAndReport());
		} finally {
			if (service != null)
				service.shutdown();
		}
		
		System.out.println();
	}
	
	private void incrementAndReport() {
		try {
			lock.lock();
			System.out.print(" " + (++sheepCount));
		} finally {
			lock.unlock();
		}
	}
	
	/*
	 * The result is that the unlock() method must be called
	 * the same number of times as the lock() method in order 
	 * to release the lock. Therefore, this code outputs Unavailable, 
	 * since the lock is still maintained by the original thread.
	 * 
	 * if all lock requests are made by the same thread, then the program
	 * would instead output Acquired , as locks are automatically 
	 * granted if the thread already has the lock
	 */
	private static void twoLock() {
		System.out.println("### Test two lock and one unlock");
		int birdCount = 0;
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			lock.lock();
			++birdCount;
		} finally {
			lock.unlock();
		}

		startThread(lock);
		
		System.out.println();
	}
	
	/*
	 * We can address the problem identify in test2 by calling unlock() once for each lock() request
	 */
	private static void twoLockOneUnlock(){
		System.out.println("### Test two lock and two unlock");
		int birdCount = 0;
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			try {
				lock.lock();
				++birdCount;
			} finally {
				lock.unlock();
			}
		} finally {
			lock.unlock();
		}
		
		startThread(lock);
		
		System.out.println();
	}
	
	private static void startThread(Lock lock){
		new Thread(() -> {
			if (lock.tryLock()) {
				try {
					System.out.println("Acquired");
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println("Unavailable");
			}
		}).start();
	}

	private static void readWriteLock() {
		System.out.println("### ReadWriteLock Test ");
		ReadWriteLockExamples.init();
		System.out.println();
	}
	
}
