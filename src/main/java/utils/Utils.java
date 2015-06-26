package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> getHeaderValue(BufferedReader request)
	    throws IOException {

	String requestString;
	List<String> listArray = new ArrayList<String>();
	List<String> headerValue = new ArrayList<String>(); 


	while ((requestString = request.readLine()) != null) {
	    listArray.add(requestString);
	    if (requestString.isEmpty()) {
		break;
	    }
	}
	request.close();
	List<String> headersValue = Utils.parseHeader(listArray);
	String contentLenght = Utils.getLenght(headersValue);
	int valueContentLenght = Integer.parseInt(contentLenght.split(":")[1]
		.trim());
	if (valueContentLenght > 0) {
	    headerValue.add(getPostBody(request, valueContentLenght));
	}

	return headerValue;
    }

    public static String getLenght(List<String> list) {
	String content = "";
	for (String line : list) {
	    if (line.contains("Content-Length")) {
		content = line;
		break;
	    }
	}
	return content;
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
