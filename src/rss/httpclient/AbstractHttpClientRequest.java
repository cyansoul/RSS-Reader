package rss.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import rss.exception.HttpClientException;

/** 
 * 抽象HttpClient 请求web服务器的request 
 * @author boyce 
 * @version 2014-1-24 
 */  
public abstract class AbstractHttpClientRequest implements HttpClientRequest {  
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AbstractHttpClientRequest.class);  
      
    //请求URL地址  
    protected String url;  
    //请求参数  
    protected Map<String, Object> params;  
      
    /** 
     * Constructor Method With All Params 
     * @param url 请求URL 
     */  
    public AbstractHttpClientRequest(String url) {  
        if (url==null||"".equals(url.trim()))  
            throw new NullPointerException("Cannot constract a HttpClientRequest with empty url.");  
          
        this.url = url;  
        this.params = new HashMap<String, Object>();  
    }  
  
    /** 
     * 添加request参数 
     * @param name 参数名 
     * @param value 参数值 
     */  
    public void addParam(String name, Object value) {  
        this.params.put(name, value);  
    }  
      
    /** 
     * 执行请求 
     * @throws HttpClientException httpClient请求异常 
     */  
    public void process(HttpClientResponseHandler httpClientResponseHandler) throws HttpClientException {  
          
        //获取子类的具体的HttpMethod实现  
        HttpMethod httpMethod = this.getHttpMethod();  
          
        if (httpMethod == null)  
            throw new NullPointerException("Cannot process request because the httpMethod is null.");  
          
        HttpClient httpClient = new HttpClient();  
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");  
          
        try {  
            long start = System.currentTimeMillis();  
            logger.info("Begin to visit {}.", httpMethod.getURI());  
              
            httpClient.executeMethod(httpMethod);  
            logger.info("End to visit and take: {} ms.", (System.currentTimeMillis() - start));  
        } catch (IOException e) { 
        	httpMethod.getPath();
            throw new HttpClientException(httpMethod.getPath(), e.getMessage());  
        }  
          
        //利用HttpClientResponseHandler处理响应结果  
        if (httpClientResponseHandler != null)  
            try {  
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream()));    
                StringBuilder builder = new StringBuilder();    
                String str = null;    
                while((str = reader.readLine()) != null){    
                    builder.append(str);    
                }    
                String response = builder.toString();   
                httpClientResponseHandler.handle(response);  
            } catch (IOException e) {  
                logger.error(e.getMessage(), e);  
            }  
              
        httpMethod.releaseConnection();  
    }  
  
    /** 
     * 子类实现返回具体的HttpMethod对象 
     * @return HttpMethod 
     */  
    public abstract HttpMethod getHttpMethod();  
      
}