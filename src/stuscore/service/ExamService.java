package stuscore.service;

import stuscore.comm.Page;
import stuscore.entity.Exam;

/**
 *	考试信息业务层处理代码
 */
public interface ExamService extends Service<Exam> {
	
	/**
	 * 分页查询考试信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page getPageExams(int pageIndex, int pageSize);
	
	/**
	 * 分页查询考试信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 考试名称
	 * @return
	 */
	public Page getPageExams(int pageIndex, int pageSize, String name);
}
