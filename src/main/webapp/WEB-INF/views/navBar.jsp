<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Brand</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li><a href="<%=request.getContextPath()%>/collaborateurs/lister">Collaborateurs</a></li>
      <li><a href="<%=request.getContextPath()%>/statistiques">Statistiques</a></li>
    </ul>
  </div><!-- /.navbar-collapse -->
  
  
  
  
  <ul class="nav navbar-nav navbar-right">
      <li><a href="<%=request.getContextPath()%>/collaborateurs/ajouter">Nouveau Collaborateur</a></li>
    </ul>
</nav>
