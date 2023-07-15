<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<title>Page Liste</title>
</head>
<body>
	<div class="container">

		<h1>Liste des stagiaires</h1>

		<c:if test="${not empty erreurs }">
			<div class="alert alert-danger" role="alert">
				<c:forEach var="erreur" items="${erreurs }">
					${erreur }
				</c:forEach>
			</div>
		</c:if>

		<p>
			<a href="ajouter" class="btn btn-primary">Ajouter un stagiaire</a>
		</p>
		<table class="table table-hover table-bordered">
			<thead class="table-dark text-center text-uppercase">
				<tr>
					<td>Id</td>
					<td>Prénom</td>
					<td>Email</td>
					<td>Date de naissance</td>
					<td>Âge</td>
					<td colspan="2">Actions</td>
				</tr>
			</thead>
			<tbody class="text-center">
				<c:forEach var="stagiaire" items="${stagiaires }">
					<tr>
						<td>${ stagiaire.id}</td>
						<td>${ stagiaire.prenom}</td>
						<td>${ stagiaire.email}</td>
						<td>${ stagiaire.ddn}</td>
						<td>${ stagiaire.age}ans</td>
						<td><a href="liste?id=${stagiaire.id }"
							class="btn btn-danger">Supprimer</a></td>
						<td><a href="" class="btn btn-warning">Mofidier</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>