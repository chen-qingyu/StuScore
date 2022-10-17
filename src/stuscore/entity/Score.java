package stuscore.entity;

/**   
 *	学生成绩实体类
 */
public class Score {
	
	private int eid;

	private int sid;

	private double score;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [eid=" + eid + ", sid=" + sid + ", score=" + score + "]";
	}
}
