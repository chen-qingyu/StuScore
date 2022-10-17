package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.ETypeDao;
import stuscore.entity.EType;

/**
 * 考试类型数据处理接口实现类
 */
public class ETypeDaoImpl extends BaseDao implements ETypeDao
{

    @Override
    public void add(EType eType)
    {

        String sql = "INSERT INTO etype (name, comm) VALUES (?, ?)";

        add(sql, eType.getName(), eType.getComm());
    }

    @Override
    public void update(EType eType)
    {

        String sql = "UPDATE etype SET name = ?, comm = ? WHERE id = ?";

        update(sql, eType.getName(), eType.getComm(), eType.getId());
    }

    @Override
    public void delete(EType eType)
    {

        String sql = "DELETE FROM etype WHERE id = ?";

        delete(sql, eType.getId());
    }

    @Override
    public EType qryOne(int id)
    {

        String sql = "SELECT id, name, comm FROM etype WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        EType eType = null;

        try
        {
            while (rs.next())
            {

                eType = new EType();
                eType.setId(rs.getInt("id"));
                eType.setName(rs.getString("name"));
                eType.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return eType;
    }

    @Override
    public EType qryETypeByName(String name)
    {

        String sql = "SELECT id, name, comm FROM etype WHERE name = ?";

        ResultSet rs = getResl(sql, name);

        EType eType = null;

        try
        {
            while (rs.next())
            {

                eType = new EType();
                eType.setId(rs.getInt("id"));
                eType.setName(rs.getString("name"));
                eType.setComm(rs.getString("comm"));
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return eType;
    }

    @Override
    public Page qryETypes(int pageIndex, int pageSize)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM etype LIMIT ?, ?";

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

        String countSql = "SELECT count(*) FROM etype";

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
    public Page qryETypes(int pageIndex, int pageSize, String name)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, comm FROM etype WHERE name LIKE CONCAT('%', ?, '%') LIMIT ?, ?";

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

        String countSql = "SELECT count(*) FROM etype WHERE name  LIKE CONCAT('%', ?, '%')";

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
    public List<EType> qryETypes()
    {

        String sql = "SELECT id, name, comm FROM etype";

        ResultSet rs = getResl(sql);

        List<EType> eTypes = new ArrayList<EType>();

        try
        {
            while (rs.next())
            {

                EType eType = new EType();

                eType.setId(rs.getInt("id"));
                eType.setName(rs.getString("name"));
                eType.setComm(rs.getString("comm"));

                eTypes.add(eType);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return eTypes;
    }

}
