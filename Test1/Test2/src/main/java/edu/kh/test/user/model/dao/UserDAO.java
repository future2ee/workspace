package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.kh.test.user.model.vo.User;

public class UserDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserDAO selectUser(Connection conn, String userId) throws Exception {
		
		
		try {
			
			User user = new User();
			String sql = "SELECT * FROM TB_USER WHERE USER_ID=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				user.setUserNo(rs.getInt("USER_NO"));
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserAge(rs.getInt("USER_AGE"));
				
				
			}
			 
			
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		
		return null;
	}

}
