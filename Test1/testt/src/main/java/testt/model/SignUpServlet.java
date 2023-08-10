package testt.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("signUp.do")
public class SignUpServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String id = req.getParameter("id");
			String pw = req.getParameter("pwd");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			
			MemberService service = new MemberService();
			int  result = service.insertMember(id, pw, name, phone);
			
			RequestDispatcher dispatcher = null;
			if(result >0) {
				dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(req, resp);
			}else {
				dispatcher = req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
				dispatcher.forward(req, resp);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
}
