package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.GradeDao;
import stuscore.entity.Grade;

/**
 * 年级信息数据处理层接口实现类
 */
public class GradeDaoImpl extends BaseDao implements GradeDao
{

    @Override
    public void add(Grade grade)
    {

        String sql = "INSERT INTO grade (name, comm) VALUES (?, ?)";

        add(sql, grade.getName(), grade.getComm());
    }

    @Override
    public void update(Grade grade)
    {

        String sql = "UPDATE grade SET name = ?, comm = ? WHERE id = ?";

        update(sql, grade.getName(), grade.getComm(), grade.getId());
    }

    @Override
    public void delete(Grade grade)
    {

        String sql = "DELETE FROM grade WHERE id = ?";

        delete(sql, grade.getId());
    }

    @Override
    public Grade qryOne(int id)
    {

        String sql = "SELECT id, name, comm FROM grade WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        Grade grade = null;

        try
        {
            while (rs.next())
            {

                grade = new Grade();
                grade.setId(rs.getInt("id"));
                grade.setName(rs.getString("name"));
                grade.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

        return grade;
    }

    @Override
    public Grade qryGradeByName(String name)
    {

        String sql = "SELECT id, name, comm FROM grade WHERE name = ?";

        ResultSet rs = getResl(sql, name);

        Grade grade = null;

        try
        {
            while (rs.next())
            {

                grade = new Grade();
                grade.setId(rs.getInt("id"));
                grade.setName(rs.getString("name"));
                grade.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return grade;
    }

    @Override
    public Page qryGrades(int pageIndex, int pageSize)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM grade LIMIT ?, ?";

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

        String countSql = "SELECT count(*) FROM grade";

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
    public Page qryGrades(int pageIndex, int pageSize, String name)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM grade WHERE name LIKE CONCAT('%', '" + name + "', '%') LIMIT ?, ?";

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

        String countSql = "SELECT count(*) FROM grade WHERE name LIKE CONCAT('%', ?, '%')";

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
    public List<Grade> qryGrades()
    {

        String sql = "SELECT id, name, comm FROM grade";

        ResultSet rs = getResl(sql);

        List<Grade> grades = new ArrayList<Grade>();

        try
        {
            while (rs.next())
            {

                Grade grade = new Grade();

                grade.setId(rs.getInt("id"));
                grade.setName(rs.getString("name"));
                grade.setComm(rs.getString("comm"));

                grades.add(grade);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return grades;
    }

}
