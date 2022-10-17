package stuscore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stuscore.comm.Page;
import stuscore.dao.StudentDao;
import stuscore.entity.Student;

/**
 * @Description: 学生信息数据处理接口实现类
 */
public class StudentDaoImpl extends BaseDao implements StudentDao
{

    @Override
    public void add(Student student)
    {

        String sql = "INSERT INTO student (name, sex, age, comm, gid) " + "VALUES (?, ?, ?, ?, ?)";

        add(sql, student.getName(), student.getSex(), student.getAge(), student.getComm(), student.getGid());
    }

    @Override
    public void update(Student student)
    {

        String sql = "UPDATE student SET name = ?, sex = ?, age = ?, " + "comm = ?, gid = ? WHERE id = ?";

        update(sql, student.getName(), student.getSex(), student.getAge(), student.getComm(), student.getGid(),
                student.getId());
    }

    @Override
    public void delete(Student student)
    {

        String sql = "DELETE FROM student WHERE id = ?";

        delete(sql, student.getId());
    }

    @Override
    public Student qryOne(int id)
    {

        String sql = "SELECT id, name, sex, age, " + "comm, gid FROM student WHERE id = ?";

        ResultSet rs = getResl(sql, id);

        Student student = null;

        try
        {
            while (rs.next())
            {

                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setAge(rs.getInt("age"));
                student.setComm(rs.getString("comm"));
                student.setGid(rs.getInt("gid"));

            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return student;
    }

    @Override
    public Page qryStudents(int pageIndex, int pageSize)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, sex, age, " + "comm, gid FROM student LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("sex", rs.getInt("sex"));
                map.put("age", rs.getInt("age"));
                map.put("comm", rs.getString("comm"));
                map.put("gid", rs.getInt("gid"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        String countSql = "SELECT count(*) FROM student";

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
    public Page qryStudents(int pageIndex, int pageSize, String name)
    {

        Page page = new Page();

        String qrySql = "SELECT id, name, sex, age, " + "comm, gid FROM student WHERE name LIKE CONCAT('%', '" + name
                + "', '%') LIMIT ?, ?";

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        ResultSet rs = getResl(qrySql, (pageIndex - 1) * pageSize, pageSize);

        try
        {
            while (rs.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", rs.getInt("id"));
                map.put("name", rs.getString("name"));
                map.put("sex", rs.getInt("sex"));
                map.put("age", rs.getInt("age"));
                map.put("comm", rs.getString("comm"));
                map.put("gid", rs.getInt("gid"));

                resl.add(map);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

        String countSql = "SELECT count(*) FROM student WHERE name LIKE CONCAT('%', ?, '%')";

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
    public List<Student> qryStudentsByGId(int gId)
    {

        String sql = "SELECT id, name, sex, age, " + "comm, gid FROM student WHERE gid = ?";

        ResultSet rs = getResl(sql, gId);

        List<Student> students = new ArrayList<Student>();

        try
        {
            while (rs.next())
            {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setAge(rs.getInt("age"));
                student.setComm(rs.getString("comm"));
                student.setGid(rs.getInt("gid"));

                students.add(student);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return students;
    }

    @Override
    public List<Student> qryStudentsByGIdAndName(int gId, String name)
    {

        // DEBUG
        String sql = "SELECT id, name, sex, age, " + "comm, gid FROM student WHERE gid = ? AND name LIKE CONCAT('%', '"
                + name + "', '%')";

        ResultSet rs = getResl(sql, gId);

        List<Student> students = new ArrayList<Student>();

        try
        {
            while (rs.next())
            {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setAge(rs.getInt("age"));
                student.setComm(rs.getString("comm"));
                student.setGid(rs.getInt("gid"));

                students.add(student);
            }
        } catch (SQLException e)
        {

            e.printStackTrace();
        }

        return students;
    }

}
