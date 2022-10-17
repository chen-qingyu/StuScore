package stuscore.dao;

import stuscore.entity.Score;

/**  
 *	学生成绩数据处理接口
 */
public interface ScoreDao extends Dao<Score> {
	
	/**
	 * 查询考试成绩信息
	 * @param sId 学生编号
	 * @param eId 考试 编号
	 * @return
	 */
	public Score qryScore(int sId, int eId);
}
