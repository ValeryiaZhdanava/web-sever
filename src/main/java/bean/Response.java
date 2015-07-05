package bean;

public class Response {

    private String body;
    private String protocol;
    private String versionProtocol;
    private String description;
    private String contentType;
    private String contentLenght;
    private int statusCode;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentLenght() {
        return contentLenght;
    }

    public void setContentLenght(String contentLenght) {
        this.contentLenght = contentLenght;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

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

    public String getVersionProtocol() {
	return versionProtocol;
    }

    public void setVersionProtocol(String versionProtocol) {
	this.versionProtocol = versionProtocol;
    }

    public int getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
    }

}
