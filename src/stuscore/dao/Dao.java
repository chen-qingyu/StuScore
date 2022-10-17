package stuscore.dao;

/**  
 *	数据处理公用接口，其他数据处理接口均需继承这个接口
 */
public interface Dao<T> {
	
	/**
	 * 添加数据
	 * @param t 要添加的实体信息
	 */
	public void add(T t);
	
	/**
	 * 更新数据
	 * @param t 要更新的实体信息
	 */
	public void update(T t);
	
	/**
	 * 删除数据
	 * @param t 要删除的实体信息
	 */
	public void delete(T t);
	
	/**
	 * 依据编号查询实体信息
	 * @param id 实体编号
	 * @return 指定编号的实体信息
	 */
	public T qryOne(int id);
}
