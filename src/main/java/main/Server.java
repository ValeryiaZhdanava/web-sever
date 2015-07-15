package main;

import handler.ErrorHandler;
import handler.IHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import utils.MatcherUtils;
import utils.Utils;
import bean.Handler;
import bean.Request;
import bean.Response;

public abstract class Server {

    private static int port = 8080;

    private final static List<Handler> handlers = new ArrayList<>();

    @SuppressWarnings("resource")
    public static void start() throws Throwable {

	ServerSocket servers = new ServerSocket(port);
	while (true) {
	    Socket socket = servers.accept();
	    new Thread(new SocketProcessor(socket)).start();
	}
	 
    }

    public static void addHandler(String method, String uri, IHandler handler) {
	handlers.add(new Handler(method, uri, handler));

    }

    public static Handler findHandler(Request req) {
	Handler errorHandler = new Handler(null, null, new ErrorHandler());
	for (Handler handler : handlers) {
	    if (req.getMethod().equals(handler.getMethod())
		    && MatcherUtils.match(handler.getUri(),
			    (req.getRequestURI()))) {
	    }
	    return handler;
	}
	return errorHandler;

    }

    private static class SocketProcessor implements Runnable {
	private BufferedReader request;
	private OutputStream response;
	private Socket socket;

	public SocketProcessor(Socket socket) throws Throwable {
	    this.socket = socket;
	}

	public void run() {
	    try {
		request = new BufferedReader(new InputStreamReader(
			socket.getInputStream()));
		response = socket.getOutputStream();
		Request req = new Request();
		req = Utils.getRequest(request);
		System.out.println(req.getBody());
		Response res = new Response();
		Handler handlerForInvoke = findHandler(req);
		handlerForInvoke.getiHandle().handle(req, res);
		Utils.writeResponseMessage(res,response);
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
