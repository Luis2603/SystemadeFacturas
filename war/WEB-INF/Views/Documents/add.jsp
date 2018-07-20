<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.Product" %> 
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<% List<Product> productos = (List<Product>) request.getAttribute("productos"); %>

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
       
       <form method="post" action="/documents/add/queue">
	       <input type="text" name="ruc" placeholder="Ingrese su Ruc" required>
	       <br>
	       <input type="text" name="dni" placeholder="Ingrese su DNI" required>
	       <br>
	       <input type="text" name="businessReason" placeholder="Ingrese su Razon Social" required>
	       <br>
	       <input type="text" name="direction" placeholder="Ingrese su Dirección" required>
	       <br>
	       <label>Escoga su producto</label>
	       <select name="id_product">
	       <%for(Product p: productos){ %>
	       			<option value="<%=p.getId() %>">Codigo: <%=p.getCode() %>|   Nombre del producto: <%=p.getName()%>  |  Precio: <%=p.getPrice() %> </option>	
	       <%} %>
	      
	       </select>
	       <br>
	        <input type="text" name="cantidad" placeholder="numero de productos" required>
	        <% double monto = 0.00; %>
	        <br>
	        <label>SUBTOTAL</label>
	       	<input type="text" name="amount" value="<%= monto %>" disabled>
	       	<% double igv=0.00; %>
	       	<br>
	       	<label>IGV</label>
	       	<input type="text" name="igv" value="<%= igv %>" disabled>
	       	<br>
	       	<label>TOTAL</label>
	       	<% double amountigv=0.00; %>
	       	<input type="text" name="igv" value="<%= amountigv %>" disabled>
	       <input type="submit" value="Añadir a la lista">
       
       
       </form>
       
			<span>Aún no ha añadido ´productos a su compra</span>
	   </div>
	   	  
	   	  	
      </body>
		
</html>