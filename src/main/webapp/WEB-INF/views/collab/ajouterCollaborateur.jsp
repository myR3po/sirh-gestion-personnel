<%@include file="../inc/header.jsp" %>

		<h1> Nouveau Collaborateur </h1>
		
		<div class="container">
            <form method="post">
	            <div class="col-md-6">
	            
	            	<div class="form-group">
					  <label for="civilite">Civilité *</label>
					    <select id="civilite" name="civilite" class="form-control">
					      <option value="">--- Select ---</option>
					      <option value="M">Monsieur</option>
					      <option value="F">Madame</option>
					    </select>
					    <div class="has-error">
					    	<span class="help-block">${form.errors['civilite']}</span>
					    </div>
					</div>
	            
	            	<div class="form-group">
	                    <label for="nom">Nom *</label>
	                    <input class="form-control" type="text" id="nom" name="nom" value="${collab.nom}" required/>
	                    <div class="has-error">
					    	<span class="help-block">${form.errors['nom']}</span>
					    </div>
					</div>
					
					<div class="form-group">
	                    <label for="prenom">Prénom *</label>
	                    <input class="form-control" type="text" id="prenom" name="prenom" value="${collab.prenom}" required />
	                    <div class="has-error">
	                    	<span class="help-block">${form.errors['prenom']}</span>
	                    </div>
					</div>
					
					<div class="form-group">
						<label for="dateNaissance">Date de naissance *</label>
			  			<input class="form-control" type="date" id="dateNaissance" name="dateNaissance" value="${collab.dateNaissance}" required />
			  			<div class="has-error">
			  				<span class="help-block">${form.errors['dateNaissance']}</span>
			  			</div>
					</div>
					
					<div class="form-group">
	                    <label for="adresse">Adresse *</label>
	                    <textarea class="form-control textarea-md" id="adresse" name="adresse" required>${collab.adresse}</textarea>
	                    <div class="has-error">
	                    	<span class="help-block">${form.errors['adresse']}</span>
	                    </div>
					</div>
					
					<div class="form-group">
	                    <label for="numeroSecuriteSociale">Numero de securite sociale</label>
			  	 		<input class="form-control" type="text"  id="numeroSecuriteSociale" name="numeroSecuriteSociale" value="${collab.numeroSecuriteSociale}" maxLength=15 required />
			  	 		<div class="has-error">
			  	 			<span class="help-block">${form.errors['numeroSecuriteSociale']}</span>
			  	 		</div>
					</div>
					
					<div class="form-group">
	                    <label for="intitulePoste">Fonction</label>
			  	 		<input class="form-control" type="text"  id="intitulePoste" name="intitulePoste" value="${collab.intitulePoste}" required />
			  	 		<div class="has-error">
			  	 			<span class="help-block">${form.errors['intitulePoste']}</span>
						</div>
					</div>
					
					
					<div class="form-group">
	                    <label for="departement">Departement</label>
						<select class="form-control" id="departement" name="departement">
							<option value="">--- Select ---</option>
							<c:forEach var="dept" items="${listeDepartements}">
								<option value="<c:out value="${dept.nom}" />"><c:out value="${dept.nom}" /></option>
							</c:forEach>
					    </select>
					    <div class="has-error">
					    	<span class="help-block">${form.errors['departement']}</span>
						</div>
					</div>
					
					<div class="form-group">
		                <input type="submit" value="Valider" class="btn btn-default submit" />
					</div>
				</div>
            </form>
        </div>

<%@include file="../inc/footer.jsp" %>