package br.bia.upgrade.objective.concurrency;

import java.util.concurrent.BlockingQueue;

/**
 * https://www.journaldev.com/1034/java-blockingqueue-example
 * @author fabiana
 *
 */
public class Producer implements Runnable{

	private BlockingQueue<Message> queue;
    
    public Producer(BlockingQueue<Message> q){
        this.queue=q;
    }
    
	@Override
	public void run() {
		 //produce messages
        for(int i=0; i<10; i++){
            Message msg = new Message(""+i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced "+msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //adding exit message
        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}

}
