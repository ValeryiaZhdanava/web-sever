package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import utils.Utils;

public abstract class Server {

    private static int port = 8080;

    @SuppressWarnings("resource")
    public static void start() throws Throwable {

	ServerSocket servers = new ServerSocket(port);
	while (true) {
	    Socket socket = servers.accept();
	    System.err.println("Client accepted");
	    new Thread(new SocketProcessor(socket)).start();
	}
    }

    private static class SocketProcessor implements Runnable {
	private BufferedReader request;
	private PrintWriter response;
	private Socket socket;

	public SocketProcessor(Socket socket) throws Throwable {
	    this.socket = socket;
	}

	public void run() {
	    try {
		request = new BufferedReader(new InputStreamReader(
			socket.getInputStream()));
		response = new PrintWriter(socket.getOutputStream(), true);
		System.out.println(Utils.getRequest(request).getBody());
	    } catch (IOException e1) {
		e1.printStackTrace();
	    } finally {
		try {
		    socket.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
