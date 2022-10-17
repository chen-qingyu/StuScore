package stuscore.service.impl;

import java.util.List;

import stuscore.comm.Page;
import stuscore.dao.ETypeDao;
import stuscore.dao.impl.ETypeDaoImpl;
import stuscore.entity.EType;
import stuscore.service.ETypeService;

/**   
 *	考试类型信息业务层实现类
 */
public class ETypeServiceImpl implements ETypeService {

	private ETypeDao eTypeDao = new ETypeDaoImpl();
	
	@Override
	public void add(EType eType) {
		
		eTypeDao.add(eType);
	}

	@Override
	public void update(EType eType) {
		
		eTypeDao.update(eType);
	}

	@Override
	public void delete(EType eType) {
		
		eTypeDao.delete(eType);
	}

	@Override
	public EType getOne(int id) {
		
		EType eType = eTypeDao.qryOne(id);
		
		return eType;
	}

	@Override
	public boolean isExitTypeByName(String name) {
		
		EType eType = getETypeByName(name);
		
		return eType == null ? false : true;
	}

	@Override
	public EType getETypeByName(String name) {
		
		EType eType = eTypeDao.qryETypeByName(name);
		
		return eType;
	}

	@Override
	public Page getPageETypes(int pageIndex, int pageSize) {
		
		Page page = eTypeDao.qryETypes(pageIndex, pageSize);
		
		return page;
	}

	@Override
	public Page getPageETypes(int pageIndex, int pageSize, String name) {
		
		Page page = eTypeDao.qryETypes(pageIndex, pageSize, name);
		
		return page;
	}

	@Override
	public List<EType> getETypes() {
		
		List<EType> eTypes = eTypeDao.qryETypes();
		
		return eTypes.size() > 0 ? eTypes : null;
	}

}
