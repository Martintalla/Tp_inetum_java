<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Convert</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
	<!--  <link rel="stylesheet" href="app.css"> -->
</head>
<body>
	<div class="d-flex flex-column min-vh-100 justify-content-center align-items-center" >
		<form class="shadow p-3 mb-5 bg-body rounded" method="get" >
			<div class="form-group">
				<label for="exampleInputEmail1">Celsius</label>
				<input type="text" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" 
					
					<% if(request.getAttribute("celsius") == null || request.getAttribute("celsius").equals("")){ %>
						placeholder="Température en °C"
					<% }
						else {%>
						
						placeholder=" Saisie précédente : <%=request.getAttribute("celsius")%>°C"
						<%} %>
					 name="celsius" >
			</div>
			<br/>
			<div 
			<% if(request.getAttribute("fahrenheit") == null){ %>
			<%= " style=\"display:none\" " %> 
			<% } %>
			class="p-3 mb-2 
			<% if(request.getAttribute("saisieValide") == "faux"){ %>
				<%="bg-warning"%> 
			<% }
				else if (request.getAttribute("saisieValide") == "vrai") { %>	
			<%="bg-success"%>
			<% } else if(request.getAttribute("saisieValide") == "problemeZeroAbsolu"){  %>
				<%="bg-info"%> 
			<% } else if(request.getAttribute("fahrenheit") == "---"){  %>
			<%="bg-secondary"%> 
		<% } %>
			
			text-white rounded"><%=request.getAttribute("fahrenheit")%></div>
			<br/>
			<button type="submit" class="btn btn-primary">Convertir</button>
			<button type="reset" class="btn btn-primary">Réinitialiser</button>
		</form>
	</div>
</body>
</html>

