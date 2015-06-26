package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import utils.Utils;

public class HttpServer {

    public static void main(String args[]) throws Exception {
	Server webServer = new Server(8080) {
	    public void service(BufferedReader rq, PrintWriter rp)
		    throws IOException {

		List<String> results = Utils.getHeaderValue(rq);
	    }
	};
	webServer.start();
    }

}
