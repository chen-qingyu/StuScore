package stuscore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.GradeDao;
import stuscore.dao.ScoreDao;
import stuscore.dao.StudentDao;
import stuscore.dao.impl.GradeDaoImpl;
import stuscore.dao.impl.ScoreDaoImpl;
import stuscore.dao.impl.StudentDaoImpl;
import stuscore.entity.Grade;
import stuscore.entity.Score;
import stuscore.entity.Student;
import stuscore.service.StudentService;

/**   
 *	学生信息业务层接口实现类
 */
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao = new StudentDaoImpl();
	
	private GradeDao gradeDao = new GradeDaoImpl();
	
	private ScoreDao scoreDao = new ScoreDaoImpl();
	
	@Override
	public void add(Student student) {
		
		studentDao.add(student);
	}

	@Override
	public void update(Student student) {
		
		studentDao.update(student);
	}

	@Override
	public void delete(Student student) {
		
		studentDao.delete(student);
	}

	@Override
	public Student getOne(int id) {
		
		Student student = studentDao.qryOne(id);
		
		return student;
	}

	@Override
	public Page getPageStudent(int pageIndex, int pageSize) {
		
		Page page = studentDao.qryStudents(pageIndex, pageSize);
		
		for(Map<String, Object> map : page.getData()) {
			
			int gId = (int) map.get("gid");
			
			Grade grade = gradeDao.qryOne(gId);
			
			map.put("gname", grade.getName());
		}
		
		return page;
	}

	@Override
	public Page getPageStudent(int pageIndex, int pageSize, String name) {
		
		Page page = studentDao.qryStudents(pageIndex, pageSize, name);
		
		for(Map<String, Object> map : page.getData()) {
			
			int gId = (int) map.get("gid");
			
			Grade grade = gradeDao.qryOne(gId);
			
			map.put("gname", grade.getName());
		}
		
		return page;
	}

	@Override
	public List<Map<String, Object>> getStudentsWithGradeAndScore(int gId, int eId) {
		
		List<Map<String, Object>> resl = new ArrayList<Map<String,Object>>();
		
		List<Student> students = studentDao.qryStudentsByGId(gId);
		
		for (Student student : students) {
			
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", student.getId());
			temp.put("name", student.getName());
			temp.put("sex", student.getSex());
			temp.put("age", student.getAge());
			temp.put("gid", student.getGid());
			
			Score score = scoreDao.qryScore(student.getId(), eId);
			if(score == null) {
				temp.put("score", null);
			}else {
				temp.put("score", score.getScore());
			}
			
			
			Grade grade = gradeDao.qryOne(gId);
			temp.put("gid", grade.getId());
			temp.put("gname", grade.getName());
			
			resl.add(temp);
		}
		
		return resl;
	}

	@Override
	public List<Map<String, Object>> getStudentsWithGradeAndScore(int gId, int eId, String name) {
		
		List<Map<String, Object>> resl = new ArrayList<Map<String,Object>>();
		
		List<Student> students = studentDao.qryStudentsByGIdAndName(gId, name);
		
		for (Student student : students) {
			
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", student.getId());
			temp.put("name", student.getName());
			temp.put("sex", student.getSex());
			temp.put("age", student.getAge());
			temp.put("gid", student.getGid());
			
			Score score = scoreDao.qryScore(student.getId(), eId);
			if(score == null) {
				temp.put("score", null);
			}else {
				temp.put("score", score.getScore());
			}
			
			
			Grade grade = gradeDao.qryOne(gId);
			temp.put("gid", grade.getId());
			temp.put("gname", grade.getName());
			
			resl.add(temp);
		}
		
		return resl;
	}

}
