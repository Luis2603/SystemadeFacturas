<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.Product" %>
<% Product editable = (Product) request.getAttribute("editable"); %> 
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
       
	   </div>
	   	   
	   	   <form method="post" action="/products/edit">
				
					<table border="0">
						<tr>
							<td>Código:</td>
							<td>
							<input type="hidden" name="id_product" value="<%= editable.getId() %>">
							<input type="text" name="code_product"required value="<%= editable.getCode() %>"></td>
						</tr>
						<tr>
							<td>Nombre del Producto:</td>
							<td><input type="text" name="name_product" required value="<%= editable.getName() %>"></td>
						</tr>
						<tr>
							<td>Precio:</td>
							<td><input type="text" name="price_product" required value="<%= editable.getPrice() %>"></td>
						</tr>
						<tr>
							<td>Id de Organización</td>
							<td><input type="text" name="orgId_product" required value="<%= editable.getOrgId() %>" disabled></td>
						</tr>
						<tr>
							<td><input type="submit" value="Actualizar"></td><td></td>
						</tr>
					</table>
					</form>
	   	   
	   	   	
      </body>
		
</html>