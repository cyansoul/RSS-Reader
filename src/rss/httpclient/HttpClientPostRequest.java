package rss.httpclient;

import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/** 
* 通过HttpClient Post请求服务器数据的Request 
* @author boyce 
* @version 2014-1-23 
*/  
public class HttpClientPostRequest extends AbstractHttpClientRequest {
	private String contentType;
	public HttpClientPostRequest(String url){
		super(url);
	}
	public HttpClientPostRequest(String url,String contentType) {
		super(url);  
		this.contentType = contentType;
	}
 
  	public HttpMethod getHttpMethod() {  
  		NameValuePair[] pairs = new NameValuePair[this.params.size()];  
  		NameValuePair pair = null;  
  		int i = 0;  
  		for (Entry<String, Object> entry : params.entrySet()) {  
  			pair = new NameValuePair(entry.getKey(), String.valueOf(entry.getValue()));  
  			pairs[i++] = pair;  
  		}  
        
  		PostMethod httpMethod = new PostMethod(this.url);  
  		httpMethod.setRequestBody(pairs);  
        
  		if (contentType!=null&&!"".equals(contentType.trim()))  
  			httpMethod.setRequestHeader("Content-Type", contentType);  
        
  		return httpMethod;  
	} 
}
