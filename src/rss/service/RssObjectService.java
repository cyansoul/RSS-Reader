package rss.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rss.bean.RssObject;
import rss.dao.IObjectDao;

@Transactional
@Service(value="rssObjectService")
public class RssObjectService implements IBaseService<RssObject> {
	
	@Resource
	private IObjectDao<RssObject> rssObjectDao;
	@Override
	public void insert(RssObject object) {
		rssObjectDao.insert(object);
	}

	@Override
	public void delete(RssObject object) {
		rssObjectDao.delete(object);
	}

	@Override
	public void update(RssObject newObj) {
		rssObjectDao.update(newObj);
	}

	@Override
	public RssObject getOne(RssObject object) {
		RssObject rssObject = rssObjectDao.getOne(object);
		try {
			rssObject.setLink(URLDecoder.decode(rssObject.getLink(), "UTF-8"));
			rssObject.setTitle(StringEscapeUtils.unescapeEcmaScript(rssObject.getTitle()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RssObject> getAll() {
		List<RssObject> list = rssObjectDao.getAll();
		try {
			for (RssObject rssObject : list) {
				rssObject.setLink(URLDecoder.decode(rssObject.getLink(), "UTF-8"));
				rssObject.setTitle(StringEscapeUtils.unescapeEcmaScript(rssObject.getTitle()));
			}
			return list;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveRssObjects(List<RssObject> objs) {
		for (RssObject rssObject : objs) {
			rssObject.setTitle(StringEscapeUtils.escapeEcmaScript(rssObject.getTitle()));
			try {
				rssObject.setLink(URLEncoder.encode(rssObject.getLink(),"UTF-8"));
				rssObjectDao.insert(rssObject);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
