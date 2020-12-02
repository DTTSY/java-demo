package dengtao.Model.dao;

import java.util.List;
import java.util.Set;

public interface Dao<E> {
	/**
	 * 实现数据增加操作
	 * @param vo 包含增加数据的vo对象
	 * @return 数据保存成功返回true ，失败返回false
	 * @throws Exception SQL执行异常
	 */
	public boolean doCreate(E vo,String sql) throws Exception;
	
	/**
	 * 实现数据修改操作
	 * @param vo 包含要修改数据的信息
	 * @return 数据修改成功返回true ，失败返回false
	 * @throws Exception SQL执行异常
	 */
	public boolean doUpdata(E vo,String sql) throws Exception;
	
	/**
	 * 实现数据批量操作
	 * @param ids 包含要删除数据的id信息，不能有重复信息
	 * @return 数据删除成功返回true ，失败返回false
	 * @throws Exception SQL执行异常
	 */
	public boolean doRemove(Set<Integer> vo_ids, String sql) throws Exception;
	
	
	public E findById(String sql) throws Exception;

	public List<E> findAll(String sql) throws Exception;

}
