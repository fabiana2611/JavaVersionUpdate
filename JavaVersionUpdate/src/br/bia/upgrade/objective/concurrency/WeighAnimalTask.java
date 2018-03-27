package br.bia.upgrade.objective.concurrency;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

/**
 * OCP Oracle Certified Professional Java SE 8 Programmer II
 * @author fabiana Araujo
 *
 */
public class WeighAnimalTask extends RecursiveTask<Double> {

	private static final long serialVersionUID = 1L;
	private int start;
	private int end;
	private Double[] weights;

	//Since compute() takes no arguments, the constructor of the class is often used to pass
	//instructions to the task.
	public WeighAnimalTask(Double[] weights, int start, int end) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected Double compute() {
		if (end - start <= 3) {
			double sum = 0;
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + i);
				sum += weights[i];
			}
			return sum;
		} else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
			RecursiveTask<Double> otherTask = new WeighAnimalTask(weights, start, middle);
			//retrieve the recursive data
			//The fork() method causes a new task to be submitted to the pool and is similar to the
			// thread executor submit() method.
			// The join() method is called after the fork() method and causes the current thread to
			// wait for the results of a subtask.
			// Unlike fork() , calling compute() within a compute() method causes the task to wait
			// for the results of the subtask.
			// The fork() method should be called before the current thread performs a compute()
			// operation, with join() called to read the results afterward.
			otherTask.fork();
			return new WeighAnimalTask(weights, middle, end).compute() + otherTask.join();
		}

	}
}
