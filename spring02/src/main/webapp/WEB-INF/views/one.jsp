<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>검색 결과 입니다.</h3>
<hr>
검색된 id: ${dto.id} <br>
검색된 name: ${dto.name} <br>
검색된 url: <a href=${dto.url}>${dto.url}</a> <br>
검색된 img: <img src="resources/img/${dto.img}">

</body>
</html>