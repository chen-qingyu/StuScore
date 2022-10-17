package stuscore.dao;

import stuscore.comm.Page;
import stuscore.entity.Exam;

/**   
 *	考试信息数据处理接口
 */
public interface ExamDao extends Dao<Exam> {
	
	/**
	 * 分页查询考试信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryExams(int pageIndex, int pageSize);
	
	/**
	 * 分页查询考试信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 考试名称
	 * @return
	 */
	public Page qryExams(int pageIndex, int pageSize, String name);
}
