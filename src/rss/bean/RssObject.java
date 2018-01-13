package rss.bean;

import java.io.Serializable;

/** 
 * RSS 部分节点封装 
 *  
 * @author boyce 
 * @version 2013-12-27 
 */ 
//@Entity
//@Table(name="RssObject")
public class RssObject implements Serializable{
//	@Id
//	@GeneratedValue(generator="identity-id")
//	@GenericGenerator(name="identity-id", strategy = "identity")
	private Long id;
//	@Column(length=255)
    private String title; 
//	@Column(length=255)
    private String link;  
  
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public String getLink() {  
        return link;  
    }  
  
    public void setLink(String link) {  
        this.link = link;  
    }  
  
  
    @Override
	public String toString() {
		return "RssObject [title=" + title + ", link=" + link;
	}  
} 