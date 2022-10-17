package stuscore.service.impl;

import stuscore.comm.Page;
import stuscore.dao.UserDao;
import stuscore.dao.impl.UserDaoImpl;
import stuscore.entity.User;
import stuscore.service.UserService;

/** 
 *	用户信息处理业务层实现类
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public void add(User user) {
		
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		
		userDao.delete(user);
	}

	@Override
	public User getOne(int id) {
		
		User user =  userDao.qryOne(id);
		
		return user;
	}

	@Override
	public boolean isExitUser(String userName) {
		
		User user = getUserByName(userName);
		
		return user == null ? false : true;
	}

	@Override
	public User getUserByName(String userName) {
		
		User user = userDao.qryUserByName(userName);
		
		return user;
	}

	@Override
	public Page getPageUser(int pageIndex, int pageSize) {
		
		Page page = userDao.qryPageUsers(pageIndex, pageSize);
		
		return page;
	}

	@Override
	public Page getPageUser(int pageIndex, int pageSize, String userName) {
		
		Page page = userDao.qryPageUsers(pageIndex, pageSize, userName);
		
		return page;
	}

}
