<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>ȸ�� ����</h1>

<table border=1>
	<tr>
		<th>ȸ�� ��ȣ</th>
		<th>ȸ�� ���̵�</th>
		<th>ȸ�� �̸�</th>
		<th>ȸ�� ����</th>
	</tr>

	<tr>
		<td><%=user.userNo%></td>
		<td><%=user.userId%></td>
		<td><%=user.userName%></td>
		<td><%=user.userAge%></td>
	</tr>
</table>

<a href="ContextPath/views/index.jsp">������������ ���ư���</a>
</body>
</html>