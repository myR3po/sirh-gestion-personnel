<%@include file="../inc/header.jsp" %>

		<div class="container">
			
			<h1>Statistiques</h1>
			<div class="row">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Chemin</th>
							<th>Nombre de visites</th>
							<th>Min (ms)</th>
							<th>Max (ms)</th>
							<th>Moyenne (ms)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="chemin" items="${listeStats}">
							<tr>
								<td><c:out value="${chemin.key}" /></td>
								<td><c:out value="${chemin.value.count}" /></td>
								<td><c:out value="${chemin.value.min}" /></td>
								<td><c:out value="${chemin.value.max}" /></td>
								<td><c:out value="${chemin.value.average}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

<%@include file="../inc/footer.jsp" %>