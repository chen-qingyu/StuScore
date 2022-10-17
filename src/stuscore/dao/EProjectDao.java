package stuscore.dao;

import java.util.List;

import stuscore.comm.Page;
import stuscore.entity.EProject;

/**
 *	考试科目信息数据处理接口
 */
public interface EProjectDao extends Dao<EProject> {
	
	/**
	 * 依据名称查询考试科目信息
	 * @param name 考试科目名称
	 * @return
	 */
	public EProject qryEProjectByName(String name);
	
	/**
	 * 分页查询考试科目信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryEProjects(int pageIndex, int pageSize);
	
	/**
	 * 分页查询考试科目信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 考试科目名称
	 * @return
	 */
	public Page qryEProjects(int pageIndex, int pageSize, String name);
	
	/**
	 * 获取全部考试科目信息
	 * @return
	 */
	public List<EProject> qryEProjects();
}
