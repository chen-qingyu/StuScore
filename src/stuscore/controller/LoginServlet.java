package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.entity.User;
import stuscore.service.UserService;
import stuscore.service.impl.UserServiceImpl;

/**  
 *	系统登录控制器
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userName =request.getParameter("userName");
		String passWord =request.getParameter("passWord");
		
		User user = userService.getUserByName(userName);
		
		if(user == null) {
			
			request.setAttribute("error", "用户名输入错误");			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			
			if(user.getPassWord().equals(passWord)) {
				
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/stuscore/");
			}else {
				
				request.setAttribute("error", "用户密码输入错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}
}
