package stuscore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据处理工具类，数据处理的公用类
 */
public abstract class BaseDao
{
    // 数据库用户名
    private final String USERNAME = "root";

    // 数据库密码
    private final String PASSWORD = "123456";

    // 数据库连接地址
    private final String URL = "jdbc:mysql://localhost:3306/db_stuscore?serverTimezone=GMT";

    // 数据库驱动类
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 获取数据库连接
     * 
     * @return 数据库连接对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConn() throws ClassNotFoundException, SQLException
    {
        Class.forName(DRIVER);

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return conn;
    }

    /**
     * 创建数据处理执行器
     * 
     * @param conn 数据库连接
     * @param sql  执行SQL
     * @return 数据处理执行器
     * @throws SQLException
     */
    public PreparedStatement createPstmt(Connection conn, String sql) throws SQLException
    {
        return conn.prepareStatement(sql);
    }

    /**
     * 获取查询结果集合
     * 
     * @param sql    执行SQL
     * @param params 附加参数
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ResultSet getResl(String sql, Object... params)
    {
        ResultSet resl = null;

        try
        {
            Connection conn = getConn();

            PreparedStatement pstmt = createPstmt(conn, sql);

            for (int i = 0; i < params.length; i++)
            {
                pstmt.setObject((i + 1), params[i]);
            }

            resl = pstmt.executeQuery();

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return resl;
    }

    /**
     * 关闭打开的连接
     * 
     * @param conn 数据库连接对象
     * @param stmt 数据库操作执行器
     * @throws SQLException
     */
    public void close(Connection conn, Statement stmt) throws SQLException
    {
        if (stmt != null)
        {
            stmt.close();
        }

        if (conn != null)
        {
            conn.close();
        }
    }

    public void close(Connection conn, PreparedStatement stmt) throws SQLException
    {
        if (stmt != null)
        {
            stmt.close();
        }

        if (conn != null)
        {
            conn.close();
        }
    }

    /**
     * 关闭数据库操作中打开的连接
     * 
     * @param conn 数据库连接
     * @param stmt 数据处理执行器
     * @param resl 数据查询集合
     * @throws SQLException
     */
    public void close(Connection conn, Statement stmt, ResultSet resl) throws SQLException
    {
        if (resl != null)
        {
            resl.close();
        }

        if (stmt != null)
        {
            stmt.close();
        }

        if (conn != null)
        {
            conn.close();
        }
    }

    /**
     * 数据更新
     * 
     * @param sql    执行SQL
     * @param params SQL 执行参数
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void execUpdate(String sql, Object... params)
    {
        Connection conn;
        try
        {
            conn = getConn();

            PreparedStatement pstmt = createPstmt(conn, sql);

            for (int i = 0; i < params.length; i++)
            {
                pstmt.setObject((i + 1), params[i]);
            }

            pstmt.executeUpdate();

            close(conn, pstmt);

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 添加数据
     * 
     * @param sql    执行SQL
     * @param params SQL占位符对应参数
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void add(String sql, Object... params)
    {
        execUpdate(sql, params);
    }

    /**
     * 修改数据
     * 
     * @param sql    执行SQL
     * @param params SQL占位符对应参数
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update(String sql, Object... params)
    {
        execUpdate(sql, params);
    }

    /**
     * 删除数据
     * 
     * @param sql    执行SQL
     * @param params SQL占位符对应参数
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void delete(String sql, Object... params)
    {
        execUpdate(sql, params);
    }

    /**
     * 获取数据总量
     * 
     * @param sql    执行SQL
     * @param params 附加参数
     * @return
     */
    public int qryCount(String sql, Object... params)
    {
        int count = 0;

        ResultSet rs = getResl(sql, params);

        try
        {
            while (rs.next())
            {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

        return count;
    }
}
