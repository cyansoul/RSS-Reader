package rss.exception;

public class HttpClientException extends RuntimeException {

	public HttpClientException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public HttpClientException(String str,String message) {
		super(message);
		System.out.println(str);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
