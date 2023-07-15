<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="assets/bs/css/bootstrap.css" />
<link rel="stylesheet" href="assets/css/app.css">
<title>Login Page</title>
</head>
<body>
	<div class="content">
		<div class="header">projet converter</div>
		<div class="monForm shadow mt-5">
			<div class="titre bg-primary">login form</div>
			<form method="post">
				<div class="mb-3">
					<label for="login" class="form-label">Email</label> <input
						type="email" class="form-control" id="login" name="email"
						placeholder="Saisir votre login au format @email">
				</div>
				<div class="mb-3">
					<label for="mdp" class="form-label">Mot de passe</label> <input
						type="password" class="form-control" id="mdp" name="mdp"
						placeholder="Saisir votre mot de passe">
				</div>
				<div>
					<input type="submit" value="Valider" class="btn btn-primary" /> <input
						type="reset" value="Reset" class="btn btn-secondary float-end" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
