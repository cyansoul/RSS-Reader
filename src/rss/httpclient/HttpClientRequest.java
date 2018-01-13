package rss.httpclient;

import rss.exception.HttpClientException;

/** 
 * HttpClient 请求web服务器的request 
 * @author boyce 
 * @version 2014-1-24 
 */  
public interface HttpClientRequest {  
    /** 
     * 添加request参数 
     * @param name 参数名 
     * @param value 参数值 
     */  
    public void addParam(String name, Object value);  
      
    /** 
     * 执行request请求 
     * @param httpClientResponseHandler 处理响应数据handler 
     * @throws HttpClientException 
     */  
    public void process(HttpClientResponseHandler httpClientResponseHandler) throws HttpClientException ;  
      
}  