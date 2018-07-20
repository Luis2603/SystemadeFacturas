<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>    
  
<%@ page import="model.entity.Document" %>
<% List<Document> documents =(List<Document>) request.getAttribute("documents"); %>
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
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recibos</title>
</head>
<body>
	<div class="menu">
              <ul class="nav nav-tabs" role="tablist">
                <li ><a href="index.jsp" class="active">Inicio</a></li>
                <li ><a href="/documents">Comprobantes</a></li>
                
                <li ><a href="/products">Productos</a></li>
                <li ><a href="/organization/add">Registrarse</a></li>
                 <%if(hayusuarioactivo){ %>
                <li ><a href="/users/login"><%= user.getEmail() %></a></li>
                <li ><a href="/users/logout">Salir</a></li>
                <% }else{ %>
                <li ><a href="/users/login">Iniciar Sesión</a></li>
                <% }%>
              </ul>
       </div>
       
       <div class="container">
       
       
      			<%if(hayusuarioactivo){ %>
       				<% if (documents.size() > 0) { %>
			
			
			<table style="margin:5px; color: #000000; text-align: center; border-color: #efefef;"border="1px" width="80%">
				<tr style="background-color:green; color:white; margin: 15px; height:30px">
					<td>Id</td>
					<td>RUC</td>
					<td>DNI</td>
					<td>Razon Social:</td>
					<td>Dirección:</td>
					<td>Monto:</td>
					<td>Ver:</td>
					<td>Eliminar</td>
				
				</tr>
					<% for (int i = 0;i<documents.size();i++) { %>
					<% Document d = (Document)documents.get(i); %>
				<tr style="padding:20px background:#ffffff"onMouseOver="this.style.background='#ffffff';"onMouseOut="this.style.background='#efefef'; height:50px; ">
					<td><%= d.getId() %></td>
					<td><%= d.getRuc() %></td>
					<td><%= d.getDni() %></td>
					<td><%= d.getBusinessReason() %></td>
					<td><%= d.getDirection() %></td>
					<td><%= d.getAmount() %>  S/ </td>
					<td><a href="/documents/view?documentId=<%= d.getId() %>">Ver Recibo</a></td>
					<td><a href="/documents/delete?documentId=<%= d.getId() %>">Eliminar Recibo</a></td>
					
					
				</tr>
					<% } %>
			</table>
			<% } else { %>
			<span>No hay Recibos </span>
			<h3>Desea agregar uno </h3>
			<h4><a href="/documents/add">Crear recibo</a></h4>
			
			
			<% } %>
       			
       			<h4><a href="/documents/add">Crear nuevo recibo</a></h4>
       			
       			
                <% }else{ %>
               		<h2>Inicie sesión primero por favor</h2>
                <% }%>
       
      
       		
	   </div>
            

</body>
</html>