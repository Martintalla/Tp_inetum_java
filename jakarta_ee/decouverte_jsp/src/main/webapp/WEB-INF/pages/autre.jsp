<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Page de AutreServlet</h1>
	<h2>Mon prénom est ${ prenom } et je suis âgé de ${ age }</h2>

	<%! String ville = "Paris"; %>
	<jsp:declaration> String codePosal = "75017"; </jsp:declaration>
	
	<h2 style="color: red">
		<%= ville %>
		<br>
		<%= codePosal %>
	</h2>
</body>
</html>