package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizzaOrder")
public class PizzaOrderServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻어오기
		String pizza= req.getParameter("pizza");
		String size = req.getParameter("size");
		
		// * radio  타입의 값은 1개 밖에 정달되지 않으므로 getParameter()를 사용한다!
		
		// 파라미터는 모두 String
		
		// -> String을 정수로 변경하고 싶은 경우
		//	  Interger.parseInt("문자열")을 사용하여 변경(파싱)
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		// 피자 - `판
		// 가이즈 L인 경우 2천원 추가
		// 수량 1~`0판
		
		int temp =0; // 사이즈에 따른 추가 금액
		if(size.equals("L")) {
			temp=2000;
		}
		int result =(10000 + temp)*amount;
		
		// 응답 화면 작성 하는것을 JSP 위임
		
		//JSP 경로는 webapp 폴더를 기준으로 작성!
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp");
		
		req.setAttribute("res", result);
		
		dispatcher.forward(req, resp);
	}
	
	
}
