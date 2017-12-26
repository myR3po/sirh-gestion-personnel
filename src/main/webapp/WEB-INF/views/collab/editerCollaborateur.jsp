<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@ page language="java" pageEncoding="UTF-8"%>


	<%@include file="../navBar.jsp" %>			
		<div class="container">
			<div class="col-md-4">
				<img class="img img-rounded img-responsive" src="<c:url value="/images/unknownPerson.jpg"/>">
			</div>
			<div class="col-md-8">			
	            <form class="form-horizontal" method="post">				
					<div class="row">
					  	<div class="col-md-4">
					  		<span class="h1">${collab.nom} ${collab.prenom} - ${collab.matricule}</span>
					  		<label>
						    	<input name="desactive" id="desactive" ${collab.actif?'':'checked'} type="checkbox"> Désactiver
						    </label>
					  	</div>
					</div>
					
					
		            <div class="row">
		            	
	<%-- 	            	<input id="matricule" name="matricule" value="${collab.matricule}" type="text"/> --%>
	
						<div class="panel panel-default">
						  	<div class="panel-heading">
						  		<h3 class="panel-title" data-toggle="collapse" data-target="#identite">Identité</h3>
						  	</div>
							<div class="panel-body">	            	
								<div id="identite" class="collapse in">
					            	<div class="form-group">
									  <label class="col-md-4 control-label" for="civilite">Civilité</label>
									  <div class="col-md-6">
									    <select id="civilite" name="civilite" class="form-control">
									      <option value=""></option>
									      <option value="M">Monsieur</option>
									      <option value="F">Madame</option>
									    </select>
									  </div>
									</div>
									
					            	<div class="form-group">
									  <label class="col-md-4 control-label" for="nom">Nom</label>  
									  <div class="col-md-6">
									  	<p class="form-control-static">${collab.nom}</p>
									  </div>
									</div>
									
									<div class="form-group">
									  <label class="col-md-4 control-label" for="prenom">Prenom</label>  
									  <div class="col-md-6">
									  	<p class="form-control-static">${collab.prenom}</p>
									  </div>
									</div>
									
									<div class="form-group">
									  <label class="col-md-4 control-label" for="dateNaissance">Date de naissance</label>  
									  <div class="col-md-6">
									  	<p class="form-control-static">${collab.dateNaissance}</p>
									  </div>
									</div>
														
									<div class="form-group">
									  <label class="col-md-4 control-label" for="adresse">Adresse</label>
									  <div class="col-md-4">                     
									    <textarea rows="3" class="form-control" id="adresse" name="adresse" required>${empty collabUpdate.adresse?collab.adresse:collabUpdate.adresse}</textarea>
									    <div class="has-error">
									    	<span class="help-block">${form.errors['adresse']}</span>
									    </div>
									  </div>
									</div>
				
									<div class="form-group">
									  <label class="col-md-4 control-label" for="numeroSecuriteSociale">Numero de securite sociale</label>  
									  <div class="col-md-6">
									  	<p class="form-control-static">${collab.numeroSecuriteSociale}</p>
									  </div>
									</div>
				
									<div class="form-group">
									  <label class="col-md-4 control-label" for="telephone">Téléphone</label>
									  <div class="col-md-6">
										  <input id="telephone" name="telephone" value="${values['telephone']}" class="form-control input-md" type="tel">
										  <div class="has-error">
										  	<span class="help-block">${form.errors['telephone']}</span>
										  </div>
									  </div>
									</div>
								</div>
							</div>
						</div>
	
						<div class="panel panel-default">
						  	<div class="panel-heading">
						  		<h3 class="panel-title" data-toggle="collapse" data-target="#poste">Poste</h3>
						  	</div>
							<div class="panel-body">							
								<div id="poste" class="collapse">
									<div class="form-group">
									  <label class="col-md-4 control-label" for="intitulePoste">Fonction</label>  
									  <div class="col-md-6">
										  	<input id="intitulePoste" name="intitulePoste" class="form-control input-md" value="${empty collabUpdate.intitulePoste?collab.intitulePoste:collabUpdate.intitulePoste}" type="text"/>
										  	<div class="has-error">
										  		<span class="help-block">${form.errors['intitulePoste']}</span>
										  	</div>
									  </div>
									</div>
									
									<div class="form-group">
									  <label class="col-md-4 control-label" for="departement">Departement</label>  
									  <div class="col-md-6">
									  	<select class="form-control input-md" id="departement" name="departement">											
											<option value="">--- Select ---</option>
											<c:forEach var="dept" items="${listeDepartements}">
													
													<option ${ dept.nom == collab.departement.nom? 'selected="true"':''} value="<c:out value="${dept.nom}" />"><c:out value="${dept.nom}" /></option>
											</c:forEach>
											
									    </select>
									    <div class="has-error">
										  	<span class="help-block">${form.errors['departement']}</span>
										</div>
									    
									  </div>
									</div>
								</div>
							</div>
						</div>
	
						<div class="panel panel-default">
						  	<div class="panel-heading">
						  		<h3 class="panel-title" data-toggle="collapse" data-target="#coordonnees">Coordonnées Bancaires</h3>
						  	</div>
							<div class="panel-body">
								<div id="coordonnees" class="collapse">			
									<div class="form-group">
									  <label class="col-md-4 control-label" for="banque">Banque</label>  
									  <div class="col-md-6">
										  	<input id="banque" name="banque" class="form-control input-md" value="${collabUpdate.banque}" type="text"/>
										  	<div class="has-error">
										  		<span class="help-block">${form.errors['banque']}</span>
										  	</div>
									  </div>
									</div>
				
									<div class="form-group">
									  <label class="col-md-4 control-label" for="bic">Bic</label>  
									  <div class="col-md-6">
									  <input id="bic" name="bic" class="form-control input-md" type="text" value="${collabUpdate.bic}" />
										  <div class="has-error">
										  	<span class="help-block">${form.errors['bic']}</span>
										  </div>
									  </div>
									</div>
									
									<div class="form-group">
									  <label class="col-md-4 control-label" for="iban">Iban</label>  
									  <div class="col-md-6">
										  <input id="iban" name="iban" class="form-control input-md" type="text" value="${collabUpdate.iban}" />
										  <div class="has-error">
											<span class="help-block">${form.errors['iban']}</span>
										  </div>
									  </div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-9 col-xs-3">
				                <input type="submit" value="Editer" class="btn btn-primary submit" />
				            </div>
						</div>
					</div>
	            </form>
            </div>
        </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>