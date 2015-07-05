package bean;

public class Response {

    private String body;
    private String protocol;
    private String version;
    private int statusCode;

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getProtocol() {
	return protocol;
    }

    public void setProtocol(String protocol) {
	this.protocol = protocol;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public int getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
    }

}
