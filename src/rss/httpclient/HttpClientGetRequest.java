package rss.httpclient;

import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/** 
 * 通过HttpClient GET请求服务器数据的Request 
 * @author boyce 
 * @version 2014-1-23 
 */  
public class HttpClientGetRequest extends AbstractHttpClientRequest {  
      
    public HttpClientGetRequest(String url) {  
        super(url);  
    }  
      
    public HttpMethod getHttpMethod() {  
        StringBuilder builder = new StringBuilder();  
        for (Entry<String, Object> entry : params.entrySet()) {  
                builder.append(entry.getKey()).append("=")  
                        .append(String.valueOf(entry.getValue()))  
                        .append("&");  
        }  
        String param = null;  
        if (builder.length() != 0)  
            param = builder.deleteCharAt(builder.length()-1).toString();  
          
        String url = null;  
        if (param==null||"".equals(param.trim()))  
            url = this.url;  
        else  
            url = this.url + "?" + param;  
          
        HttpMethod httpMethod = new GetMethod(url);  
        return httpMethod;  
    }  
      
}  