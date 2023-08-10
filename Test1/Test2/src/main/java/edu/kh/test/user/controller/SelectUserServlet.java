package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

@WebServlet("/Test2")
public class SelectUserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = req.getParameter("USER_ID");
		
		try {
			
			 
			UserService service = new UserService();
			
			User user = service.selectUser(userId);
			
			req.setAttribute("user", user);
			
			RequestDispatcher dispatcher = null;
			
			if(user != null) {
				dispatcher = req.getRequestDispatcher("/views/searchSuccess.jsp");
				dispatcher.forward(req, resp);
			}else {
				dispatcher = req.getRequestDispatcher("/views/searchFail.jsp");
				dispatcher.forward(req, resp);
			}
			
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
