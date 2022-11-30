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
	<h3>북마크 삭제</h3>
	<form action="delete2">
		아이디 : <input type="text" name="id"><br>
		<button type="submit">서버로 전송</button>
	</form>
	<hr>
	<h3>북마크 추가</h3>
	<form action="create2">
		이름 : <input type="text" name="name"><br>
		URL : <input type="text" name="url"><br>
		IMG : <input type="text" name="img"><br>
		<button type="submit">서버로 전송</button>
	</form>
</body>
</html>