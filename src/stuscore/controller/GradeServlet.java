package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.Grade;
import stuscore.service.GradeService;
import stuscore.service.impl.GradeServiceImpl;

/**   
 *	年级信息请求控制器
 */
@WebServlet("/grade")
public class GradeServlet extends HttpServlet {
	
	private GradeService gradeService = new GradeServiceImpl();	
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action.equals("info")) {

			descInfo(request, response);
		}else if(action.equals("page")) {

			pageInfo(request, response);
		}else if(action.equals("add")) {

			addInfo(request, response);
		}else if(action.equals("upd")) {

			updInfo(request, response);
		}else if(action.equals("del")) {

			delInfo(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	/**
	 * 添加年级信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String comm =  request.getParameter("comm");
		
		if(isExit(name)) {
			
			request.setAttribute("error", "要添加的信息已存在");
			
			request.getRequestDispatcher("/pages/grade/add.jsp").forward(request, response);
		}else {
			
			Grade grade = new Grade();
			grade.setName(name);
			grade.setComm(comm);
			
			gradeService.add(grade);
			
			response.sendRedirect("/stuscore/grade?action=page&pageIndex=1&pageSize=10");
		}
	}

	/**
	 * 修改年级信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String comm =  request.getParameter("comm");
		
		Grade grade = gradeService.getOne(id);
		
		if(!name.equals(grade.getName()) && isExit(name)) {
			
			request.setAttribute("error", "要修改的信息已存在");
			
			request.getRequestDispatcher("/grade?action=info&id=" + id).forward(request, response);
		}else {
			
			grade.setName(name);
			grade.setComm(comm);
			
			gradeService.update(grade);
			
			response.sendRedirect("/stuscore/grade?action=page&pageIndex=1&pageSize=10");
		}
	}

	/**
	 * 删除年级信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		Grade grade = gradeService.getOne(id);
		
		gradeService.delete(grade);
		
		response.sendRedirect("/stuscore/grade?action=page&pageIndex=1&pageSize=10");
	}

	/**
	 * 获取指定年级信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void descInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		Grade grade = gradeService.getOne(id);
		
		request.setAttribute("grade", grade);
		
		request.getRequestDispatcher("/pages/grade/upd.jsp").forward(request, response);
	}

	/**
	 * 分页查询年级信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		String name = request.getParameter("name");
		
		Page page = null;
		
		if(name != null && !name.equals("")) {
			
			page = gradeService.getPageGrades(pageIndex, pageSize, name);
		}else {

			page = gradeService.getPageGrades(pageIndex, pageSize);
		}
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/grade/list.jsp").forward(request, response);
	}
	
	/**
	 * 检查考试年级信息是否存在
	 * @param name
	 * @return
	 */
	public boolean isExit(String name) {
		
		boolean flag = gradeService.isExitGrade(name);
		
		return flag;
	}
}
