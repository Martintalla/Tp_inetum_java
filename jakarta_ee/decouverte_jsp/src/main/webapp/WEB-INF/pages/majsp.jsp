<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Premiere JSP</title>
</head>
<body>
	<h1>Ma première JSP</h1> 
	<h2>${ msg }</h2>
	<h2>Mon prénom est ${ prenom }</h2>
	<h2>Je suis agé(e) de ${ age } ans</h2>
	<h2>Nous somme le ${ date } et il est ${ heure }</h2>
	<h2>Mon adresse IP est ${ ip }</h2>
	
	<hr/>
	
	<form action="autre" method="post">
		<p>
			prenom : <input type="text" name="prenom"/>
		</p>
		<p>
			age : <input type="text" name="age"/>
		</p>
		<input type="submit" value="Valider">
	</form>
</body>
</html>