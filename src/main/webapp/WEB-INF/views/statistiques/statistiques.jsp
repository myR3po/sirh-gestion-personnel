<%@page import="java.util.Map"%>
<%@page import="java.util.LongSummaryStatistics"%>
<%@page import="dev.sgp.entite.VisiteWeb"%>
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
				Map<String, LongSummaryStatistics> listeStats =(Map<String, LongSummaryStatistics>)request.getAttribute("listeStats");
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
	</body>
</html>