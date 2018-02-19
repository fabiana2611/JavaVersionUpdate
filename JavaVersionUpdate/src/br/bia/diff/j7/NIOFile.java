package br.bia.diff.j7;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;

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

	private static String HOME = System.getProperty("user.home");
	private WatchService watchService = null;
	
	public void workingWithPath(String fileName) {

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
