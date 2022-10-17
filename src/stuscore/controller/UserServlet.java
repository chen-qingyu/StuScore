package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.comm.Page;
import stuscore.entity.User;
import stuscore.service.UserService;
import stuscore.service.impl.UserServiceImpl;
import stuscore.utils.DateUtils;

/**
 *	用户信息请求控制器
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	
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
	 * 打开修改用户信息的详情页
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public void descInfo(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		User user =  userService.getOne(id);
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/pages/user/upd.jsp").forward(request, response);
	}
	
	/**
	 * 分页查询用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public void pageInfo(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		String userName = request.getParameter("userName");
		
		if(userName != null && !userName.equals("")) {
			
			Page page =  userService.getPageUser(pageIndex, pageSize, userName);
			
			request.setAttribute("page", page);
		}else {

			Page page =  userService.getPageUser(pageIndex, pageSize);
			
			request.setAttribute("page", page);
		}
		
		request.getRequestDispatcher("/pages/user/list.jsp").forward(request, response);
	}
	
	/**
	 * 添加用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public void addInfo(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		String userName = request.getParameter("userName");
		String passWord = "123456";
		String comm = request.getParameter("comm");
		String createTime = DateUtils.getNowDate();
		
		if(isExit(userName)) {
			
			request.setAttribute("error", "要添加的用户已存在");
			
			request.getRequestDispatcher("/pages/user/add.jsp").forward(request, response);
		}else {
			
			User user = new User();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setComm(comm);
			user.setCreateTime(createTime);
			user.setType(1);
			
			userService.add(user);
			
			response.sendRedirect("/stuscore/user?action=page&pageIndex=1&pageSize=10");
		}
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public void updInfo(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		String userName = request.getParameter("userName");
		String comm = request.getParameter("comm");
		int id = Integer.valueOf(request.getParameter("id"));
		
		User user = userService.getOne(id);
		
		if(!userName.equals(user.getUserName()) && isExit(userName)) {
			
			request.setAttribute("error", "要修改的用户已存在");
			
			request.getRequestDispatcher("/pages/user/upd.jsp").forward(request, response);			
		}else {
			
			user.setUserName(userName);
			user.setComm(comm);
			
			userService.update(user);
			
			response.sendRedirect("/stuscore/user?action=page&pageIndex=1&pageSize=10");
		}
		
	}
	
	/**
	 * 删除用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void delInfo(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		User user =  userService.getOne(id);
		
		userService.delete(user);
		
		response.sendRedirect("/stuscore/user?action=page&pageIndex=1&pageSize=10");
	}
	
	/**
	 * 检查用户信息是否存在
	 * @param name
	 * @return
	 */
	public boolean isExit(String name) {
		
		boolean flag = userService.isExitUser(name);
		
		return flag;
	}
}
