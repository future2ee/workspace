package login;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import login.model.MemberService;
import login.model.login;

@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
	
	Response resp = null;
	Request req = null;
	String path = null;
	ResultSet rs = null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberService service = MemberService();
		
			
		try {
			int result = 0;	
			
			result = login.service();
			
			
			if(rs != null) {
				
				path= "WEB-INF/views/common/login.do.jsp";
				
			}else {
				
				path= "WEB-INF/views/common/erroePage.jsp";
				
				
			}
			resp.getOutputStream();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		
		
	}










}
