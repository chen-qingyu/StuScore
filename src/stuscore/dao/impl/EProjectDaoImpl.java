package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.EProjectDao;
import stuscore.entity.EProject;

/**
 * 考试科目数据处理接口实现类
 */
public class EProjectDaoImpl extends BaseDao implements EProjectDao
{

    @Override
    public void add(EProject eProject)
    {

        String sql = "INSERT INTO eproject (name, comm) VALUES (?, ?)";

        add(sql, eProject.getName(), eProject.getComm());
    }

    @Override
    public void update(EProject eProject)
    {

        String sql = "UPDATE eproject SET name = ?, comm = ? WHERE id = ?";

        update(sql, eProject.getName(), eProject.getComm(), eProject.getId());
    }

    @Override
    public void delete(EProject eProject)
    {

        String sql = "DELETE FROM eproject WHERE id = ?";

        delete(sql, eProject.getId());
    }

    @Override
    public EProject qryOne(int id)
    {

        String sql = "SELECT id, name, comm FROM eproject WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        EProject eProject = null;

        try
        {
            while (rs.next())
            {

                eProject = new EProject();
                eProject.setId(rs.getInt("id"));
                eProject.setName(rs.getString("name"));
                eProject.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {

        }

        return eProject;
    }

    @Override
    public EProject qryEProjectByName(String name)
    {

        String sql = "SELECT id, name, comm FROM eproject WHERE name = ?";

        ResultSet rs = getResl(sql, name);

        EProject eProject = null;

        try
        {
            while (rs.next())
            {

                eProject = new EProject();
                eProject.setId(rs.getInt("id"));
                eProject.setName(rs.getString("name"));
                eProject.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return eProject;
    }

    @Override
    public Page qryEProjects(int pageIndex, int pageSize)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM eproject LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("comm", rs.getString("comm"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM eproject";

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
    public Page qryEProjects(int pageIndex, int pageSize, String name)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM eproject WHERE name LIKE CONCAT('%', ?, '%') LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, name, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("comm", rs.getString("comm"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM eproject WHERE name LIKE CONCAT('%', ?, '%')";

        int count = qryCount(countSql, name);
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);

        page.setCount(count);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setData(resl);

        return page;
    }

    @Override
    public List<EProject> qryEProjects()
    {

        String sql = "SELECT id, name, comm FROM eproject";

        ResultSet rs = getResl(sql);

        List<EProject> eProjects = new ArrayList<EProject>();

        try
        {
            while (rs.next())
            {

                EProject eProject = new EProject();

                eProject.setId(rs.getInt("id"));
                eProject.setName(rs.getString("name"));
                eProject.setComm(rs.getString("comm"));

                eProjects.add(eProject);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return eProjects;
    }

}
