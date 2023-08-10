package testt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public int insertMember(Connection conn, String id, String pw, String name, String phone) throws Exception {

		int result = 0;
		try {
			
			String sql = "INSERT INTO MEMBER VALUES(USER_ID, USER_PWD, USER_NAME, PHONE)";
				
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			
			
			result = pstmt.executeUpdate();
		
			
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				
			} catch (Exception e) {
				
			}
			
		}
			
			
		
		
		return result;
	}



}
