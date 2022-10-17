package stuscore.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.EProject;
import stuscore.entity.EType;
import stuscore.entity.Exam;
import stuscore.entity.Grade;
import stuscore.service.impl.EProjectServiceImpl;
import stuscore.service.impl.ETypeServiceImpl;
import stuscore.service.impl.ExamServiceImpl;
import stuscore.service.impl.GradeServiceImpl;
import stuscore.service.impl.StudentServiceImpl;

/**
 * 考试信息请求控制器
 */
@WebServlet("/exam")
public class ExamServlet extends HttpServlet
{
    private ExamServiceImpl examService = new ExamServiceImpl();

    private ETypeServiceImpl eTypeService = new ETypeServiceImpl();

    private EProjectServiceImpl eProjectService = new EProjectServiceImpl();

    private GradeServiceImpl gradeService = new GradeServiceImpl();

    private StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
        else if (action.equals("scoreView"))
        {
            openScoreView(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /**
     * 添加考试信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String intro = request.getParameter("intro");
        String eTime = request.getParameter("eTime");
        int epId = Integer.valueOf(request.getParameter("epId"));
        int etId = Integer.valueOf(request.getParameter("etId"));
        int gId = Integer.valueOf(request.getParameter("gId"));

        Exam exam = new Exam();
        exam.setName(name);
        exam.setIntro(intro);
        exam.seteTime(eTime);
        exam.setEpId(epId);
        exam.setEtId(etId);
        exam.setgId(gId);

        examService.add(exam);

        response.sendRedirect("/stuscore/exam?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 修改考试信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String intro = request.getParameter("intro");
        String eTime = request.getParameter("eTime");
        int epId = Integer.valueOf(request.getParameter("epId"));
        int etId = Integer.valueOf(request.getParameter("etId"));
        int gId = Integer.valueOf(request.getParameter("gId"));
        int id = Integer.valueOf(request.getParameter("id"));

        Exam exam = examService.getOne(id);
        exam.setName(name);
        exam.setIntro(intro);
        exam.seteTime(eTime);
        exam.setEpId(epId);
        exam.setEtId(etId);
        exam.setgId(gId);

        examService.update(exam);

        response.sendRedirect("/stuscore/exam?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 删除考试信息
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.valueOf(request.getParameter("id"));

        Exam exam = examService.getOne(id);

        examService.delete(exam);

        response.sendRedirect("/stuscore/exam?action=page&pageIndex=1&pageSize=10");
    }

    /**
     * 分页查询考试信息
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
            page = examService.getPageExams(pageIndex, pageSize, name);
        }
        else
        {
            page = examService.getPageExams(pageIndex, pageSize);
        }

        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/exam/list.jsp").forward(request, response);
    }

    /**
     * 打开添加考试信息页面
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void openAddView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<EProject> projects = eProjectService.getEProjects();
        List<EType> types = eTypeService.getETypes();
        List<Grade> grades = gradeService.getGrades();

        request.setAttribute("projects", projects);
        request.setAttribute("types", types);
        request.setAttribute("grades", grades);

        request.getRequestDispatcher("/pages/exam/add.jsp").forward(request, response);
    }

    /**
     * 打开修改考试信息页面
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

        Exam exam = examService.getOne(id);
        List<EProject> projects = eProjectService.getEProjects();
        List<EType> types = eTypeService.getETypes();
        List<Grade> grades = gradeService.getGrades();

        request.setAttribute("projects", projects);
        request.setAttribute("types", types);
        request.setAttribute("grades", grades);
        request.setAttribute("exam", exam);

        request.getRequestDispatcher("/pages/exam/upd.jsp").forward(request, response);
    }

    /**
     * 打开成绩编辑页面
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void openScoreView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int gId = Integer.valueOf(request.getParameter("gId"));
        int eId = Integer.valueOf(request.getParameter("eId"));
        String name = request.getParameter("name");

        if (name != null && !name.equals(""))
        {
            Exam exam = examService.getOne(eId);
            List<Map<String, Object>> students = studentService.getStudentsWithGradeAndScore(gId, eId, name);

            request.setAttribute("exam", exam);
            request.setAttribute("students", students);
        }
        else
        {
            Exam exam = examService.getOne(eId);
            List<Map<String, Object>> students = studentService.getStudentsWithGradeAndScore(gId, eId);

            request.setAttribute("exam", exam);
            request.setAttribute("students", students);
        }

        request.getRequestDispatcher("/pages/exam/score.jsp").forward(request, response);

    }
}
