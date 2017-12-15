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
		<h1> Nouveau Collaborateur </h1>
		
				
		<div class="container">
            <form method="post">
	            <div class="col-md-6 form-line">
	            	<div class="form-group">
	                    <label for="nom">Nom *</label>
	                    <input class="form-control" type="text" id="nom" name="nom" value="${nom}" required/>
	                    <span class="help-block has-error">${nomError}</span>
					</div>
					
					<div class="form-group">
	                    <label for="prenom">Prénom *</label>
	                    <input class="form-control" type="text" id="prenom" name="prenom" value="${prenom}" required />
	                    <span class="help-block has-error">${prenomError}</span>
					</div>
					
					<div class="form-group">
						<label for="dateNaissance">Date de naissance *</label>
			  			<input class="form-control" type="date" id="dateNaissance" name="dateNaissance" value="${dateNaissance}" required />
			  			<span class="help-block has-error">${dateNaissanceError}</span>
					</div>
					
					<div class="form-group">
	                    <label for="adresse">Adresse *</label>
	                    <textarea class="form-control" id="adresse" name="adresse" required>${adresse}</textarea>
	                    <span class="help-block has-error">${adresseError}</span>
					</div>
					
					<div class="form-group">
	                    <label for="numeroSecuriteSociale">Numero de securite sociale</label>
			  	 		<input class="form-control" type="text"  id="numeroSecuriteSociale" name="numeroSecuriteSociale" value="${numeroSecuriteSociale}" maxLength=15 required />
			  	 		<span class="help-block has-error">${numeroSecuriteSocialeError}</span>
					</div>
					
					<div class="form-group">
	                    <label for="intitule">Fonction</label>
			  	 		<input class="form-control" type="text"  id="intitule" name="intitule" value="${intitule}" required />
			  	 		<span class="help-block has-error">${intituleError}</span>
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
					    <span class="help-block has-error">${departementError}</span>
					</div>
					
					<div class="form-group">
		                <input type="submit" value="Valider" class="btn btn-default submit" />
					</div>
				</div>
            </form>
        </div>
	
	
	
	</body>
</html>