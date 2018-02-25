package br.bia.diff.j7;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Logger;

/**
 * Based in: 
 * https://www.oreilly.com/learning/java7-features
 * https://baptiste-wicht.com/posts/2010/03/nio-2-path-api-java-7.html
 * http://www.baeldung.com/java-nio-2-file-api
 * 
 * @author Fabiana Araujo
 *
 */
public class NIOFile {

	private Logger logger = Logger.getAnonymousLogger();
	
	private static String HOME = System.getProperty("user.home");
	private WatchService watchService = null;
	
	/**
	 * V < 7: Resources such as Connections, Files, Input/OutStreams, etc. should be 
	 * closed manually by the developer by writing bog-standard code. Usually 
	 * we use a try-finally block to close the respective resources. 
	 * 
	 * V7: Declare the resources in the try as follows
	 */
	public void automaticResourceManagement(String fileName) {
		
		//The try-with-resources Statement
		try (
				FileOutputStream fos = new FileOutputStream(fileName);
				DataOutputStream dos = new DataOutputStream(fos)
			) {

			dos.writeUTF("Java 7 Block Buster");
			
			System.out.println("SUCESS to create file!!!");

		} catch (IOException e) {
			logger.warning("Error to create file");
		}
	}
	
	public void nioFileWorkingWithPath(){
		String fileName = "testeFile.txt";
		automaticResourceManagement(fileName);
		workingWithPath(fileName);
		
		System.out.println();
	}
	
	private void workingWithPath(String fileName) {

		File file = new File(fileName);
		System.out.println("File Path:" + file.getPath());
		Path path = Paths.get(file.getPath());
		System.out.println("Number of Nodes:" + path.getNameCount());

		System.out.println("File Name:" + path.getFileName());
		System.out.println("File Root:" + path.getRoot());
		System.out.println("File Parent:" + path.getParent());

		try {
			Files.deleteIfExists(path);
			if (file.exists()) {
				System.out.println("Error!!!");
			} else {
				System.out.println("Sucess!!!");
			}

		} catch (IOException e) {
			System.out.println("Error to delete:" + path.getFileName());
		}

	}
	
	public void fileChangeNotifications(String fileName) {

		System.out.println("### fileChangeNotifications ###");
		System.out.println("PS> To see results, create, delete or modify the file /testNioFileDir/testeNotification.txt");
		
	    try{
	    	
	    	Path pathDir = Paths.get(HOME + "/testNioFileDir");
		    Files.createDirectories(pathDir);
		    
		    Path path = Paths.get(HOME, "testNioFileDir", fileName);
		    
		    if(!Files.exists(path)){
		    	Files.createFile(path);
		    }

		    initObserver(pathDir);
	    	
	    }catch (IOException e){
	    	System.out.println ("Error: file not created!!!");
	    }
	}

	private void initObserver(Path path){
		this.init(path);
		this.doRounds();
	}
	
	private void init(Path path) {
		
		try {
			watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, ENTRY_CREATE, ENTRY_DELETE,
					ENTRY_MODIFY);
		} catch (IOException e) {
			System.out.println("IOException: "+ e.getMessage());
		}
	}
	
	/**
	 * The police will start making rounds
	 */
	private void doRounds() {
		WatchKey key = null;
		while (true) {
			try {
				key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Kind<?> kind = event.kind();
					System.out.println("Event on " + event.context().toString() + " is " + kind);
				}
			} catch (InterruptedException e) {
				System.out.println("InterruptedException: " + e.getMessage());
			}
			boolean reset = key.reset();
			if (!reset)
				break;
		}
	}
	

}
