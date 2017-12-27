<%@include file="../inc/header.jsp" %>

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
									<option value="">--- Select ---</option>
									<c:forEach var="dept" items="${listeDepartements}">
										<option value="<c:out value="${dept.nom}" />"><c:out value="${dept.nom}" /></option>
									</c:forEach>
							    </select>
						  	</div>
						</div>
					</div>
				  
				</form>
			
			<div class="row">	
			</div>
<!-- 			<div class="row"> -->
				<c:forEach var="collab" items="${listeCollabs}">
					<div class="col-md-4">
			            <div class="panel panel-default">
						  	<div class="panel-heading">
						  	<h3 class="panel-title"> <span class="text-uppercase">${collab.nom}</span> <span class="text-capitalize">${collab.prenom}</span> </h3>
						  	</div>
							<div class="panel-body">
								<div class="col-md-4">
									<img class="img img-rounded img-responsive" src="<c:url value="/images/${collab.photo}"/>">
								</div>
								<div class="col-md-8">
									
									<div class="row">
									  <div class="col-md-4"> Fonction </div>  
									  <div class="col-md-offset-1 col-md-6"> ${collab.intitulePoste} </div>
									</div>
									
									<div class="row">
									  <div class="col-md-4"> Département </div>  
									  <div class="col-md-offset-1 col-md-6"> ${collab.departement.nom}</div>
									</div>
									
									<div class="row">
									  <div class="col-md-4"> Email </div>  
									  <div class="col-md-offset-1 col-md-6">  <a href="mailto:#">${collab.emailPro}</a> </div>
									</div>
									
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
								
										Date d'entrée le ${collab.dateHeureCreationFormatte}
									
									
										<a class="pull-right btn btn-primary" href="<c:url value="/collaborateurs/editer">
										  	<c:param name="matricule" value="${collab.matricule}"/>
										 </c:url>
										">Editer</a>
								
								</div>
							</div>
						</div>
		            </div>
					
				</c:forEach>
<!-- 			</div> -->
		</div>
		
<%@include file="../inc/footer.jsp" %>