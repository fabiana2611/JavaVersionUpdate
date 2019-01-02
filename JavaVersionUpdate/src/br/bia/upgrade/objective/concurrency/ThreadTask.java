package br.bia.upgrade.objective.concurrency;

//discouraged
public class ThreadTask extends Thread{

	public void run() {
        System.out.println("Running subclass thread");
    }
}
