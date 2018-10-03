package server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import server.DirListener;


public class BasicServer {

	public static void main(String[] args) {
		/*
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(1337), 0);
			HttpContext context = server.createContext("/");
			context.setHandler(BasicServer::handle);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		if(args.length > 0) {
			File file = new File(args[0]);
			
			try {
				DirListener.watchForChanges(file);
				
			}
			catch(IOException e) {
			e.printStackTrace();
			}
			
		}
		else {
			System.out.println("pass a directory as argument");
		}
		System.out.println("will I ever be printed?");
	}
	
	public static void handle(HttpExchange exchange) throws IOException {
		String response = "I work";
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}
}
