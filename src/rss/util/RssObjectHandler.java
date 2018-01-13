package rss.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import rss.bean.RssObject;
import rss.exception.HttpClientException;
import rss.httpclient.HttpClientResponseHandler;
  
public class RssObjectHandler extends DefaultHandler implements HttpClientResponseHandler{  
    /** 
     * 保存正在读取的标签名称 
     */  
    private final static Stack<String> STACK = new Stack<String>();  
      
    /** 
     * 保存HunteronObservation对象集合 
     */  
    private final List<RssObject> RSS_OBJECTS = new ArrayList<RssObject>();  
      
    private String title;  
    private StringBuilder stringBuilderLink;  
    private final static String ITEM = "item";  
    private final static String TITLE = "title";  
    private final static String LINK = "link";  
    public RssObjectHandler(){
    	RSS_OBJECTS.clear();
    }
    //是否开始读取  
    private boolean start = false;  
      
    /** 
     * 处理HttpClient返回数据，由HttpClientRequest请求发送后调用，将HttpMethod传入获取ResponseBody 
     * @throws HttpClientException  
     */  
    public void handle(String response) throws HttpClientException {  
        try {  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            SAXParser parser = factory.newSAXParser();  
            //SAX处理xml文本内容。将当前handler传入，由SAXParser回调  
            parser.parse(new ByteArrayInputStream(response.getBytes()), this); 
        } catch (Exception e) {  
            throw new HttpClientException(e.getMessage());  
        }  
    }  
      
    public List<RssObject> getRssObejcts() {  
        return RSS_OBJECTS;
    }
      
    /** 
     * 开始读取元素 
     */  
    public void startElement(String uri, String localName, String qName,    
            Attributes attributes) throws SAXException    
    {    
        //遇到item标签，开始保存有效属性  
        if (ITEM.equals(qName)) {  
            start = true;  
        }  
          
        //保存当前读取的标签到栈顶  
        if (start) {  
            if (ITEM.equals(qName) || TITLE.equals(qName) ||  
                    LINK.equals(qName))   
                STACK.push(qName);  
        }  
    }    
      
    /** 
     * 元素标签读取结束 
     */  
    public void endElement(String uri, String localName, String qName)    
            throws SAXException    
    {    
        //弹出栈顶标签  
        if (!STACK.isEmpty() && (ITEM.equals(qName) || TITLE.equals(qName) ||  
                LINK.equals(qName)))  
            STACK.pop();  
          
        //如果一个item结束，保存一个HunteronObservation  
        if (ITEM.equals(qName)) {  
            RssObject rssObject = new RssObject();  
            rssObject.setTitle(title);  
            rssObject.setLink(stringBuilderLink.toString());  
            stringBuilderLink = null;  
            RSS_OBJECTS.add(rssObject);  
        }  
    }   
      
    /** 
     * 保存当前读取元素内的内容，注意，在读取某个标签大文本内容时，该方法不会一次性将大文本内容 
     * 全部传入，只会传入一定长度的char数组，然后多次调用该方法。 
     * 所以，对于大文本内容，需要每次调用改方法的时候先临时保存起来，将多次调用返回的char数组拼接起来 
     * 才是整个大文本内容的全部。例如将content保存在stringBuilder中。 
     * 在读取到结束标签后将stringBuilder中的内容取出并清空。 
     */  
    public void characters(char[] ch, int start, int length) throws SAXException {  
        if (!STACK.isEmpty()) {  
            String qName = STACK.peek();
            //title  
            if (TITLE.equals(qName))  
                this.title = new String(ch, start, length);  
              
            if (LINK.equals(qName)){
            	 String linkContent = new String(ch, start,length);  
                 String txtContent = Jsoup.parse(linkContent).text();  
                   
                 if (stringBuilderLink == null)  
                	 stringBuilderLink = new StringBuilder();  
                 stringBuilderLink.append(txtContent); 
            }
        }  
    }  
}