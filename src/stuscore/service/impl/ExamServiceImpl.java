package stuscore.service.impl;

import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.impl.EProjectDaoImpl;
import stuscore.dao.impl.ETypeDaoImpl;
import stuscore.dao.impl.ExamDaoImpl;
import stuscore.dao.impl.GradeDaoImpl;
import stuscore.entity.EProject;
import stuscore.entity.EType;
import stuscore.entity.Exam;
import stuscore.entity.Grade;
import stuscore.service.ExamService;

/**
 * 考试信息业务层实现类
 */
public class ExamServiceImpl implements ExamService
{
    private ExamDaoImpl examDao = new ExamDaoImpl();

    private ETypeDaoImpl eTypeDao = new ETypeDaoImpl();

    private EProjectDaoImpl eProjectDao = new EProjectDaoImpl();

    private GradeDaoImpl gradeDao = new GradeDaoImpl();

    @Override
    public void add(Exam exam)
    {
        examDao.add(exam);
    }

    @Override
    public void update(Exam exam)
    {
        examDao.update(exam);
    }

    @Override
    public void delete(Exam exam)
    {
        examDao.delete(exam);
    }

    @Override
    public Exam getOne(int id)
    {
        Exam exam = examDao.qryOne(id);

        return exam;
    }

    @Override
    public Page getPageExams(int pageIndex, int pageSize)
    {
        Page page = examDao.qryExams(pageIndex, pageSize);

        for (Map<String, Object> map : page.getData())
        {
            int etId = (int) map.get("etid");
            int epId = (int) map.get("epid");
            int gId = (int) map.get("gid");

            EType eType = eTypeDao.qryOne(etId);
            EProject eProject = eProjectDao.qryOne(epId);
            Grade grade = gradeDao.qryOne(gId);

            if (eType != null && eProject != null && grade != null)
            {
                map.put("tname", eType.getName());
                map.put("pname", eProject.getName());
                map.put("gname", grade.getName());
            }

        }

        return page;
    }

    @Override
    public Page getPageExams(int pageIndex, int pageSize, String name)
    {
        Page page = examDao.qryExams(pageIndex, pageSize, name);

        for (Map<String, Object> map : page.getData())
        {
            int etId = (int) map.get("etid");
            int epId = (int) map.get("epid");
            int gId = (int) map.get("gid");

            EType eType = eTypeDao.qryOne(etId);
            EProject eProject = eProjectDao.qryOne(epId);
            Grade grade = gradeDao.qryOne(gId);

            map.put("tname", eType.getName());
            map.put("pname", eProject.getName());
            map.put("gname", grade.getName());
        }

        return page;
    }
}
