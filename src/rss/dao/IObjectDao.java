package rss.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 实现增删改查操作的接口
 * @author
 *
 */
public interface IObjectDao<T extends Serializable> {
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
}
