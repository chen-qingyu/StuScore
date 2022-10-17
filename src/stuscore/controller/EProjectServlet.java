package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.EProject;
import stuscore.service.EProjectService;
import stuscore.service.impl.EProjectServiceImpl;

/** 
 *	考试科目请求控制器
 */
@WebServlet("/project")
public class EProjectServlet extends HttpServlet {
	
	private EProjectService eProjectService = new EProjectServiceImpl();
	
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
	 * 添加考试科目信息
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
			
			request.setAttribute("error", "要添加目信息已存在");
			
			request.getRequestDispatcher("/pages/project/add.jsp").forward(request, response);
		}else {
			
			EProject eProject = new EProject();
			eProject.setName(name);
			eProject.setComm(comm);
			
			eProjectService.add(eProject);
			
			response.sendRedirect("/stuscore/project?action=page&pageIndex=1&pageSize=10");
		}
	}
	
	/**
	 * 修改考试科目信息
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
		
		EProject eProject = eProjectService.getOne(id);
		
		if(!name.equals(eProject.getName()) && isExit(name)) {
			
			request.setAttribute("error", "要修改信息已存在");
			
			request.getRequestDispatcher("/project?action=info&id=" + id).forward(request, response);
		}else {
			
			eProject.setName(name);
			eProject.setComm(comm);
			
			eProjectService.update(eProject);
			
			response.sendRedirect("/stuscore/project?action=page&pageIndex=1&pageSize=10");
		}
	}
	
	/**
	 * 删除考试科目信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		EProject eProject = eProjectService.getOne(id);
		
		eProjectService.delete(eProject);
		
		response.sendRedirect("/stuscore/project?action=page&pageIndex=1&pageSize=10");
	}
	
	/**
	 * 获取指定考试科目信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void descInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		EProject eProject = eProjectService.getOne(id);
		
		request.setAttribute("project", eProject);
		
		request.getRequestDispatcher("/pages/project/upd.jsp").forward(request, response);
	}
	
	/**
	 * 分页查询考试科目信息
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
			
			page = eProjectService.getPageEProjects(pageIndex, pageSize, name);
		}else {

			page = eProjectService.getPageEProjects(pageIndex, pageSize);
		}
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/project/list.jsp").forward(request, response);
	}
	
	/**
	 * 检查考试科目信息是否存在
	 * @param name
	 * @return
	 */
	public boolean isExit(String name) {
		
		boolean flag = eProjectService.isExitProjectByName(name);
		
		return flag;
	}
}
