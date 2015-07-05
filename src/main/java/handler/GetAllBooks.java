package handler;

import bean.Request;
import bean.Response;

public class GetAllBooks implements IHandler {

    @Override
    public void handle(Request req, Response res) {
	res.setProtocol(req.getProtocol());
	res.setVersionProtocol(req.getVersionProtocol());
	res.setDescription("OK");

    }

}
