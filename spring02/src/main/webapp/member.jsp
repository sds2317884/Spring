<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<h3>회원가입</h3>
<form action="insert.multi">
	id : <input name="id"> <br>
	pw : <input name="pw"> <br>
	name : <input name="name"> <br>
	tel : <input name="tel"> <br>
	<button>서버로 전송</button>
</form>
<hr>
<h3>로그인</h3>
<form action="login.multi">
	id : <input name="id"> <br>
	pw : <input name="pw"> <br>
	<button>서버로 전송</button>
</form>	
<hr>
<h3>로그인</h3>
<form action="delete.multi">
	id : <input name="id"> <br>
	<button>서버로 전송</button>
</form>
</body>
</html>