<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<title>SGP - App</title>
	</head>
	<body>
	<%@include file="../navBar.jsp" %>
		<h1>Les collaborateurs</h1>
		
		<div class="container-fluid">
				<form class="form-horizontal">
			  	<div class="row">
			  		<div class="form-group">
						<label class="col-md-3 control-label" for="nom">Rechercher un nom qui commence par</label>  
					  	<div class="col-md-9">
					  		<input type="text" class="input-md" name="nom" id="nom" placeholder="nom">
					
							<input type="submit" value="Rechercher" class="btn btn-default submit" />
<!-- 							  	<div class="checkbox"> -->
							    <label class="col-md-offset-2">
							    	<input name="pasActif" id="pasActif" type="checkbox"> Voir les collaborateurs désactivés
							    </label>
<!-- 							  	</div> -->
						</div>
					</div>
				</div>
				<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-label" for="departement">Filtrer par Departement</label> 
						  	<div class="col-md-9">
						  		<select id="departement" name="departement">
									<option></option>
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
						</div>
					</div>
				  
				</form>
			
			<div class="row">	
			</div>
			<div class="row">			
				<%
				Departement departement;
				List<Collaborateur> listeCollabs =(List<Collaborateur>)request.getAttribute("listeCollabs");
				for (Collaborateur collab : listeCollabs) {
					departement = collab.getDepartement();
				%>
					<div class="col-md-4">
			            <div class="panel panel-default">
						  	<div class="panel-heading">
						  	<h3 class="panel-title"> <%= collab.getNom().toUpperCase() %> <%= collab.getPrenom() %> </h3>
						  	</div>
							<div class="panel-body">
								<div class="col-md-4">
									<img class="img img-rounded img-responsive" src="http://success-at-work.com/wp-content/uploads/2015/04/free-stock-photos.gif">
								</div>
								<div class="col-md-8">
									
									<div class="row">
									  <div class="col-md-4"> Fonction </div>  
									  <div class="col-md-offset-1 col-md-6"> <%= collab.getIntitulePoste() %> </div>
									</div>
									
									<div class="row">
									  <div class="col-md-4"> Département </div>  
									  <div class="col-md-offset-1 col-md-6">  <%= departement.getNom() %> </div>
									</div>
									
									<div class="row">
									  <div class="col-md-4"> Email </div>  
									  <div class="col-md-offset-1 col-md-6">  <a href="mailto:#"><%= collab.getEmailPro() %></a> </div>
									</div>
									
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
								
										Date d'entrée le <%= collab.getDateHeureCreation().format(DateTimeFormatter.ofPattern("dd MMMM yyyy à hh:mm")) %>
									
									
										<a class="pull-right btn btn-primary" href="<%=request.getContextPath()%>/collaborateurs/editer?matricule=<%= collab.getMatricule() %>">Editer</a>
								
								</div>
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