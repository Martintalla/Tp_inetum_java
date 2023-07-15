<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
	<!--  <link rel="stylesheet" href="app.css"> -->
</head>
<body>
	<div class="d-flex flex-column min-vh-100 justify-content-center align-items-center" >
		<form class="shadow p-3 mb-5 bg-body rounded" action="liste?sessionId=${ sessionId }" method="post" >
			<div class="form-group">
				<label for="exampleInputEmail1">Identifiant</label>
				<input type="text" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Entrez votre identifiant" name="identifiant" >
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Mot de passe</label>
				<input type="password" class="form-control"
					id="exampleInputPassword1" placeholder="Entrez votre mot de passe" name="motDePasse">
			</div>
			
			<c:if test="${echec=='1'}">
				<br/>
				<div class="alert alert-danger" role="alert">
					Login et/ou mdp incorrect(s).
				</div>
			</c:if>
			<c:if test="${echec=='2'}">
				<br/>
				<div class="alert alert-danger" role="alert">
					Tentative d'accéder à une page non autorisée.
				</div>
			</c:if>
			<br/>
			<button type="submit" class="btn btn-primary">Valider</button>
			<button type="reset" class="btn btn-primary">Réinitialiser</button>
		</form>
	</div>
</body>
</html>

