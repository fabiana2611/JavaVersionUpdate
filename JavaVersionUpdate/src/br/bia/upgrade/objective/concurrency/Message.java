package br.bia.upgrade.objective.concurrency;

/**
 * https://www.journaldev.com/1034/java-blockingqueue-example
 * @author fabiana
 *
 */
public class Message {

	 private String msg;
	    
	    public Message(String str){
	        this.msg=str;
	    }

	    public String getMsg() {
	        return msg;
	    }
}
