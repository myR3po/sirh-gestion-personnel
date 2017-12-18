<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<title>SGP - App</title>
	</head>
	<body>
	<%@include file="../navBar.jsp" %>
		<h1> Nouveau Collaborateur </h1>
		
				
		<div class="container">
            <form method="post">
	            <div class="col-md-6">
	            
	            	<div class="form-group">
					  <label for="civilite">Civilité</label>
					    <select id="civilite" name="civilite" class="form-control">
					      <option value="M">Monsieur</option>
					      <option value="F">Madame</option>
					    </select>
					    <div class="has-error">
					    	<span class="help-block">${errors['civilite']}</span>
					    </div>
					</div>
	            
	            	<div class="form-group">
	                    <label for="nom">Nom *</label>
	                    <input class="form-control" type="text" id="nom" name="nom" value="${values['nom']}" required/>
	                    <div class="has-error">
					    	<span class="help-block">${errors['nom']}</span>
					    </div>
					</div>
	            
	            	<div class="form-group">
	                    <label for="nom">Nom *</label>
	                    <input class="form-control" type="text" id="nom" name="nom" value="${values['nom']}" required/>
	                    <div class="has-error">
					    	<span class="help-block">${errors['nom']}</span>
					    </div>
					</div>
					
					<div class="form-group">
	                    <label for="prenom">Prénom *</label>
	                    <input class="form-control" type="text" id="prenom" name="prenom" value="${values['prenom']}" required />
	                    <div class="has-error">
	                    	<span class="help-block">${errors['prenom']}</span>
	                    </div>
					</div>
					
					<div class="form-group">
						<label for="dateNaissance">Date de naissance *</label>
			  			<input class="form-control" type="date" id="dateNaissance" name="dateNaissance" value="${values['dateNaissance']}" required />
			  			<div class="has-error">
			  				<span class="help-block">${errors['dateNaissance']}</span>
			  			</div>
					</div>
					
					<div class="form-group">
	                    <label for="adresse">Adresse *</label>
	                    <textarea class="form-control" id="adresse" name="adresse" required>${values['adresse']}</textarea>
	                    <div class="has-error">
	                    	<span class="help-block">${errors['adresse']}</span>
	                    </div>
					</div>
					
					<div class="form-group">
	                    <label for="numeroSecuriteSociale">Numero de securite sociale</label>
			  	 		<input class="form-control" type="text"  id="numeroSecuriteSociale" name="numeroSecuriteSociale" value="${values['numeroSecuriteSociale']}" maxLength=15 required />
			  	 		<div class="has-error">
			  	 			<span class="help-block">${errors['numeroSecuriteSociale']}</span>
			  	 		</div>
					</div>
					
					<div class="form-group">
	                    <label for="intitulePoste">Fonction</label>
			  	 		<input class="form-control" type="text"  id="intitulePoste" name="intitulePoste" value="${values['intitulePoste']}" required />
			  	 		<div class="has-error">
			  	 			<span class="help-block">${errors['intitulePoste']}</span>
						</div>
					</div>
					
					
					<div class="form-group">
	                    <label for="departement">Departement</label>
						<select class="form-control" id="departement" name="departement">
							<%
								List<Departement> listeDepartements =(List<Departement>)request.getAttribute("listeDepartements");
								for (Departement departement : listeDepartements) {
							%>
					      		<option value="<%= departement.getNom() %>"><%= departement.getNom() %></option>
					    	<%
								}
							%>
					    </select>
					    <div class="has-error">
					    	<span class="help-block">${errors['departement']}</span>
						</div>
					</div>
					
					<div class="form-group">
		                <input type="submit" value="Valider" class="btn btn-default submit" />
					</div>
				</div>
            </form>
        </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>	
	</body>
</html>