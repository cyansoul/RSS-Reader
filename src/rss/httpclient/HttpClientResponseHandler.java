package rss.httpclient;

import rss.exception.HttpClientException;

public interface HttpClientResponseHandler {
	public void handle(String response) throws HttpClientException ; 
}
