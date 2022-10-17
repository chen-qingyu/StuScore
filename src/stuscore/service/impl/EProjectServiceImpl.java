package stuscore.service.impl;

import java.util.List;

import stuscore.comm.Page;
import stuscore.dao.EProjectDao;
import stuscore.dao.impl.EProjectDaoImpl;
import stuscore.entity.EProject;
import stuscore.service.EProjectService;

/** 
 *	考试科目业务层实现类
 */
public class EProjectServiceImpl implements EProjectService {

	private EProjectDao eProjectDao = new EProjectDaoImpl();
	
	@Override
	public void add(EProject eProject) {
		
		eProjectDao.add(eProject);
	}

	@Override
	public void update(EProject eProject) {
		
		eProjectDao.update(eProject);
	}

	@Override
	public void delete(EProject eProject) {
		
		eProjectDao.delete(eProject);
	}

	@Override
	public EProject getOne(int id) {
		
		EProject eProject = eProjectDao.qryOne(id);
		
		return eProject;
	}

	@Override
	public boolean isExitProjectByName(String name) {
		
		EProject eProject = getEProjectByName(name);
		
		return eProject == null ? false : true;
	}

	@Override
	public EProject getEProjectByName(String name) {
		
		EProject eProject = eProjectDao.qryEProjectByName(name);
		
		return eProject;
	}

	@Override
	public Page getPageEProjects(int pageIndex, int pageSize) {
		
		Page page = eProjectDao.qryEProjects(pageIndex, pageSize);
		
		return page;
	}

	@Override
	public Page getPageEProjects(int pageIndex, int pageSize, String name) {
		
		Page page = eProjectDao.qryEProjects(pageIndex, pageSize, name);
		
		return page;
	}

	@Override
	public List<EProject> getEProjects() {
		
		List<EProject> eProjects = eProjectDao.qryEProjects();
		
		return eProjects.size() > 0 ? eProjects : null;
	}

}
