package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.ExamDao;
import stuscore.entity.Exam;

/**
 * 考试信息数据处理实现类
 */
public class ExamDaoImpl extends BaseDao implements ExamDao
{
    @Override
    public void add(Exam exam)
    {
        String sql = "INSERT INTO exam (name, etime, intro, epid, etid, gid) " + "VALUES (?, ?, ?, ?, ?, ?)";

        add(sql, exam.getName(), exam.geteTime(), exam.getIntro(), exam.getEpId(), exam.getEtId(), exam.getgId());
    }

    @Override
    public void update(Exam exam)
    {
        String sql = "UPDATE exam SET name = ?, etime = ?, intro = ?, " + "epid = ?, etid = ?, gid = ? WHERE id = ?";

        update(sql, exam.getName(), exam.geteTime(), exam.getIntro(), exam.getEpId(), exam.getEtId(), exam.getgId(),
                exam.getId());
    }

    @Override
    public void delete(Exam exam)
    {
        // DEBUG 先删除考试 eId 对应的成绩记录，不然无法删除考试表
        String sqlpre = "DELETE FROM score WHERE eid = ?";

        delete(sqlpre, exam.getId());

        String sql = "DELETE FROM exam WHERE id = ?";

        delete(sql, exam.getId());
    }

    @Override
    public Exam qryOne(int id)
    {
        String sql = "SELECT id, name, etime, intro, epid, etid, gid " + "FROM exam WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        Exam exam = null;

        try
        {
            while (rs.next())
            {
                exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setName(rs.getString("name"));
                exam.seteTime(rs.getString("etime"));
                exam.setIntro(rs.getString("intro"));
                exam.setEpId(rs.getInt("epid"));
                exam.setEtId(rs.getInt("etid"));
                exam.setgId(rs.getInt("gid"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return exam;
    }

    @Override
    public Page qryExams(int pageIndex, int pageSize)
    {
        Page page = new Page();

        String qrySql = "SELECT id, name, etime, intro, epid, etid, gid FROM exam LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("etime", rs.getString("etime"));
                map.put("intro", rs.getString("intro"));
                map.put("epid", rs.getInt("epid"));
                map.put("etid", rs.getInt("etid"));
                map.put("gid", rs.getInt("gid"));

                resl.add(map);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

        String countSql = "SELECT count(*) FROM exam";

        int count = qryCount(countSql);
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);

        page.setCount(count);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setData(resl);

        return page;
    }

    @Override
    public Page qryExams(int pageIndex, int pageSize, String name)
    {
        Page page = new Page();

        // DEBUG MySQL 5.0似乎无法识别CONCAT ()这种，需要去掉空格，且加了单引号无法识别?，所以直接字符串链接。
        String qrySql = "SELECT id, name, etime, intro, epid, etid, gid " + "FROM exam WHERE name LIKE CONCAT('%', '"
                + name + "', '%') LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("etime", rs.getString("etime"));
                map.put("intro", rs.getString("intro"));
                map.put("epid", rs.getInt("epid"));
                map.put("etid", rs.getInt("etid"));
                map.put("gid", rs.getInt("gid"));

                resl.add(map);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM exam WHERE name LIKE CONCAT('%', ?, '%')";

        int count = qryCount(countSql, name);
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);

        page.setCount(count);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setData(resl);

        return page;
    }
}
