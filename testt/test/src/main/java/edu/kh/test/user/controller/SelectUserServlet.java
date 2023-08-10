package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.vo.UserDTO;

@WebServlet("/selectUser")
public class SelectUserServlet extends HttpServlet  {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int UserNo = Integer.parseInt(req.getParameter("USER_NO"));
		
		try {
			
			UserDAO dao = new UserDAO();
			UserDTO dto = dao.selectUser(UserNo);
			
			req.setAttribute("dto", dto);
			
			RequestDispatcher dispatcher = null;
			
			if(dto != null) {
				dispatcher = req.getRequestDispatcher("/views/searchSuccess.jsp");
				dispatcher.forward(req, resp);
			}else {
				dispatcher = req.getRequestDispatcher("views/searchFail.jsp");
				dispatcher.forward(req, resp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
