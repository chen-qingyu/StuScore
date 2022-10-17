package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import stuscore.dao.ScoreDao;
import stuscore.entity.Score;

/** 
 *	学生成绩数据处理接口实现类
 */
public class ScoreDaoImpl extends BaseDao implements ScoreDao {

	@Override
	public void add(Score score) {
		
		String sql = "INSERT INTO score (eid, sid, score) "
				+ "VALUES (?, ?, ?)";
		
		add(sql, score.getEid(), score.getSid(), score.getScore());
	}

	@Override
	public void update(Score score) {
		
		String sql = "UPDATE score SET score = ? WHERE eid = ? AND sid = ?";
		
		update(sql, score.getScore(), score.getEid(), score.getSid());
	}

	@Override
	public void delete(Score score) {
		
		String sql = "DELETE FROM score WHERE eid = ? AND sid = ?";
		
		delete(sql, score.getEid(), score.getSid());
	}

	@Override
	public Score qryOne(int id) {
		
		
		
		return null;
	}
	
	@Override
	public Score qryScore(int sId, int eId) {
		
		String sql = "SELECT eid, sid, score "
				+ "FROM score WHERE eid = ? AND sid = ?";
		
		ResultSet rs = getResl(sql, eId, sId);
		
		Score score = null;
		
		try {
			while(rs.next()) {
				
				score = new Score();
				score.setEid(rs.getInt("eid"));
				score.setSid(rs.getInt("sid"));
				score.setScore(rs.getDouble("score"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return score;
	}
}
