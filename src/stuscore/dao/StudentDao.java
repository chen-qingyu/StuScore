package stuscore.dao;

import java.util.List;

import stuscore.comm.Page;
import stuscore.entity.Student;

/** 
 *	学生信息数据处理接口
 */
public interface StudentDao extends Dao<Student> {
	
	/**
	 * 分页查询学生信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryStudents(int pageIndex, int pageSize);
	
	/**
	 * 分页查询学生信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 学生姓名
	 * @return
	 */
	public Page qryStudents(int pageIndex, int pageSize, String name); 
	
	/**
	 * 查询指定年级的所有学生信息
	 * @param gId 年级编号
	 * @return
	 */
	public List<Student> qryStudentsByGId(int gId);
	
	/**
	 * 查询指定年级的所有学生信息
	 * @param gId 年级编号
	 * @param name 学生姓名
	 * @return
	 */
	public List<Student> qryStudentsByGIdAndName(int gId, String name);
}
