package br.bia.upgrade.objective.concurrency;

import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * OCP Oracle Certified Professional Java SE 8 Programmer II
 * @author fabiana Araujo
 *
 */
public class WeighAnimalAction extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	
	private int start;
	private int end;
	private Double[] weights;

	public WeighAnimalAction(Double[] weights, int start, int end) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected void compute() {
		if (end - start <= 3)
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + i);
			}
		else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
			//The invokeAll() method takes two instances of the fork/join class and 
			// does not return a result.
			invokeAll(new WeighAnimalAction(weights, start, middle), new WeighAnimalAction(weights, middle, end));
		}
	}
}
