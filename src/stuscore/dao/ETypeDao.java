package stuscore.dao;

import java.util.List;

import stuscore.comm.Page;
import stuscore.entity.EType;

/**
 *	考试类型信息数据处理接口
 */
public interface ETypeDao extends Dao<EType> {
	
	/**
	 * 依据名称查询考试类别信息
	 * @param name 考试类别名称
	 * @return
	 */
	public EType qryETypeByName(String name);
	
	/**
	 * 分页查询考试类别信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Page qryETypes(int pageIndex, int pageSize);
	
	/**
	 * 分页查询考试类别信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param name 年级名称
	 * @return
	 */
	public Page qryETypes(int pageIndex, int pageSize, String name);
	
	/**
	 * 获取全部考试类型信息
	 * @return
	 */
	public List<EType> qryETypes();
}
