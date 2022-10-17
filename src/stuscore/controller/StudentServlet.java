package stuscore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.Grade;
import stuscore.entity.Student;
import stuscore.service.GradeService;
import stuscore.service.StudentService;
import stuscore.service.impl.GradeServiceImpl;
import stuscore.service.impl.StudentServiceImpl;

/**
 * 学生信息请求控制器
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet
{

    private StudentService studentService = new StudentServiceImpl();

    private GradeService gradeService = new GradeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action.equals("page"))
        {

            pageInfo(request, response);
        }
        else if (action.equals("add"))
        {

            addInfo(request, response);
        }
        else if (action.equals("upd"))
        {

            updInfo(request, response);
        }
        else if (action.equals("del"))
        {

            delInfo(request, response);
        }
        else if (action.equals("addView"))
        {

            openAddView(request, response);
        }
        else if (action.equals("updView"))
        {

            openUpdView(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        doGet(request, response);
    }

    /**
     * 添加学生信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String name = request.getParameter("name");
        int sex = Integer.valueOf(request.getParameter("sex"));
        int age = Integer.valueOf(request.getParameter("age"));
        String comm = request.getParameter("comm");
        int gId = Integer.valueOf(request.getParameter("gid"));

        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setAge(age);
        student.setComm(comm);
        student.setGid(gId);

        studentService.add(student);

        response.sendRedirect("/stuscore/student?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 修改学生信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        int sex = Integer.valueOf(request.getParameter("sex"));
        int age = Integer.valueOf(request.getParameter("age"));
        String comm = request.getParameter("comm");
        int gId = Integer.valueOf(request.getParameter("gid"));

        Student student = studentService.getOne(id);
        student.setName(name);
        student.setSex(sex);
        student.setAge(age);
        student.setComm(comm);
        student.setGid(gId);

        studentService.update(student);

        response.sendRedirect("/stuscore/student?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 删除学生信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int id = Integer.valueOf(request.getParameter("id"));

        Student student = studentService.getOne(id);

        studentService.delete(student);

        response.sendRedirect("/stuscore/student?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 分页查询学生信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String name = request.getParameter("name");

        Page page = null;

        if (name != null && !name.equals(""))
        {

            page = studentService.getPageStudent(pageIndex, pageSize, name);
        }
        else
        {

            page = studentService.getPageStudent(pageIndex, pageSize);
        }

        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/student/list.jsp").forward(request, response);
    }

    /**
     * 打开添加学生信息页面
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void openAddView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        List<Grade> grades = gradeService.getGrades();

        request.setAttribute("grades", grades);

        request.getRequestDispatcher("/pages/student/add.jsp").forward(request, response);
    }

    /**
     * 打开修改学生信息页面
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void openUpdView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        int id = Integer.valueOf(request.getParameter("id"));

        Student student = studentService.getOne(id);

        List<Grade> grades = gradeService.getGrades();

        request.setAttribute("grades", grades);

        request.setAttribute("student", student);

        request.getRequestDispatcher("/pages/student/upd.jsp").forward(request, response);
    }
}
