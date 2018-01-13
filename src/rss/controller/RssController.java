package rss.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rss.bean.RssObject;
import rss.httpclient.HttpClientGetRequest;
import rss.httpclient.HttpClientRequest;
import rss.service.IBaseService;
import rss.util.RssObjectHandler;

@Controller
@RequestMapping("/parse")
public class RssController {
	@Resource
	private IBaseService<RssObject> rssObjectService;
	/**
	 * 跳转到form title 页面中
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping
	public String index(HttpServletRequest request,HttpSession session){
		return "formTitle";
	}
	@RequestMapping(value="/visitRss",method = RequestMethod.POST)
	@ResponseBody()
	public Map<String, Object> visitRss(HttpServletRequest request,HttpServletResponse response,Model model){
		String rssUrl = request.getParameter("rssUrl");
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			HttpClientRequest req = new HttpClientGetRequest(rssUrl);
			RssObjectHandler handler = new RssObjectHandler();
			req.process(handler);
			List<RssObject> objs = handler.getRssObejcts();
			rssObjectService.saveRssObjects(objs);
			try {
				for (RssObject rssObject : objs) {
					rssObject.setLink(URLDecoder.decode(rssObject.getLink(), "UTF-8"));
					rssObject.setTitle(StringEscapeUtils.unescapeEcmaScript(rssObject.getTitle()));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("result", "success");
			map.put("rssList", objs);
		}catch(Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("rssList", "");
		}
		return map;
	}
}
