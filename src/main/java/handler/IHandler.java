package handler;

import bean.Request;
import bean.Response;

public interface IHandler {
    public void handle(Request rq, Response rp);
}
