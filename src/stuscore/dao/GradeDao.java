package stuscore.dao;

import java.util.List;

import stuscore.comm.Page;
import stuscore.entity.Grade;

/**   
 *	年级信息数据管理接口
 */
public interface GradeDao extends Dao<Grade> {
	
	/**
	 * 依据名称查询年级信息
	 * @param name 年级名称
	 * @return
	 */
	public Grade qryGradeByName(String name);
	
	/**
	 * 分页查询年级信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryGrades(int pageIndex, int pageSize);
	
	/**
	 * 分页查询年级信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 年级名称
	 * @return
	 */
	public Page qryGrades(int pageIndex, int pageSize, String name);
	
	/**
	 * 获取所有的年级信息
	 * @return
	 */
	public List<Grade> qryGrades();
}
