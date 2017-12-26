<%@page import="java.util.Map"%>
<%@page import="java.util.LongSummaryStatistics"%>
<%@page import="dev.sgp.entite.VisiteWeb"%>
<%@ page language="java" pageEncoding="UTF-8"%>


	<%@include file="../navBar.jsp" %>
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
	
		
					<%
					Map<String, LongSummaryStatistics> listeStats = (Map<String, LongSummaryStatistics>)request.getAttribute("listeStats");
					for (String chemin : listeStats.keySet()) {
					%>
						<tr>
							<td><%= chemin %></td>
							<td><%= listeStats.get(chemin).getCount() %></td>
							<td><%= listeStats.get(chemin).getMin() %></td>
							<td><%= listeStats.get(chemin).getMax() %></td>
							<td><%= listeStats.get(chemin).getAverage() %></td>
						</tr>
						
					<%
					}
					%>
					</tbody>
				</table>
			</div>
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>