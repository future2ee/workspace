<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>ȸ�� ���� ��ȸ(ȸ�� ��ȣ �˻�)</h1>
<form action="<%=request.getContextPath()%>/selectUser">
<input type="text" placeholder="ȸ�� ��ȣ �Է�" name="userNo">
<button>��ȸ</button>
</form>

</body>
</html>