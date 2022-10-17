package stuscore.service;

import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.entity.Student;

/** 
 *	学生信息业务层数据处理接口
 */
public interface StudentService extends Service<Student> {
	
	/**
	 * 分页查询学生信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page getPageStudent(int pageIndex, int pageSize);
	
	/**
	 * 分页查询学生信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 学生姓名
	 * @return
	 */
	public Page getPageStudent(int pageIndex, int pageSize, String name);
	
	/**
	 * 获取带有年级和成绩信息的学生信息列表
	 * @param gId 年级编号
	 * @param eId 考试编号
	 * @return
	 */
	public List<Map<String, Object>> getStudentsWithGradeAndScore(int gId, int eId);
	
	/**
	 * 获取带有年级和成绩信息的学生信息列表
	 * @param gId 年级编号
	 * @param eId 考试编号
	 * @param name 学生姓名
	 * @return
	 */
	public List<Map<String, Object>> getStudentsWithGradeAndScore(int gId, int eId, String name);
}
