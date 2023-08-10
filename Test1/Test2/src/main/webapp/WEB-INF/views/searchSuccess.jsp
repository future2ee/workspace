<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보</h1>

<table border=1>
	<tr>
		<th>회원 번호</th>
		<th>회원 아이디</th>
		<th>회원 이름</th>
		<th>회원 나이</th>
	</tr>

	<tr>
		<td><%=user.userNo%></td>
		<td><%=user.userId%></td>
		<td><%=user.userName%></td>
		<td><%=user.userAge%></td>
	</tr>
</table>

<a href="ContextPath/views/index.jsp">메인페이지로 돌아가기</a>
</body>
</html>