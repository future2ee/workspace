package edu.kh.test.user.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.vo.User;

public class UserService {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public User selectUser(String userId) throws Exception{
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			String user = "servertest";
			
			String pw = "1234";
			
			conn = DriverManager.getConnection(url,user,pw);
			
			
			UserDAO dao = new UserDAO();
			
			dao.selectUser(conn, userId);
			
			if(conn != null) conn.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return null;
	}

}
