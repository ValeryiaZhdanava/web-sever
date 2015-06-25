package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebService extends Thread {

    private Socket socket;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;

    public WebService(Socket socket) throws IOException {
	this.socket = socket;

    }

    public void run() {

	try {

	    inFromClient = new BufferedReader(new InputStreamReader(
		    socket.getInputStream()));
	    outToClient = new PrintWriter(socket.getOutputStream());

	    StringBuilder sb = new StringBuilder();
	    String requestString;
	    List<String> listArray = new ArrayList<String>();
	    while (true) {
		while ((requestString = inFromClient.readLine()) != null) {
		    listArray.add(requestString);
		    System.out.println(requestString);
		    if (requestString.isEmpty()) {
			break;
		    }

		}

		List<String> headersValue = parseHeader(sb);
		String contentLenght = getLenght(headersValue);
		int valueContentLenght = Integer.parseInt(contentLenght
			.split(":")[1].trim());
		System.out.println(valueContentLenght);
		if (requestString.equals("GET")) {
		    System.out.println("It is post request");
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private List<String> parseHeader(StringBuilder sb) {
	String[] mas = sb.toString().split("\r\n");
	List<String> list = new ArrayList<String>();
	for (int i = 0; i < mas.length; i++) {
	    list.add(mas[i].toString());
	}
	return list;
    }

    // private List<String> parseHeader(StringBuilder sb) {
    // String[] mas = sb.toString().split("\r\n");
    // List<String> list = new ArrayList<String>();
    // for (int i = 0; i < mas.length; i++) {
    // list.add(mas[i].toString());
    // }
    // return list;
    // }

    private String getLenght(List<String> list) {
	String content = "";
	for (String line : list) {
	    if (line.contains("Content-Length")) {
		content = line;
		break;
	    }
	}
	return content;
    }

    public static void main(String args[]) throws Exception {

	ServerSocket server = new ServerSocket(8080, 10,
		InetAddress.getByName("127.0.0.1"));

	while (true) {
	    Socket connected = server.accept();
	    System.err.println("Client accepted");
	    (new WebService(connected)).start();
	}
    }

    public static int getContentLength(BufferedReader rq) throws IOException {
	String stringForRead = "";
	String contLength = "";

	do {
	    if (stringForRead.contains("Content-Length:")) {
		contLength = stringForRead;
		break;
	    }
	} while ((stringForRead = rq.readLine()) != null);

	return Integer.parseInt(getLastSplitValueBy(contLength, ":"));
    }

    public static String getPostBody(BufferedReader rq) throws IOException {

	StringWriter postRequest = new StringWriter();
	char[] buffer = new char[1024];
	int n = 0;
	while ((n = rq.read(buffer)) != -1) {
	    postRequest.write(buffer, 0, n);
	    break;
	}
	return getLastSplitValueBy(postRequest.toString(), "\r\n\r\n");
    }

    public static String getLastSplitValueBy(String sourceString,
	    String patternForSplit) {
	String lastValue = "";
	String[] content = sourceString.split(patternForSplit);

	if (content.length > 1) {
	    lastValue = content[content.length - 1].trim();
	}
	return lastValue;
    }
}
