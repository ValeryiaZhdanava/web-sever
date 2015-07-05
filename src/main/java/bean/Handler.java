package bean;

import handler.IHandler;

public class Handler {

    private String method;
    private String uri;
    private IHandler iHandle;

    public Handler(String method, String uri, IHandler iHandle) {
	super();
	this.method = method;
	this.uri = uri;
	this.iHandle = iHandle;
    }

    public String getMethod() {
	return method;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    public String getUri() {
	return uri;
    }

    public void setUri(String uri) {
	this.uri = uri;
    }

    public IHandler getiHandle() {
	return iHandle;
    }

    public void setiHandle(IHandler iHandle) {
	this.iHandle = iHandle;
    }

}
