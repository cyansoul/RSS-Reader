package rss.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import rss.bean.RssObject;


/**
 * 服务接口
 * @author 
 *
 */
@Transactional
public interface IBaseService<T> {
	/**
	 * 插入数据
	 * @param object
	 */
	public void insert(T object);
	/**
	 * 删除数据
	 * @param object
	 */
	public void delete(T object);
	/**
	 * 更新数据
	 * @param newObj
	 * @param oldObj
	 */
	public void update(T newObj);
	/**
	 * 查询一条数据
	 * @param object
	 * @return
	 */
	public T getOne(T object);
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> getAll();
	/**
	 * 批量插入
	 * @param objs
	 */
	public void saveRssObjects(List<T> objs);
}
