<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="<%=req.getContextPath()%>/selectUser">
	<input type="text" name="userNo" placeholder="회원 번호 입력">
	<button>조회</button>
</form>



</body>
</html>