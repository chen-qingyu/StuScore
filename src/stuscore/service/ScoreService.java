package stuscore.service;

import stuscore.entity.Score;

/**  
 *	学生成绩业务层数据处理接口
 */
public interface ScoreService extends Service<Score> {
	
	/**
	 * 编辑学生成绩
	 * @param sIds 学生编号集合
	 * @param scores 学生成绩集合
	 * @param eId 考试编号
	 */
	public void editScore(String[] sIds, String[] scores, int eId);
	
	/**
	 * 获取指定的学生成绩
	 * @param sId 学生编号
	 * @param eId 考试编号
	 * @return
	 */
	public Score getScore(int sId, int eId);
}
