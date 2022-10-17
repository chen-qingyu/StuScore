package stuscore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stuscore.service.ScoreService;
import stuscore.service.impl.ScoreServiceImpl;

/**
 *	学生成绩请求控制器
 */
@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	
	private ScoreService scoreService = new ScoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");

		if(action.equals("edit")) {

			editScore(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	protected void editScore(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] sIds = request.getParameterValues("sIds[]");
		String[] scores = request.getParameterValues("scores[]");
		int eId = Integer.valueOf(request.getParameter("eId"));
		
		scoreService.editScore(sIds, scores, eId);		
	}
}
