package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import bean.Request;

public class Utils {

    private static List<String> headerValue = new ArrayList<String>();

    public static Request getRequest(BufferedReader request) throws IOException {

	Request req = new Request();
	String requestString;

	while (true) {
	    requestString = request.readLine();
	    headerValue.add(requestString);
	    if (requestString == null || requestString.trim().length() == 0) {
		break;
	    }
	}

	req.setContentLength(getLenght(headerValue));
	req.setContentType(getContentType(headerValue));
	System.out.println(req.getContentLength());
	getRequestLine(headerValue, req);

	if (req.getContentLength() > 0) {
	    req.setBody(getPostBody(request, req.getContentLength()));
	}

	return req;
    }

    public static void getRequestLine(List<String> list, Request req) {
	String line = list.get(0);
	if (line.split("/")[1].trim().equalsIgnoreCase("HTTP")) {
	    req.setProtocol(line.split("/")[1].trim());
	    req.setVersionProtocol(line.split("/")[2].trim());
	} else {
	    req.setProtocol(line.split("/")[2].trim());
	    req.setVersionProtocol(line.split("/")[3].trim());
	    req.setRequestURI(line.split(" ")[1]);
	}
	req.setMethod(line.split(" ")[0].trim());
    }

    public static int getLenght(List<String> list) {
	int valueContentLenght = 0;
	for (String line : list) {
	    if (line.contains("Content-Length")) {
		valueContentLenght = Integer
			.parseInt(line.split(":")[1].trim());
		break;
	    }
	}
	return valueContentLenght;
    }

    public static String getContentType(List<String> list) {
	String valueContentType = "";
	for (String line : list) {
	    if (line.contains("Content-Type")) {
		valueContentType = (line.split(":")[1].trim());
		break;
	    }
	}
	return valueContentType;
    }

    public static List<String> parseHeader(List<String> headerMas) {
	List<String> list = new ArrayList<String>();
	for (String line : headerMas) {
	    list.add(line);
	}
	return list;
    }

    private static String getPostBody(BufferedReader rq, int contentLeng)
	    throws IOException {

	StringWriter postRequest = new StringWriter();
	char[] buff = new char[1024];
	int charToWrite = 0;
	while ((charToWrite = rq.read(buff)) != -1) {
	    postRequest.write(buff, 0, charToWrite);
	    if (charToWrite == contentLeng) {
		break;
	    }
	}
	return postRequest.toString();
    }

}
