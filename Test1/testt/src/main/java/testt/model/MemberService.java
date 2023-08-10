package testt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberService {

	Connection conn = null;
	MemberService() throws SQLException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "KH";
			String password = "KH";
			
			conn = DriverManager.getConnection(url,username,password);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public int insertMember(String id, String pw, String name, String phone) throws Exception{

		int result = 0;
		Member member = new Member();
		try {
			
			MemberDAO dao = new MemberDAO();
			
			result = dao.insertMember(conn, id, pw, name, phone);
			
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
