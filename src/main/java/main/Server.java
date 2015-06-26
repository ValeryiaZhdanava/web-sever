package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {

    private int port = 8080;

    public Server(int port) {
	this.port = port;
    }

    @SuppressWarnings("resource")
    public void start() throws IOException {

	ServerSocket servers = new ServerSocket(port);
	while (true) {
	    Socket socket = servers.accept();
	}
    }

    public void stop() {
    }

    public abstract void service(BufferedReader request, PrintWriter response)
	    throws IOException;

    private class SocketProcessor implements Runnable {
	private BufferedReader request;
	private PrintWriter response;
	private Socket socket;

	public SocketProcessor(Socket socket) {
	    this.socket = socket;
	}

	public void run() {
	    try {
		request = new BufferedReader(new InputStreamReader(
			socket.getInputStream()));
		response = new PrintWriter(socket.getOutputStream(), true);
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }

	    try {
		service(request, response);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    try {
		this.socket.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
