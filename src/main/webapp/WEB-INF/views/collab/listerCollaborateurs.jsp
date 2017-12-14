<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
		<title>SGP - App</title>
	</head>
	<body>
		<h1>Les collaborateurs</h1>
		
		<div class="container">
			<%
			List<Collaborateur> listeCollabs =(List<Collaborateur>)request.getAttribute("listeCollabs");
			for (Collaborateur collab : listeCollabs) {
			%>
				<div class="col-sm-6 col-md-4 col-lg-3">
	                <div class="card card-inverse card-info">
	                    <img class="card-img-top" src="http://success-at-work.com/wp-content/uploads/2015/04/free-stock-photos.gif">
	                    <div class="card-block">
	                        <h4 class="card-title"><%= collab.getPrenom() %> <%= collab.getNom() %></h4>
	                        <div class="card-text"> 
	                            <address>
								  <a href="mailto:#"><%= collab.getEmailPro() %></a>
								</address>
	                        </div>
	                    </div>
	                    <div class="card-footer">
	                        <small>Créé le <%= collab.getDateHeureCreation().format(DateTimeFormatter.ofPattern("dd MMMM yyyy à hh:mm")) %></small>
	                    </div>
	                </div>
	            </div>
				
			<%
			}
			%>
		</div>
	</body>
</html>