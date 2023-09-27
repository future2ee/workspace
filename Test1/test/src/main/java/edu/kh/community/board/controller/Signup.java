package edu.kh.community.board.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.board.model.dto.Member;
import edu.kh.community.board.model.service.MemberService;

@WebServlet("/member/signup")
public class Signup extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		String memberNickname = req.getParameter("memberNickname");
		
		Member mem = new Member();
		
		mem.setMemberEmail(memberEmail);
		mem.setMemberPw(memberPw);
		mem.setMemberNickname(memberNickname);
	
		try {
			MemberService service = new MemberService();
			
			int result = service.signUp(mem);
			
			HttpSession session = req.getSession();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	

}
