package stuscore.dao;

import stuscore.comm.Page;
import stuscore.entity.User;

/**  
 *	用户信息数据处理接口
 */
public interface UserDao extends Dao<User> {
	
	/**
	 * 依据用户名查询用户信息
	 * @param userName 指定的用户名
	 * @return
	 */
	public User qryUserByName(String userName);
	
	/**
	 * 分页查询用户信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryPageUsers(int pageIndex, int pageSize);
	
	/**
	 * 分页查询用户信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userName 输入的用户名
	 * @return
	 */
	public Page qryPageUsers(int pageIndex, int pageSize, String userName);
}
