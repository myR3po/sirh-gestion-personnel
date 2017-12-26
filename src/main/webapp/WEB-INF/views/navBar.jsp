<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<title>SGP - App</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
		  <!-- Brand and toggle get grouped for better mobile display -->
		  <div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		      <span class="sr-only">Toggle navigation</span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="<%=request.getContextPath()%>">SIRH</a>
		  </div>
		
		  <!-- Collect the nav links, forms, and other content for toggling -->
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav">
		      <li class="dropdown">
		         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Collaborateur <span class="caret"></span></a>
		         <ul class="dropdown-menu">
		           <li><a href="<%=request.getContextPath()%>/collaborateurs/lister">Lister</a></li>
		           <li><a href="<%=request.getContextPath()%>/collaborateurs/ajouter">ajouter</a></li>
		         </ul>
		       </li>
		      
		      
		      <li><a href="<%=request.getContextPath()%>/statistiques">Statistiques</a></li>
		    </ul>
		    
		    <ul class="nav navbar-nav navbar-right">
				
			</ul>
		  </div><!-- /.navbar-collapse -->
		</nav>