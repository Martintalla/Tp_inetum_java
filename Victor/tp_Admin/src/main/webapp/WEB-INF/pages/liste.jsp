<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Liste</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
	
	<c:set var = "ADMIN" value="ADMIN"/>
</head>
<body>
	

	<div
		class="d-flex flex-column min-vh-100 justify-content-center align-items-center ">
		<h1>Liste des stagiaires</h1>

		<br />
		<table class="table shadow p-3 mb-5 bg-body rounded w-50 p-3">
			<thead class="table-dark">
				<tr>
					<th>Id</th>
					<th>Prénom</th>
					<th>Date de naissance</th>
					<th>Age</th>
					<th>Email</th>
					<th>Role</th>
					<th>Voie</th>
					<th>Ville</th>
					
					
				    <c:if test="${ admin }">
						<th colspan="2">Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:if test="${isEmpty}">
					<tr>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<td>--</td>
						<c:if test="${ admin }">
							<td>--</td>
							<td>--</td>
						</c:if>
					</tr>
				</c:if>
				<c:forEach var="stagiaire" items="${ stagiaires }">

					<tr>
						<td>${ stagiaire.id }</td>
						<td>${ stagiaire.prenom }</td>
						<td>${ stagiaire.ddn }</td>
						<td>${ stagiaire.age } ans</td>
						<td>${ stagiaire.email }</td>
						<td>${ stagiaire.role }</td>
						<td>${ stagiaire.adresse.nomVoie }</td>
						<td>${ stagiaire.adresse.ville }</td>
						
						
						
						<c:if test="${ admin }">
							<td><a class="btn btn-danger"
								href="modifier?nummodid=${stagiaire.id}&sessionId=${sessionId}">Modifier</a></td>
							<td><a class="btn btn-warning"
								href="liste?numdelid=${stagiaire.id}&sessionId=${sessionId}">Supprimer</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<p>
			<c:if test="${ admin }">
				<a class="btn btn-success" href="ajouter?sessionId=${sessionId}">Ajouter un stagiaire </a>
			</c:if>
			<a class="btn btn-danger" href="login?deconnexion=1&sessionId=${sessionId}"">Déconnexion</a>
		</p>
		<c:if test="${champsFormulaireNull=='1'}">
				<br/>
				<div class="alert alert-warning" role="alert">
					Le formulaire d'ajout était incomplet, nous n'avons pas pu y donner suite.
				</div>
		</c:if>
		<c:if test="${champsFormulaireNull=='2'}">
				<br/>
				<div class="alert alert-warning" role="alert">
					Le formulaire de modification était incomplet, nous n'avons pas pu y donner suite.
				</div>
		</c:if>
		<c:if test="${validationFormulaire=='1'}">
				<br/>
				<div class="alert alert-success" role="alert">
					Le nouvel utilisateur a bien été ajouté.
				</div>
		</c:if>
		<c:if test="${validationFormulaire=='2'}">
				<br/>
				<div class="alert alert-success" role="alert">
					L'utilisateur a bien été modifié.
				</div>
		</c:if>
		<c:if test="${suppressionStagiaire=='1'}">
				<br/>
				<div class="alert alert-info" role="alert">
					L'utilisateur a bien été supprimé.
				</div>
		</c:if>
		



	</div>
</body>
</html>