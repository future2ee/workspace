<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 조회(회원 번호 검색)</h1>
<form action="<%=request.getContextPath()%>/selectUser">
<input type="text" placeholder="회원 번호 입력" name="userNo">
<button>조회</button>
</form>

</body>
</html>