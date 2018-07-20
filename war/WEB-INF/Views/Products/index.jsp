<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.User"%>
<%@ page import="model.entity.Product"%>
<% User useractive = (User) request.getAttribute("user"); %>
<% List<Product> products = (List<Product>) request.getAttribute("products"); %>
  
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%  %>
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
<title>Productos</title>
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
       			<%if(hayusuarioactivo){ %>
       				<% if (products.size() > 0) { %>
			
			
			<table style="margin:5px; color: #000000; text-align: center; border-color: #efefef;"border="1px" width="80%">
				<tr style="background-color:green; color:white; margin: 15px; height:30px">
					<td>Id</td>
					<td>Codigo</td>
					<td>Nombre del producto</td>
					<td>Precio</td>
					<td>Organización</td>
					<td>Ver</td>
					<td>Editar</td>
					<td>Eliminar</td>
				
				</tr>
					<% for (int i = 0;i<products.size();i++) { %>
					<% Product p = (Product)products.get(i); %>
				<tr style="padding:20px background:#ffffff"onMouseOver="this.style.background='#ffffff';"onMouseOut="this.style.background='#efefef'; height:50px; ">
					<td><%= p.getId() %></td>
					<td><%= p.getCode() %></td>
					<td><%= p.getName() %></td>
					<td><%= p.getPrice() %>  S/ </td>
					<td><%= p.getOrgName() %></td>
					<td><a href="/products/view?productId=<%= p.getId() %>">Ver Producto</a></td>
					<td><a href="/products/edit?productId=<%= p.getId() %>">Editar Producto</a></td>
					<td><a href="/products/delete?productId=<%= p.getId() %>">Eliminar Producto</a></td>
					
					
				</tr>
					<% } %>
			</table>
			<h4><a href="/products/add">Agregar producto</a></h4>
			
			<% } else { %>
			<span>No hay Productos para mostrar</span>
			<h3>Desea agregar uno </h3>
			<h4><a href="/products/add">Agregar producto</a></h4>
			
			<% } %>
       			
       			
       			
       			
                <% }else{ %>
               		<h2>Inicie sesión primero por favor</h2>
                <% }%>
       
       
       
       
	   </div>
	   	   		
      </body>
		
</html>