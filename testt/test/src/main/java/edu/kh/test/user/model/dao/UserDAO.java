Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserDAO() {
		
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String pw ="web1234";
		
		try {
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pw);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public UserDTO selectUser(int userNo) throws Exception {
		
		UserDTO dto = new UserDTO();
		try {
			
			
			String sql = "SELECT * FROM TB_USER WHERE USER_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setUserNo(rs.getInt("USER_NO"));
				dto.setUserId(rs.getString("USER_ID"));
				dto.setUserName(rs.getString("USER_NAME"));
				dto.setUserAge(rs.getInt("USER_AGE"));
				
			}
			
		}finally {
			rs.close();
			pstmt.close();
			conn.close();
		} 
		
		
		

		return dto;
	}