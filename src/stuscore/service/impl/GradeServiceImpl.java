package stuscore.service.impl;

import java.util.List;

import stuscore.comm.Page;
import stuscore.dao.GradeDao;
import stuscore.dao.impl.GradeDaoImpl;
import stuscore.entity.Grade;
import stuscore.service.GradeService;

/**   
 *	年级信息业务层数据处理接口实现类
 */
public class GradeServiceImpl implements GradeService {

	private GradeDao gradeDao = new GradeDaoImpl();
	
	@Override
	public void add(Grade grade) {
		
		gradeDao.add(grade);
	}

	@Override
	public void update(Grade grade) {
		
		gradeDao.update(grade);
	}

	@Override
	public void delete(Grade grade) {
		
		gradeDao.delete(grade);
	}

	@Override
	public Grade getOne(int id) {
		
		Grade grade = gradeDao.qryOne(id);
		
		return grade;
	}

	@Override
	public boolean isExitGrade(String name) {
		
		Grade grade = getGradeByName(name);
		
		return grade == null ? false : true;
	}

	@Override
	public Grade getGradeByName(String name) {
		
		Grade grade = gradeDao.qryGradeByName(name);
		
		return grade;
	}

	@Override
	public Page getPageGrades(int pageIndex, int pageSize) {
		
		Page page = gradeDao.qryGrades(pageIndex, pageSize);
		
		return page;
	}

	@Override
	public Page getPageGrades(int pageIndex, int pageSize, String name) {
		
		Page page = gradeDao.qryGrades(pageIndex, pageSize, name);
		
		return page;
	}

	@Override
	public List<Grade> getGrades() {
		
		List<Grade> grades = gradeDao.qryGrades();
		
		return grades.size() > 0 ? grades : null;
	}

}
