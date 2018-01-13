package rss.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import rss.bean.RssObject;
import rss.exception.CustomeException;
import rss.util.CommUtils;

@Repository(value="rssObjectDao")
public class RssObjectDaoImpl extends AbstractDao implements IObjectDao<RssObject> {

	@Override
	public void insert(RssObject object) {
		if(object==null){
			throw new CustomeException("请传入一个非空的实体");
		}
		this.getSession().saveOrUpdate(object);
//		StringBuffer sb = new StringBuffer("insert into RssObject (title,link) values (?,?)");
//		Query query = this.getSession().createQuery(sb.toString());
//		query.setString(1, object.getTitle());
//		query.setString(2, object.getLink());
//		System.out.println(query.getQueryString());
//		query.executeUpdate();
	}

	@Override
	public void delete(RssObject object) {
		if(object==null){
			throw new CustomeException("请传入一个非空的实体");
		}
		StringBuffer sb = new StringBuffer("delete from RssObject where 1=1");
		if(object.getId()!=null&&object.getId()>0){
			sb.append(" and id=:id");
		}
		Query query = this.getSession().createSQLQuery(sb.toString());
		if(object.getId()!=null&&object.getId()>0){
			query.setParameter("id", object.getId());
		}
		query.executeUpdate();
	}

	@Override
	public void update(RssObject newObj) {
		this.getSession().update(newObj);
	}

	@Override
	public RssObject getOne(RssObject object) {
//		if(object==null){
//			throw new CustomeException("请传入一个非空的实体");
//		}
//		StringBuffer sb = new StringBuffer("select id,title,link from RssObject where 1=1");
//		if(object.getId()!=null&&object.getId()>0){
//			sb.append(" and id=:id");
//		}
//		if(CommUtils.hasLength(object.getTitle())){
//			sb.append(" and title like :title");
//		}
//		if(CommUtils.hasLength(object.getLink())){
//			sb.append(" and link like :link");
//		}
//		Query query = this.getSession().createQuery(sb.toString());
//		if(object.getId()!=null&&object.getId()>0){
//			query.setParameter("id", object.getId());
//		}
//		if(CommUtils.hasLength(object.getTitle())){
//			query.setParameter("title", object.getTitle());
//		}
//		if(CommUtils.hasLength(object.getLink())){
//			query.setParameter("link", object.getLink());
//		}
//		List<RssObject> list = query.list();
//		if(list.size()>0){
//			return list.get(0);
//		}
		return this.getSession().get(RssObject.class, object.getId());
		
	}

	@Override
	public List<RssObject> getAll() {
		List<RssObject> list = this.getSession().createQuery("from " + RssObject.class.getName()).list();
		return list;
		
	}

}
