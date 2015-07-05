package bean;

public class Request {
    private String method = "";
    private String requestURI = "";
    private String versionProtocol = "";
    private String protocol = "";
    private String body = "";
    private String contentType;
    private int contentLength;


    public String getMethod() {
	return method;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    public String getRequestURI() {
	return requestURI;
    }

    public void setRequestURI(String requestURI) {
	this.requestURI = requestURI;
    }

    public String getVersionProtocol() {
	return versionProtocol;
    }

    public void setVersionProtocol(String versionProtocol) {
	this.versionProtocol = versionProtocol;
    }

    public String getProtocol() {
	return protocol;
    }

    public void setProtocol(String protocol) {
	this.protocol = protocol;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public int getContentLength() {
	return contentLength;
    }

    public void setContentLength(int contentLength) {
	this.contentLength = contentLength;
    }
}
