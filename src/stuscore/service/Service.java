package stuscore.service;

/**
 *	业务层处理接口，其他业务层接口需要继承它
 */
public interface Service<T> {
	
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
	public T getOne(int id);
}
