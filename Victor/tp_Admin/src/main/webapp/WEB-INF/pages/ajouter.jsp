<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter/Modifier</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<div
		class="d-flex flex-column min-vh-100 justify-content-center align-items-center ">

		<h1>Page Ajouter/Modifier</h1>

		<form method="post"
			class="table shadow p-3 mb-5 bg-body rounded w-50 p-3">
			<p>
				Nom de la Voie : <input class="form-control" type="text"
					name="nomvoie" placeholder="Saisir ici le nom de la voie" />
			</p>
			<p>
				Code postal : <input class="form-control" type="text"
					name="codepostal" maxlength="5"
					placeholder="Saisir ici le code postal" />
			</p>
			<p>
				Ville : <input class="form-control" type="text" name="ville"
					placeholder="Saisir ici le nom de la ville" />
			</p>




			<!--  <input type="hidden" name="id"> -->
			<p>
				Prénom : <input class="form-control" type="text" name="prenom"
					placeholder="Saisir ici le prénom" />
			</p>
			<p>
				Email : <input class="form-control" type="email" name="email"
					placeholder="Saisir ici l'email" />
			</p>
			<p>
				Mot de passe : <input class="form-control" type="password"
					name="mdp" placeholder="Saisir ici le mot de passe" />
			</p>
			<p>
				Date de naissance : <input class="form-control" type="date"
					name="ddn" " />
			</p>
			<p>
				<!-- Role : <input class="form-control" type="text" name="role"
					placeholder="Saisir ici le role" />-->
				 
				Role :
				<select name="role" id="select-role">
					<option value="">--Choisissez le rôle de l'utilisateur--</option>
					<option value="ADMIN">ADMIN</option>
					<option value="OTHER">OTHER</option>
				</select>
			</p>
			<input class="btn btn-success" type="submit" value="valider">
		</form>
	</div>
</body>
</html>

<!-- 

String nomvoie= req.getParameter("nomvoie");
		String codepostal= req.getParameter("codepostal");
		String ville= req.getParameter("ville");
		
		
		String prenom= req.getParameter("prenom");
		String email= req.getParameter("email");
		String mdp= req.getParameter("mdp");
		LocalDate ddn= LocalDate.parse(req.getParameter("ddn"));
		String role =  req.getParameter("role");


 -->