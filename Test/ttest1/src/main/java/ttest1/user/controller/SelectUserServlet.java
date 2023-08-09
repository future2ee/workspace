package ttest1.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import ttest1.user.model.dao.UserDAO;
import ttest1.user.model.vo.UserDTO;

@WebServlet("/selectUser")
public class SelectUserServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int userNo = Integer.parseInt(req.getParameter("userNo"));
			
			UserDAO dao = new UserDAO();
			UserDTO dto = dao.selectUser(userNo);
			
			
			
			
			
			
			
		} catch (Exception e) {

		}
		
	}
	
	
	
	
	
	
	
}
