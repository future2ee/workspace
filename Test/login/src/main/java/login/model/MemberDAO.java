package login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private int MemberService(conn) throws Exception {
		
		int result = 0;
		
		try {
			String sql = "SELECT USER_ID, USER_PWD FROM MEMBER WHERE USER_ID =? AND USER_PWD=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "USER_ID");
			pstmt.setString(2, "USER_PWD");
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
			if(pstmt != null) pstmt.close();
		

		}
		
		
		
		
		
		return result;
	}

}
