package stuscore.service;

import stuscore.comm.Page;
import stuscore.entity.User;

/**  
 *	用户信息处理业务层代码
 */
public interface UserService extends Service<User> {
	
	/**
	 * 用户名是否已存在
	 * @param userName 用户名
	 * @return
	 */
	public boolean isExitUser(String userName);
	
	/**
	 * 依据用户名查询用户信息
	 * @param userName 用户名
	 * @return
	 */
	public User getUserByName(String userName);
	
	/**
	 * 分页获取用户信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page getPageUser(int pageIndex, int pageSize);
	
	/**
	 * 分页获取用户信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userName 用户名
	 * @return
	 */
	public Page getPageUser(int pageIndex, int pageSize, String userName);
}
