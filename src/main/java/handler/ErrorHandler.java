package handler;

import bean.Request;
import bean.Response;

public class ErrorHandler implements IHandler {

    @Override
    public void handle(Request req, Response res) {
	res.setDescription("ERROR");
	res.setStatusCode(404);
    }

}
