package login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberService  {
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	private int MemberService() throws Exception {
		
		int result = 0;
		
		MemberDAO dao = MemberDAO();
		
		Member = login.dao(conn);
		
		if(conn != null) conn.close();
		
		
		return result;
	}











}
