<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@page import="dev.sgp.entite.Departement"%>
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
	<%@include file="../navBar.jsp" %>
		<h1>Les collaborateurs</h1>
		
		<div class="container">
			<div class="row">
				<form class="form-inline">
				  	<div class="form-group">
				    	<label for="nom">Rechercher un nom ou un prenom qui commence par</label>
				    	<input type="text" class="form-control" name="nom" id="nom" placeholder="nom">
				  	</div>
				  	
				  	<input type="submit" value="Rechercher" class="btn btn-default submit" />
				  	<div class="checkbox">
				    	<label>
				      		<input name="pasActif" id="pasActif" type="checkbox"> Voir les collaborateurs désactivés
				    	</label>
				  	</div>
				  	
				  	<div class="form-group">
		                <label for="departement">Filtrer par Departement</label>
						<select class="form-control" id="departement" name="departement">
							<option default></option>
							<%
								List<Departement> listeDepartements =(List<Departement>)request.getAttribute("listeDepartements");
								for (Departement departement : listeDepartements) {
							%>
					      		<option value="<%= departement.getNom() %>"><%= departement.getNom() %></option>
					    	<%
								}
							%>
					    </select>
					</div>
				</form>
			
				
			</div>
			<div class="row">			
				<%
				Departement departement;
				List<Collaborateur> listeCollabs =(List<Collaborateur>)request.getAttribute("listeCollabs");
				for (Collaborateur collab : listeCollabs) {
					departement = collab.getDepartement();
				%>
					<div class="col-sm-6 col-md-4 col-lg-3">
		                <div class="card card-inverse card-info">
		                    <img class="card-img-top" src="http://success-at-work.com/wp-content/uploads/2015/04/free-stock-photos.gif">
		                    <div class="card-block">
		                        <h4 class="card-title"><%= collab.getPrenom() %> <%= collab.getNom() %></h4>
		                        <div class="card-text"> 
		                            <address>
		                            	<strong><%= collab.getIntitulePoste() %></strong>
										<a href="mailto:#"><%= collab.getEmailPro() %></a>
										
										<strong><%= departement.getNom() %></strong>
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
		</div>
	</body>
</html>