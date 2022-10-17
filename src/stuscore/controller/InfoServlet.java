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
 *	个人信息请求控制器
 */

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if(action.equals("updInfo")) {

			updInfo(request, response);
		}else if(action.equals("updPwd")) {

			updPwd(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {


		doGet(request, response);
	}

	/**
	 * 更改个人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String comm = request.getParameter("comm");

		User user = (User)request.getSession().getAttribute("user");
		user.setUserName(userName);
		user.setComm(comm);

		userService.update(user);

		request.getSession().setAttribute("user", user);

		response.sendRedirect("/stuscore/");
	}

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updPwd(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");

		User user = (User)request.getSession().getAttribute("user");

		if(oldPwd.equals(user.getPassWord())) {

			user.setPassWord(newPwd);

			userService.update(user);
			
			request.getSession().setAttribute("user", user);

			response.sendRedirect("/stuscore/");		
		}else {

			request.setAttribute("error", "原始密码输入有误");

			request.getRequestDispatcher("/pages/info/pwd.jsp").forward(request, response);
		}
	}
}
