package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.EType;
import stuscore.service.ETypeService;
import stuscore.service.impl.ETypeServiceImpl;

/**  
 *	考试类型请求控制器
 */
@WebServlet("/type")
public class ETypeServlet extends HttpServlet {
	
	private ETypeService eTypeService = new ETypeServiceImpl();
	
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
	 * 添加考试类型信息
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
			
			request.getRequestDispatcher("/pages/type/add.jsp").forward(request, response);
		}else {
			
			EType eType = new EType();
			eType.setName(name);
			eType.setComm(comm);
			
			eTypeService.add(eType);

			response.sendRedirect("/stuscore/type?action=page&pageIndex=1&pageSize=10");
		}
	}

	/**
	 * 修改考试类型信息
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
		
		EType eType = eTypeService.getOne(id);
		
		if(!name.equals(eType.getName()) && isExit(name)) {
			
			request.setAttribute("error", "要修改的信息已存在");
			
			request.getRequestDispatcher("/type?action=info&id=" + id).forward(request, response);
		}else {
		
			eType.setName(name);
			eType.setComm(comm);
			
			eTypeService.update(eType);

			response.sendRedirect("/stuscore/type?action=page&pageIndex=1&pageSize=10");
		}
	}

	/**
	 * 删除考试类型信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		EType eType = eTypeService.getOne(id);
		
		eTypeService.delete(eType);
		
		response.sendRedirect("/stuscore/type?action=page&pageIndex=1&pageSize=10");
	}

	/**
	 * 获取指定考试类型信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void descInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		EType eType = eTypeService.getOne(id);
		
		request.setAttribute("type", eType);
		
		request.getRequestDispatcher("/pages/type/upd.jsp").forward(request, response);
	}

	/**
	 * 分页查询考试类型信息
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
			
			page = eTypeService.getPageETypes(pageIndex, pageSize, name);
		}else {

			page = eTypeService.getPageETypes(pageIndex, pageSize);
		}
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/type/list.jsp").forward(request, response);
	}
	
	/**
	 * 检查考试类型信息是否存在
	 * @param name
	 * @return
	 */
	public boolean isExit(String name) {
		
		boolean flag = eTypeService.isExitTypeByName(name);
		
		return flag;
	}
}
