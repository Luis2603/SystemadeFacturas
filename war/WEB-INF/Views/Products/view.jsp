<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%> 
<%@ page import="model.entity.Product" %>
<% Product vista = (Product) request.getAttribute("vista"); %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%  UserService us = UserServiceFactory.getUserService();
	com.google.appengine.api.users.User user = us.getCurrentUser();
	boolean hayusuarioactivo=false;
	if(user != null){
		hayusuarioactivo=true;
	}else{
		hayusuarioactivo=false;
	} %>	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SysFac</title>
</head>
<body>
	  <div class="menu">
              <ul class="nav nav-tabs" role="tablist">
                <li ><a href="/index.jsp" class="active">Inicio</a></li>
                <li ><a href="/documents">Comprobantes</a></li>
                <li ><a href="/products">Productos</a></li>
                <li ><a href="/users">Usuarios</a></li>
                <li ><a href="/users/add">Registrarse</a></li>
                 <%if(hayusuarioactivo){ %>
                <li ><a href="/users/login"><%= user.getEmail() %></a></li>
                <li ><a href="/users/logout">Salir</a></li>
                <% }else{ %>
                <li ><a href="/users/login">Iniciar Sesión</a></li>
                <% }%>
              </ul>
       </div>
       
       <div class="container">
       <table>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Id:</td>
  					<td><%= vista.getId() %></td>
  				</tr>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Codigo:</td>
  					<td><%= vista.getCode() %></td>
  				</tr>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Nombre:</td>
  					<td><%= vista.getName() %></td>
  				</tr>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Precio:</td>
  					<td><%= vista.getPrice() %> S/ </td>
  				</tr>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Id de Organización: </td>
  					<td><%= vista.getOrgId() %></td>
  				</tr>
  				<tr style="background:#ffffff"onMouseOver="this.style.background='#eeeeee';"onMouseOut="this.style.background='#ffffff';">
  					<td>Nombre de Organización: </td>
  					<td><%= vista.getOrgName() %></td>
  				</tr>
  			</table>
       		
       	
       
	   </div>
	   	   
      </body>
		
</html>