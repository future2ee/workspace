package ttest1.user.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ttest1.user.model.vo.UserDTO;


public class UserDAO {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	
	public UserDTO selectUser(int userNo) throws Exception{
		
		conn = DriverManager.getConnection("oracle.jdbc.driver.OracleDriver");
		
		String path = "jdbc:oracle:thin:@localhost:1521:@lmrlmr1234";
		
		
		return null;
	}

}
