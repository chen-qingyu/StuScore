package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.UserDao;
import stuscore.entity.User;

/**
 * @Description: 用户数据处理实现类
 */
public class UserDaoImpl extends BaseDao implements UserDao
{

    @Override
    public void add(User user)
    {

        String sql = "INSERT INTO user (username, password, type, " + "createtime, comm) VALUES (?, ?, ?, ?, ?)";

        add(sql, user.getUserName(), user.getPassWord(), user.getType(), user.getCreateTime(), user.getComm());
    }

    @Override
    public void update(User user)
    {

        String sql = "UPDATE user SET username = ?, password = ?, " + "type = ?, createtime = ?, comm = ? WHERE id = ?";

        update(sql, user.getUserName(), user.getPassWord(), user.getType(), user.getCreateTime(), user.getComm(),
                user.getId());
    }

    @Override
    public void delete(User user)
    {

        String sql = "DELETE FROM user WHERE id = ?";

        delete(sql, user.getId());
    }

    @Override
    public User qryOne(int id)
    {

        String sql = "SELECT id, username, password, type, " + "createtime, comm FROM user WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        User user = null;

        try
        {
            while (rs.next())
            {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setCreateTime(rs.getString("createtime"));
                user.setComm(rs.getString("comm"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User qryUserByName(String userName)
    {

        String sql = "SELECT id, username, password, type, " + "createtime, comm FROM user WHERE username = ?";

        ResultSet rs = getResl(sql, userName);

        User user = null;

        try
        {
            while (rs.next())
            {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setCreateTime(rs.getString("createtime"));
                user.setComm(rs.getString("comm"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

        return user;
    }

    @Override
    public Page qryPageUsers(int pageIndex, int pageSize)
    {

        Page page = new Page();

        String sql = "SELECT id, username, password, type, " + "createtime, comm FROM user LIMIT ?, ?";

        ResultSet rs = getResl(sql, (pageIndex - 1) * pageSize, pageSize);

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("userName", rs.getString("username"));
                map.put("passWord", rs.getString("password"));
                map.put("type", rs.getInt("type"));
                map.put("createTime", rs.getString("createtime"));
                map.put("comm", rs.getString("comm"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM user";

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
    public Page qryPageUsers(int pageIndex, int pageSize, String userName)
    {

        Page page = new Page();

        String sql = "SELECT id, username, password, type, "
                + "createtime, comm FROM user WHERE username LIKE CONCAT('%', ?, '%') LIMIT ?, ? ";

        ResultSet rs = getResl(sql, userName, (pageIndex - 1) * pageSize, pageSize);

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("userName", rs.getString("username"));
                map.put("passWord", rs.getString("password"));
                map.put("type", rs.getInt("type"));
                map.put("createTime", rs.getString("createtime"));
                map.put("comm", rs.getString("comm"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM user WHERE username  LIKE CONCAT('%', ?, '%')";

        int count = qryCount(countSql, userName);
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);

        page.setCount(count);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setData(resl);

        return page;
    }

}
