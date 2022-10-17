package stuscore.service;

import java.util.List;

import stuscore.comm.Page;
import stuscore.entity.Grade;

/** 
 *	年级信息业务层处理接口
 */
public interface GradeService extends Service<Grade> {
	
	/**
	 * 年级是否存在
	 * @param name 年级名称
	 * @return
	 */
	public boolean isExitGrade(String name);
	
	/**
	 * 依据名称查询年级信息
	 * @param name 年级名称
	 * @return
	 */
	public Grade getGradeByName(String name);
	
	/**
	 * 分页查询年级信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page getPageGrades(int pageIndex, int pageSize);
	
	/**
	 * 分页查询年级信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 年级名称
	 * @return
	 */
	public Page getPageGrades(int pageIndex, int pageSize, String name);
	
	/**
	 * 获取所有年级信息
	 * @return
	 */
	public List<Grade> getGrades();
}
