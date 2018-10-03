package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.List;

public class DirListener {

	public static void watchForChanges(File file) throws IOException {
		Path path = file.toPath();
		if(file.isDirectory()) {
			System.out.println(path);
			WatchService ws = path.getFileSystem().newWatchService();
			path.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
			WatchKey watch = null;
			while(true) {
			try {
				watch = ws.take();
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
			List<WatchEvent<?>> events = watch.pollEvents();
			watch.reset();
			for(WatchEvent<?> event: events) {
				Kind<Path> kind = (Kind<Path>) event.kind();
				Path context = (Path) event.context();
				if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					System.out.println(extension(context.getFileName().toString()));
					if(extension(context.getFileName().toString()).equals("apk")) {
						System.out.println("file created. file is an apk");
					}
					else {
						System.out.println("file created. illegal filetype");
				}
					
				}
				
				
			}
			
		}
		}
		else {
			System.out.println("argument is not a directory");
		}
	}
	
	private static String extension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
}
